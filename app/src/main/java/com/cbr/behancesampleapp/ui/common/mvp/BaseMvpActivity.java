package com.cbr.behancesampleapp.ui.common.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/** Created by Dimitrios on 8/26/2017.*/
public abstract class BaseMvpActivity<PRESENTER extends MvpPresenter> extends AppCompatActivity implements MvpView {

	@Inject
	PRESENTER mPresenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

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
	protected void onResume() {
		super.onResume();
		getPresenter().subscribe();
	}

	@Override
	protected void onPause() {
		super.onPause();
		getPresenter().unsubscribe();
	}
}
