package com.sha.apphead

import org.junit.Before
import org.junit.Test

class HeadTest {

    private lateinit var builder: Head.Builder

    @Before
    fun setup() {
        builder = Head.Builder(-1)
    }

    @Test
    fun headDrawableRes() {
        builder.build()
        assert(Head.args!!.headDrawableRes == -1)
    }

    @Test
    fun dismissLayoutRes() {
        builder.dismissLayoutRes(1, 2)
        assert(Head.args!!.dismissLayoutRes == 1)
        assert(Head.args!!.dismissImageViewId == 2)
    }

    @Test
    fun headLayoutRes() {
        builder.headLayoutRes(1, 2)
        assert(Head.args!!.headLayoutRes == 1)
        assert(Head.args!!.headImageViewId == 2)
    }

    @Test
    fun dismissDrawableRes() {
        builder.dismissDrawableRes(1)
        assert(Head.args!!.dismissDrawableRes == 1)
    }

    @Test
    fun dismissViewScaleRatio() {
        builder.dismissViewScaleRatio(1.0)
        assert(Head.args!!.dismissViewScaleRatio == 1.0)
    }

    @Test
    fun headViewAlpha() {
        builder.headViewAlpha(1f)
        assert(Head.args!!.headViewAlpha == 1f)
    }

    @Test
    fun allowHeadBounce() {
        builder.allowHeadBounce(true)
        assert(Head.args!!.allowHeadBounce)
    }

    @Test
    fun dismissViewAlpha() {
        builder.dismissViewAlpha(1f)
        assert(Head.args!!.dismissViewAlpha == 1f)
    }

    @Test
    fun dismissOnClick() {
        builder.dismissOnClick(true)
        assert(Head.args!!.dismissOnClick)
    }

    @Test
    fun onFinishHeadViewInflate() {
        builder.onFinishHeadViewInflate {}
        assert(Head.args!!.onFinishHeadViewInflate != null)
    }

    @Test
    fun loadHeadImage() {
        builder.loadHeadImage {}
        assert(Head.args!!.loadHeadImage != null)
    }

    @Test
    fun onFinishDismissViewInflate() {
        builder.onFinishDismissViewInflate {}
        assert(Head.args!!.onFinishDismissViewInflate != null)
    }

    @Test
    fun onClick() {
        builder.onClick {}
        assert(Head.args!!.onClick != null)
    }

    @Test
    fun onLongClick() {
        builder.onLongClick {}
        assert(Head.args!!.onLongClick != null)
    }

    @Test
    fun onDismiss() {
        builder.onDismiss {}
        assert(Head.args!!.onDismiss != null)
    }

}