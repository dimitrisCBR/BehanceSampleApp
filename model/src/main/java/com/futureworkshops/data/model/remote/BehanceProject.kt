package com.futureworkshops.data.model.remote

import com.google.gson.annotations.SerializedName

data class BehanceProject(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("published_on") val publishDate: Long,
        @SerializedName("created_on") val creationDate: Long,
        @SerializedName("modified_on") val lastModifiedDate: Long,
        @SerializedName("url") val url: String,
        @SerializedName("fields") val field: List<String>,
        @SerializedName("covers") val covers: HashMap<String, String>,
        @SerializedName("mature_content") val nsfw: Int,
        @SerializedName("owners") val owners: List<BehanceUser>,
        @SerializedName("stats") val stats: BehanceStats,
        @SerializedName("description") val description: String,
        @SerializedName("modules") val modules: List<BehanceModule>,
        @SerializedName("copyright") val copyright: String,
        @SerializedName("featured_on") val featuredOn: BehanceFeaturedOn
)