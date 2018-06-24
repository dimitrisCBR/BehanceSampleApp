package com.cbr.behancesampleapp.model

import com.google.gson.annotations.SerializedName

enum class PRIVACY {
    PUBLIC,
    PRIVATE
}

data class BehanceProject(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("published_on") val publishDate: Long,
    @SerializedName("created_on") val creationDate: Long,
    @SerializedName("modified_on") val lastModifiedDate: Long,
    @SerializedName("url") val url: String,
    @SerializedName("fields") val field: List<String>,
    @SerializedName("covers") val covers: BehanceImages,
    @SerializedName("mature_content") val nsfw: Int,
    @SerializedName("owners") val owners: List<BehanceUser>,
    @SerializedName("stats") val stats: BehanceStats,
    @SerializedName("description") val description: String,
    @SerializedName("modules") val modules: List<BehanceModule>,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("featured_on") val featuredOn: BehanceFeaturedOn
)

data class BehanceModule(
    @SerializedName("type") val type: String,
    @SerializedName("src") val image: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("text") val text: String?,
    @SerializedName("text_plain") val textPlain: String?
)

data class BehanceCopyright(
    @SerializedName("license") val license: String,
    @SerializedName("description") val description: String
)

data class BehanceFeaturedOn(
    @SerializedName("site") val site: String,
    @SerializedName("timestamp") val date: Long
)