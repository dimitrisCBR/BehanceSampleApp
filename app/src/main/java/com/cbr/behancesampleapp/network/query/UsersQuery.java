package com.cbr.behancesampleapp.network.query;

/**
 * Created by dimitrios on 25/08/2017.
 */

public class UsersQuery extends PaginatedApiQuery {

	private final static String PARAM_COUNTRY = "country";

	public UsersQuery withCountry(String country) {
		mQueryParams.put(PARAM_COUNTRY, country);
		return this;
	}

}
