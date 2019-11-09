package com.sha.apphead

import org.junit.Before
import org.junit.Test

class HeadViewTest {

    private lateinit var args: HeadView.Args

    @Before
    fun setup() {
        args = HeadView.Args()
    }

    @Test
    fun dismissOnClick() {
        args.dismissOnClick(true)
        assert(args.dismissOnClick)
    }


    @Test
    fun layoutRes() {
        args.layoutRes(1, 2)
        assert(args.layoutRes == 1)
        assert(args.imageViewId == 2)
    }

    @Test
    fun alpha() {
        args.alpha(1f)
        assert(args.alpha == 1f)
    }

    @Test
    fun allowBounce() {
        args.allowBounce(true)
        assert(args.allowBounce)
    }

    @Test
    fun onFinishInflate() {
        args.onFinishInflate {}
        assert(args.onFinishInflate != null)
    }

    @Test
    fun setupImage() {
        args.setupImage {}
        assert(args.setupImage != null)
    }

    @Test
    fun onClick() {
        args.onClick {}
        assert(args.onClick != null)
    }

    @Test
    fun onLongClick() {
        args.onLongClick {}
        assert(args.onLongClick != null)
    }

    @Test
    fun onDismiss() {
        args.onDismiss {}
        assert(args.onDismiss != null)
    }

}