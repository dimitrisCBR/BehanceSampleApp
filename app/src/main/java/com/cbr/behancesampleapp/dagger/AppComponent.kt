package com.cbr.behancesampleapp.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.cbr.behancesampleapp.dagger.module.AppModule
import com.cbr.behancesampleapp.dagger.module.NetModule
import com.cbr.behancesampleapp.domain.network.BehanceApiService
import com.cbr.behancesampleapp.domain.network.BehanceRepository
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetModule::class)])
interface AppComponent {
    
    fun context(): Context
    
    fun resources(): Resources
    
    fun sharedPrefs(): SharedPreferences
    
    fun cache(): Cache
    
    fun okHttpInterceptor(): Interceptor
    
    fun okHttpClient(): OkHttpClient
    
    fun retrofit(): Retrofit
    
    fun behanceService(): BehanceApiService
    
    fun behanceRepository(): BehanceRepository
}
