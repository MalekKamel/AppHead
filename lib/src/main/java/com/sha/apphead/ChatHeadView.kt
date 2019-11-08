package com.sha.apphead

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.media.Image
import android.os.Build
import android.os.CountDownTimer
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.exp

class ChatHeadView : RelativeLayout {

    private val showDismissAfter = 200L

    private lateinit var windowManager: WindowManager

    private fun setup() {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getSize(szWindow)
        dismissHeadView = DismissHeadView.setup(context);
    }

    lateinit var listener: HeadViewListener

    private lateinit var dismissHeadView: DismissHeadView

    private val szWindow = Point()

    private val statusBarHeight: Int
        get() = ceil((25 * context.resources.displayMetrics.density).toDouble()).toInt()

    private var timeStart: Long = 0
    private var timeEnd: Long = 0
    private var isLongClick = false
    private var inBounded = false

    private var xInitCord: Int = 0
    private var yInitCord: Int = 0
    private var xInitMargin: Int = 0
    private var yInitMargin: Int = 0

    private var handlerLongClick = Handler()

    private var runnableLongClick: Runnable = Runnable {
        Log.d(javaClass.simpleName, "Into runnableLongClick")

        isLongClick = true
        dismissHeadView.visibility = View.VISIBLE
        onLongClick()
    }

    private var yCordDestination: Int = 0

    var params: WindowManager.LayoutParams
        get() = layoutParams as WindowManager.LayoutParams
        set(_) {}


