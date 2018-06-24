package com.cbr.behancesampleapp.ui.user.list

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cbr.behancesampleapp.R
import com.cbr.behancesampleapp.model.BehanceUser
import com.cbr.behancesampleapp.ui.common.recycler.LoadingViewHolder
import com.cbr.behancesampleapp.ui.common.recycler.PagingAdapter
import com.cbr.behancesampleapp.ui.common.recycler.PagingListItem
import com.cbr.behancesampleapp.util.loadImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_user.*

/** Created by Dimitrios on 8/26/2017. */
class UserGridAdapter(interactor: Interactor<BehanceUser>) : PagingAdapter<BehanceUser, RecyclerView.ViewHolder>(interactor) {
    
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

class BehanceUserGridViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun onBind(user: BehanceUser, position: Int) {
        user.images.largeUrl?.let {
            userImageView.loadImage(user.images.largeUrl)
        }

        userNameTextView.text = user.displayName
        userSubtitleTextView.text = user.country
        infoDivider.visibility = if (TextUtils.isEmpty(user.occupation)) View.GONE else View.VISIBLE
        extraTextView.visibility = if (TextUtils.isEmpty(user.occupation)) View.GONE else View.VISIBLE
        extraTextView.text = user.occupation
    }

    companion object {

        fun newInstance(parent: ViewGroup): BehanceUserGridViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_user, parent, false)
            return BehanceUserGridViewHolder(view)
        }
    }
}