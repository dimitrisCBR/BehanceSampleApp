package com.cbr.behancesampleapp.ui.userdetails.dagger;

import com.cbr.behancesampleapp.domain.network.BehanceRepository;
import com.cbr.behancesampleapp.ui.userdetails.UserDetailsActivity;
import com.cbr.behancesampleapp.ui.userdetails.UserDetailsPresenter;
import com.cbr.behancesampleapp.ui.userdetails.mvp.UserDetailsContract;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/** Created by Dimitrios on 8/26/2017.*/
@Module
public abstract class UserDetailsActivityModule {

	@Binds
	abstract UserDetailsContract.View bindActivity(UserDetailsActivity activity);

	@Provides
	static UserDetailsContract.Presenter providePresenter(UserDetailsContract.View view, BehanceRepository repository) {
		return new UserDetailsPresenter(view, repository);
	}
}
