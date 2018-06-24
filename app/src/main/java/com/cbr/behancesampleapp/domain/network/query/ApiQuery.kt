package com.cbr.behancesampleapp.domain.network.query

import com.cbr.behancesampleapp.BuildConfig
import com.cbr.behancesampleapp.domain.network.Urls

/** Created by dimitrios on 25/08/2017.*/
open class ApiQuery {

    var parameters: MutableMap<String, Any> = mutableMapOf()

    init {
        parameters[Urls.PARAM_API_KEY] = BuildConfig.API_KEY
    }

    fun build(): MutableMap<String, Any> {
        return parameters
    }

    open fun reset() {
        parameters.clear()
        parameters[Urls.PARAM_API_KEY] = BuildConfig.API_KEY
    }

    companion object {
        const val DEFAULT_PAGE = 1

        const val PARAM_COUNTRY = "country"
        const val PARAM_STATE= "state"
        const val PARAM_CITY= "city"
        const val PARAM_TAGS= "tags"
        const val PARAM_LICENSE= "tags"
        const val PARAM_SORT= "sort"
        const val PARAM_PAGE = "page"
    }
}
