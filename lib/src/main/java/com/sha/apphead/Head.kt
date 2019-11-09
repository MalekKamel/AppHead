package com.sha.apphead

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.sha.apphead.DismissView.Builder

/**
 * builds the app head.
 */
object Head {
    internal var args: AppHeadArgs? = null

    internal val headView
        get() = args!!.headView

    internal val dismissView
        get() = args!!.dismissView

    /**
     * the object that holds all info necessary for building the head.
     * @param headDrawableRes the drawable of the head.
     */
    class Builder(@DrawableRes private val headDrawableRes: Int) {
        private var headViewBuilder = HeadView.Builder()
        private var dismissViewBuilder = DismissView.Builder()

        /**
         * build [HeadView].
         * @param builder holds available options.
         * @return [Builder] to allow chaining.
         */
        fun headView(builder: HeadView.Builder): Builder {
            headViewBuilder = builder
            return this
        }

        /**
         * build [DismissView].
         * @param builder holds available options.
         * @return [Builder] to allow chaining.
         */
        fun dismissView(builder: DismissView.Builder): Builder {
            dismissViewBuilder = builder
            return this
        }

        /**
         * build the object.
         * @return [Head] containing info.
         */
        internal fun build(): Head {
            headViewBuilder.headDrawableRes = headDrawableRes
            args = AppHeadArgs(headViewBuilder, dismissViewBuilder)
            return Head
        }

    }

}