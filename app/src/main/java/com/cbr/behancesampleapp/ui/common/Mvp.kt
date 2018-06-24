package com.cbr.behancesampleapp.ui.common

interface MvpView

interface MvpPresenter<V : MvpView> {

    fun attachView(view : V)

    fun onSubscribe()

    fun onUnsubscribe()

}