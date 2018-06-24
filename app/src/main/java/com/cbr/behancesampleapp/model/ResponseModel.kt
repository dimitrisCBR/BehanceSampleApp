package com.cbr.behancesampleapp.model

import com.google.gson.annotations.SerializedName

/** Created by dimitrios on 25/08/2017.*/
data class BehanceListResponse<T>(
    @SerializedName("users",alternate = ["projects","collections"]) val items : List<T>)

data class BehanceUserListResponse(@SerializedName("users") val users: List<BehanceUser>)

data class BehanceUserResponse(@SerializedName("user") val user : BehanceUser)

data class BehanceProjectListResponse(@SerializedName("user") val user : BehanceUser)