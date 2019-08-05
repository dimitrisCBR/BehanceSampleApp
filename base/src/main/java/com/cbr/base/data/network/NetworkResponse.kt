package com.cbr.base.data.network

import com.google.gson.annotations.SerializedName

data class ListResponse<T>(
        @SerializedName("users", alternate = ["projects", "collections"]) val items: List<T>)

data class ObjectResponse<T>(
        @SerializedName("user", alternate = ["project"]) val item: T)