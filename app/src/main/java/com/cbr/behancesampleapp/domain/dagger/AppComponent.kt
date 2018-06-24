package com.cbr.behancesampleapp.domain.dagger

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.cbr.behancesampleapp.domain.dagger.module.AppModule
import com.cbr.behancesampleapp.domain.dagger.module.NetModule
import com.cbr.behancesampleapp.domain.network.BehanceApiService
import com.cbr.behancesampleapp.domain.network.repository.BehanceRepository
import com.cbr.behancesampleapp.domain.network.scheduler.AppSchedulerProvider
import com.cbr.behancesampleapp.util.SharedPrefHelper
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetModule::class)])
interface AppComponent {
    
    fun context(): Context
    
    fun resources(): Resources
    
    fun sharedPrefs(): SharedPreferences
    
    fun sharedPrefsHelper(): SharedPrefHelper
    
    fun compositeDisposable(): CompositeDisposable
    
    fun schedulerProvider(): AppSchedulerProvider
    
    fun retrofitService(): BehanceApiService
    
    fun behanceRepository(): BehanceRepository
}
