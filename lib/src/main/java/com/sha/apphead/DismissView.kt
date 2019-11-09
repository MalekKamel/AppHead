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
import androidx.core.view.ViewCompat
import kotlin.math.ceil


class DismissView: RelativeLayout {

    val boundsRatio
        get() = 1.5

    val scaleRatio
        get() = Head.dismissView.dismissViewScaleRatio

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
        Head.dismissView.run {
            image = findViewById(dismissImageViewId)
            image.setImageResource(dismissDrawableRes)
            image.alpha = dismissViewAlpha
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
        ViewCompat.setTranslationZ(this, 1f)
        bringToFront()
        requestLayout()
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
    
    data class Builder(
            var dismissViewScaleRatio: Double = 1.5,
            var dismissViewAlpha: Float = 0.8f,
            var dismissLayoutRes: Int = R.layout.dismiss_view,
            var dismissDrawableRes: Int = R.drawable.ic_dismiss_apphead,
            var dismissImageViewId: Int = R.id.ivDismiss,
            var dismissOnClick: Boolean = true,
            var onFinishDismissViewInflate: ((DismissView) -> Unit)? = null,
            var setupImage: ((ImageView) -> Unit)? = null
            ) {

        /**
         * alpha value for [DismissView].
         * @param alpha value.
         * @return [Builder] to allow chaining.
         */
        fun alpha(alpha: Float): Builder {
            dismissViewAlpha = alpha
            return this
        }

        /**
         * dismiss [HeadView] on click.
         * @param dismiss boolean.
         * @return [Builder] to allow chaining.
         */
        fun dismissOnClick(dismiss: Boolean): Builder {
            dismissOnClick = dismiss
            return this
        }


        /**
         * called when [DismissView] is inflated.
         * here you can customize the view.
         * @param listener callback.
         * @return [Builder] to allow chaining.
         */
        fun onFinishInflate(listener: ((DismissView) -> Unit)?): Builder {
            onFinishDismissViewInflate = listener
            return this
        }

        /**
         * a custom layout res for the [DismissView].
         * Note that the root view of the layout must be [DismissView].
         * @param layoutRes layout resource.
         * @param imageViewId the id of the ImageView.
         * @return [Builder] to allow chaining.
         */
        fun dismissLayoutRes(@LayoutRes layoutRes: Int, @IdRes imageViewId: Int): Builder {
            dismissLayoutRes = layoutRes
            dismissImageViewId = imageViewId
            return this
        }

        /**
         * drawable res for [DismissView].
         * @param res resource id.
         * @return [Builder] to allow chaining.
         */
        fun drawableRes(@DrawableRes res: Int): Builder {
            dismissDrawableRes = res
            return this
        }

        /**
         * scale ratio for [DismissView].
         * [DismissView] is scaled when the head when [HeadView] becomes inside
         * [DismissView] bounds.
         * @param ratio value.
         * @return [Builder] to allow chaining.
         */
        fun scaleRatio(ratio: Double): Builder {
            dismissViewScaleRatio = ratio
            return this
        }

        /**
         * called when [HeadView] is inflated to give you the opportunity
         * to load an image from Picasso/Glide or any way.
         * @param listener callback.
         * @return [Builder] to allow chaining.
         */
        fun setupImage(listener: ((ImageView) -> Unit)?): Builder {
            setupImage = listener
            return this
        }
    }

    companion object {
        fun setup(context: Context): DismissView {
            val view: View = LayoutInflaterHelper.inflateView(Head.dismissView.dismissLayoutRes, context)

            require(view is DismissView) { "The root view of dismiss view must be DismissView!" }

            val params = WindowManagerHelper.overlayParams()
            params.gravity = Gravity.TOP or Gravity.START

            view.visibility = View.GONE
            WindowManagerHelper.manager(context).addView(view, params)
            return view
        }
    }
}