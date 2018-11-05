package com.cbr.behancesample

import android.app.Application
import com.cbr.behancesample.domain.di.ApplicationComponent
import com.cbr.behancesample.domain.di.ApplicationModule
import com.cbr.behancesample.domain.di.DaggerApplicationComponent

/**
 * Created by dimitrios on 12/10/2018.
 */

class BehanceSampleApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}
