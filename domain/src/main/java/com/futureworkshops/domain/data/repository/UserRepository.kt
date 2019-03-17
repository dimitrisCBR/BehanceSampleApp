package com.futureworkshops.domain.data.repository

import com.futureworkshops.data.model.domain.User
import com.futureworkshops.domain.data.database.dao.UserDAO
import com.futureworkshops.domain.data.database.entity.UserEntity
import com.futureworkshops.domain.data.network.BehanceApiService
import io.reactivex.Single
import java.net.UnknownHostException
import javax.inject.Inject


class UserRepository @Inject constructor(
        private val behanceApiService: BehanceApiService,
        private val userDAO: UserDAO
) {

    fun loadUsers(map: Map<String, Any>): Single<List<User>> {
        return behanceApiService.getUsers(map)
                .map { response ->
                    response.items.map { it.toModel() }
                }
                .doOnSuccess { items ->
                    userDAO.insertAll(items.map { UserEntity.fromModel(it) })
                }
                .onErrorResumeNext {
                    if (it is UnknownHostException) {
                        userDAO.getAll().toObservable()
                                .flatMapIterable { items -> items }
                                .map { item -> item.toModel() }
                                .toList()
                    } else {
                        Single.error(it)
                    }
                }
    }

}