package com.cbr.behancesampleapp.domain.network.repository

import com.cbr.behancesampleapp.BuildConfig
import com.cbr.behancesampleapp.model.BehanceSingleUserReponse
import com.cbr.behancesampleapp.model.BehanceUserResponse
import com.cbr.behancesampleapp.domain.network.BehanceApiService

import io.reactivex.Observable

/** Created by dimitrios on 25/08/2017. */
class BehanceRepository(private val mBehanceApiService: BehanceApiService) : DataRepository() {
    
    fun getUsers(params: MutableMap<String, Any>): Observable<BehanceUserResponse> {
        return mBehanceApiService.getUsers(params).compose(this.applySchedulers())
    }
    
    fun getUserById(userId: String): Observable<BehanceSingleUserReponse> {
        return this.mBehanceApiService.getUserById(userId, BuildConfig.API_KEY).compose(this.applySchedulers())
    }
    
}