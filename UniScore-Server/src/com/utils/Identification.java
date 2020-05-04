package com.utils;

public class Identification {

	public static String getFormatedId(String unformattedId, String prefix) {
		String id = prefix;
		switch(unformattedId.length()) {
			case 1 : id = id.concat("00000").concat(unformattedId); break;
			case 2 : id = id.concat("0000").concat(unformattedId); break;
			case 3 : id = id.concat("000").concat(unformattedId); break;
			case 4 : id = id.concat("00").concat(unformattedId); break;
			case 5 : id = id.concat("0").concat(unformattedId); break;
		}
		return id;
	}
}
