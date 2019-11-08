package com.apphead.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import com.sha.apphead.ChatHeadService

class MessengerActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)

        findViewById<View>(R.id.dialog_btn).setOnClickListener {
            findViewById<EditText>(R.id.dialog_edt).text.toString().apply {
                Intent(this@MessengerActivity, ChatHeadService::class.java).apply {
                    putExtra("MESSAGE", this)
                    startService(this)
                }
            }
        }

        findViewById<View>(R.id.root).setOnClickListener {
            finish()
        }
    }

}
