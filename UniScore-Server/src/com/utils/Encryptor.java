/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import models.User;

public class Encryptor {
	

	/*
	 * Method getEncryptedPassword : used to return the encrypted password
	 * @params user	Password property from user object will be used to encrypt unsecure password
	 */
	public static String getEncryptedPassword(User user) throws NoSuchAlgorithmException, NoSuchProviderException {
		return securePassword(user.getPassword().toString());
    }
     

	/*
	 * Method securePassword : used to encrypt a password using MD% algorithms
	 * @params passwordToHash		 	Password that needs to be encrypted
	 * @returns generatedPassword		Encrypted password using MD5 algorithm
	 */
	private static String securePassword(String passwordToHash) throws NoSuchAlgorithmException {
		String generatedPassword = null;
		// Create MessageDigest instance for MD5
		MessageDigest md = MessageDigest.getInstance("MD5");
		// Add password bytes to digest
		md.update(passwordToHash.getBytes());
		// Get the hash's bytes
		byte[] bytes = md.digest();
		// This bytes[] has bytes in decimal format;
		// Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		// gets complete hashed password in hex format
		generatedPassword = sb.toString();
		
		return generatedPassword;
	}
	
     
}
