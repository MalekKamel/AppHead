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
}