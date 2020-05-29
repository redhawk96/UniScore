/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package com.utils;

public class Identification {
	
	/*
	 * getFormatedId : to format a given id
	 * @param unformattedId   unformatted id
	 * @param prefix		  prefix to format id, either L, S or A
	 * @returns String		  formatted id
	 */
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
