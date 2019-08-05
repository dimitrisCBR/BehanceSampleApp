package com.cbr.base.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.loadImage(url: String?, @DrawableRes placeholder: Int = 0, @DrawableRes error: Int = 0) {
    Glide.with(this)
            .load(url)
            .apply(
                    RequestOptions()
                            .placeholder(placeholder)
                            .error(error)
                            .centerCrop()
            )
            .into(this)

}

fun ImageView.loadImage(url: String?, callback: () -> Unit, @DrawableRes placeholder: Int = 0, @DrawableRes error: Int = 0) {
    Glide.with(this)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    callback()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    callback()
                    return false
                }

            })
            .apply(
                    RequestOptions()
                            .placeholder(placeholder)
                            .error(error)
                            .centerCrop()
            )
            .into(this)
}