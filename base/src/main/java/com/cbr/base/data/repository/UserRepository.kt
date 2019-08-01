package com.cbr.base.data.repository

import com.cbr.base.data.network.BehanceApiService
import com.cbr.base.model.domain.User
import com.cbr.base.data.database.dao.UserDAO
import com.cbr.base.data.database.entity.UserEntity
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