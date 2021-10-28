package com.example.homework14.extensions

import android.util.Log.d
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.homework14.R

fun ImageView.setImage(url: String?) {
    if(!url.isNullOrEmpty()) {
        Glide.with(this).load(url).placeholder(R.drawable.ic_launcher_foreground).into(this)
    }else{
        setImageResource(R.drawable.ic_launcher_background)
    }
}