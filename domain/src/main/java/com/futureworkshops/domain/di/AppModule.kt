package com.futureworkshops.domain.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.futureworkshops.domain.data.database.RoomDB
import com.futureworkshops.domain.data.database.dao.ProjectDAO
import com.futureworkshops.domain.data.database.dao.UserDAO
import com.futureworkshops.domain.data.scheduler.SchedulersProvider
import com.futureworkshops.domain.data.scheduler.WorkerSchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun schedulers(): SchedulersProvider = WorkerSchedulerProvider()

    @Provides
    @Singleton
    fun roomDB() = Room
            .databaseBuilder(application, RoomDB::class.java, "room")
            .build()

    @Singleton
    @Provides
    fun userDAO(roomDB: RoomDB): UserDAO = roomDB.userDao()

    @Singleton
    @Provides
    fun projectDAO(roomDB: RoomDB): ProjectDAO = roomDB.projectDao()

}

