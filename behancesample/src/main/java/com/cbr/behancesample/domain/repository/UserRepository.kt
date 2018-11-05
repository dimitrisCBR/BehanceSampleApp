package com.cbr.behancesample.domain.repository

import com.futureworkshops.data.model.domain.User
import com.futureworkshops.domain.*
import com.futureworkshops.domain.database.dao.UserDAO
import com.futureworkshops.domain.database.entity.UserEntity
import com.futureworkshops.domain.network.BehanceApiService
import com.futureworkshops.domain.network.query.UsersQuery
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by dimitrios on 04/11/2018.
 */

class UserRepository @Inject constructor(
        val userDAO: UserDAO,
        val behanceApi: BehanceApiService) {

    private val compositeDisposable = CompositeDisposable()

    val outcome: PublishSubject<Outcome<List<User>>> =
            PublishSubject.create<Outcome<List<User>>>()

    fun fetchUsers() {
        outcome.loading(true)
        compositeDisposable.add(
                fetchFromDB()
                        .flatMap { users ->
                            if (users.isNotEmpty()) {
                                Observable.just(users)
                            } else {
                                fetchFromServerAndUpdate()
                                        .andThen(fetchFromDB())
                            }
                        }
                        .subscribeOn(AppSchedulers.io)
                        .observeOn(AppSchedulers.mainThread)
                        .subscribe(
                                { outcome.success(it) },
                                { t -> outcome.failed(t) }
                        )
        )
    }

    private fun fetchFromDB(): Observable<List<User>> {
        return userDAO.getAll()
                .map { list -> list.map { it.toModel() } }
                .toObservable()
    }

    private fun fetchFromServerAndUpdate(): Completable {
        return behanceApi.getUsers(UsersQuery().build())
                .map { it -> it.items.map { it.toModel() } }
                .doOnSuccess { items -> updateUserDatabase(newData = items) }
                .toCompletable()
    }

    fun refreshUsers(usersQuery: UsersQuery) {
        outcome.loading(true)
        compositeDisposable.add(
                behanceApi.getUsers(usersQuery.build())
                        .map { list -> list.items.map { it.toModel() } }
                        .subscribeOn(AppSchedulers.io)
                        .observeOn(AppSchedulers.mainThread)
                        .doOnSuccess {
                            updateUserDatabase(it)
                        }
                        .subscribe(
                                { items -> outcome.success(items) },
                                { t -> outcome.failed(t) }
                        )
        )
    }

    private fun updateUserDatabase(newData: List<User>) {
        compositeDisposable.add(
                Single.fromCallable {
                    userDAO.insertAll(newData.map { UserEntity.fromModel(it) })
                }
                        .subscribeOn(AppSchedulers.io)
                        .observeOn(AppSchedulers.mainThread)
                        .subscribe()
        )

    }
}