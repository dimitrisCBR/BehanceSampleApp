package com.futureworkshops.core.model.api

import com.futureworkshops.core.model.commons.ProjectFeature
import com.futureworkshops.core.model.commons.Stats
import com.futureworkshops.core.model.domain.Project
import com.google.gson.annotations.SerializedName


data class BehanceProject(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("published_on") val publishDate: Long,
        @SerializedName("created_on") val creationDate: Long,
        @SerializedName("modified_on") val lastModifiedDate: Long,
        @SerializedName("url") val url: String,
        @SerializedName("fields") val fields: List<String>,
        @SerializedName("covers") val covers: HashMap<String, String>,
        @SerializedName("mature_content") val nsfw: Int,
        @SerializedName("owners") val owners: List<BehanceUser>,
        @SerializedName("stats") val stats: Stats,
        @SerializedName("description") val description: String?,
        @SerializedName("copyright") val copyright: String?,
        @SerializedName("features") val features: List<ProjectFeature>
) {

    fun toModel() = Project(
            id, name, publishDate, creationDate, lastModifiedDate,
            url, fields, covers, nsfw, owners.map { it.toModel() },
            stats, description ?: "", copyright ?: "",
            features)
}