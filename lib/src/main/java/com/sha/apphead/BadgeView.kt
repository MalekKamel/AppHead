package com.sha.apphead

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

class BadgeView: RelativeLayout {

    lateinit var tvCount: TextView

    var count
        get() = tvCount.text.toString()
        set(value){ tvCount.text = value }

    enum class Position(val gravity: Int) {
        TOP_START(Gravity.TOP or Gravity.START),
        TOP_END(Gravity.TOP or Gravity.END),
        BOTTOM_START(Gravity.BOTTOM or Gravity.START),
        BOTTOM_END(Gravity.BOTTOM or Gravity.END),
        CENTER(Gravity.CENTER)
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    fun show() {
        visibility = View.VISIBLE
    }

    fun hide() {
        visibility = View.GONE
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        tvCount = findViewById(Head.badgeViewArgs!!.countTextViewId)
    }

    data class Args(
            internal var count: String = "",
            internal var layoutRes: Int = R.layout.badge_view,
            internal var countTextViewId: Int = R.id.tvCount,
            internal var position: Position = Position.TOP_END
    ) {

        /**
         * Custom layout res.
         * Note that the root view of the layout must be [HeadView].
         * @param layoutRes layout resource.
         * @param countTextViewId the id of the count [TextView] .
         * @return [Args] to allow chaining.
         */
        fun layoutRes(@LayoutRes layoutRes: Int, @IdRes countTextViewId: Int): Args {
            this.layoutRes = layoutRes
            this.countTextViewId = countTextViewId
            return this
        }

        /**
         * set position
         * @param position enum.
         * @return [Args] to allow chaining.
         */
        fun position(position: Position): Args {
            this.position = position
            return this
        }

        /**
         * set count
         * @param count value.
         * @return [Args] to allow chaining.
         */
        fun count(count: String): Args {
            this.count = count
            return this
        }

    }

    companion object {
        fun setup(parent: FrameLayout) {
            Head.badgeViewArgs?.apply {
                val view: View = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)

                require(view is BadgeView) { "The root view of badge view must be BadgeView!" }

                val params = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply { gravity = position.gravity }

                view.count = count
                parent.addView(view, params)
                Head.badgeView = view
            }
        }
    }

}