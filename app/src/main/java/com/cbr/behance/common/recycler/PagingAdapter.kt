package com.cbr.behance.common.recycler

import androidx.recyclerview.widget.RecyclerView


abstract class PagingAdapter<T : RecyclerView.ViewHolder>(
        protected val callback: Callback
) : RecyclerView.Adapter<T>() {

    private var isLoading = false
    private var loadingFinished = false

    override fun onBindViewHolder(holder: T, position: Int) {
        if (position > (itemCount - LIMIT) && !(isLoading || loadingFinished)) {
            isLoading = true
            callback.needMoreData()
        }
    }

    fun onRefresh() {
        loadingFinished = false
        isLoading = false
    }

    fun onLoadingFinished() {
        isLoading = false
    }

    fun onEverythingLoaded() {
        loadingFinished = true
        isLoading = false
    }

    companion object {

        private const val LIMIT = 5
    }

    interface Callback {

        fun needMoreData()
    }
}