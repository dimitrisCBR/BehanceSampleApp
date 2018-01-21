package com.cbr.behancesampleapp.ui.userdetails.mvp

import com.cbr.behancesampleapp.domain.model.BehanceUser
import com.cbr.behancesampleapp.ui.common.mvp.MvpPresenter
import com.cbr.behancesampleapp.ui.common.mvp.MvpView

/** Created by Dimitrios on 8/27/2017. */
interface UserDetailsContract {
    
    interface View : MvpView {
        
        fun onUserFetched(user: BehanceUser)
        
        fun showError()
    }
    
    interface Presenter : MvpPresenter<View> {
        
        fun fetchUserById(userId: Long)
    }
}
