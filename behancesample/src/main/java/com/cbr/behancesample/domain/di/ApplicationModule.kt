package com.cbr.behancesample.domain.di

import android.app.Application
import android.arch.persistence.room.Room
import com.futureworkshops.domain.database.RoomDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dimitrios on 12/10/2018.
 */

@Module
class ApplicationModule(val application: Application) {

    @Singleton
    @Provides
    fun providesContext() = application.applicationContext

    @Singleton
    @Provides
    fun roomDatabase(): RoomDB =
            Room.databaseBuilder(application, RoomDB::class.java, "behance.db")
                    .fallbackToDestructiveMigration()
                    .build()
}