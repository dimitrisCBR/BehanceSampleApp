package com.cbr.behancesampleapp.domain.network.query

/** Created by Dimitrios on 8/27/2017.*/
open class PaginatedApiQuery : ApiQuery() {
    
    private var mPageNumber = DEFAULT_PAGE
    
    init {
        mQueryParams.put(PARAM_PAGE, mPageNumber)
    }
    
    fun nextPage() {
        mPageNumber++
        mQueryParams.put(PARAM_PAGE, mPageNumber)
    }
    
    fun reset() {
        mQueryParams.clear()
        mPageNumber = DEFAULT_PAGE
    }
    
    companion object {
        
        private val PARAM_PAGE = "page"
        
        private val DEFAULT_PAGE = 1
    }
}
