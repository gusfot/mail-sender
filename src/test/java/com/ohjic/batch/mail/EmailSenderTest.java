package com.ohjic.batch.mail;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.junit.Test;

import com.ohjic.batch.common.UnkownSmtpHostException;
import com.ohjic.batch.common.helper.Mailer;
import com.ohjic.batch.mail.impl.MailFatory;

public class EmailSenderTest {

	private static final String Subject = null;

	@Test
	public void testSend() {
		
		
		boolean sendResult = false;
		
		try {
			
			boolean isAuth=true;
			String to = "gusfot@gmail.com";
			String toName = "김현래";
			String from="hyunlae.kim@ohjic.com";
			String fromName ="김현래";
			String text ="메일본문";
			String subject = "메일제목";
			Mailer emailSender = MailFatory.getInstance(isAuth, "hyunlae.kim@ohjic.com");
			
			sendResult = emailSender.send(to, toName, from, fromName, isAuth, subject, text);
			
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnkownSmtpHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(sendResult);
	}
}
