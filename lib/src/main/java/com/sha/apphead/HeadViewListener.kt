package com.sha.apphead

interface HeadViewListener {
    fun onDismiss(view: AppHeadView)
    fun onClick(view: AppHeadView)
    fun onLongClick(view: AppHeadView)
}