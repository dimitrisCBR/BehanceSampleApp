package com.cbr.behancesampleapp.ui.user.list

import com.cbr.behancesampleapp.domain.network.query.UsersQuery
import com.cbr.behancesampleapp.ui.common.mvp.BasePresenter
import com.cbr.behancesampleapp.ui.user.UserInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserListPresenter
@Inject
constructor(
    private val userInteractor: UserInteractor,
    compositeDisposable: CompositeDisposable) : BasePresenter<UserListView>(compositeDisposable) {

    private val usersQuery: UsersQuery = UsersQuery()

    fun requestBehanceUsers() {
        cancelPending()
        view?.showLoading()
        userInteractor.getUsers(usersQuery)
            .subscribe(
                { response ->
                    usersQuery.nextPage()
                    view?.onUsersFetched(response.items)
                },
                { t -> view?.showError(t.message) })
    }

    fun refresh() {
        usersQuery.reset()
        requestBehanceUsers()
    }
}