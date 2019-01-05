package com.cbr.behancesample.ui.user

import com.cbr.behancesample.common.Outcome
import com.futureworkshops.data.model.domain.User
import com.futureworkshops.domain.data.network.query.UsersQuery
import com.futureworkshops.domain.data.scheduler.SchedulersProvider
import com.futureworkshops.domain.domain.UserRepository
import io.reactivex.Single
import javax.inject.Inject


class UserListInteractor @Inject
constructor(private val usersRepository: UserRepository,
            private val schedulersProvider: SchedulersProvider) {

    private val apiQuery = UsersQuery()

    fun loadUsers(): Single<Outcome<List<User>>> =
            usersRepository.loadUsers(apiQuery.build())
                    .map { users -> Outcome.success(users) }
                    .doOnSuccess {
                        apiQuery.nextPage()
                    }
                    .subscribeOn(schedulersProvider.io())
                    .observeOn(schedulersProvider.ui())


    fun refresh() {
        apiQuery.reset()
    }

}