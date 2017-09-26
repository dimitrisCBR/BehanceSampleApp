package com.cbr.behancesampleapp.ui.common.mvp;

import android.app.Fragment;

import javax.inject.Inject;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public abstract class BaseMvpFragment<PRESENTER extends MvpPresenter> extends Fragment implements MvpView {

	@Inject
	PRESENTER mPresenter;

	public PRESENTER getPresenter() {
		if (mPresenter == null) {
			throw new RuntimeException("You forgot to inject or set your Presenter :(");
		}
		return mPresenter;
	}

	public void setPresenter(PRESENTER presenter) {
		this.mPresenter = presenter;
	}


	@Override
	public void onResume() {
		super.onResume();
		getPresenter().subscribe();
	}

	@Override
	public void onPause() {
		super.onPause();
		getPresenter().unsubscribe();
	}

}
