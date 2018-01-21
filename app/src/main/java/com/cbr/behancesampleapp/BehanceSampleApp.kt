package com.cbr.behancesampleapp

import android.app.Application
import com.cbr.behancesampleapp.dagger.AppComponent
import com.cbr.behancesampleapp.dagger.DaggerAppComponent
import com.cbr.behancesampleapp.dagger.module.AppModule
import com.cbr.behancesampleapp.dagger.module.NetModule

/** Created by Dimitrios on 8/26/2017.  */
class BehanceSampleApp : Application() {
    
    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }
    
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .build()
    }
    
}