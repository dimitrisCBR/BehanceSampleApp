package com.cbr.behance.commons.recycler

import android.view.View
import android.view.ViewGroup
import com.futureworkshops.core.extension.inflater

class LoadingViewHolder<T : ListItem>(view: View) : BaseVH<T>(view) {

    override fun bind(position: Int, data: T) {
        //no-op
    }


    companion object {

        fun <T : ListItem> newInstance(parent: ViewGroup) =
                LoadingViewHolder<T>(parent.inflater().inflate(com.futureworkshops.core.R.layout.viewholder_loading, parent, false))


        const val TYPE_LOADING = -1
    }
}