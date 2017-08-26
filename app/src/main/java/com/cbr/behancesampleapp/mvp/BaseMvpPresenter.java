package com.cbr.behancesampleapp.mvp;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public abstract class BaseMvpPresenter<VIEW extends MvpView> implements MvpPresenter {

	private VIEW mMvpView;

	public VIEW getMvpView(){
		return mMvpView;
	}

	

}
