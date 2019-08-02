package com.cbr.base.data.network


import com.cbr.base.data.network.Urls.PARAM_ID
import com.cbr.base.model.api.BehanceProject
import com.cbr.base.model.api.BehanceUser
import com.cbr.base.model.domain.Project
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface BehanceApiService {

    @GET(Urls.GET_USERS)
    fun getUsers(@QueryMap queryParams: @JvmSuppressWildcards Map<String, Any>): Single<ListResponse<BehanceUser>>

    @GET(Urls.GET_USER_BY_ID)
    fun getUserById(@Path(PARAM_ID) id: String, @QueryMap queryParams: @JvmSuppressWildcards Map<String, Any>): Single<BehanceUser>

    @GET(Urls.GET_PROJECTS)
    fun getProjects(@QueryMap queryParams: @JvmSuppressWildcards Map<String, Any>): Single<ListResponse<BehanceProject>>

    @GET(Urls.GET_PROJECT_BY_ID)
    fun getProjectById(@Path(PARAM_ID) id: String, @QueryMap queryParams: @JvmSuppressWildcards Map<String, Any>): Single<Project>
}