package com.cbr.behancesample.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.cbr.behancesample.R

/** Created by Dimitrios on 1/19/2018.*/

fun ImageView.loadImage(url: String?) {
    Glide.with(this)
            .load(url)
            .apply(RequestOptions()
                    .placeholder(R.drawable.ic_behace_logo)
                    .error(R.drawable.ic_error)
                    .centerCrop())
            .into(this)

}

fun ImageView.loadImage(url: String?, listener: RequestListener<Drawable>) {
    Glide.with(this)
            .load(url)
            .listener(listener)
            .apply(RequestOptions()
                    .placeholder(R.drawable.ic_behace_logo)
                    .error(R.drawable.ic_error))
            .into(this)
}