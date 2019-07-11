package com.futureworkshops.core.data.database.entity.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HashMapTypeDataConverter {


    @TypeConverter
    fun fromStringMap(value: HashMap<String, String>): String {
        val gson = Gson()
        val type = object : TypeToken<HashMap<String, String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringMap(value: String): HashMap<String, String> {
        val gson = Gson()
        val type = object : TypeToken<HashMap<String, String>>() {}.type
        return gson.fromJson(value, type)
    }

}