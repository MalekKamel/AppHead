package com.sha.apphead

import android.content.Context
import androidx.fragment.app.FragmentActivity

/**
 * build, show and hide the App Head.
 * @param builder the object that holds all info necessary for building the head
 */
class AppHead(builder: Head.Builder) {

    init {
        builder.build()
    }

    /**
     * Show the app head if the drawing over other apps is granted.
     * If not granted request for the permission then show after granting
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
     * @param context any [Context]
     */
    fun showWithoutPermissionRequest(context: Context) {
        if(!SystemOverlayHelper.canDrawOverlays(context)) return
        ServiceHelper.start(ChatHeadService::class.java, context)
    }

    companion object {
        /**
         * Hide the head.
         * @param context any context.
         */
        @JvmStatic
        fun hide(context: Context) {
            ServiceHelper.stop(ChatHeadService::class.java, context)
        }
    }

}