package com.apphead.sample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity

class MessengerActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)

        findViewById<View>(R.id.root).setOnClickListener { finish() }
    }

}
