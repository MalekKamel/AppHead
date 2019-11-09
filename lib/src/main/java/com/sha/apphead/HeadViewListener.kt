package com.sha.apphead

internal interface HeadViewListener {
    fun onDismiss(view: HeadView)
    fun onClick(view: HeadView)
    fun onLongClick(view: HeadView)
}