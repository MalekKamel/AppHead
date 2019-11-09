package com.apphead.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.sha.apphead.AppHead
import com.sha.apphead.DismissView
import com.sha.apphead.Head
import com.sha.apphead.HeadView
import com.squareup.picasso.Picasso

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        findViewById<View>(R.id.btnShowHead).setOnClickListener {
            val builder = Head.Builder(R.drawable.ic_messenger)
                    .headView(HeadView.Builder().onClick {
                        Intent(this, MessengerActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(this)
                        }
                    })
            AppHead(builder).show(this)
        }

        findViewById<View>(R.id.btnShowReadHead).setOnClickListener {
            val headViewBuilder = HeadView.Builder()
                    .layoutRes(R.layout.app_head_red, R.id.headImageView)
                    .onClick {
                        Intent(this, MessengerActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(this)
                        }
                    }
                    .onLongClick { log("onLongClick") }
                    .alpha(0.8f)
                    .allowBounce(false)
                    .onFinishInflate { log("onFinishHeadViewInflate")  }
                    .setupImage {
                        it.layoutParams.height = 190
                        it.layoutParams.width = 190
                        Picasso.get()
                                .load("https://www.icon.digital/wp-content/uploads/facebook_messenger1600-570x570.png")
                                .placeholder(R.drawable.ic_messenger_red)
                                .into(it)
                    }
                    .onDismiss { log("onDismiss") }

            val dismissViewBuilder = DismissView.Builder()
                    .alpha(0.5f)
                    .scaleRatio(1.0)
                    .drawableRes(R.drawable.ic_dismiss)
                    .dismissOnClick(false)
                    .onFinishInflate {  log("onFinishDismissViewInflate") }
                    .setupImage { }

            val builder = Head.Builder(R.drawable.ic_messenger_red)
                    .headView(headViewBuilder)
                    .dismissView(dismissViewBuilder)

            AppHead(builder).show(this)
        }
    }

    private fun log(message: String) {
        Log.i(javaClass.simpleName, message)
    }
}