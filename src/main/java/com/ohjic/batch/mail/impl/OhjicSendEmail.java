package com.ohjic.batch.mail.impl;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ohjic.batch.common.config.OhjicConfig;
import com.ohjic.batch.common.helper.Mailer;

public class OhjicSendEmail implements Mailer {
	
	private static final Logger logger = LoggerFactory.getLogger(OhjicSendEmail.class);
	
	@Override
	public boolean send(String to, String toName, String from, String fromName, String isAuth, String subject, String text, String textType)
			throws UnsupportedEncodingException, MessagingException {

		boolean result = false;
        // Assuming you are sending email from localhost
        String host = OhjicConfig.get("smtp.server.ip");;
        
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);
        
        try {
	       // Create a default MimeMessage object.
	       MimeMessage message = new MimeMessage(session);
	
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
	       
	       // 메일제목
	       message.setSubject(subject);
	
	       // 메일내용 타입 구분(1:html, 0:text)
	       if("1".equals(textType)) {
		       message.setText(text);
	       }else {
		       message.setContent(text, "text/html");
	       }
	       
	       // 메일 전송
	       Transport.send(message);
	       logger.info("Sent message successfully....");
	       
	       result = true;
	       
	    } catch (MessagingException e) {
//	        throw new RuntimeException(e);
	    	logger.error(e.getMessage());
	        e.printStackTrace();
	  }
       return result;
	}
	
	@Override
	public boolean send(String to, String toName, String from, String fromName, String password, String isAuth,
			String subject, String text, String textType) throws UnsupportedEncodingException, MessagingException {
		
		boolean result = false;
        // Assuming you are sending email from localhost
        String host = OhjicConfig.get("smtp.server.ip");;
        
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);
        
        try {
	       // Create a default MimeMessage object.
	       MimeMessage message = new MimeMessage(session);
	
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
	       
	       // 메일제목
	       message.setSubject(subject);
	
	       // 메일내용 타입 구분(1:html, 0:text)
	       if("1".equals(textType)) {
		       message.setText(text);
	       }else {
		       message.setContent(text, "text/html");
	       }
	       
	       // 메일 전송
	       Transport.send(message);
	       logger.info("Sent message successfully....");
	       
	       result = true;
	       
	    } catch (MessagingException e) {
//	        throw new RuntimeException(e);
	    	logger.error(e.getMessage());
	        e.printStackTrace();
	  }
       return result;
	}
	
    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {

    	// Recipient's email ID needs to be mentioned.
        String to = "hyunlae.kim@ohjic.com";

        // Sender's email ID needs to be mentioned
        String from = "gusfot@naver.com";

        // Assuming you are sending email from localhost
//        String host = "115.68.44.227";
        String host = "61.35.195.34";
//        String host = "192.168.0.67";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
//        props.setProperty("mail.user", "myuser");
//        props.setProperty("mail.password", "mypwd");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        
       // Create a default MimeMessage object.
       MimeMessage message = new MimeMessage(session);

       // Set From: header field of the header.
       message.setFrom(new InternetAddress(from, "현래"));
       
       // Set To: header field of the header.
       message.addRecipient(Message.RecipientType.TO,
                                new InternetAddress(to));

       // Set Subject: header field
       message.setSubject("This is the Subject Line!!");

	   // Now set the actual message
       message.setText("This is actual message");
       
       // Send message
       Transport.send(message);
       System.out.println("Sent message successfully....");

    }

}
