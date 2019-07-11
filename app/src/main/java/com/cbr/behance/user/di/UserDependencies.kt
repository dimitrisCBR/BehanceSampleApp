package com.cbr.behance.user.di

import com.cbr.behance.user.list.UserListFragment
import com.futureworkshops.core.di.ApplicationComponent
import dagger.Component


@UserScope
@Component(dependencies = [ApplicationComponent::class])
interface UserComponent {

    fun inject(userFragment: UserListFragment)

}