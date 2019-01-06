package com.cbr.behance.ui.user.di

import com.cbr.behance.ui.user.UserListFragment
import com.futureworkshops.domain.di.ApplicationComponent
import dagger.Component


@UserScope
@Component(dependencies = [ApplicationComponent::class])
interface UserComponent {

    fun inject(userFragment: UserListFragment)

}