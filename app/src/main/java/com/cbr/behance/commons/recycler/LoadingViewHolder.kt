package com.cbr.behance.commons.recycler

import android.view.View
import android.view.ViewGroup
import com.cbr.base.extension.inflater
import com.cbr.behance.R

class LoadingViewHolder<T : ListItem>(view: View) : BaseVH<T>(view) {

    override fun bind(position: Int, data: T) {
        //no-op
    }


    companion object {

        fun <T : ListItem> newInstance(parent: ViewGroup) =
                LoadingViewHolder<T>(parent.inflater().inflate(R.layout.viewholder_loading, parent, false))


        const val TYPE_LOADING = -1
    }
}