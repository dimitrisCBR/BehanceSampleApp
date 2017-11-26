package com.cbr.behancesampleapp.domain.network;

import com.cbr.behancesampleapp.BuildConfig;
import com.cbr.behancesampleapp.domain.model.BehanceSinlgeUserReponse;
import com.cbr.behancesampleapp.domain.model.BehanceUserResponse;

import java.util.Map;

import io.reactivex.Observable;

/** Created by dimitrios on 25/08/2017.*/
public class BehanceRepository extends DataRepository {

	private BehanceApiService mBehanceApiService;

	public BehanceRepository(BehanceApiService service) {
		this.mBehanceApiService = service;
	}

	public Observable<BehanceUserResponse> getUsers(Map<String, Object> params) {
		return mBehanceApiService.getUsers(params).compose(this.<BehanceUserResponse>applySchedulers());
	}

	public Observable<BehanceSinlgeUserReponse> getUserById(String userId) {
		return this.mBehanceApiService.getUserById(userId, BuildConfig.API_KEY).compose(this.<BehanceSinlgeUserReponse>applySchedulers());
	}

}