package com.futureworkshops.data.model.commons

import com.google.gson.annotations.SerializedName


data class SiteRef(
        val id: Long,
        @SerializedName("parent_id") val parentId: Long,
        val name: String,
        val key: String,
        val icon: String,
        @SerializedName("app_icon") val appIcon: String,
        val domain: String,
        val url: String,
        val ribbon: SiteRibbon
)

data class SiteRibbon(
        val image: String,
        @SerializedName("image_2x") val imageLarge: String
)