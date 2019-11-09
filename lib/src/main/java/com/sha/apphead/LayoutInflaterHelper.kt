package com.sha.apphead

import android.content.Context
import android.view.LayoutInflater

internal object LayoutInflaterHelper {

    fun <T> inflateView(res: Int, context: Context): T {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return inflater.inflate(res, null) as T
    }

}