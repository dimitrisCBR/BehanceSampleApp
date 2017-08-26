package com.example.dimitrios.disample.model

import com.google.gson.annotations.SerializedName

/**
 * Created by dimitrios on 25/08/2017.
 */
data class BehanceImages(@SerializedName("50") val smallUrl: String, @SerializedName("100") val mediumUrl: String, @SerializedName("276") val largeUrl: String)