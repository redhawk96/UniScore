/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package com.utils;

import java.io.IOException;
import java.net.MalformedURLException;

public class IpifyAPI {

	/*
	 * Method getIP : used to get the IP of the user which is signing into the system
	 * @returns s 	  IP address of the user who is signing in to the system
	 */
	public static String getIP() throws MalformedURLException, IOException {
		@SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipify.org").openStream(), "UTF-8").useDelimiter("\\A");
		return s.next();
	}
}
