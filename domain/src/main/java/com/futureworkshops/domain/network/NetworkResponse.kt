package com.futureworkshops.domain.network

import com.google.gson.annotations.SerializedName

/**
 * Created by dimitrios on 03/11/2018.
 */
data class ListResponse<T>(
        @SerializedName("users", alternate = ["projects", "collections"]) val items: List<T>)