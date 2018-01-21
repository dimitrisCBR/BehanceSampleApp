package com.cbr.behancesampleapp.ui.userdetails.dagger

import com.cbr.behancesampleapp.dagger.AppComponent
import com.cbr.behancesampleapp.dagger.scope.ActivityScope
import com.cbr.behancesampleapp.ui.userdetails.UserDetailsActivity
import dagger.Component

/** Created by Dimitrios on 1/21/2018.*/
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(UserDetailsActivityModule::class))
interface UserDetailsComponent {
    
    fun inject(userDetailsActivity: UserDetailsActivity)
}