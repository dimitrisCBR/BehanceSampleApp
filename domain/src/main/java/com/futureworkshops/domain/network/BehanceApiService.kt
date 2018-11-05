package com.futureworkshops.domain.network


import com.futureworkshops.data.model.remote.BehanceProject
import com.futureworkshops.data.model.remote.BehanceUser
import com.futureworkshops.domain.network.ListResponse
import com.futureworkshops.domain.network.Urls
import com.futureworkshops.domain.network.Urls.PARAM_API_KEY
import com.futureworkshops.domain.network.Urls.PARAM_ID
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/** Created by dimitrios on 24/08/2017. */
interface BehanceApiService {

    @GET(Urls.GET_USERS)
    fun getUsers(@QueryMap queryParams: @JvmSuppressWildcards Map<String, Any>): Single<ListResponse<BehanceUser>>

    @GET(Urls.GET_USER_BY_ID)
    fun getUserById(@Path(PARAM_ID) id: String, @Query(PARAM_API_KEY) apiKey: String): Single<BehanceUser>

    @GET(Urls.GET_PROJECTS)
    fun getProjects(@QueryMap queryParams: Map<String, Any>): Single<ListResponse<BehanceProject>>

}