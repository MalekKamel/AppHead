package com.sha.apphead

import org.junit.Before
import org.junit.Test

class DismissViewTest {

    private lateinit var args: DismissView.Args

    @Before
    fun setup() {
        args = DismissView.Args()
    }

    @Test
    fun layoutRes() {
        args.layoutRes(1, 2)
        assert(args.layoutRes == 1)
        assert(args.imageViewId == 2)
    }

    @Test
    fun drawableRes() {
        args.drawableRes(1)
        assert(args.drawableRes == 1)
    }

    @Test
    fun scaleRatio() {
        args.scaleRatio(1.0)
        assert(args.scaleRatio == 1.0)
    }

    @Test
    fun alpha() {
        args.alpha(1f)
        assert(args.alpha == 1f)
    }

    @Test
    fun onFinishInflate() {
        args.onFinishInflate {}
        assert(args.onFinishInflate != null)
    }

}