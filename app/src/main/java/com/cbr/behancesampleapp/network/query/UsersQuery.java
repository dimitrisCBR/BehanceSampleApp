package com.cbr.behancesampleapp.network.query;

import java.util.Map;

/**
 * Created by dimitrios on 25/08/2017.
 */

public class UsersQuery extends ApiQuery {

    private final static String PARAM_COUNTRY = "country";

    public UsersQuery withCountry(String country) {
        mQueryParams.put(PARAM_COUNTRY, country);
        return this;
    }

    public Map<String, Object> build() {
        return mQueryParams;
    }

}
