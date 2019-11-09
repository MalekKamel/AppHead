package com.sha.apphead

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
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

class HeadView : RelativeLayout {

    private val showDismissAfter = 200L

    private lateinit var windowManager: WindowManager

    lateinit var listener: HeadViewListener

    private lateinit var dismissView: DismissView
    private lateinit var image: ImageView

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
        dismissView.visibility = View.VISIBLE
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

    private fun setup() {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getSize(szWindow)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        Head.args!!.run {
            image = findViewById<ImageView>(headImageViewId).apply {
                setImageResource(headDrawableRes)
            }
            try {
                loadHeadImage?.invoke(image)
            }catch (e: Exception) {
                e.printStackTrace()
            }
            image.alpha = headViewAlpha
            onFinishHeadViewInflate?.invoke(this@HeadView)
        }
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

        dismissView.currentWidth = dismissView.imgWidth
        dismissView.currentHeight = dismissView.imgHeight

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
            dismissView.move()
        }

        params.x = xInitMargin + xDiffMove
        params.y = yCordDestination

        WindowManagerHelper.updateViewLayout(this, layoutParams)
    }

    private fun handleHeadInBounds(xCord: Int, yCord: Int): Boolean {
        val xBoundStart = szWindow.x / 2 - (dismissView.currentWidth * dismissView.boundsRatio).toInt()
        val xBoundEnd = szWindow.x / 2 + (dismissView.currentWidth * dismissView.boundsRatio).toInt()
        val yBoundTop = szWindow.y - (dismissView.currentHeight * dismissView.boundsRatio).toInt()

        val isInBounds = xCord in xBoundStart..xBoundEnd && yCord >= yBoundTop
        if (!isInBounds) return false

        val xCordDismiss = ((szWindow.x - dismissView.currentHeight * dismissView.scaleRatio) / 2).toInt()
        val yCordDismiss = (szWindow.y - (dismissView.currentWidth * dismissView.scaleRatio + statusBarHeight)).toInt()
        inBounded = true

        dismissView.onHeadInBounds(xCordDismiss, yCordDismiss)
        onHeadInBound(xCordDismiss, yCordDismiss)

        return true
    }

    private fun onUp(xCord: Int, yCord: Int) {
        isLongClick = false

        dismissView.reset()

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

    private fun onHeadInBound(xCordDismiss: Int, yCordDismiss: Int) {
        params.x = xCordDismiss + abs(dismissView.width - width) / 2
        params.y = yCordDismiss + abs(dismissView.height - height) / 2

        WindowManagerHelper.updateViewLayout(this, layoutParams)
    }

    private fun resetPosition(xCord: Int) {
        if (xCord <= szWindow.x / 2) moveToStart(xCord)
        else moveToEnd(xCord)
    }

    private fun moveToStart(xCord: Int) {
        if(!Head.args!!.allowHeadBounce) {
            params.x = 0
            WindowManagerHelper.updateViewLayout(this@HeadView, params)
            return
        }
        val x = szWindow.x - xCord

        object : CountDownTimer(500, 5) {
            override fun onTick(t: Long) {
                val step = (500 - t) / 5
                params.x = 0 - bounceValue(step, x.toLong()).toInt()
                WindowManagerHelper.updateViewLayout(this@HeadView, params)
            }

            override fun onFinish() {
                params.x = 0
                WindowManagerHelper.updateViewLayout(this@HeadView, params)
            }
        }.start()
    }

    private fun moveToEnd(xCord: Int) {
        if(!Head.args!!.allowHeadBounce) {
            params.x = szWindow.x - width
            WindowManagerHelper.updateViewLayout(this@HeadView, params)
            return
        }

        object : CountDownTimer(500, 5) {
            override fun onTick(t: Long) {
                val step = (500 - t) / 5
                params.x = szWindow.x + bounceValue(step, xCord.toLong()).toInt() - width
                WindowManagerHelper.updateViewLayout(this@HeadView, params)
            }

            override fun onFinish() {
                params.x = szWindow.x - width
                WindowManagerHelper.updateViewLayout(this@HeadView, params)
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

        val paramDismiss = dismissView.layoutParams as WindowManager.LayoutParams
        val xCordDismiss = (szWindow.x - dismissView.width) / 2
        val yCordDismiss = szWindow.y - (dismissView.height + statusBarHeight)

        paramDismiss.x = xCordDismiss
        paramDismiss.y = yCordDismiss

        WindowManagerHelper.updateViewLayout(dismissView, paramDismiss)

        listener.onLongClick(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        windowManager.defaultDisplay.getSize(szWindow)


        when(newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {

                if (params.y + (height + statusBarHeight) > szWindow.y) {
                    params.y = szWindow.y - (height + statusBarHeight)
                    WindowManagerHelper.updateViewLayout(this, layoutParams)
                }

                if (params.x != 0 && params.x < szWindow.x) {
                    resetPosition(szWindow.x)
                }
            }

            Configuration.ORIENTATION_PORTRAIT -> {

                if (params.x > szWindow.x) {
                    resetPosition(szWindow.x)
                }
            }
        }
    }

    companion object {
        fun setup(context: Context): HeadView {

            val view: View = LayoutInflaterHelper.inflateView(Head.args!!.headLayoutRes, context)

            require(view is HeadView) { "The root view of head view must be HeadView!" }

            val params = WindowManagerHelper.overlayParams()
            params.gravity = Gravity.TOP or Gravity.START
            params.x = 0
            params.y = 100

            WindowManagerHelper.manager(context).addView(view, params)

            val dismissView = DismissView.setup(context)
            view.dismissView = dismissView

            return view
        }
    }

}