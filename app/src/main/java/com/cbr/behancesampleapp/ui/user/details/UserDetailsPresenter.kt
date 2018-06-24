package com.cbr.behancesampleapp.ui.user.details

import com.cbr.behancesampleapp.model.BehanceUserResponse
import com.cbr.behancesampleapp.ui.common.mvp.BasePresenter
import com.cbr.behancesampleapp.ui.user.UserInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/** Created by Dimitrios on 8/27/2017. */
class UserDetailsPresenter
@Inject
constructor(
    private val userInteractor: UserInteractor,
    compositeDisposable: CompositeDisposable
) : BasePresenter<UserDetailsView>(compositeDisposable) {

    fun fetchUserById(userId: Long) {
        userInteractor.getUserById(userId.toString())
            .subscribe(
                { response: BehanceUserResponse -> view?.onUserFetched(response.user) },
                { error ->
                    error.printStackTrace()
                    view?.showError()
                })
    }

}
