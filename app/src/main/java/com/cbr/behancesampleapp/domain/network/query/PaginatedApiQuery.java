package com.cbr.behancesampleapp.domain.network.query;

/**
 * Created by Dimitrios on 8/27/2017.
 */

public class PaginatedApiQuery extends ApiQuery {

	private final static String PARAM_PAGE = "page";

	private final static int DEFAULT_PAGE = 1;

	private int mPageNumber = DEFAULT_PAGE;


	public PaginatedApiQuery() {
		super();
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
