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
        private var headViewArgs = HeadView.Args()
        private var dismissViewArgs = DismissView.Args()
        private var badgeViewArgs: BadgeView.Args? = null

        /**
         * build [HeadView].
         * @param args holds available options.
         * @return [Builder] to allow chaining.
         */
        fun headView(args: HeadView.Args): Builder {
            headViewArgs = args
            return this
        }

        /**
         * build [HeadView].
         * @param block provides view options.
         * @return [Builder] to allow chaining.
         */
        fun headView(block: HeadView.Args.() -> Unit): Builder {
            val builder = HeadView.Args()
            builder.block()
            headViewArgs = builder
            return this
        }

        /**
         * build [DismissView].
         * @param args holds available options.
         * @return [Builder] to allow chaining.
         */
        fun dismissView(args: DismissView.Args): Builder {
            dismissViewArgs = args
            return this
        }

        /**
         * build [DismissView].
         * @param block provides view options.
         * @return [Builder] to allow chaining.
         */
        fun dismissView(block: DismissView.Args.() -> Unit): Builder {
            val builder = DismissView.Args()
            builder.block()
            dismissViewArgs = builder
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
         * build [BadgeView].
         * @param block provides view options.
         * @return [Builder] to allow chaining.
         */
        fun badgeView(block: BadgeView.Args.() -> Unit): Builder {
            val builder = BadgeView.Args()
            builder.block()
            badgeViewArgs = builder
            return this
        }

        /**
         * build the object.
         * @return [Head] containing info.
         */
        internal fun build(): Head {
            headViewArgs.drawableRes = headDrawableRes
            args = AppHeadArgs(headViewArgs, dismissViewArgs, badgeViewArgs)
            return Head
        }

    }

}