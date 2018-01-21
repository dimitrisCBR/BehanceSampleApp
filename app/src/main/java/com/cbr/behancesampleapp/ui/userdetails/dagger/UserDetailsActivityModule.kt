package com.cbr.behancesampleapp.ui.userdetails.dagger

import com.cbr.behancesampleapp.domain.network.BehanceRepository
import com.cbr.behancesampleapp.ui.userdetails.UserDetailsActivity
import com.cbr.behancesampleapp.ui.userdetails.UserDetailsPresenter
import com.cbr.behancesampleapp.ui.userdetails.mvp.UserDetailsContract
import dagger.Module
import dagger.Provides

/** Created by Dimitrios on 8/26/2017. */
@Module
class UserDetailsActivityModule(val activity: UserDetailsActivity) {
    
    @Provides
    fun bindActivity(): UserDetailsContract.View = activity
    
    @Provides
    fun providePresenter(view: UserDetailsContract.View, repository: BehanceRepository): UserDetailsContract.Presenter {
        return UserDetailsPresenter(view, repository)
    }
}
