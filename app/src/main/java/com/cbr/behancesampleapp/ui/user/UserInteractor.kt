package com.cbr.behancesampleapp.ui.user

import com.cbr.behancesampleapp.domain.network.query.UsersQuery
import com.cbr.behancesampleapp.domain.network.repository.BehanceRepository
import com.cbr.behancesampleapp.model.BehanceListResponse
import com.cbr.behancesampleapp.model.BehanceUser
import com.cbr.behancesampleapp.model.BehanceUserResponse
import com.cbr.behancesampleapp.model.BehanceUserListResponse
import io.reactivex.Observable

class UserInteractor(val repository: BehanceRepository) {

    fun getUsers(usersQuery: UsersQuery): Observable<BehanceListResponse<BehanceUser>> {
        return repository.getUsers(usersQuery.build())
    }

    fun getUserById(userId: String): Observable<BehanceUserResponse> {
        return this.repository.getUserById(userId)
    }

}