package com.cbr.behancesample.ui.user.di

import com.cbr.behancesample.ui.user.UserListFragment
import com.futureworkshops.domain.di.AppModule
import com.futureworkshops.domain.di.ApplicationComponent
import dagger.Component


@UserScope
@Component(dependencies = [ApplicationComponent::class])
interface UserComponent {

    fun inject(userFragment: UserListFragment)

}