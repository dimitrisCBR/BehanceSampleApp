package com.cbr.behance.user.list


import com.cbr.behance.user.list.recycler.UserGridItem
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

    fun loadUsers(): Single<List<UserGridItem>> =
            usersRepository.loadUsers(apiQuery.build())
                    .doOnSuccess {
                        apiQuery.nextPage()
                    }
                    .flatMapObservable { items -> Observable.fromIterable(items) }
                    .map { user -> UserGridItem(UserGridItem.TYPE_USER, user) }
                    .toList()
                    .subscribeOn(schedulersProvider.io())
                    .observeOn(schedulersProvider.ui())

    fun refresh() {
        apiQuery.reset()
    }

}