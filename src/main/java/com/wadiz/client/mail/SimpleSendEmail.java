package com.wadiz.client.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleSendEmail {
    public static void main(String[] args) throws UnsupportedEncodingException {

    	// Recipient's email ID needs to be mentioned.
        String to = "hyunlae.kim@ohjic.com";

        // Sender's email ID needs to be mentioned
        String from = "gusfot@naver.com";

        // Assuming you are sending email from localhost
//        String host = "115.68.44.227";
        String host = "61.35.195.34";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
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
        }catch (MessagingException mex) {
           mex.printStackTrace();
        }
    }
}
