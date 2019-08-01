package com.cbr.behance.user.di

import com.cbr.base.di.ApplicationComponent
import com.cbr.behance.user.list.UserListFragment
import dagger.Component


@UserScope
@Component(dependencies = [ApplicationComponent::class])
interface UserComponent {

    fun inject(userFragment: UserListFragment)

}