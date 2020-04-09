package com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import models.User;

public class Encryptor {

/*
 * For development purposes only
 */
//	public static void main(String args[]) {
//		try {
//			User user = new User();
//			user.setEmail("uditha@uniscore.com");
//			user.setPassword("123456");
//	        
//			System.out.println(getEncryptedPassword(user));
//			System.out.println(getEncryptedPassword(user));
//			System.out.println(getEncryptedPassword(user));
//			System.out.println(getEncryptedPassword(user));	
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//	}
	
	public static String getEncryptedPassword(User user) throws NoSuchAlgorithmException, NoSuchProviderException {
		return securePassword(user.getPassword().toString());
    }
     
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
