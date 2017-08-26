package com.cbr.behancesampleapp.network.query;

import com.cbr.behancesampleapp.BuildConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dimitrios on 25/08/2017.
 */

public class ApiQuery {

	private final static String PARAM_API_KEY = "api_key";
	private final static String PARAM_PAGE = "page";

	private final static int DEFAULT_PAGE = 1;

	private int mPageNumber = DEFAULT_PAGE;

	public Map<String, Object> mQueryParams;

	public ApiQuery() {
		mQueryParams = new HashMap<>();
		mQueryParams.put(PARAM_API_KEY, BuildConfig.API_KEY);
		mQueryParams.put(PARAM_PAGE, mPageNumber);
	}

	public void nextPage() {
		mPageNumber++;
		mQueryParams.put(PARAM_PAGE, mPageNumber);
	}

	public void reset() {
		mQueryParams.clear();
		mPageNumber = DEFAULT_PAGE;
	}

}
