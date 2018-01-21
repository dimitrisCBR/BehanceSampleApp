package com.cbr.behancesampleapp.ui.userdetails

import com.cbr.behancesampleapp.domain.model.BehanceSingleUserReponse
import com.cbr.behancesampleapp.domain.network.BehanceRepository
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpPresenter
import com.cbr.behancesampleapp.ui.userdetails.mvp.UserDetailsContract
import javax.inject.Inject

/** Created by Dimitrios on 8/27/2017. */
class UserDetailsPresenter @Inject
constructor(view: UserDetailsContract.View, private val mBehanceRepository: BehanceRepository)
    : BaseMvpPresenter<UserDetailsContract.View>(view), UserDetailsContract.Presenter {
    
    override fun fetchUserById(userId: Long) {
        mBehanceRepository.getUserById(userId.toString())
                .subscribe(
                        { response: BehanceSingleUserReponse -> mvpView.onUserFetched(response.user) },
                        { error ->
                            error.printStackTrace()
                            mvpView.showError()
                        })
    }
    
}
