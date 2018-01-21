package com.cbr.behancesampleapp.domain.network

/** Created by dimitrios on 24/08/2017. */
class Urls {
    
    object Users {
        
        const val GET_USERS = "users/"
    
        const val GET_USER_BY_ID = "users/{id}"
    
        const val GET_USER_PROJECTS = "users/{id}/projects/"
    
        const val GET_USER_WIPS = "users/{id}/wips/"
    
        const val GET_USER_APPRECIATIONS = "users/{id}/appreciations/"
        
    }
    
    object Base {
    
        const val BASE_URL = "http://behance.net/v2/"
    }
    
    object Params {
    
        const val PARAM_ID = "id"
    
        const val PARAM_API_KEY = "api_key"
    }
}
