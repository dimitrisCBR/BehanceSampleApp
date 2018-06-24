package com.cbr.behancesampleapp.domain.network

import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/** Created by dimitrios on 25/08/2017. */
abstract class BehanceSubscriber<T>(private val compositeDisposable: CompositeDisposable) : Observer<T> {

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onComplete() {
        /* Override if you need this */
    }
}
