package com.cbr.behance.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel() : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}