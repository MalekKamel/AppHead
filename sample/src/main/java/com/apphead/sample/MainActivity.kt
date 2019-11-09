package com.apphead.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.sha.apphead.*
import com.squareup.picasso.Picasso

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        showDefault()
        findViewById<View>(R.id.btnShowHead).setOnClickListener {
            showDefault()
        }

        findViewById<View>(R.id.btnShowReadHead).setOnClickListener {
            showCustom()
        }

        findViewById<View>(R.id.btnUpdateBadge).setOnClickListener {
            updateBadge()
        }
    }

    private fun showDefault() {
        // build BadgeView
        val badgeViewBuilder = BadgeView.Args()
                .count("100")
                .position(BadgeView.Position.TOP_END)

        val builder = Head.Builder(R.drawable.ic_messenger)
                .headView(HeadView.Args().onClick { showMessenger() })
                .badgeView(badgeViewBuilder)

        AppHead(builder).show(this)
    }

    private fun showCustom() {
        // build HeadView
        val headViewArgs = HeadView.Args()
                .layoutRes(R.layout.app_head_red, R.id.headImageView)
                .onClick { showMessenger() }
                .onLongClick { log("onLongClick") }
                .alpha(0.8f)
                .allowBounce(false)
                .onFinishInflate { log("onFinishHeadViewInflate")  }
                .setupImage { loadImage(it) }
                .onDismiss { log("onDismiss") }
                .dismissOnClick(false)

        // build DismissView
        val dismissViewArgs = DismissView.Args()
                .alpha(0.5f)
                .scaleRatio(1.0)
                .drawableRes(R.drawable.ic_dismiss)
                .onFinishInflate {  log("onFinishDismissViewInflate") }
                .setupImage { }

        // build BadgeView
        val badgeViewArgs = BadgeView.Args()
                .count("3333")

        val builder = Head.Builder(R.drawable.ic_messenger_red)
                .headView(headViewArgs)
                .dismissView(dismissViewArgs)
                .badgeView(badgeViewArgs)

        AppHead(builder).show(this)
    }

    private fun loadImage(iv: ImageView) {
        iv.layoutParams.height = 190
        iv.layoutParams.width = 190
        Picasso.get()
                .load("https://www.icon.digital/wp-content/uploads/facebook_messenger1600-570x570.png")
                .placeholder(R.drawable.ic_messenger_red)
                .into(iv)
    }

    private fun showMessenger() {
        Intent(this, MessengerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(this)
        }
    }

    private var count = 3
    private fun updateBadge() {
        if(count == 0){
            count = 3
            Head.badgeView?.hide()
            return
        }
        Head.badgeView?.show()
        Head.badgeView?.count = count.toString()
        count--
    }

    private fun log(message: String) {
        Log.i(javaClass.simpleName, message)
    }
}