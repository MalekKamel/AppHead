package com.sha.apphead

import org.junit.Before
import org.junit.Test

class DismissViewTest {

    private lateinit var builder: DismissView.Builder

    @Before
    fun setup() {
        builder = DismissView.Builder()
    }

    @Test
    fun layoutRes() {
        builder.layoutRes(1, 2)
        assert(builder.layoutRes == 1)
        assert(builder.imageViewId == 2)
    }

    @Test
    fun drawableRes() {
        builder.drawableRes(1)
        assert(builder.drawableRes == 1)
    }

    @Test
    fun scaleRatio() {
        builder.scaleRatio(1.0)
        assert(builder.scaleRatio == 1.0)
    }

    @Test
    fun alpha() {
        builder.alpha(1f)
        assert(builder.alpha == 1f)
    }

    @Test
    fun onFinishInflate() {
        builder.onFinishInflate {}
        assert(builder.onFinishInflate != null)
    }

}