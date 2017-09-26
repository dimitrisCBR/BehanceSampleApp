package com.cbr.behancesampleapp.ui.userdetails.mvp;

import com.cbr.behancesampleapp.domain.model.BehanceUser;
import com.cbr.behancesampleapp.ui.common.mvp.MvpPresenter;
import com.cbr.behancesampleapp.ui.common.mvp.MvpView;

/**
 * Created by Dimitrios on 8/27/2017.
 */

public interface UserDetailsContract {

	interface View extends MvpView {

		void onUserFetched(BehanceUser user);

		void showError();
	}

	interface Presenter extends MvpPresenter {

		void fetchUserById(long userId);
	}
}
