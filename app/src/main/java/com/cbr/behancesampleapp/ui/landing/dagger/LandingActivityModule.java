package com.cbr.behancesampleapp.ui.landing.dagger;

import com.cbr.behancesampleapp.domain.network.BehanceRepository;
import com.cbr.behancesampleapp.ui.landing.LandingActivity;
import com.cbr.behancesampleapp.ui.landing.LandingActivityPresenter;
import com.cbr.behancesampleapp.ui.landing.mvp.LandingActivityContract;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Dimitrios on 8/26/2017.
 */
@Module
public abstract class LandingActivityModule {

	@Binds
	abstract LandingActivityContract.View bindMvpView(LandingActivity activity);

	@Provides
	static LandingActivityContract.Presenter providePresenter(LandingActivityContract.View view, BehanceRepository repository) {
		return new LandingActivityPresenter(view, repository);
	}
}
