package com.cbr.behancesampleapp.domain.network.query

/** Created by Dimitrios on 8/27/2017.*/
open class PaginatedApiQuery : ApiQuery() {

    private var pageNumber = DEFAULT_PAGE

    init {
        parameters[PARAM_PAGE] = pageNumber
    }

    fun nextPage() {
        pageNumber++
        parameters[PARAM_PAGE] = pageNumber
    }

    override fun reset() {
        super.reset()
        pageNumber = DEFAULT_PAGE
    }

}