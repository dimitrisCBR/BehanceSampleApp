package com.cbr.behance.user.list.recycler

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cbr.base.extension.loadImage
import com.cbr.base.model.domain.User
import com.cbr.behance.R
import com.cbr.behance.commons.recycler.BaseVH
import com.cbr.behance.commons.recycler.ListItem
import com.cbr.behance.commons.recycler.LoadingViewHolder
import com.cbr.behance.commons.recycler.LoadingViewHolder.Companion.TYPE_LOADING
import com.cbr.behance.commons.recycler.PagingAdapter
import kotlinx.android.synthetic.main.viewholder_grid.*

class UserGridAdapter(val columnCount: Int, callback: Callback) : PagingAdapter<UserGridItem>(callback) {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<UserGridItem> {
        return when (viewType) {
            TYPE_LOADING -> LoadingViewHolder.newInstance(parent)
            else -> UserGridViewHolder.newInstance(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (holder is UserGridViewHolder) {
            holder.bind(position, items[position])
        }
    }

    fun setUsers(newUsers: List<UserGridItem>) {
        val diffResult = DiffUtil.calculateDiff(UsersDiffCallback(items, newUsers))
        diffResult.dispatchUpdatesTo(this)
        items.clear()
        items.addAll(newUsers)
    }

    fun getSpanForPosition(position: Int): Int = if (getItemViewType(position) == TYPE_LOADING) columnCount else 1

    fun isEmpty(): Boolean = itemCount <= 1

}

class UserGridViewHolder(view: View) : BaseVH<UserGridItem>(view) {

    override fun bind(position: Int, item: UserGridItem) {
        val user = item.user()
        user.images.takeIf { it.isNotEmpty() }?.let {
            val key = it.keys.first()
            imageView.loadImage(it[key])
        }

        titleTextView.text = user.displayName
        subtitleTextView.text = user.country
        infoDivider.visibility = if (TextUtils.isEmpty(user.occupation)) View.GONE else View.VISIBLE
        extraTextView.visibility = if (TextUtils.isEmpty(user.occupation)) View.GONE else View.VISIBLE
        extraTextView.text = user.occupation
    }

    companion object {

        fun newInstance(parent: ViewGroup): UserGridViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_grid, parent, false)
            return UserGridViewHolder(view)
        }
    }
}

class UserGridItem(type: Int, data: Any) : ListItem(type, data) {

    fun user() = data as User

    companion object {
        const val TYPE_USER = 0
    }
}