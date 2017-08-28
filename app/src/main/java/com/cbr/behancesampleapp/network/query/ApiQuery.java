package com.cbr.behancesampleapp.network.query;

import com.cbr.behancesampleapp.BuildConfig;
import com.cbr.behancesampleapp.network.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dimitrios on 25/08/2017.
 */

public class ApiQuery {

	public Map<String, Object> mQueryParams;

	public ApiQuery() {
		mQueryParams = new HashMap<>();
		mQueryParams.put(Urls.Params.PARAM_API_KEY, BuildConfig.API_KEY);
	}

	public Map<String, Object> build() {
		return mQueryParams;
	}

}
