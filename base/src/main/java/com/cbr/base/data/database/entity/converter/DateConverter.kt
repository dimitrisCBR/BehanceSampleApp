package com.cbr.base.data.database.entity.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {


    @TypeConverter
    fun fromLong(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun toLong(date: Date): Long {
        return date.time
    }

}