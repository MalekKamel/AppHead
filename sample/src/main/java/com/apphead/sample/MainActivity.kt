package com.apphead.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.sha.apphead.AppHead
import com.sha.apphead.Head

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        findViewById<View>(R.id.btnShowHead).setOnClickListener {
            val builder = Head.Builder(R.drawable.ic_messenger)
                    .onClick {
                        Intent(this, MessengerActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(this)
                        }
                    }
            AppHead(builder).show(this)
        }
    }

}
