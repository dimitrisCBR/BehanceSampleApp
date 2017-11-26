package com.cbr.behancesampleapp.ui.common.mvp;

import io.reactivex.disposables.CompositeDisposable;

/** Created by Dimitrios on 8/26/2017.*/
public abstract class BaseMvpPresenter<VIEW extends MvpView> implements MvpPresenter {

	private CompositeDisposable mCompositeDisposable;

	private VIEW mMvpView;

	public BaseMvpPresenter(VIEW view) {
		this.mMvpView = view;
	}

	public VIEW getMvpView() {
		return mMvpView;
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
