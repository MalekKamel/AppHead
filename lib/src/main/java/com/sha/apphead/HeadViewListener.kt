package com.sha.apphead

interface HeadViewListener {
    fun onDismiss(view: ChatHeadView)
    fun onClick(view: ChatHeadView)
    fun onLongClick(view: ChatHeadView)
}