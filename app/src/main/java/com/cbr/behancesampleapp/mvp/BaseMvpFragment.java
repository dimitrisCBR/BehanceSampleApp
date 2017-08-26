package com.cbr.behancesampleapp.mvp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public abstract class BaseMvpFragment<PRESENTER extends MvpPresenter> extends Fragment implements MvpView<PRESENTER> {

	private PRESENTER mPresenter;

	private void setPresenter(PRESENTER presenter) {
		this.mPresenter = presenter;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		setPresenter(getPresenter());
		return view;
	}

	public PRESENTER getPresenter() {
		if (mPresenter == null) {
			mPresenter = createPresenter();
		}
		return mPresenter;
	}

}
