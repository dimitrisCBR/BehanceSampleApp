package com.cbr.behancesampleapp.ui.common.mvp

import com.cbr.behancesampleapp.ui.common.MvpPresenter
import com.cbr.behancesampleapp.ui.common.MvpView
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

open class BasePresenter<VIEW : MvpView> constructor(val compositeDisposable: CompositeDisposable) : MvpPresenter<VIEW> {

    lateinit var weakReference: WeakReference<VIEW>

    val view: VIEW?
        get() = weakReference.get()

    override fun attachView(view: VIEW) {
        weakReference = WeakReference(view)
    }

    override fun onSubscribe() {

    }


    override fun onUnsubscribe() {
        cancelPending()
    }

    fun cancelPending() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

}