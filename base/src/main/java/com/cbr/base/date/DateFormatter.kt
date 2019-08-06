package com.cbr.base.date

import android.content.Context
import com.cbr.base.text.TextProvider
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateFormatter @Inject constructor(val context: Context) {

    val textProvider = TextProvider(context)

    fun formatFullDate(date: Date): String {
        val formatter = SimpleDateFormat(fullDateFormat, Locale.getDefault())
        return formatter.format(date)
    }

    companion object {
        private const val fullDateFormat = "MMM dd, yyyy"
    }
}