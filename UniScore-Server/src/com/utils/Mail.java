/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package com.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
	
	/*
	 * Method send : used to send mail(s) using Java Mail
	 * @params recepients 	   Mail addresses of the recepient(s). seperated by a comma
	 * @params subject		   Subject of the mail
	 * @params htmlBody		   HTML body of the mail
	 */
	public static void send(String recepients, String subject, String htmlBody) throws AddressException, MessagingException {

		// Declaring and initializing gmail credentials
		final String username = "";
		final String password = "";

		// Creating a new Property object to hold properties of the mail
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// Creating a new session using defined properties and gmail credentials to send mail(s)
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		// Setting mail recepients, subject and body to be sent
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepients));
		message.setSubject(subject);
		message.setContent(htmlBody, "text/html");

		// Send mail(s)
		Transport.send(message);
	}
}
