package com.cbr.behancesample.ui.user

import android.support.v7.util.DiffUtil
import com.futureworkshops.data.model.domain.User

/**
 * Created by dimitrios on 12/10/2018.
 */

class UsersDiffCallback(val oldUsers: List<User>, val newUsers: List<User>) : DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean = oldUsers[p0].id == newUsers[p1].id

    override fun getOldListSize(): Int = oldUsers.size

    override fun getNewListSize(): Int = newUsers.size

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean = oldUsers[p0] == newUsers[p1]

}