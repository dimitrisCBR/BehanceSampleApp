package com.cbr.behancesampleapp.domain.network;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by dimitrios on 25/08/2017.
 */

public abstract class BehanceSubscriber<T> implements Observer<T> {

	private CompositeDisposable mCompositeDisposable;

	public BehanceSubscriber(CompositeDisposable compositeDisposable) {
		this.mCompositeDisposable = compositeDisposable;
	}

	@Override
	public void onSubscribe(Disposable d) {
		mCompositeDisposable.add(d);
	}

	@Override
	public void onComplete() {

	}
}
