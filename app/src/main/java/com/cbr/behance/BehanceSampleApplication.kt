package com.cbr.behance

import android.app.Application
import com.cbr.base.di.AppModule
import com.cbr.base.di.ApplicationComponent
import com.cbr.base.di.DaggerApplicationComponent
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
