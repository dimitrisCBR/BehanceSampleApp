package com.futureworkshops.core.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions

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

fun ImageView.loadImage(url: String?, listener: RequestListener<Drawable>, @DrawableRes placeholder: Int = 0, @DrawableRes error: Int = 0) {
    Glide.with(this)
            .load(url)
            .listener(listener)
            .apply(
                    RequestOptions()
                            .placeholder(placeholder)
                            .error(error)
                            .centerCrop()
            )
            .into(this)
}