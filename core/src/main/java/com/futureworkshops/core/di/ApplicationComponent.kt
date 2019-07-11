package com.futureworkshops.core.di

import android.content.Context
import com.futureworkshops.core.data.database.dao.ProjectDAO
import com.futureworkshops.core.data.database.dao.UserDAO
import com.futureworkshops.core.data.network.BehanceApiService
import com.futureworkshops.core.data.scheduler.SchedulersProvider
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