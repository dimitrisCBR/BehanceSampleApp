package com.cbr.behancesampleapp.domain.network.query

/** Created by dimitrios on 25/08/2017.*/
class UsersQuery : PaginatedApiQuery() {
    
    fun withCountry(country: String): UsersQuery {
        mQueryParams[PARAM_COUNTRY] = country
        return this
    }
    
    companion object {
        
        private val PARAM_COUNTRY = "country"
    }
    
}
