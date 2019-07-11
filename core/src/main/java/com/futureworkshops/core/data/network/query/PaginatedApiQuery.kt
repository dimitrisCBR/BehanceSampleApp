package com.futureworkshops.core.data.network.query


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
        parameters[PARAM_PAGE] = pageNumber
    }

}
