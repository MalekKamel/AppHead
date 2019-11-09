package com.sha.apphead


import android.content.Context
import android.content.Intent

internal object ServiceHelper {

    fun start(clazz: Class<*>, context: Context) {
        try {
            context.startService(Intent(context, clazz))
        } catch (e: Exception) {
        }
    }

    fun stop(clazz: Class<*>, context: Context) {
        try {
            context.stopService(Intent(context, clazz))
        } catch (e: Exception) {
        }
    }
}
