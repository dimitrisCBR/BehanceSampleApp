package com.futureworkshops.data.model.commons

import com.google.gson.annotations.SerializedName

data class ProjectFeature(
        val site: SiteRef?,
        @SerializedName("featured_on") val date: Long,
        val url: String
)