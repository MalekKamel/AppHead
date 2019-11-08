package com.sha.apphead

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.fragment.app.FragmentActivity

object SystemOverlayHelper {

    fun canDrawOverlays(context: Context): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(context)
    }

    fun checkDrawOverlayPermission(activity: FragmentActivity): Boolean {
        if (canDrawOverlays(activity)) return true

        com.sha.apphead.PermissionFrag.requestForPermission(activity)
        return false
    }

}
