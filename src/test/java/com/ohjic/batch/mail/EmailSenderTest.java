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
			
			String isAuth="0";
			String to = "gusfot@naver.com";
			String toName = "김현래";
			String from="gusfot@gmail.com";
			String fromName ="김현래";
			String text ="메일본문";
			String subject = "메일제목";
			Mailer emailSender = MailFatory.getInstance(isAuth, from);
			String textType="0";
			
			String password="";
			sendResult = emailSender.send(to, toName, from, fromName, password, isAuth, subject, text, textType, null);
			
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
