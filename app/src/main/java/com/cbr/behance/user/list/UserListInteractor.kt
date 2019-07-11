package com.cbr.behance.user.list


import com.futureworkshops.core.ui.Outcome
import com.futureworkshops.core.model.domain.User
import com.futureworkshops.core.data.network.query.UsersQuery
import com.futureworkshops.core.data.repository.UserRepository
import com.futureworkshops.core.data.scheduler.SchedulersProvider
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