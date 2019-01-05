package com.futureworkshops.data.model.remote

import com.futureworkshops.data.model.domain.User
import com.google.gson.annotations.SerializedName

data class BehanceUser(
        @SerializedName("id") var id: Long,
        @SerializedName("first_name") var firstName: String,
        @SerializedName("last_name") var lastName: String,
        @SerializedName("username") var username: String,
        @SerializedName("city") var city: String,
        @SerializedName("state") var state: String,
        @SerializedName("country") var country: String,
        @SerializedName("company") var company: String,
        @SerializedName("occupation") var occupation: String,
        @SerializedName("created_on") var createdOn: Long,
        @SerializedName("url") var url: String,
        @SerializedName("images") var images: HashMap<String, String>,
        @SerializedName("display_name") var displayName: String,
        @SerializedName("fields") var fields: MutableList<String>,
        @SerializedName("stats") var stats: BehanceStats
) {

    fun toModel(): User = User(id, firstName, lastName, username, city, state, country, company,
            occupation, createdOn, url, images, displayName, fields, stats.toModel())
}