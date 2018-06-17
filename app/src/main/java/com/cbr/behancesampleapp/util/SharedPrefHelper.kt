package com.cbr.behancesampleapp.util

import android.content.SharedPreferences

class SharedPrefHelper constructor(var sharedPreferences: SharedPreferences) {
    
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()
    
    @Synchronized
    fun <T> getDataFromSharedPref(dataType: Class<T>, key: String): Any? =
            when (dataType) {
                Int::class.java -> sharedPreferences.getInt(key, 0)
                String::class.java -> sharedPreferences.getString(key, null)
                Long::class.java -> sharedPreferences.getLong(key, 0)
                Boolean::class.java -> sharedPreferences.getBoolean(key, false)
                else -> null
            }
    
    @Synchronized
    fun <T> putDatatoSharedPref(data: T, dataType: Class<*>, key: String) {
        when (dataType) {
            Int::class.java -> editor.putInt(key, data as Int)
            String::class.java -> editor.putString(key, data as String)
            Long::class.java -> editor.putLong(key, data as Long)
            Boolean::class.java -> editor.putBoolean(key, data as Boolean)
        }
        editor.commit()
    }
}