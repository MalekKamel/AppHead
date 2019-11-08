package com.sha.apphead

import android.content.Context
import androidx.fragment.app.FragmentActivity

class AppHead(builder: Head.Builder) {

    init {
        builder.build()
    }

    /**
     * Show the app head if the drawing over other apps is granted.
     * If not granted request for the permission then show after granting
     * @param builder contains options of the app head
     * @param activity a [FragmentActivity]
     */
    fun show(activity: FragmentActivity) {
        if(!SystemOverlayHelper.checkDrawOverlayPermission(activity)) return
        ServiceHelper.start(ChatHeadService::class.java, activity)
    }

    /**
     * it's the same as show() but this function doesn't request for
     * drawing over other apps permission.
     * It's useful when you want to show the app head from within a service
     * or any component without an Activity
     * @param builder contains options of the app head
     * @param context any [Context]
     */
    fun showWithoutPermissionRequest(context: Context) {
        if(!SystemOverlayHelper.canDrawOverlays(context)) return
        ServiceHelper.start(ChatHeadService::class.java, context)
    }

    companion object {
        @JvmStatic
        fun hide(context: Context) {
            ServiceHelper.stop(ChatHeadService::class.java, context)
        }
    }

}