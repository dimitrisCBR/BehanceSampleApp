package com.futureworkshops.data.model.remote

import com.futureworkshops.data.model.commons.ProjectFeature
import com.futureworkshops.data.model.commons.Stats
import com.futureworkshops.data.model.domain.Project
import com.google.gson.annotations.SerializedName


data class BehanceProject(
        val id: Long,
        val name: String,
        @SerializedName("published_on") val publishDate: Long,
        @SerializedName("created_on") val creationDate: Long,
        @SerializedName("modified_on") val lastModifiedDate: Long,
        val url: String,
        val fields: List<String>,
        val covers: HashMap<String, String>,
        @SerializedName("mature_content") val nsfw: Int,
        val owners: List<BehanceUser>,
        val stats: Stats,
        val description: String?,
        val copyright: String?,
        val features: List<ProjectFeature>
) {

    fun toModel() = Project(
            id, name, publishDate, creationDate, lastModifiedDate,
            url, fields, covers, nsfw, owners.map { it.toModel() },
            stats, description ?: "", copyright ?: "",
            features)
}