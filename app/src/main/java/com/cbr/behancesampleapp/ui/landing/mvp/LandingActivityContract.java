package com.cbr.behancesampleapp.ui.landing.mvp;

import com.cbr.behancesampleapp.model.BehanceUser;
import com.cbr.behancesampleapp.mvp.MvpPresenter;
import com.cbr.behancesampleapp.mvp.MvpView;

import java.util.List;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public interface LandingActivityContract {

	interface View extends MvpView<Presenter> {

		void onUsersFetched(List<BehanceUser> behanceUser);

		void showError();
	}

	interface Presenter extends MvpPresenter<View> {

		void requestBehanceUsers();
	}
}
