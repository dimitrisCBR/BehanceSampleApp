package com.futureworkshops.domain.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.futureworkshops.domain.database.dao.ProjectDAO
import com.futureworkshops.domain.database.dao.UserDAO
import com.futureworkshops.domain.database.entity.ProjectEntity
import com.futureworkshops.domain.database.entity.UserEntity
import com.futureworkshops.domain.database.entity.converter.ListTypeDataConverter
import com.futureworkshops.domain.database.entity.converter.MapTypeDataConverter

/**
 * Created by dimitrios on 12/10/2018.
 */

@Database(
        entities = [
            UserEntity::class,
            ProjectEntity::class
        ],
        version = 1,
        exportSchema = false
)
@TypeConverters(value = [(ListTypeDataConverter::class), (MapTypeDataConverter::class)])
abstract class RoomDB : RoomDatabase() {

    abstract fun userDao(): UserDAO

    abstract fun projectDao(): ProjectDAO

}