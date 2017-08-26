package com.cbr.behancesampleapp.ui.landing;

import com.cbr.behancesampleapp.model.BehanceUserResponse;
import com.cbr.behancesampleapp.mvp.BaseMvpPresenter;
import com.cbr.behancesampleapp.network.BehanceRepository;
import com.cbr.behancesampleapp.network.BehanceSubscriber;
import com.cbr.behancesampleapp.network.query.UsersQuery;
import com.cbr.behancesampleapp.ui.landing.mvp.LandingActivityContract;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public class LandingActivityPresenter extends BaseMvpPresenter<LandingActivityContract.View> implements LandingActivityContract.Presenter {

	private BehanceRepository mBehanceRepository;

	public LandingActivityPresenter(BehanceRepository behanceRepository) {
		this.mBehanceRepository = behanceRepository;
	}

	@Override
	public void subscribe() {
		super.subscribe();

	}

	@Override
	public void unsubscribe() {
		super.unsubscribe();
	}

	@Override
	public void requestBehanceUsers() {
		cancelPending();
		mBehanceRepository.getUsers(new UsersQuery().build())
			.subscribe(new BehanceSubscriber<BehanceUserResponse>(getCompositeDisposable()) {
				@Override
				public void onNext(BehanceUserResponse listResponse) {
					if (listResponse != null && !listResponse.getUsers().isEmpty()) {
						getMvpView().onUsersFetched(listResponse.getUsers());
					} else {
						getMvpView().showError();
					}
				}

				@Override
				public void onError(Throwable e) {
					getMvpView().showError();
				}
			});
	}
}
