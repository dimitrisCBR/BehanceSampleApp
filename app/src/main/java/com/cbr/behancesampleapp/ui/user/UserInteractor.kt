package com.cbr.behancesampleapp.ui.user

import com.cbr.behancesampleapp.domain.network.query.UsersQuery
import com.cbr.behancesampleapp.domain.network.repository.BehanceRepository
import com.cbr.behancesampleapp.model.BehanceSingleUserReponse
import com.cbr.behancesampleapp.model.BehanceUserResponse
import io.reactivex.Observable

class UserInteractor(val repository: BehanceRepository) {

    fun getUsers(usersQuery: UsersQuery): Observable<BehanceUserResponse> {
        return repository.getUsers(usersQuery.build())
    }

    fun getUserById(userId: String): Observable<BehanceSingleUserReponse> {
        return this.repository.getUserById(userId)
    }

}