package com.futureworkshops.core.model.api

import com.futureworkshops.core.model.commons.Stats
import com.futureworkshops.core.model.domain.User
import com.google.gson.annotations.SerializedName


data class BehanceUser(
        @SerializedName("id") val id: Long,
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
        @SerializedName("images") val images: HashMap<String, String>,
        @SerializedName("display_name") val displayName: String,
        @SerializedName("fields") val fields: MutableList<String>,
        @SerializedName("stats") val stats: Stats
) {

    fun toModel(): User = User(id, firstName, lastName, username, city, state, country, company,
            occupation, createdOn, url, images, displayName, fields, stats)
}