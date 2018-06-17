package com.cbr.behancesampleapp.ui.landing

import com.cbr.behancesampleapp.domain.dagger.AppComponent
import com.cbr.behancesampleapp.domain.dagger.scope.ActivityScope
import com.cbr.behancesampleapp.domain.network.repository.BehanceRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LandingActivityModule(val activity: LandingActivity) {
    
    @Provides
    fun bindMvpView(): LandingActivityContract.View = activity
    
    @Provides
    @Singleton
    fun providePresenter(view: LandingActivityContract.View, repository: BehanceRepository): LandingActivityContract.Presenter = LandingActivityPresenter(view, repository)
}

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(LandingActivityModule::class)])
interface LandingComponent {
    
    fun inject(activity: LandingActivity)
}