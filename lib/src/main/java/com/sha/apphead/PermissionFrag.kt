package com.sha.apphead

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

internal class PermissionFrag : Fragment() {

    private val REQUEST_CODE = 222

    private lateinit var readyCallback: (PermissionFrag) -> Unit

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> {
                activity?.apply {
                    if (!SystemOverlayHelper.canDrawOverlays(this)) return
                    ServiceHelper.start(ChatHeadService::class.java, this)
                }
            }
        }
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            requestPermission()
    }

    private fun requestPermission() {
        val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + activity!!.packageName)
        )
        startActivityForResult(intent, REQUEST_CODE)
    }

    companion object {
        private fun newInstance(readyCallback: (PermissionFrag) -> Unit): PermissionFrag {
            val frag = PermissionFrag()
            frag.readyCallback = readyCallback
            return frag
        }

        fun requestForPermission(activity: FragmentActivity): PermissionFrag {
            var frag = findFragment(activity.supportFragmentManager)
            if (frag == null) {
                frag = newInstance { }
                        activity.supportFragmentManager
                        .beginTransaction()
                        .add(frag, PermissionFrag::class.java.simpleName)
                        .commitAllowingStateLoss()
                return frag
            }
            frag.requestPermission()
            return frag
        }

        private fun findFragment(fragmentManager: FragmentManager): PermissionFrag? {
            return fragmentManager.findFragmentByTag(PermissionFrag::class.java.simpleName) as PermissionFrag?
        }

    }
}