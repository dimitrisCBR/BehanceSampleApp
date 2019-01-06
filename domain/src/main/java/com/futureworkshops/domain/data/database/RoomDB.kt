package com.futureworkshops.domain.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.futureworkshops.domain.data.database.dao.ProjectDAO
import com.futureworkshops.domain.data.database.dao.UserDAO
import com.futureworkshops.domain.data.database.entity.ProjectEntity
import com.futureworkshops.domain.data.database.entity.UserEntity
import com.futureworkshops.domain.data.database.entity.converter.HashMapTypeDataConverter
import com.futureworkshops.domain.data.database.entity.converter.ListTypeDataConverter
import com.futureworkshops.domain.data.database.entity.converter.MapTypeDataConverter


@Database(
        entities = [
            UserEntity::class,
            ProjectEntity::class
        ],
        version = 1,
        exportSchema = false
)
@TypeConverters(value = [
    (ListTypeDataConverter::class),
    (MapTypeDataConverter::class),
    (HashMapTypeDataConverter::class)
])
abstract class RoomDB : RoomDatabase() {

    abstract fun userDao(): UserDAO

    abstract fun projectDao(): ProjectDAO

}