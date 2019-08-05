package com.cbr.base.model.api

import com.cbr.base.model.commons.ProjectCopyright
import com.cbr.base.model.commons.ProjectFeature
import com.cbr.base.model.commons.Stats
import com.cbr.base.model.domain.Project
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
        @SerializedName("mature_access") val nsfwAccess: String,
        @SerializedName("owners") val owners: List<BehanceUser>?,
        @SerializedName("stats") val stats: Stats,
        @SerializedName("description") val description: String?,
        @SerializedName("copyright") val copyright: ProjectCopyright?,
        @SerializedName("features") val features: List<ProjectFeature>,
        @SerializedName("tags") val tags: List<String>,
        @SerializedName("modules") val modules: List<BehanceModule>?
) {

    fun toModel() = Project(
            id, name, publishDate, creationDate, lastModifiedDate,
            url,
            fields,
            covers,
            nsfw,
            owners?.map { it.toModel() } ?: listOf(),
            stats, description ?: "", copyright,
            features, modules ?: listOf())
}