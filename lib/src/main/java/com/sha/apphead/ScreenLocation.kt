package com.sha.apphead

import android.content.Context

internal data class ScreenLocation(
        val x: Int,
        val y: Int,
        val orientation: Int
) {
    fun isOrientationChanged(context: Context): Boolean {
        return context.resources.configuration.orientation != orientation
    }

    companion object {
        const val DEF_X = 0
        const val DEF_Y = 100
        fun default(context: Context) : ScreenLocation {
            return ScreenLocation(DEF_X, DEF_Y, context.resources.configuration.orientation)
        }
    }
}