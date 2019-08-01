package com.cbr.base.model.commons

import com.google.gson.annotations.SerializedName

data class ProjectFeature(
        @SerializedName("site") val site: SiteRef?,
        @SerializedName("featured_on") val date: Long,
        @SerializedName("url") val url: String
)