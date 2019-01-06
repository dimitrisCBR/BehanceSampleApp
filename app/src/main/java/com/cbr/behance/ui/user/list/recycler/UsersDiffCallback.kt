package com.cbr.behance.ui.user.list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.futureworkshops.data.model.domain.User

class UsersDiffCallback(
        private val oldUsers: List<User>,
        private val newUsers: List<User>) : DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean = oldUsers[p0].id == newUsers[p1].id

    override fun getOldListSize(): Int = oldUsers.size

    override fun getNewListSize(): Int = newUsers.size

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean = oldUsers[p0] == newUsers[p1]

}