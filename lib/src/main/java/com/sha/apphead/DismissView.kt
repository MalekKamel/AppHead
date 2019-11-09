package com.sha.apphead

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import kotlin.math.ceil


class DismissView: RelativeLayout {

    val boundsRatio
        get() = 1.5

    val scaleRatio
        get() = Head.dismissViewBuilder.scaleRatio

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
        Head.dismissViewBuilder.run {
            image = findViewById(imageViewId)
            image.setImageResource(drawableRes)
            image.alpha = alpha
            onFinishInflate?.invoke(this@DismissView)
        }

        // catch exceptions thrown here as the library user may think it's
        // a library exception
        Head.dismissViewBuilder.runCatching {
            setupImage?.invoke(image)
        }.onFailure { print("Exception thrown in DismissView image setup: ${it.stackTrace}") }
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
    
    data class Args(
            var scaleRatio: Double = 1.5,
            var alpha: Float = 0.8f,
            var layoutRes: Int = R.layout.dismiss_view,
            var drawableRes: Int = R.drawable.ic_dismiss_apphead,
            var imageViewId: Int = R.id.ivDismiss,
            var onFinishInflate: ((DismissView) -> Unit)? = null,
            var setupImage: ((ImageView) -> Unit)? = null
            ) {

        /**
         * alpha value for [DismissView].
         * @param alpha value.
         * default: 1f
         * @return [Args] to allow chaining.
         */
        fun alpha(alpha: Float): Args {
            this.alpha = alpha
            return this
        }

        /**
         * called when [DismissView] is inflated.
         * here you can customize the view.
         * @param listener callback.
         * @return [Args] to allow chaining.
         */
        fun onFinishInflate(listener: ((DismissView) -> Unit)?): Args {
            onFinishInflate = listener
            return this
        }

        /**
         * a custom layout res for the [DismissView].
         * Note that the root view of the layout must be [DismissView].
         * @param layoutRes layout resource.
         * @param imageViewId the id of the ImageView.
         * @return [Args] to allow chaining.
         */
        fun layoutRes(@LayoutRes layoutRes: Int, @IdRes imageViewId: Int): Args {
            this.layoutRes = layoutRes
            this.imageViewId = imageViewId
            return this
        }

        /**
         * drawable res for [DismissView].
         * @param res resource id.
         * @return [Args] to allow chaining.
         */
        fun drawableRes(@DrawableRes res: Int): Args {
            drawableRes = res
            return this
        }

        /**
         * scale ratio for [DismissView].
         * [DismissView] is scaled when the head when [HeadView] becomes inside
         * [DismissView] bounds.
         * @param ratio value.
         * default: 1.5.
         * @return [Args] to allow chaining.
         */
        fun scaleRatio(ratio: Double): Args {
            scaleRatio = ratio
            return this
        }

        /**
         * called when [HeadView] is inflated to give you the opportunity
         * to load an image from Picasso/Glide or any way.
         * @param listener callback.
         * @return [Args] to allow chaining.
         */
        fun setupImage(listener: ((ImageView) -> Unit)?): Args {
            setupImage = listener
            return this
        }
    }

    companion object {
        fun setup(context: Context): DismissView {
            val view: View = LayoutInflaterHelper.inflateView(Head.dismissViewBuilder.layoutRes, context)

            require(view is DismissView) { "The root view of dismiss view must be DismissView!" }

            val params = WindowManagerHelper.overlayParams()
            params.gravity = Gravity.TOP or Gravity.START

            view.visibility = View.GONE
            WindowManagerHelper.manager(context).addView(view, params)
            return view
        }
    }
}