package com.futureworkshops.data.model.commons

import com.google.gson.annotations.SerializedName


data class SiteRibbon(
        @SerializedName("image") val image: String,
        @SerializedName("image_2x") val imageLarge: String
)