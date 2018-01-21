package com.cbr.behancesampleapp.ui.common.mvp

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/** Created by Dimitrios on 8/26/2017. */
open class BaseMvpPresenter<VIEW : MvpView>(val mvpView: VIEW) : MvpPresenter<VIEW> {
    private var weakReference: WeakReference<VIEW>? = null
    
    private val view: VIEW?
        get() = weakReference?.get()
    
    private val isViewAttached: Boolean
        get() = weakReference?.get() != null
    
    override fun attachView(view: VIEW) {
        if (!isViewAttached) {
            weakReference = WeakReference(view)
            view.setPresenter(this)
        }
    }
    
    override fun detachView() {
        weakReference?.clear()
        weakReference = null
    }
    
    var compositeDisposable: CompositeDisposable? = null
        private set
    
    override fun subscribe() {
        compositeDisposable = CompositeDisposable()
    }
    
    override fun unsubscribe() {
        cancelPending()
        compositeDisposable!!.dispose()
    }
    
    fun cancelPending() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.clear()
        }
    }
}
