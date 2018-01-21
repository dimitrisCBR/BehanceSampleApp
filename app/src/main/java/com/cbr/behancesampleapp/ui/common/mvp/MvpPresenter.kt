package com.cbr.behancesampleapp.ui.common.mvp

/** Created by dimitrios on 24/08/2017.  */
interface MvpPresenter<in VIEW : MvpView> {
    
    fun attachView(view : VIEW)
    
    fun detachView()
    
    fun subscribe()
    
    fun unsubscribe()
    
}
