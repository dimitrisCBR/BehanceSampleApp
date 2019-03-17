package com.cbr.behance.ui.user.list

import com.cbr.behance.common.Outcome
import com.futureworkshops.data.model.domain.User
import com.futureworkshops.domain.data.network.query.UsersQuery
import com.futureworkshops.domain.scheduler.SchedulersProvider
import com.futureworkshops.domain.data.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class UserListInteractor @Inject
constructor(private val usersRepository: UserRepository,
            private val schedulersProvider: SchedulersProvider) {

    private val apiQuery = UsersQuery()
    private val allUsers = mutableListOf<User>()

    fun loadUsers(): Observable<Outcome<List<User>>> =
            usersRepository.loadUsers(apiQuery.build())
                    .doOnSuccess {
                        apiQuery.nextPage()
                        allUsers.addAll(it)
                    }
                    .flatMap { Single.just(allUsers.toList()) }
                    .map { allUsers -> Outcome.success(allUsers) }
                    .onErrorResumeNext { t ->
                        Single.just(Outcome.error(t.message ?: ""))
                    }
                    .toObservable()
                    .startWith(Outcome.loading())
                    .subscribeOn(schedulersProvider.io())
                    .observeOn(schedulersProvider.ui())

    fun refresh() {
        allUsers.clear()
        apiQuery.reset()
    }

}