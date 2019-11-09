package com.sha.apphead

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.view.WindowManager

internal class HeadService : Service() {
    private lateinit var headView: HeadView

    private lateinit var windowManager: WindowManager

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        setupChatHeadView()
    }

    private fun setupChatHeadView() {
        headView = HeadView.setup(this)

        headView.listener = object: HeadViewListener {

            override fun onDismiss(view: HeadView) {
                Head.headViewArgs.onDismiss?.invoke(view)
                stopSelf()
            }

            override fun onClick(view: HeadView) {
                Head.headViewArgs.onClick?.invoke(view)
                if (Head.headViewArgs.dismissOnClick) stopSelf()
            }

            override fun onLongClick(view: HeadView) {
                Head.headViewArgs.onLongClick?.invoke(view)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        windowManager.removeView(headView)
        // clean up singleton to avoid memory leaks
        Head.args = null
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

}
