package com.sha.apphead

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

/**
 * builds the app head.
 */
object Head {
    internal var args: AppHeadArgs? = null

    /**
     * the object that holds all info necessary for building the head.
     * @param headDrawableRes the drawable of the head.
     */
    class Builder(@DrawableRes private val headDrawableRes: Int) {
        init {
            args = AppHeadArgs()
        }

        /**
         * a custom layout res for the [HeadView].
         * Note that the root view of the layout must be [HeadView].
         * @param layoutRes layout resource.
         * @param imageViewId the id of the ImageView.
         * @return [Builder] to allow chaining.
         */
        fun headLayoutRes(@LayoutRes layoutRes: Int, @IdRes imageViewId: Int): Builder {
            args!!.headLayoutRes = layoutRes
            args!!.headImageViewId = imageViewId
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
            args!!.dismissLayoutRes = layoutRes
            args!!.dismissImageViewId = imageViewId
            return this
        }

        /**
         * drawable res for [DismissView].
         * @param res resource id.
         * @return [Builder] to allow chaining.
         */
        fun dismissDrawableRes(@DrawableRes res: Int): Builder {
            args!!.dismissDrawableRes = res
            return this
        }

        /**
         * scale ratio for [DismissView].
         * [DismissView] is scaled when the head when [HeadView] becomes inside
         * [DismissView] bounds.
         * @param ratio value.
         * @return [Builder] to allow chaining.
         */
        fun dismissViewScaleRatio(ratio: Double): Builder {
            args!!.dismissViewScaleRatio = ratio
            return this
        }

        /**
         * alpha value for [HeadView].
         * @param alpha value.
         * @return [Builder] to allow chaining.
         */
        fun headViewAlpha(alpha: Float): Builder {
            args!!.headViewAlpha = alpha
            return this
        }

        /**
         * alpha value for [DismissView].
         * @param alpha value.
         * @return [Builder] to allow chaining.
         */
        fun dismissViewAlpha(alpha: Float): Builder {
            args!!.dismissViewAlpha = alpha
            return this
        }

        /**
         * toggle bounce of [HeadView]. the bounce occurs when [HeadView]
         * is moved to start or end after user moving.
         * @param allow boolean.
         * @return [Builder] to allow chaining.
         */
        fun allowHeadBounce(allow: Boolean): Builder {
            args!!.allowHeadBounce = allow
            return this
        }

        /**
         * dismiss [HeadView] on click.
         * @param dismiss boolean.
         * @return [Builder] to allow chaining.
         */
        fun dismissOnClick(dismiss: Boolean): Builder {
            args!!.dismissOnClick = dismiss
            return this
        }

        /**
         * called when [HeadView] is inflated.
         * here you can customize the view.
         * @param listener callback.
         * @return [Builder] to allow chaining.
         */
        fun onFinishHeadViewInflate(listener: ((HeadView) -> Unit)?): Builder {
            args!!.onFinishHeadViewInflate = listener
            return this
        }

        /**
         * called when [DismissView] is inflated.
         * here you can customize the view.
         * @param listener callback.
         * @return [Builder] to allow chaining.
         */
        fun onFinishDismissViewInflate(listener: ((DismissView) -> Unit)?): Builder {
            args!!.onFinishDismissViewInflate = listener
            return this
        }

        /**
         * called when [HeadView] is inflated to give you the opportunity
         * to load an image from Picasso/Glide or any way.
         * @param listener callback.
         * @return [Builder] to allow chaining.
         */
        fun loadHeadImage(listener: ((ImageView) -> Unit)?): Builder {
            args!!.loadHeadImage = listener
            return this
        }

        /**
         * called when user clicks [HeadView].
         * @param listener callback.
         * @return [Builder] to allow chaining.
         */
        fun onClick(listener: ((HeadView) -> Unit)?): Builder {
            args!!.onClick = listener
            return this
        }

        /**
         * called when user long clicks [HeadView].
         * @param listener callback.
         * @return [Builder] to allow chaining.
         */
        fun onLongClick(listener: ((HeadView) -> Unit)?): Builder {
            args!!.onLongClick = listener
            return this
        }

        /**
         * called when user dismisses [HeadView] by moving it to [DismissView].
         * @param listener callback.
         * @return [Builder] to allow chaining.
         */
        fun onDismiss(listener: ((HeadView) -> Unit)?): Builder {
            args!!.onDismiss = listener
            return this
        }

        /**
         * build the object.
         * @return [Head] containing info.
         */
        internal fun build(): Head {
            args!!.headDrawableRes = headDrawableRes
            return Head
        }

    }

}