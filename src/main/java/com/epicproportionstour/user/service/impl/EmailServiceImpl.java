package com.epicproportionstour.user.service.impl;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.epicproportionstour.user.service.EmailService;


public class EmailServiceImpl implements EmailService {

	private String sRecipient;
	private String sSender;
	private String sPassword;
	private String generatedPassword;
	private String userName;
	String Result;
	private String result;
	private Properties properties;

	public EmailServiceImpl(String sRecipient, String sSender, String sPassword,String generatedPassword,String userName) {
		
		
		this.sRecipient = sRecipient;
		this.sSender = sSender;
		this.sPassword = sPassword;
		this.generatedPassword=generatedPassword;
		this.userName=userName;
		// Get system properties object
		properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
	}

	public int SendPwdByMail() {
		// Get the default Session object.
		Session mailSession = Session.getInstance(properties,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(sSender, sPassword);
					}
				});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(mailSession);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(sSender));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					sRecipient));
			// Set Subject: header field
			message.setSubject("Application Password");
			// Now set the actual message

//			message.setText("Application UserName is : "+userName
//					+"\n\n And password is :-"+generatedPassword);
			message.setText("The Application password is :-"+generatedPassword);
			
			// Send message
			Transport.send(message);
			result = "Sent message successfully....";
			System.out.println(result);
			return 1;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			result = "Error: unable to send message....";
			System.out.println(result);
			return 0;
		}
	}

}
