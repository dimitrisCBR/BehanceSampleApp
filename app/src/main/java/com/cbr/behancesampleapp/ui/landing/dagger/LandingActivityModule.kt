package com.cbr.behancesampleapp.ui.landing.dagger

import com.cbr.behancesampleapp.domain.network.BehanceRepository
import com.cbr.behancesampleapp.ui.landing.LandingActivity
import com.cbr.behancesampleapp.ui.landing.LandingActivityPresenter
import com.cbr.behancesampleapp.ui.landing.mvp.LandingActivityContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/** Created by Dimitrios on 8/26/2017. */
@Module
class LandingActivityModule(val activity: LandingActivity) {
    
    @Provides
    fun bindMvpView(): LandingActivityContract.View = activity
    
    @Provides
    @Singleton
    fun providePresenter(view: LandingActivityContract.View, repository: BehanceRepository): LandingActivityContract.Presenter = LandingActivityPresenter(view, repository)
}
