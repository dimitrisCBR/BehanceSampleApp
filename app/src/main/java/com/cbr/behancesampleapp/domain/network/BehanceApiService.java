package com.cbr.behancesampleapp.domain.network;

import com.cbr.behancesampleapp.domain.model.BehanceSinlgeUserReponse;
import com.cbr.behancesampleapp.domain.model.BehanceUserResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by dimitrios on 24/08/2017.
 */

public interface BehanceApiService {

	@GET(Urls.Behance.Users.GET_USERS)
	Observable<BehanceUserResponse> getUsers(@QueryMap Map<String, Object> queryParams);

	@GET(Urls.Behance.Users.GET_USER_BY_ID)
	Observable<BehanceSinlgeUserReponse> getUserById(@Path(Urls.Params.PARAM_ID) String id, @Query(Urls.Params.PARAM_API_KEY) String apiKey);

}
