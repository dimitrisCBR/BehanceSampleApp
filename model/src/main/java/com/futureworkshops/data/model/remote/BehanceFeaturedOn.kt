package com.futureworkshops.data.model.remote

import com.google.gson.annotations.SerializedName

data class BehanceFeaturedOn(
        @SerializedName("site") val site: String,
        @SerializedName("timestamp") val date: Long
)