package com.futureworkshops.domain.data.network.query

class UsersQuery : PaginatedApiQuery() {

    fun withCountry(country: String): UsersQuery {
        parameters[PARAM_COUNTRY] = country
        return this
    }

    fun withState(state: String): UsersQuery {
        parameters[PARAM_STATE] = state
        return this
    }

    fun withCity(city: String): UsersQuery {
        parameters[PARAM_CITY] = city
        return this
    }

    fun withTags(tags: Array<String>): UsersQuery {
        parameters[PARAM_TAGS] = tags
        return this
    }
}
