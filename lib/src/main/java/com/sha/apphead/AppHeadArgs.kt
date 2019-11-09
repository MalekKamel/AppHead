package com.sha.apphead

import android.widget.ImageView

internal data class AppHeadArgs(
        var headView: HeadView.Builder,
        var dismissView: DismissView.Builder
)