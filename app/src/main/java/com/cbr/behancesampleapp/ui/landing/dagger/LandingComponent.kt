package com.cbr.behancesampleapp.ui.landing.dagger

import com.cbr.behancesampleapp.dagger.AppComponent
import com.cbr.behancesampleapp.dagger.scope.ActivityScope
import com.cbr.behancesampleapp.ui.landing.LandingActivity
import dagger.Component

/** Created by Dimitrios on 1/21/2018.*/
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(LandingActivityModule::class))
interface LandingComponent {
    
    fun inject(activity: LandingActivity)
}