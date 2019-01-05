package com.futureworkshops.data.model.remote

import com.futureworkshops.data.model.domain.Stats
import com.google.gson.annotations.SerializedName

data class BehanceStats(@SerializedName("followers") val followers: Long,
                        @SerializedName("following") val following: Long,
                        @SerializedName("appreciations") val appreciations: Long,
                        @SerializedName("views") val views: Long,
                        @SerializedName("comments") val comments: Long) {

    fun toModel(): Stats = Stats(followers, following, appreciations, views, comments)
}
