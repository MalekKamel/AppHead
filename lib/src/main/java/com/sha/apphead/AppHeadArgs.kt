package com.sha.apphead

import android.widget.ImageView

internal data class AppHeadArgs(
        var headDrawableRes: Int = 0,
        var dismissViewScaleRatio: Double = 1.5,
        var headViewAlpha: Float = 1f,
        var allowHeadBounce: Boolean = true,
        var dismissViewAlpha: Float = 0.8f,
        var headLayoutRes: Int = R.layout.app_head,
        var headImageViewId: Int = R.id.ivHead,
        var dismissLayoutRes: Int = R.layout.dismiss_view,
        var dismissDrawableRes: Int = R.drawable.ic_dismiss_apphead,
        var dismissImageViewId: Int = R.id.ivDismiss,
        var dismissOnClick: Boolean = true,
        var loadHeadImage: ((ImageView) -> Unit)? = null,
        var onFinishHeadViewInflate: ((HeadView) -> Unit)? = null,
        var onFinishDismissViewInflate: ((DismissView) -> Unit)? = null,
        var onClick: ((HeadView) -> Unit)? = null,
        var onLongClick: ((HeadView) -> Unit)? = null,
        var onDismiss: ((HeadView) -> Unit)? = null
)