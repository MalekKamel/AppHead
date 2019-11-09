package com.sha.apphead

import org.junit.Before
import org.junit.Test

class HeadViewTest {

    private lateinit var builder: HeadView.Builder

    @Before
    fun setup() {
        builder = HeadView.Builder()
    }

    @Test
    fun dismissOnClick() {
        builder.dismissOnClick(true)
        assert(builder.dismissOnClick)
    }


    @Test
    fun layoutRes() {
        builder.layoutRes(1, 2)
        assert(builder.layoutRes == 1)
        assert(builder.imageViewId == 2)
    }

    @Test
    fun alpha() {
        builder.alpha(1f)
        assert(builder.alpha == 1f)
    }

    @Test
    fun allowBounce() {
        builder.allowBounce(true)
        assert(builder.allowBounce)
    }

    @Test
    fun onFinishInflate() {
        builder.onFinishInflate {}
        assert(builder.onFinishInflate != null)
    }

    @Test
    fun setupImage() {
        builder.setupImage {}
        assert(builder.setupImage != null)
    }

    @Test
    fun onClick() {
        builder.onClick {}
        assert(builder.onClick != null)
    }

    @Test
    fun onLongClick() {
        builder.onLongClick {}
        assert(builder.onLongClick != null)
    }

    @Test
    fun onDismiss() {
        builder.onDismiss {}
        assert(builder.onDismiss != null)
    }

}