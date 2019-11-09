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
        assert(Head.headView.headDrawableRes == -1)
    }

    @Test
    fun dismissLayoutRes() {
        builder.dismissLayoutRes(1, 2)
        assert(Head.dismissView.dismissLayoutRes == 1)
        assert(Head.dismissView.dismissImageViewId == 2)
    }

    @Test
    fun headLayoutRes() {
        builder.headLayoutRes(1, 2)
        assert(Head.headView.headLayoutRes == 1)
        assert(Head.headView.headImageViewId == 2)
    }

    @Test
    fun dismissDrawableRes() {
        builder.dismissDrawableRes(1)
        assert(Head.dismissView.dismissDrawableRes == 1)
    }

    @Test
    fun dismissViewScaleRatio() {
        builder.dismissViewScaleRatio(1.0)
        assert(Head.dismissView.dismissViewScaleRatio == 1.0)
    }

    @Test
    fun headViewAlpha() {
        builder.headViewAlpha(1f)
        assert(Head.headView.headViewAlpha == 1f)
    }

    @Test
    fun allowHeadBounce() {
        builder.allowHeadBounce(true)
        assert(Head.headView.allowHeadBounce)
    }

    @Test
    fun dismissViewAlpha() {
        builder.dismissViewAlpha(1f)
        assert(Head.dismissView.dismissViewAlpha == 1f)
    }

    @Test
    fun dismissOnClick() {
        builder.dismissOnClick(true)
        assert(Head.dismissView.dismissOnClick)
    }

    @Test
    fun onFinishHeadViewInflate() {
        builder.onFinishHeadViewInflate {}
        assert(Head.headView.onFinishHeadViewInflate != null)
    }

    @Test
    fun loadHeadImage() {
        builder.loadHeadImage {}
        assert(Head.headView.setupImage != null)
    }

    @Test
    fun onFinishDismissViewInflate() {
        builder.onFinishDismissViewInflate {}
        assert(Head.dismissView.onFinishDismissViewInflate != null)
    }

    @Test
    fun onClick() {
        builder.onClick {}
        assert(Head.headView.onClick != null)
    }

    @Test
    fun onLongClick() {
        builder.onLongClick {}
        assert(Head.headView.onLongClick != null)
    }

    @Test
    fun onDismiss() {
        builder.onDismiss {}
        assert(Head.headView.onDismiss != null)
    }

}