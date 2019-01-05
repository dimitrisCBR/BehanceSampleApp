package com.cbr.behancesample.ui.user.recycler

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cbr.behancesample.R
import com.futureworkshops.data.model.domain.User
import com.futureworkshops.domain.extension.loadImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_user.*

class UserGridAdapter : RecyclerView.Adapter<UserGridViewHolder>() {

    var items: ArrayList<User> = arrayListOf()

    override fun onBindViewHolder(holder: UserGridViewHolder, position: Int) {
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
    }

    fun getSpanForPosition(position: Int): Int = 1

}

class UserGridViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun onBind(user: User, position: Int) {
        user.images.takeIf { it.isNotEmpty() }.let {
            val key = it?.keys?.first()
            userImageView.loadImage(it?.get(key))
        }

        userNameTextView.text = user.displayName
        userSubtitleTextView.text = user.country
        infoDivider.visibility = if (TextUtils.isEmpty(user.occupation)) View.GONE else View.VISIBLE
        extraTextView.visibility = if (TextUtils.isEmpty(user.occupation)) View.GONE else View.VISIBLE
        extraTextView.text = user.occupation
    }

    companion object {

        fun newInstance(parent: ViewGroup): UserGridViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_user, parent, false)
            return UserGridViewHolder(view)
        }
    }
}