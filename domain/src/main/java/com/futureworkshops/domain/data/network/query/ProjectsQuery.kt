package com.futureworkshops.domain.data.network.query

class ProjectsQuery : PaginatedApiQuery() {

    fun withCountry(country: String): ProjectsQuery {
        parameters[PARAM_COUNTRY] = country
        return this
    }

    fun withState(state: String): ProjectsQuery {
        parameters[PARAM_STATE] = state
        return this
    }

    fun withCity(city: String): ProjectsQuery {
        parameters[PARAM_CITY] = city
        return this
    }

    fun withTags(tags: Array<String>): ProjectsQuery {
        parameters[PARAM_TAGS] = tags
        return this
    }

    fun withLicense(license: String): ProjectsQuery {
        parameters[PARAM_LICENSE] = license
        return this
    }
}
