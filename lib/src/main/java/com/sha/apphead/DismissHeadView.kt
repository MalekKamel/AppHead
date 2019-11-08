package com.sha.apphead

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import kotlin.math.ceil

class DismissHeadView: RelativeLayout {

    val scaleRatio = 1.5

    private lateinit var image: ImageView

    var currentWidth = 0
    var currentHeight = 0

    private val szWindow = Point()
    private lateinit var windowManager: WindowManager

    var imgHeight: Int
        get() = image.layoutParams.height
        set(value) { image.layoutParams.height = value }

    var imgWidth: Int
        get() = image.layoutParams.width
        set(value) { image.layoutParams.width = value }

    private val statusBarHeight: Int
        get() = ceil((25 * context.resources.displayMetrics.density).toDouble()).toInt()

    constructor(context: Context) : super(context) { setup() }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup() }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int)
            : super(context, attrs, defStyle) {
        image.setImageResource(Head.args!!.dismissDrawableRes)
        setup()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
       image = findViewById(R.id.image)
    }

    private fun setup() {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getSize(szWindow)
    }

    fun onHeadInBound(xCordRemove: Int, yCordRemove: Int) {
        if (imgHeight != currentHeight) return

        imgWidth = (currentWidth * scaleRatio).toInt()
        imgHeight = (currentHeight * scaleRatio).toInt()

        val paramRemove = layoutParams as WindowManager.LayoutParams
        paramRemove.x = xCordRemove
        paramRemove.y = yCordRemove

        WindowManagerHelper.updateViewLayout(this, paramRemove)
    }

    fun move() {
        imgHeight = currentHeight
        imgWidth = currentWidth

        val param = layoutParams as WindowManager.LayoutParams
        val xCord = (szWindow.x - width) / 2
        val yCord = szWindow.y - (height + statusBarHeight)

        param.x = xCord
        param.y = yCord

        WindowManagerHelper.updateViewLayout(this, param)
    }

    fun reset() {
        visibility = View.GONE
        imgHeight = currentHeight
        imgWidth = currentWidth
    }

    companion object {
        fun setup(context: Context): DismissHeadView {
            val view: DismissHeadView = LayoutInflaterHelper.inflateView(Head.args!!.dismissLayoutRes, context)

            val params = WindowManagerHelper.overlayParams()
            params.gravity = Gravity.TOP or Gravity.START

            view.visibility = View.GONE
            WindowManagerHelper.manager(context).addView(view, params)
            return view
        }
    }
}