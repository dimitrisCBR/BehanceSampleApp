package com.cbr.behancesampleapp.ui.userdetails

import com.cbr.behancesampleapp.domain.dagger.AppComponent
import com.cbr.behancesampleapp.domain.dagger.scope.ActivityScope
import com.cbr.behancesampleapp.domain.network.repository.BehanceRepository
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class UserDetailsActivityModule(val activity: UserDetailsActivity) {
    
    @Provides
    fun bindActivity(): UserDetailsContract.View = activity
    
    @Provides
    fun providePresenter(view: UserDetailsContract.View, repository: BehanceRepository): UserDetailsContract.Presenter {
        return UserDetailsPresenter(view, repository)
    }
}

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(UserDetailsActivityModule::class)])
interface UserDetailsComponent {
    
    fun inject(userDetailsActivity: UserDetailsActivity)
}