package com.cbr.behancesampleapp.ui.user

import com.cbr.behancesampleapp.domain.dagger.AppComponent
import com.cbr.behancesampleapp.domain.dagger.scope.FragmentScope
import com.cbr.behancesampleapp.domain.network.repository.BehanceRepository
import com.cbr.behancesampleapp.ui.user.details.UserDetailsActivity
import com.cbr.behancesampleapp.ui.user.list.UserListFragment
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    @FragmentScope
    fun providesUserInteractor(repository: BehanceRepository): UserInteractor {
        return UserInteractor(repository)
    }

}

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [UserModule::class])
interface UserComponent {

    fun inject(userListFragment: UserListFragment)

    fun inject(userDetailsActivity: UserDetailsActivity)
}