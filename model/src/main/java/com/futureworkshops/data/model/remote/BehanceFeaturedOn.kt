package com.futureworkshops.data.model.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by dimitrios on 03/11/2018.
 */

data class BehanceFeaturedOn(
        @SerializedName("site") val site: String,
        @SerializedName("timestamp") val date: Long
)