    constructor(context: Context) : super(context) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<ImageView>(Head.args!!.headImageViewId)
                .setImageResource(Head.args!!.headDrawableRes)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        val xCord = event.rawX.toInt()
        val yCord = event.rawY.toInt()
        yCordDestination = 0

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                onDown(xCord, yCord)
            }

            MotionEvent.ACTION_MOVE -> {
                onMove(xCord, yCord)
            }

            MotionEvent.ACTION_UP -> {
                onUp(xCord, yCord)
            }

            else -> Log.d(javaClass.simpleName, "ChatHeadView.setOnTouchListener  -> event.getAction() : default")
        }
        return true
    }

    private fun onDown(xCord: Int, yCord: Int) {
        timeStart = System.currentTimeMillis()
        handlerLongClick.postDelayed(runnableLongClick, showDismissAfter)

        dismissHeadView.currentWidth = dismissHeadView.imgWidth
        dismissHeadView.currentHeight = dismissHeadView.imgHeight

        xInitCord = xCord
        yInitCord = yCord

        xInitMargin = params.x
        yInitMargin = params.y
    }

    private fun onMove(xCord: Int, yCord: Int) {
        val xDiffMove = xCord - xInitCord
        val yDiffMove = yCord - yInitCord

        yCordDestination = yInitMargin + yDiffMove

        if (isLongClick) {
            if (handleHeadInBounds(xCord, yCord)) return
            inBounded = false
            dismissHeadView.move()
        }

        params.x = xInitMargin + xDiffMove
        params.y = yCordDestination

        WindowManagerHelper.updateViewLayout(this, layoutParams)
    }

    private fun handleHeadInBounds(xCord: Int, yCord: Int): Boolean {
        val xBoundStart = szWindow.x / 2 - (dismissHeadView.currentWidth * dismissHeadView.scaleRatio).toInt()
        val xBoundEnd = szWindow.x / 2 + (dismissHeadView.currentWidth * dismissHeadView.scaleRatio).toInt()
        val yBoundTop = szWindow.y - (dismissHeadView.currentHeight * dismissHeadView.scaleRatio).toInt()

        val isInBounds = xCord in xBoundStart..xBoundEnd && yCord >= yBoundTop
        if (!isInBounds) return false

        val xCordDismiss = ((szWindow.x - dismissHeadView.currentHeight * dismissHeadView.scaleRatio) / 2).toInt()
        val yCordDismiss = (szWindow.y - (dismissHeadView.currentWidth * dismissHeadView.scaleRatio + statusBarHeight)).toInt()
        inBounded = true

        dismissHeadView.onHeadInBound(xCordDismiss, yCordDismiss)
        onHeadInBound(xCordDismiss, yCordDismiss)

        return true
    }

    private fun onUp(xCord: Int, yCord: Int) {
        isLongClick = false

        dismissHeadView.reset()

        handlerLongClick.removeCallbacks(runnableLongClick)

        if (inBounded) {
            listener.onDismiss(this)
            inBounded = false
            return
        }

        val xDiff = xCord - xInitCord
        val yDiff = yCord - yInitCord


        calcYDestination(yDiff)

        resetPosition(xCord)

        handleClick(xDiff, yDiff)
    }

    private fun handleClick(xDiff: Int, yDiff: Int) {
        if (abs(xDiff) > 5 || abs(yDiff) > 5) return

        timeEnd = System.currentTimeMillis()
        if (timeEnd - timeStart < 300) onClick()
    }

    private fun calcYDestination(yDiff: Int) {
        yCordDestination = yInitMargin + yDiff

        if (yCordDestination < 0) {
            yCordDestination = 0
        } else if (yCordDestination + (height + statusBarHeight) > szWindow.y) {
            yCordDestination = szWindow.y - (height + statusBarHeight)
        }
        params.y = yCordDestination
    }

    private fun onHeadInBound(xCordRemove: Int, yCordRemove: Int) {
        params.x = xCordRemove + abs(dismissHeadView.width - width) / 2
        params.y = yCordRemove + abs(dismissHeadView.height - height) / 2

        WindowManagerHelper.updateViewLayout(this, layoutParams)
    }

    private fun resetPosition(xCord: Int) {
        if (xCord <= szWindow.x / 2) moveToStart(xCord)
        else moveToEnd(xCord)
    }

    private fun moveToStart(xCord: Int) {
        val x = szWindow.x - xCord

        object : CountDownTimer(500, 5) {
            var mParams = layoutParams as WindowManager.LayoutParams
            override fun onTick(t: Long) {
                val step = (500 - t) / 5
                mParams.x = 0 - bounceValue(step, x.toLong()).toInt()
                WindowManagerHelper.updateViewLayout(this@ChatHeadView, mParams)
            }

            override fun onFinish() {
                mParams.x = 0
                WindowManagerHelper.updateViewLayout(this@ChatHeadView, mParams)
            }
        }.start()
    }

    private fun moveToEnd(xCord: Int) {
        object : CountDownTimer(500, 5) {
            var mParams = layoutParams as WindowManager.LayoutParams
            override fun onTick(t: Long) {
                val step = (500 - t) / 5
                mParams.x = szWindow.x + bounceValue(step, xCord.toLong()).toInt() - width
                WindowManagerHelper.updateViewLayout(this@ChatHeadView, mParams)
            }

            override fun onFinish() {
                mParams.x = szWindow.x - width
                WindowManagerHelper.updateViewLayout(this@ChatHeadView, mParams)
            }
        }.start()
    }

    private fun bounceValue(step: Long, scale: Long): Double {
        return scale.toDouble() * exp(-0.055 * step) * cos(0.08 * step)
    }

    private fun onClick() {
        listener.onClick(this)
    }

    private fun onLongClick() {
        Log.d(javaClass.simpleName, "Into ChatHeadView.onLongClick() ")

        val paramRemove = dismissHeadView.layoutParams as WindowManager.LayoutParams
        val xCordRemove = (szWindow.x - dismissHeadView.width) / 2
        val yCordRemove = szWindow.y - (dismissHeadView.height + statusBarHeight)

        paramRemove.x = xCordRemove
        paramRemove.y = yCordRemove

        WindowManagerHelper.updateViewLayout(dismissHeadView, paramRemove)

        listener.onLongClick(this)
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        windowManager.defaultDisplay.getSize(szWindow)

        val layoutParams = layoutParams as WindowManager.LayoutParams

        when(newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {

                if (layoutParams.y + (height + statusBarHeight) > szWindow.y) {
                    layoutParams.y = szWindow.y - (height + statusBarHeight)
                    WindowManagerHelper.updateViewLayout(this, layoutParams)
                }

                if (layoutParams.x != 0 && layoutParams.x < szWindow.x) {
                    resetPosition(szWindow.x)
                }
            }

            Configuration.ORIENTATION_PORTRAIT -> {

                if (layoutParams.x > szWindow.x) {
                    resetPosition(szWindow.x)
                }
            }
        }
    }

    companion object {
        fun setup(context: Context): ChatHeadView {
            val view: ChatHeadView = LayoutInflaterHelper.inflateView(Head.args!!.headLayoutRes, context)

            val params = WindowManagerHelper.overlayParams()
            params.gravity = Gravity.TOP or Gravity.START
            params.x = 0
            params.y = 100

            WindowManagerHelper.manager(context).addView(view, params)

            return view
        }
    }

}