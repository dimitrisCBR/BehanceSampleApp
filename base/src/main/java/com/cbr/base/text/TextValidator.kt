package com.cbr.base.text

import android.util.Patterns
import java.util.regex.Pattern
import javax.inject.Inject


class TextValidator @Inject constructor() {

    companion object {

        const val PASSWORD_REGEX = "^(?=\\S+\$).{3,}\$"
    }

    private val passwordPattern = Pattern.compile(PASSWORD_REGEX)


    fun isEmailValid(input: String): Boolean {
        return input.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }

    fun isPasswordValid(input: String): Boolean {
        return input.isNotEmpty() && passwordPattern.matcher(input).matches()
    }

}