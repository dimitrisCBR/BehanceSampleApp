package com.cbr.behancesampleapp.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public abstract class BaseMvpActivity<PRESENTER extends MvpPresenter> extends AppCompatActivity implements MvpView<PRESENTER> {

	private PRESENTER mPresenter;

	private void setPresenter(PRESENTER presenter) {
		this.mPresenter = presenter;
		this.mPresenter.setView(this);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setPresenter(getPresenter());
	}

	public PRESENTER getPresenter() {
		if (mPresenter == null) {
			mPresenter = createPresenter();
		}
		return mPresenter;
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
