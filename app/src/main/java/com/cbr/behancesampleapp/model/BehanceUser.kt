package com.cbr.behancesampleapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by dimitrios on 25/08/2017.
 */
data class BehanceUser(@SerializedName("id") val id: Long,
                       @SerializedName("first_name") val firstName: String,
                       @SerializedName("last_name") val lastName: String,
                       @SerializedName("username") val username: String,
                       @SerializedName("city") val city: String,
                       @SerializedName("state") val state: String,
                       @SerializedName("country") val country: String,
                       @SerializedName("company") val company: String,
                       @SerializedName("occupation") val occupation: String,
                       @SerializedName("created_on") val createdOn: Long,
                       @SerializedName("url") val url: String,
                       @SerializedName("images") val images: BehanceImages,
                       @SerializedName("display_name") val displayName: String,
                       @SerializedName("fields") val fields: List<String>
)

data class BehanceImages(@SerializedName("50") val smallUrl: String, @SerializedName("100") val mediumUrl: String, @SerializedName("276") val largeUrl: String)
