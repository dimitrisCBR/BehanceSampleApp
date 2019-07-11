package com.cbr.behance.user.list.recycler

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.cbr.behance.R
import com.futureworkshops.core.ui.recycler.BaseVH
import com.futureworkshops.core.ui.recycler.PagingAdapter
import com.futureworkshops.core.model.domain.User
import com.futureworkshops.core.extension.loadImage
import kotlinx.android.synthetic.main.viewholder_grid.*

class UserGridAdapter(callback: PagingAdapter.Callback) : PagingAdapter<UserGridViewHolder>(callback) {

    var items: ArrayList<User> = arrayListOf()

    override fun onBindViewHolder(holder: UserGridViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGridViewHolder {
        return UserGridViewHolder.newInstance(parent)
    }

    fun setUsers(newUsers: List<User>) {
        val diffResult = DiffUtil.calculateDiff(UsersDiffCallback(items, newUsers))
        diffResult.dispatchUpdatesTo(this)
        items.clear()
        items.addAll(newUsers)
        onLoadingFinished()
    }

    fun getSpanForPosition(position: Int): Int = 1

}

class UserGridViewHolder(view: View) : BaseVH(view) {

    fun onBind(user: User, position: Int) {
        user.images.takeIf { it.isNotEmpty() }.let {
            val key = it?.keys?.first()
            imageView.loadImage(it?.get(key))
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