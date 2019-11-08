package com.sha.apphead


import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build

import androidx.core.content.ContextCompat

object ServiceHelper {

    fun startForegroundService(clazz: Class<*>, context: Context) {
        try {
            ContextCompat.startForegroundService(context, Intent(context, clazz))
        } catch (e: Exception) {
        }
    }

    fun startForegroundService(intent: Intent, context: Context) {
        try {
            ContextCompat.startForegroundService(context, intent)
        } catch (e: Exception) {
        }
    }

    fun start(clazz: Class<*>, context: Context) {
        try {
            context.startService(Intent(context, clazz))
        } catch (e: Exception) {
        }
    }
    fun start(intent: Intent, context: Context) {
        try {
            context.startService(intent)
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
