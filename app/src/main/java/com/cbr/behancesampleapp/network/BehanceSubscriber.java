package com.cbr.behancesampleapp.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by dimitrios on 25/08/2017.
 */

public abstract class BehanceSubscriber<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
