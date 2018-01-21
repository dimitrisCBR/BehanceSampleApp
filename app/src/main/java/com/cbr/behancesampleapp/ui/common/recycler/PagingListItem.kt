package com.cbr.behancesampleapp.ui.common.recycler

/** Created by Dimitrios on 8/27/2017. */
class PagingListItem<T>(val item: T, val type: Type) {
    
    enum class Type {
        LOADING,
        DATA,
        HEADER
    }
}
