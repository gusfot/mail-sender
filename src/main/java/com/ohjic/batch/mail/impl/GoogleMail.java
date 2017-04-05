package com.ohjic.batch.mail.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ohjic.batch.common.helper.Mailer;

public class GoogleMail implements Mailer {

	private static final Logger logger = LoggerFactory.getLogger(GoogleMail.class);
	
	@Override
	public boolean send(String to, String toName, String from, String fromName, String password, String isAuth, String subject, String text, String textType, List<String> fileList)
			throws UnsupportedEncodingException, MessagingException {
		
		boolean result = false;
		
		  // Sender's email ID needs to be mentioned
		  final String username = from;//change accordingly
		  final String passwd = password;//change accordingly
		
		  // Assuming you are sending email through relay.jangosmtp.net
		  String host = "smtp.gmail.com";
		
		  Properties props = new Properties();
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.starttls.enable", "true");
		  props.put("mail.smtp.host", host);
		  props.put("mail.smtp.port", "587");
		
		  // Get the Session object.
		  Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
		     protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(username, passwd);
		     }
		  });
		  
		  session.setDebug(true);
		  
		  try {
		     // Create a default MimeMessage object.
		     Message message = new MimeMessage(session);
		
			   // 보내는 사람 
			   if(!"".equals(fromName)) {
				   message.setFrom(new InternetAddress(from, fromName));
			   }else {
				   message.setFrom(new InternetAddress(from));
			   }
			   
			   // 받는 사람
			   if(!"".equals(toName)) {
				   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to, toName));
			   }else {
				   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			   }
			
			     // Set Subject: header field
			     message.setSubject(subject);
			
			   // 메일내용 타입 구분(1:html, 0:text)
			   if("1".equals(textType)) {
			       message.setText(text);
			   }else {
			       message.setContent(text, "text/html; charset=UTF-8");
			   }
			
			     // Send message
			 Transport.send(message);
			
			 System.out.println("Sent message successfully....");
		
		     result = true;
		  } catch (MessagingException e) {
		        e.printStackTrace();
		        logger.error(e.getMessage());
		  }
		  
		return result;
		
	}
	
	@Deprecated
	@Override
	public boolean send(String to, String toName, String from, String fromName, String isAuth, String subject, String text, String textType)
			throws UnsupportedEncodingException, MessagingException {

			boolean result = false;
			
		  // Sender's email ID needs to be mentioned
		  final String username = "gusfot";//change accordingly
		  final String password = "alswn14921427onyou";//change accordingly
		
		  // Assuming you are sending email through relay.jangosmtp.net
		  String host = "smtp.gmail.com";
		
		  Properties props = new Properties();
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.starttls.enable", "true");
		  props.put("mail.smtp.host", host);
		  props.put("mail.smtp.port", "587");
		
		  // Get the Session object.
		  Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
		     protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(username, password);
		     }
		  });
		  
		  session.setDebug(true);
		  
		  try {
		     // Create a default MimeMessage object.
		     Message message = new MimeMessage(session);
		
			   // 보내는 사람 
			   if(!"".equals(fromName)) {
				   message.setFrom(new InternetAddress(from, fromName));
			   }else {
				   message.setFrom(new InternetAddress(from));
			   }
			   
			   // 받는 사람
			   if(!"".equals(toName)) {
				   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to, toName));
			   }else {
				   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			   }
			
			     // Set Subject: header field
			     message.setSubject(subject);
			
			   // 메일내용 타입 구분(1:html, 0:text)
			   if("1".equals(textType)) {
			       message.setText(text);
			   }else {
			       message.setContent(text, "text/html");
			   }
			
			     // Send message
			 Transport.send(message);
			
			 System.out.println("Sent message successfully....");
		
		     result = true;
		  } catch (MessagingException e) {
		//	            throw new RuntimeException(e);
		        e.printStackTrace();
		        logger.error(e.getMessage());
		  }
		  
		return result;
	}
	
	public static void main(String[] args) {
	      // Recipient's email ID needs to be mentioned.
	      String to = "hyunlae.kim@ohjic.com";//change accordingly

	      // Sender's email ID needs to be mentioned
	      String from = "gusfot@gmail.com";//change accordingly
	      final String username = "gusfot";//change accordingly
	      final String password = "alswn14921427onyou";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });
	      
	      session.setDebug(true);
	      
	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("Testing Subject");

	         // Now set the actual message
	         message.setText("Hello, this is sample for to check send "
	            + "email using JavaMailAPI ");

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	   }
}