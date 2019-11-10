package com.sha.apphead


import android.content.Context
import android.content.Intent

internal object ServiceHelper {

    fun start(clazz: Class<*>, context: Context) {
        runCatching {
            context.startService(Intent(context, clazz))
        }
    }

    fun stop(clazz: Class<*>, context: Context) {
        runCatching {
            context.startService(Intent(context, clazz))
        }
    }
}
