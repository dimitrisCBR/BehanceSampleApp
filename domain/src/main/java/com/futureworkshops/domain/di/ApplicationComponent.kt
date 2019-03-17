package com.futureworkshops.domain.di

import android.content.Context
import com.futureworkshops.domain.data.database.dao.ProjectDAO
import com.futureworkshops.domain.data.database.dao.UserDAO
import com.futureworkshops.domain.data.network.BehanceApiService
import com.futureworkshops.domain.scheduler.SchedulersProvider
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