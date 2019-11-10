package com.sha.apphead

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPref(private val context: Context) {
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var pref: SharedPreferences

    init {
        setup()
    }

    @SuppressLint("CommitPrefEdits")
    private fun setup() {
        pref = context.getSharedPreferences(
                "${BuildConfig.LIBRARY_PACKAGE_NAME}.preferences",
                Context. MODE_PRIVATE)
        editor = pref.edit()

    }
    private enum class Key{
        LAST_SCREEN_LOCATION_X,
        LAST_SCREEN_LOCATION_Y,
        LAST_SCREEN_LOCATION_ORIENTATION
    }

    private fun putString(value: String?, key: Key){
        editor.putString(key.name, value)
        editor.apply()
    }

    private fun getString(key: Key, def: String): String? {
        return pref.getString(key.name, def)
    }

    private fun putInt(value: Int, key: Key){
        editor.putInt(key.name, value)
        editor.apply()
    }

    private fun getInt(key: Key, def: Int): Int{
        return pref.getInt(key.name, def)
    }

    internal var lastScreenLocation: ScreenLocation
        get() {
            val currentOrientation = context.resources.configuration.orientation
            val orientation = getInt(Key.LAST_SCREEN_LOCATION_ORIENTATION, currentOrientation)

            if(orientation != currentOrientation)
                return ScreenLocation.default(context)

            val x = getInt(Key.LAST_SCREEN_LOCATION_X, ScreenLocation.DEF_X)
            val y = getInt(Key.LAST_SCREEN_LOCATION_Y, ScreenLocation.DEF_Y)

            return ScreenLocation(x, y, orientation)
        }
        set(value) {
            putInt(value.x, Key.LAST_SCREEN_LOCATION_X)
            putInt(value.y, Key.LAST_SCREEN_LOCATION_Y)
            putInt(value.orientation, Key.LAST_SCREEN_LOCATION_ORIENTATION)
        }

}