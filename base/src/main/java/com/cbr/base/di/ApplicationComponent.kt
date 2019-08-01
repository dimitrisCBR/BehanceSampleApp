package com.cbr.base.di

import android.content.Context
import com.cbr.base.data.database.dao.ProjectDAO
import com.cbr.base.data.database.dao.UserDAO
import com.cbr.base.data.network.BehanceApiService
import com.cbr.base.data.scheduler.SchedulersProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun context(): Context

    fun apiService(): BehanceApiService

    fun schedulers(): SchedulersProvider

    fun userDao(): UserDAO

    fun projectDao(): ProjectDAO
}