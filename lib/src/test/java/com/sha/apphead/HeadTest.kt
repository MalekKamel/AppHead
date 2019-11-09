package com.sha.apphead

import org.junit.Before
import org.junit.Test

class HeadTest {

    private lateinit var builder: Head.Builder

    @Before
    fun setup() {
        builder = Head.Builder(-1)
        builder.build()
    }

    @Test
    fun headDrawableRes() {
        assert(Head.headViewBuilder.drawableRes == -1)
    }

}