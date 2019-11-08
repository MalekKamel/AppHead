package com.sha.apphead

data class AppHeadArgs(
        var headDrawableRes: Int = 0,
        var headLayoutRes: Int = R.layout.app_head,
        var dismissLayoutRes: Int = R.layout.dismiss,
        var dismissDrawableRes: Int = R.drawable.dismiss,
        var dismissOnClick: Boolean = true,
        var onClick: ((ChatHeadView) -> Unit)? = null,
        var onLongClick: ((ChatHeadView) -> Unit)? = null,
        var onDismiss: ((ChatHeadView) -> Unit)? = null
)