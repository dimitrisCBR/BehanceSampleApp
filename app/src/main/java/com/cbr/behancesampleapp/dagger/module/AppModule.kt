package com.cbr.behancesampleapp.dagger.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
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
}