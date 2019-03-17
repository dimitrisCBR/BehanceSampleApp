package com.futureworkshops.data.model.commons

import com.google.gson.annotations.SerializedName


data class SiteRef(
        @SerializedName("id") val id: Long,
        @SerializedName("parent_id") val parentId: Long,
        @SerializedName("name") val name: String,
        @SerializedName("key") val key: String,
        @SerializedName("icon") val icon: String,
        @SerializedName("app_icon") val appIcon: String,
        @SerializedName("domain") val domain: String,
        @SerializedName("url") val url: String,
        @SerializedName("ribbon") val ribbon: SiteRibbon
)