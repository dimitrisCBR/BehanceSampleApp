package com.cbr.behancesampleapp.domain.network.query

import com.cbr.behancesampleapp.BuildConfig
import com.cbr.behancesampleapp.domain.network.Urls

import java.util.HashMap

/** Created by dimitrios on 25/08/2017.*/
open class ApiQuery {
    
    var mQueryParams: MutableMap<String, Any> = HashMap()
    
    init {
        mQueryParams.put(Urls.PARAM_API_KEY, BuildConfig.API_KEY)
    }
    
    fun build(): MutableMap<String, Any> {
        return mQueryParams
    }
    
}
