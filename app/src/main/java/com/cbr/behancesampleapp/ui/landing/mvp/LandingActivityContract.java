package com.cbr.behancesampleapp.ui.landing.mvp;

import com.cbr.behancesampleapp.domain.model.BehanceUser;
import com.cbr.behancesampleapp.ui.common.mvp.MvpPresenter;
import com.cbr.behancesampleapp.ui.common.mvp.MvpView;

import java.util.List;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public interface LandingActivityContract {

	interface View extends MvpView {

		void onUsersFetched(List<BehanceUser> behanceUser, boolean clearPrevious);

		void showError();
	}

	interface Presenter extends MvpPresenter {

		void requestBehanceUsers();

		void refresh();
	}
}
