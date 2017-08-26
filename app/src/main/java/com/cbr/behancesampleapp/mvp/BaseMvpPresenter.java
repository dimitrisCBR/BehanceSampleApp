package com.cbr.behancesampleapp.mvp;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public abstract class BaseMvpPresenter<VIEW extends MvpView> implements MvpPresenter<VIEW> {

	private CompositeDisposable mCompositeDisposable;

	private VIEW mMvpView;

	public VIEW getMvpView() {
		return mMvpView;
	}

	@Override
	public void setView(VIEW view) {
		this.mMvpView = view;
	}

	public CompositeDisposable getCompositeDisposable() {
		return mCompositeDisposable;
	}

	@Override
	public void subscribe() {
		mCompositeDisposable = new CompositeDisposable();
	}

	@Override
	public void unsubscribe() {
		cancelPending();
		mCompositeDisposable.dispose();
	}

	public void cancelPending() {
		if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
			mCompositeDisposable.clear();
		}
	}
}
