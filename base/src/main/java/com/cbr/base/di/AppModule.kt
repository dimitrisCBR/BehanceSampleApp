package com.cbr.base.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.cbr.base.data.database.RoomDB
import com.cbr.base.data.database.dao.ProjectDAO
import com.cbr.base.data.database.dao.UserDAO
import com.cbr.base.data.scheduler.SchedulersProvider
import com.cbr.base.data.scheduler.WorkerSchedulerProvider
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
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun userDAO(roomDB: RoomDB): UserDAO = roomDB.userDao()

    @Singleton
    @Provides
    fun projectDAO(roomDB: RoomDB): ProjectDAO = roomDB.projectDao()

}

