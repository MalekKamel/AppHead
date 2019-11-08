package com.sha.apphead

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

object Head {
    internal var args: AppHeadArgs? = null

    class Builder(@DrawableRes private val headDrawableRes: Int) {
        init {
            args = AppHeadArgs()
        }

        fun headLayoutRes(@LayoutRes layoutRes: Int, @IdRes imageViewId: Int): Builder {
            args!!.headLayoutRes = layoutRes
            args!!.headImageViewId = imageViewId
            return this
        }

        fun dismissLayoutRes(@LayoutRes layoutRes: Int, @IdRes imageViewId: Int): Builder {
            args!!.dismissLayoutRes = layoutRes
            args!!.dismissImageViewId = imageViewId
            return this
        }

        fun dismissDrawableRes(@DrawableRes res: Int): Builder {
            args!!.dismissDrawableRes = res
            return this
        }

        fun dismissViewScaleRatio(ratio: Double): Builder {
            args!!.dismissViewScaleRatio = ratio
            return this
        }

        fun dismissOnClick(dismiss: Boolean): Builder {
            args!!.dismissOnClick = dismiss
            return this
        }

        fun onFinishHeadViewInflate(listener: ((AppHeadView) -> Unit)?): Builder {
            args!!.onFinishHeadViewInflate = listener
            return this
        }

        fun loadHeadImage(listener: ((ImageView) -> Unit)?): Builder {
            args!!.loadHeadImage = listener
            return this
        }

        fun onFinishDismissViewInflate(listener: ((DismissView) -> Unit)?): Builder {
            args!!.onFinishDismissViewInflate = listener
            return this
        }

        fun onClick(listener: ((AppHeadView) -> Unit)?): Builder {
            args!!.onClick = listener
            return this
        }

        fun onLongClick(listener: ((AppHeadView) -> Unit)?): Builder {
            args!!.onLongClick = listener
            return this
        }

        fun onDismiss(listener: ((AppHeadView) -> Unit)?): Builder {
            args!!.onDismiss = listener
            return this
        }

        fun build(): Head {
            args!!.headDrawableRes = headDrawableRes
            return Head
        }

    }

}