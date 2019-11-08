package com.sha.apphead

internal data class AppHeadArgs(
        var headDrawableRes: Int = 0,
        var headLayoutRes: Int = R.layout.app_head,
        var headImageViewId: Int = R.id.ivHead,
        var dismissLayoutRes: Int = R.layout.dismiss_view,
        var dismissDrawableRes: Int = R.drawable.dismiss,
        var dismissImageViewId: Int = R.id.ivDismiss,
        var dismissOnClick: Boolean = true,
        var onClick: ((ChatHeadView) -> Unit)? = null,
        var onLongClick: ((ChatHeadView) -> Unit)? = null,
        var onDismiss: ((ChatHeadView) -> Unit)? = null
) {

}