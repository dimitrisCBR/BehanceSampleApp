package com.cbr.behancesampleapp.ui.landing.mvp

import com.cbr.behancesampleapp.domain.model.BehanceUser
import com.cbr.behancesampleapp.ui.common.mvp.MvpPresenter
import com.cbr.behancesampleapp.ui.common.mvp.MvpView

/** Created by Dimitrios on 8/26/2017. */
interface LandingActivityContract {
    
    interface View : MvpView {
        
        fun onUsersFetched(behanceUser: List<BehanceUser>, clearPrevious: Boolean)
        
        fun showError()
    }
    
    interface Presenter : MvpPresenter<View> {
        
        fun requestBehanceUsers()
        
        fun refresh()
    }
}
