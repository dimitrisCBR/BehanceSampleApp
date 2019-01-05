package com.futureworkshops.domain.data.database.entity.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapTypeDataConverter {


    @TypeConverter
    fun fromStringMap(value: Map<String, String>): String {
        val gson = Gson()
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringMap(value: String): Map<String, String> {
        val gson = Gson()
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(value, type)
    }

}