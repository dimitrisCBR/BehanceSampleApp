package com.cbr.base.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cbr.base.data.database.dao.ProjectDAO
import com.cbr.base.data.database.dao.UserDAO
import com.cbr.base.data.database.entity.ProjectEntity
import com.cbr.base.data.database.entity.UserEntity
import com.cbr.base.data.database.entity.converter.DateConverter
import com.cbr.base.data.database.entity.converter.HashMapTypeDataConverter
import com.cbr.base.data.database.entity.converter.ListTypeDataConverter
import com.cbr.base.data.database.entity.converter.MapTypeDataConverter


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
    (DateConverter::class),
    (HashMapTypeDataConverter::class)
])
abstract class RoomDB : RoomDatabase() {

    abstract fun userDao(): UserDAO

    abstract fun projectDao(): ProjectDAO

}