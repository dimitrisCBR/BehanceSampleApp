package com.cbr.behancesampleapp.domain.network

import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/** Created by dimitrios on 25/08/2017. */
abstract class BehanceSubscriber<T>(private val mCompositeDisposable: CompositeDisposable) : Observer<T> {
    
    override fun onSubscribe(d: Disposable) {
        mCompositeDisposable.add(d)
    }
    
    override fun onComplete() {
    
    }
}
