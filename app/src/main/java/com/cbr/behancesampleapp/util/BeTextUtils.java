package com.cbr.behancesampleapp.util;

import java.util.List;

/** Created by Dimitrios on 8/29/2017.*/
public class BeTextUtils {

	public static String formatUserFields(List<String> fields) {
		String append = ",";
		StringBuilder stringBuilder = new StringBuilder();
		if (fields != null && !fields.isEmpty()) {
			for (String field : fields) {
				stringBuilder.append(field);
				stringBuilder.append(append);
			}
			return stringBuilder.substring(0, stringBuilder.length() - 1);
		}
		return "";
	}

}
