package com.futureworkshops.core.data.network


import com.futureworkshops.core.model.api.BehanceProject
import com.futureworkshops.core.model.api.BehanceUser
import com.futureworkshops.core.data.network.Urls.PARAM_API_KEY
import com.futureworkshops.core.data.network.Urls.PARAM_ID
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface BehanceApiService {

    @GET(Urls.GET_USERS)
    fun getUsers(@QueryMap queryParams: @JvmSuppressWildcards Map<String, Any>): Single<ListResponse<BehanceUser>>

    @GET(Urls.GET_USER_BY_ID)
    fun getUserById(@Path(PARAM_ID) id: String, @Query(PARAM_API_KEY) apiKey: String): Single<BehanceUser>

    @GET(Urls.GET_PROJECTS)
    fun getProjects(@QueryMap queryParams: @JvmSuppressWildcards Map<String, Any>): Single<ListResponse<BehanceProject>>

}