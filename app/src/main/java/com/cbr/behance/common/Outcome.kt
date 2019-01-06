package com.cbr.behance.common

sealed class Outcome<T> {

    data class Progress<T>(var nothing: T?) : Outcome<T>()
    data class Success<T>(var data: T) : Outcome<T>()
    data class Error<T>(val errorMsg: String) : Outcome<T>()

    companion object {
        fun <T> loading(): Outcome<T> = Progress(null)

        fun <T> success(data: T): Outcome<T> = Success(data)

        fun <T> error(msg: String): Outcome<T> = Error(msg)
    }
}