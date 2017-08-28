package com.cbr.behancesampleapp.ui.userdetails;

import com.cbr.behancesampleapp.model.BehanceSinlgeUserReponse;
import com.cbr.behancesampleapp.model.BehanceUser;
import com.cbr.behancesampleapp.mvp.BaseMvpPresenter;
import com.cbr.behancesampleapp.network.BehanceRepository;
import com.cbr.behancesampleapp.network.BehanceSubscriber;
import com.cbr.behancesampleapp.ui.userdetails.mvp.UserDetailsContract;

/**
 * Created by Dimitrios on 8/27/2017.
 */

public class UserDetailsPresenter extends BaseMvpPresenter<UserDetailsContract.View> implements UserDetailsContract.Presenter {

	private BehanceRepository mBehanceRepository;
	private long mUserId;
	private BehanceUser mBehanceUser;

	public UserDetailsPresenter(BehanceRepository behanceRepository, long userIdFromIntent) {
		this.mBehanceRepository = behanceRepository;
		this.mUserId = userIdFromIntent;
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
	public void refresh() {
		mBehanceRepository.getUserById(String.valueOf(mUserId))
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
