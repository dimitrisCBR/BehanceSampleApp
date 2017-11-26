package com.cbr.behancesampleapp.domain.network;

/** Created by dimitrios on 24/08/2017.*/
public class Urls {

	public class Behance {

		public final static String BASE_URL = "http://behance.net/v2/";

		public class Users {

			public final static String GET_USERS = "users/";

			public final static String GET_USER_BY_ID = "users/{id}";

			public final static String GET_USER_PROJECTS = "users/{id}/projects/";

			public final static String GET_USER_WIPS = "users/{id}/wips/";

			public final static String GET_USER_APPRECIATIONS = "users/{id}/appreciations/";

		}
	}

	public class Params {

		public final static String PARAM_ID = "id";

		public final static String PARAM_API_KEY = "api_key";
	}
}
