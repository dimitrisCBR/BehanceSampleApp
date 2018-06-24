package com.cbr.behancesampleapp.ui.project.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cbr.behancesampleapp.R
import com.cbr.behancesampleapp.model.BehanceProject
import com.cbr.behancesampleapp.model.BehanceUser
import com.cbr.behancesampleapp.ui.common.recycler.LoadingViewHolder
import com.cbr.behancesampleapp.ui.common.recycler.PagingAdapter
import com.cbr.behancesampleapp.ui.common.recycler.PagingListItem
import com.cbr.behancesampleapp.ui.user.list.BehanceUserGridViewHolder
import kotlinx.android.extensions.LayoutContainer

/** Created by Dimitrios on 8/26/2017. */
class ProjectsAdapter(interactor: Interactor<BehanceProject>) : PagingAdapter<BehanceProject, RecyclerView.ViewHolder>(interactor) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == PagingListItem.Type.LOADING.ordinal) {
            LoadingViewHolder.newInstance(parent)
        } else BehanceProjectViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //ignore other types, unless you want to mess with the Loader.
        if (holder is BehanceProjectViewHolder) {
            holder.onBind(data[position].item, position)
            holder.itemView.setOnClickListener { interactor.onListItemClicked(data[position].item, position) }
        }
    }

}

class BehanceProjectViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun onBind(project: BehanceProject, position: Int) {

    }

    companion object {

        fun newInstance(parent: ViewGroup): BehanceUserGridViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_project, parent, false)
            return BehanceUserGridViewHolder(view)
        }
    }
}