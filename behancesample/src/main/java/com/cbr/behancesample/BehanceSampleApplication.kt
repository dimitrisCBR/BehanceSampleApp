package com.cbr.behancesample

import android.app.Application

import com.futureworkshops.domain.di.AppModule
import com.futureworkshops.domain.di.ApplicationComponent
import com.futureworkshops.domain.di.DaggerApplicationComponent
import timber.log.Timber

class BehanceSampleApplication : Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimberLogging()
    }

    private fun initTimberLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initDagger() {
        appComponent = DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}
