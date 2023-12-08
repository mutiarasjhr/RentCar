package com.il.rentcar.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object Extensions {
    fun ImageView.showImage(context: Context, url: String) {
        Glide.with(context)
            .load(url)
            .into(this)
    }
}