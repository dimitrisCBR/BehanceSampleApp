package com.cbr.behancesampleapp.domain.network

import com.cbr.behancesampleapp.model.BehanceSingleUserReponse
import com.cbr.behancesampleapp.model.BehanceUserResponse
import com.cbr.behancesampleapp.domain.network.Urls.PARAM_API_KEY
import com.cbr.behancesampleapp.domain.network.Urls.PARAM_ID

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/** Created by dimitrios on 24/08/2017. */
interface BehanceApiService {
    
    @GET(Urls.GET_USERS)
    fun getUsers(@QueryMap queryParams: MutableMap<String, Any>): Observable<BehanceUserResponse>
    
    @GET(Urls.GET_USER_BY_ID)
    fun getUserById(@Path(PARAM_ID) id: String, @Query(PARAM_API_KEY) apiKey: String): Observable<BehanceSingleUserReponse>

}

object Urls{

    const val BASE_URL = "http://behance.net/v2/"

    const val GET_USERS = "users/"

    const val GET_USER_BY_ID = "users/{id}"

    const val GET_USER_PROJECTS = "users/{id}/projects/"

    const val GET_USER_WIPS = "users/{id}/wips/"

    const val GET_USER_APPRECIATIONS = "users/{id}/appreciations/"

    const val PARAM_ID = "id"

    const val PARAM_API_KEY = "api_key"

}