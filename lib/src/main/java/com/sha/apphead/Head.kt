package com.sha.apphead

import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes

object Head {
    var args: AppHeadArgs? = null

    class Builder(@DrawableRes private val headDrawableRes: Int) {
        init {
            args = AppHeadArgs()
        }

        fun headLayoutRes(@LayoutRes res: Int): Builder {
            args!!.headDrawableRes = res
            return this
        }

        fun dismissLayoutRes(@LayoutRes res: Int): Builder {
            args!!.dismissLayoutRes = res
            return this
        }

        fun dismissDrawableRes(@DrawableRes res: Int): Builder {
            args!!.dismissDrawableRes = res
            return this
        }

        fun dismissOnClick(dismiss: Boolean): Builder {
            args!!.dismissOnClick = dismiss
            return this
        }

        fun onClick(listener: ((ChatHeadView) -> Unit)?): Builder {
            args!!.onClick = listener
            return this
        }

        fun onLongClick(listener: ((ChatHeadView) -> Unit)?): Builder {
            args!!.onLongClick = listener
            return this
        }

        fun onDismiss(listener: ((ChatHeadView) -> Unit)?): Builder {
            args!!.onDismiss = listener
            return this
        }

        fun build(): Head {
            args!!.headDrawableRes = headDrawableRes
            return Head
        }

    }

}