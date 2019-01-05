package com.futureworkshops.domain.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.futureworkshops.domain.R

fun ImageView.loadImage(url: String?) {
    Glide.with(this)
            .load(url)
            .apply(
                    RequestOptions()
                            .placeholder(R.drawable.notification_action_background)
                            .error(R.drawable.notification_action_background)
                            .centerCrop()
            )
            .into(this)

}

fun ImageView.loadImage(url: String?, listener: RequestListener<Drawable>) {
    Glide.with(this)
            .load(url)
            .listener(listener)
            .apply(
                    RequestOptions()
                            .placeholder(R.drawable.notification_action_background)
                            .error(R.drawable.notification_action_background)
            )
            .into(this)
}