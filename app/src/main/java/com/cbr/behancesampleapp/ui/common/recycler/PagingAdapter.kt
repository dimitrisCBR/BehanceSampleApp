package com.cbr.behancesampleapp.ui.common.recycler

import android.support.v7.widget.RecyclerView

/** Created by Dimitrios on 8/26/2017. */
abstract class PagingAdapter<T, V : RecyclerView.ViewHolder>(val interactor: Interactor<T>) : RecyclerView.Adapter<V>() {
    
    val PAGING_THREESHOLD = 7
    
    val data = mutableListOf<PagingListItem<T>>()
    
    private var mIsLoading: Boolean = false
    private var mIsFinished: Boolean = false
    
    override fun getItemCount(): Int = data.size + 1
    
    override fun getItemViewType(position: Int): Int =
            if (position == data.size) PagingListItem.Type.LOADING.ordinal
            else data[position].type.ordinal
    
    override fun onBindViewHolder(holder: V, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        if (position > data.size - PAGING_THREESHOLD && !(mIsLoading || mIsFinished)) {
            startLoading()
            interactor.requestMoreData()
        }
    }
    
    fun onDataLoaded(data: Collection<T>, clearPrevious: Boolean) {
        stopLoading()
        if (clearPrevious) {
            clearData()
        }
        addData(data)
    }
    
    private fun addData(items: Collection<T>?) {
        if (items != null && !items.isEmpty()) {
            val currentItems = this.data.size
            var newItemsCounter = 0
            for (item in items) {
                data.add(currentItems + newItemsCounter, PagingListItem(item, PagingListItem.Type.DATA))
                newItemsCounter++
            }
            notifyItemRemoved(currentItems)
            notifyItemRangeInserted(currentItems, data.size)
        }
    }
    
    fun clearData() {
        val previousCount = data.size
        data.clear()
        if (previousCount > 0) {
            notifyItemRangeRemoved(0, previousCount)
        }
    }
    
    fun startLoading() {
        mIsLoading = true
    }
    
    fun stopLoading() {
        mIsLoading = false
    }
    
    fun onAllItemsLoaded() {
        mIsFinished = true
    }
    
    interface Interactor<T> {
        
        fun requestMoreData()
        
        fun onListItemClicked(item: T, position: Int)
        
    }
}
