package com.cbr.behancesampleapp.ui.common.mvp

/** Created by dimitrios on 24/08/2017. */
interface MvpView {
    
    fun setPresenter(presenter: MvpPresenter<*>)
    
}