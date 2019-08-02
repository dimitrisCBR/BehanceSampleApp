package com.cbr.base.model.api

import com.cbr.base.model.commons.ProjectCopyright
import com.cbr.base.model.commons.ProjectFeature
import com.google.gson.annotations.SerializedName

data class BehanceModule(
        @SerializedName("id") val id: Long,
        @SerializedName("project_id") val projectId: Long,
        @SerializedName("type") val image: String,
        @SerializedName("full_bleed") val fullBleed: Int,
        @SerializedName("alignment") val alignment: String,
        @SerializedName("src") val url: String,
        @SerializedName("sizes") val sizes: HashMap<String, String>,
        @SerializedName("width") val width: Int,
        @SerializedName("height") val height: Int,
        @SerializedName("short_url") val miniUrl: String,
        @SerializedName("copyright") val copyRight: ProjectCopyright,
        @SerializedName("features") val features: List<ProjectFeature>
)