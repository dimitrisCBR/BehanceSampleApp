package com.futureworkshops.domain

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AppSchedulers {

    val mainThread = AndroidSchedulers.mainThread()

    val io = Schedulers.io()
}