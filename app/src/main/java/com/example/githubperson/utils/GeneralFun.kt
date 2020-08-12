package com.example.githubperson.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.githubperson.R

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun String.toBitmap(context: Context): Bitmap {
    var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.image_default)

    val option = RequestOptions()
        .error(R.drawable.image_default)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    try {
        Glide.with(context)
            .setDefaultRequestOptions(option)
            .asBitmap()
            .load(this)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmap = resource
                }
            })
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return bitmap
}

fun loadImageGlide(context: Context, imageUrl: String?, imageView: ImageView){
    val option = RequestOptions()
        .error(R.drawable.image_default)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(imageUrl)
        .into(imageView)
}