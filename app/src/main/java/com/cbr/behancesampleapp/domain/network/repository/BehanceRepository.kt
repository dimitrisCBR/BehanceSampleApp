package com.cbr.behancesampleapp.domain.network.repository

import com.cbr.behancesampleapp.BuildConfig
import com.cbr.behancesampleapp.domain.network.BehanceApiService
import com.cbr.behancesampleapp.model.BehanceListResponse
import com.cbr.behancesampleapp.model.BehanceProject
import com.cbr.behancesampleapp.model.BehanceUser
import com.cbr.behancesampleapp.model.BehanceUserResponse
import io.reactivex.Observable

/** Created by dimitrios on 25/08/2017. */
class BehanceRepository(private val mBehanceApiService: BehanceApiService) : DataRepository() {

    fun getUsers(params: MutableMap<String, Any>): Observable<BehanceListResponse<BehanceUser>> {
        return mBehanceApiService.getUsers(params).compose(this.applySchedulers())
    }

    fun getUserById(userId: String): Observable<BehanceUserResponse> {
        return this.mBehanceApiService.getUserById(userId, BuildConfig.API_KEY).compose(this.applySchedulers())
    }

    fun getProjects(params: MutableMap<String, Any>): Observable<BehanceListResponse<BehanceProject>> {
        return mBehanceApiService.getProjects(params).compose(this.applySchedulers())
    }

}