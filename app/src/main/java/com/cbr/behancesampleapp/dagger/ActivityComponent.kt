package com.cbr.behancesampleapp.dagger

import com.cbr.behancesampleapp.ui.landing.LandingActivity
import com.cbr.behancesampleapp.ui.landing.dagger.LandingActivityModule
import com.cbr.behancesampleapp.ui.userdetails.UserDetailsActivity
import com.cbr.behancesampleapp.ui.userdetails.dagger.UserDetailsActivityModule
import dagger.Subcomponent

/** Created by Dimitrios on 1/20/2018.*/
@Subcomponent(modules = arrayOf(LandingActivityModule::class, UserDetailsActivityModule::class))
interface ActivityComponent {
    
    fun inject(landingActivity: LandingActivity)
    
    fun inject(userDetailsActivity: UserDetailsActivity)
}