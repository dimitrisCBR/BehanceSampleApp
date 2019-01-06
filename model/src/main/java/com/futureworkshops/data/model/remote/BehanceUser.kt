package com.futureworkshops.data.model.remote

import com.futureworkshops.data.model.commons.Stats
import com.futureworkshops.data.model.domain.User
import com.google.gson.annotations.SerializedName


data class BehanceUser(
        val id: Long,
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String,
        val username: String,
        val city: String,
        val state: String,
        val country: String,
        val company: String,
        val occupation: String,
        @SerializedName("created_on") val createdOn: Long,
        val url: String,
        val images: HashMap<String, String>,
        @SerializedName("display_name") val displayName: String,
        val fields: MutableList<String>,
        val stats: Stats
) {

    fun toModel(): User = User(id, firstName, lastName, username, city, state, country, company,
            occupation, createdOn, url, images, displayName, fields, stats)
}