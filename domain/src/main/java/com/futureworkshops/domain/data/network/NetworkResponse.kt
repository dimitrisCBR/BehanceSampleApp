package com.futureworkshops.domain.data.network

import com.google.gson.annotations.SerializedName

data class ListResponse<T>(
        @SerializedName("users", alternate = ["projects", "collections"]) val items: List<T>)