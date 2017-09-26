package com.cbr.behancesampleapp.ui.userdetails;

import com.cbr.behancesampleapp.domain.model.BehanceSinlgeUserReponse;
import com.cbr.behancesampleapp.domain.model.BehanceUser;
import com.cbr.behancesampleapp.domain.network.BehanceRepository;
import com.cbr.behancesampleapp.domain.network.BehanceSubscriber;
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpPresenter;
import com.cbr.behancesampleapp.ui.userdetails.mvp.UserDetailsContract;

import javax.inject.Inject;

/**
 * Created by Dimitrios on 8/27/2017.
 */

public class UserDetailsPresenter extends BaseMvpPresenter<UserDetailsContract.View> implements UserDetailsContract.Presenter {

	private BehanceRepository mBehanceRepository;
	private BehanceUser mBehanceUser;

	@Inject
	public UserDetailsPresenter(UserDetailsContract.View view, BehanceRepository behanceRepository) {
		super(view);
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
	public void fetchUserById(long userId) {
		mBehanceRepository.getUserById(String.valueOf(userId))
			.subscribe(new BehanceSubscriber<BehanceSinlgeUserReponse>(getCompositeDisposable()) {
				@Override
				public void onNext(BehanceSinlgeUserReponse response) {
					if (response != null) {
						mBehanceUser = response.getUser();
						getMvpView().onUserFetched(mBehanceUser);
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
