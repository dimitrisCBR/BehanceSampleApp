package com.cbr.behance.commons.recycler

import androidx.recyclerview.widget.RecyclerView


abstract class PagingAdapter<ITEM : ListItem>(
        protected val callback: Callback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items = mutableListOf<ITEM>()

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return items[position].itemType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position > (itemCount - LIMIT)) {
            callback.needMoreData()
        }
    }

    companion object {

        private const val LIMIT = 5
    }

    interface Callback {

        fun needMoreData()
    }
}

open class ListItem(val itemType: Int, val data: Any) {

    fun isLoading() = itemType == LoadingViewHolder.TYPE_LOADING
}