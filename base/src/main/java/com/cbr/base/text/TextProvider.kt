package com.cbr.base.text

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject


class TextProvider
@Inject
constructor(private val context: Context) {

    fun getText(@StringRes res: Int, vararg args: Any): String {
        return context.getString(res, *args)
    }

    fun getFormattedString(@StringRes rest: Int, value: Int): String {
        return context.getString(rest, value)
    }

    fun getFormattedString(@StringRes rest: Int, value: Long): String {
        return context.getString(rest, value)
    }
}