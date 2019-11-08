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

class DismissView: RelativeLayout {

    val boundsRatio
        get() = 1.5

    val scaleRatio
        get() = Head.args!!.dismissViewScaleRatio

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
        setup()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        Head.args!!.run {
            image = findViewById(dismissImageViewId)
            image.setImageResource(dismissDrawableRes)
            onFinishDismissViewInflate?.invoke(this@DismissView)
        }
    }

    private fun setup() {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getSize(szWindow)
    }

    fun onHeadInBounds(xCord: Int, yCord: Int) {
        if (imgHeight != currentHeight) return

        imgWidth = (currentWidth * scaleRatio).toInt()
        imgHeight = (currentHeight * scaleRatio).toInt()

        val params = layoutParams as WindowManager.LayoutParams
        params.x = xCord
        params.y = yCord

        WindowManagerHelper.updateViewLayout(this, params)
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
        fun setup(context: Context): DismissView {
            val view = LayoutInflaterHelper.inflateView<View>(Head.args!!.dismissLayoutRes, context)

            require(view is DismissView) { "The root view of dismiss_view view must be DismissHeadView!" }

            val params = WindowManagerHelper.overlayParams()
            params.gravity = Gravity.TOP or Gravity.START

            view.visibility = View.GONE
            WindowManagerHelper.manager(context).addView(view, params)
            return view
        }
    }
}