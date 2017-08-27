package com.cbr.behancesampleapp.ui.userdetails.mvp;

import com.cbr.behancesampleapp.mvp.MvpPresenter;
import com.cbr.behancesampleapp.mvp.MvpView;

/**
 * Created by Dimitrios on 8/27/2017.
 */

public interface UserDetailsContract {

	interface View extends MvpView<Presenter> {

	}

	interface Presenter extends MvpPresenter<View> {

	}
}
