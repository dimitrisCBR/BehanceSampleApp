package com.futureworkshops.core.data.network

object Urls {

    private const val API_VERSION = "v2/"

    const val GET_USERS = API_VERSION + "users/"

    const val GET_PROJECTS = API_VERSION + "projects/"

    const val GET_USER_BY_ID = API_VERSION + "users/{id}"

    const val GET_USER_PROJECTS = API_VERSION + "users/{id}/projects/"

    const val GET_USER_WIPS = API_VERSION + "users/{id}/wips/"

    const val GET_USER_APPRECIATIONS = API_VERSION + "users/{id}/appreciations/"

    const val PARAM_ID = "id"

    const val PARAM_API_KEY = "api_key"

}