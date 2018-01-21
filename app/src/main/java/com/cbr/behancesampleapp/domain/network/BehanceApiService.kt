package com.cbr.behancesampleapp.domain.network

import com.cbr.behancesampleapp.domain.model.BehanceSingleUserReponse
import com.cbr.behancesampleapp.domain.model.BehanceUserResponse

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/** Created by dimitrios on 24/08/2017. */
interface BehanceApiService {
    
    @GET(Urls.Users.GET_USERS)
    fun getUsers(@QueryMap queryParams: MutableMap<String, Any>): Observable<BehanceUserResponse>
    
    @GET(Urls.Users.GET_USER_BY_ID)
    fun getUserById(@Path(Urls.Params.PARAM_ID) id: String, @Query(Urls.Params.PARAM_API_KEY) apiKey: String): Observable<BehanceSingleUserReponse>
    
}
