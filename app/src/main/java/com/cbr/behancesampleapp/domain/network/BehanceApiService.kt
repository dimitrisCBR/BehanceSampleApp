package com.cbr.behancesampleapp.domain.network

import com.cbr.behancesampleapp.domain.network.Urls.PARAM_API_KEY
import com.cbr.behancesampleapp.domain.network.Urls.PARAM_ID
import com.cbr.behancesampleapp.model.BehanceListResponse
import com.cbr.behancesampleapp.model.BehanceProject
import com.cbr.behancesampleapp.model.BehanceUser
import com.cbr.behancesampleapp.model.BehanceUserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/** Created by dimitrios on 24/08/2017. */
interface BehanceApiService {

    @GET(Urls.GET_USERS)
    fun getUsers(@QueryMap queryParams: MutableMap<String, Any>): Observable<BehanceListResponse<BehanceUser>>

    @GET(Urls.GET_USER_BY_ID)
    fun getUserById(@Path(PARAM_ID) id: String, @Query(PARAM_API_KEY) apiKey: String): Observable<BehanceUserResponse>

    @GET(Urls.GET_PROJECTS)
    fun getProjects(@QueryMap queryParams: MutableMap<String, Any>): Observable<BehanceListResponse<BehanceProject>>

}

object Urls {

    private const val API_VERSION = "v2/"

    const val BASE_URL = "http://behance.net/"

    const val GET_USERS = API_VERSION + "users/"

    const val GET_PROJECTS = API_VERSION + "projects/"

    const val GET_USER_BY_ID = API_VERSION + "users/{id}"

    const val GET_USER_PROJECTS = API_VERSION + "users/{id}/projects/"

    const val GET_USER_WIPS = API_VERSION + "users/{id}/wips/"

    const val GET_USER_APPRECIATIONS = API_VERSION + "users/{id}/appreciations/"

    const val PARAM_ID = "id"

    const val PARAM_API_KEY = "api_key"

}