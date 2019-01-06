package com.cbr.behance.ui.user

import com.cbr.behance.common.Outcome
import com.futureworkshops.data.model.domain.User
import com.futureworkshops.domain.data.network.query.UsersQuery
import com.futureworkshops.domain.data.scheduler.SchedulersProvider
import com.futureworkshops.domain.domain.UserRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class UserListInteractor @Inject
constructor(private val usersRepository: UserRepository,
            private val schedulersProvider: SchedulersProvider) {

    private val apiQuery = UsersQuery()

    fun loadUsers(): Observable<Outcome<List<User>>> =
            usersRepository.loadUsers(apiQuery.build())
                    .map { users -> Outcome.success(users) }
                    .onErrorResumeNext {
                        Single.just(Outcome.error(it.message ?: ""))
                    }
                    .doOnSuccess {
                        apiQuery.nextPage()
                    }
                    .toObservable()
                    .startWith(Outcome.loading())
                    .subscribeOn(schedulersProvider.io())
                    .observeOn(schedulersProvider.ui())

    fun refresh() {
        apiQuery.reset()
    }

}