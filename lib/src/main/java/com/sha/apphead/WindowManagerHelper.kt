package com.sha.apphead

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

object WindowManagerHelper {

    fun manager(context: Context): WindowManager {
        return context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    fun overlayParams(): WindowManager.LayoutParams {
        val overlayParam = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        else
            WindowManager.LayoutParams.TYPE_PHONE

        return WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                overlayParam,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        )
    }

    fun updateViewLayout(view: View, params: ViewGroup.LayoutParams) {
        if (view.context == null) return
        // check if the view is attached
        if (view.windowToken == null) return
        manager(view.context).updateViewLayout(view, params)
    }

    fun updateViewLayout(view: View, params: WindowManager.LayoutParams) {
        if (view.context == null) return
        // check if the view is attached
        if (view.windowToken == null) return
        manager(view.context).updateViewLayout(view, params)
    }

}