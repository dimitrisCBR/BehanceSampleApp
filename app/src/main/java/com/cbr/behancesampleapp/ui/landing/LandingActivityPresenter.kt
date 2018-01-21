package com.cbr.behancesampleapp.ui.landing

import com.cbr.behancesampleapp.domain.network.BehanceRepository
import com.cbr.behancesampleapp.domain.network.query.UsersQuery
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpPresenter
import com.cbr.behancesampleapp.ui.landing.mvp.LandingActivityContract
import javax.inject.Inject

/** Created by Dimitrios on 8/26/2017.  */

class LandingActivityPresenter
@Inject
constructor(view: LandingActivityContract.View, private val mBehanceRepository: BehanceRepository)
    : BaseMvpPresenter<LandingActivityContract.View>(view), LandingActivityContract.Presenter {
    
    
    val mQuery: UsersQuery = UsersQuery()
    
    var mClearPrevious: Boolean = false
    
    override fun requestBehanceUsers() {
        mBehanceRepository.getUsers(mQuery.build())
                .subscribe(
                        { response ->
                            mQuery.nextPage()
                            mvpView.onUsersFetched(response.users, mClearPrevious)
                        },
                        { mvpView.showError() })
    }
    
    override fun refresh() {
        mQuery.reset()
        mClearPrevious = true
        cancelPending()
        requestBehanceUsers()
    }
}
