package com.sha.apphead

import androidx.annotation.DrawableRes

/**
 * builds the app head.
 */
object Head {
    internal var args: AppHeadArgs? = null

    internal val headViewArgs
        get() = args!!.headViewArgs

    internal val dismissViewArgs
        get() = args!!.dismissViewArgs

    internal val badgeViewArgs: BadgeView.Args?
        get() = args!!.badgeViewArgs

    var badgeView: BadgeView?
        get() = args?.badgeView
        internal set(value) { args?.badgeView = value }

    /**
     * the object that holds all info necessary for building the head.
     * @param headDrawableRes the drawable of the head.
     */
    class Builder(@DrawableRes private val headDrawableRes: Int) {
        private var headViewBuilder = HeadView.Args()
        private var dismissViewBuilder = DismissView.Args()
        private var badgeViewArgs: BadgeView.Args? = null

        /**
         * build [HeadView].
         * @param args holds available options.
         * @return [Builder] to allow chaining.
         */
        fun headView(args: HeadView.Args): Builder {
            headViewBuilder = args
            return this
        }

        /**
         * build [DismissView].
         * @param args holds available options.
         * @return [Builder] to allow chaining.
         */
        fun dismissView(args: DismissView.Args): Builder {
            dismissViewBuilder = args
            return this
        }

        /**
         * build [BadgeView].
         * @param args holds available options.
         * @return [Builder] to allow chaining.
         */
        fun badgeView(args: BadgeView.Args): Builder {
            badgeViewArgs = args
            return this
        }

        /**
         * build the object.
         * @return [Head] containing info.
         */
        internal fun build(): Head {
            headViewBuilder.drawableRes = headDrawableRes
            args = AppHeadArgs(headViewBuilder, dismissViewBuilder, badgeViewArgs)
            return Head
        }

    }

}