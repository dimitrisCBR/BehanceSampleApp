package com.futureworkshops.data.model.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by dimitrios on 03/11/2018.
 */

data class BehanceModule(
        @SerializedName("type") val type: String,
        @SerializedName("src") val image: String?,
        @SerializedName("width") val width: Int?,
        @SerializedName("height") val height: Int?,
        @SerializedName("text") val text: String?,
        @SerializedName("text_plain") val textPlain: String?
)