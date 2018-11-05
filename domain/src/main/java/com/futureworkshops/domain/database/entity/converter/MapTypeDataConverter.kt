package com.futureworkshops.domain.database.entity.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by dimitrios on 03/11/2018.
 */
const val KEY_KEYS = "keys"
const val KEY_VALUES = "values"

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