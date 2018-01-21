package com.cbr.behancesampleapp.ui.landing

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.cbr.behancesampleapp.domain.model.BehanceUser
import com.cbr.behancesampleapp.ui.common.recycler.LoadingViewHolder
import com.cbr.behancesampleapp.ui.common.recycler.PagingAdapter
import com.cbr.behancesampleapp.ui.common.recycler.PagingListItem

/** Created by Dimitrios on 8/26/2017. */
class BehanceUserGridAdapter(interactor: Interactor<BehanceUser>) : PagingAdapter<BehanceUser, RecyclerView.ViewHolder>(interactor) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == PagingListItem.Type.LOADING.ordinal) {
            LoadingViewHolder.newInstance(parent)
        } else BehanceUserGridViewHolder.newInstance(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //ignore other types, unless you want to mess with the Loader.
        if (holder is BehanceUserGridViewHolder) {
            holder.onBind(data[position].item, position)
            holder.itemView.setOnClickListener { interactor.onListItemClicked(data[position].item, position) }
        }
    }
    
}
