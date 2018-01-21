package com.cbr.behancesampleapp.ui.common.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cbr.behancesampleapp.R

/** Created by Dimitrios on 8/27/2017.  */
class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        
        private val LAYOUT = R.layout.viewholder_loading
        
        fun newInstance(parent: ViewGroup): LoadingViewHolder {
            return LoadingViewHolder(
                    LayoutInflater.from(parent.context).inflate(LAYOUT, parent, false)
            )
        }
    }
}
