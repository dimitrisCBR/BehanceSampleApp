package com.cbr.behance.user.list.recycler

import androidx.recyclerview.widget.DiffUtil

class UsersDiffCallback(
        private val oldUsers: List<UserGridItem>,
        private val newUsers: List<UserGridItem>) : DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean = (oldUsers[p0].isLoading() && newUsers[p1].isLoading())
            || (!oldUsers[p0].isLoading() && !newUsers[p1].isLoading() && oldUsers[p0].user().id == newUsers[p1].user().id)

    override fun getOldListSize(): Int = oldUsers.size

    override fun getNewListSize(): Int = newUsers.size

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean = oldUsers[p0] == newUsers[p1]

}