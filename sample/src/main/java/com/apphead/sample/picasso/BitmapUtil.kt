package com.apphead.sample.picasso

import android.graphics.Bitmap

object BitmapUtil {

    fun empty(): Bitmap {
        return Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
        )
    }


}
