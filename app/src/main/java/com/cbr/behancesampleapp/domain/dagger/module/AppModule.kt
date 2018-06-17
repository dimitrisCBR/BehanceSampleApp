package com.cbr.behancesampleapp.domain.dagger.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import com.cbr.behancesampleapp.domain.network.scheduler.AppSchedulerProvider
import com.cbr.behancesampleapp.util.SharedPrefHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class AppModule(val app: Application) {
    
    @Singleton
    @Provides
    fun bindContext(): Context = app
    
    @Provides
    fun provideRes(): Resources = app.resources
    
    @Provides
    fun provideSharedPrefs() : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
    
    @Provides
    fun provideSharedPrefHelper(sharedPreferences: SharedPreferences): SharedPrefHelper = SharedPrefHelper(sharedPreferences)
    
    @Provides
    @Singleton
    fun provideCompositeDisposable() = CompositeDisposable()
    
    @Provides
    @Singleton
    fun provideSchedulerProvider() = AppSchedulerProvider()
    
}