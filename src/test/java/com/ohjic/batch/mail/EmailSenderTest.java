package com.ohjic.batch.mail;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.junit.Test;

import com.ohjic.batch.common.UnkownSmtpHostException;
import com.ohjic.batch.common.helper.Mailer;
import com.ohjic.batch.mail.impl.MailFatory;
import com.ohjic.batch.model.Email;

public class EmailSenderTest {

	private static final String Subject = null;

	@Test
	public void testSend() {
		
		Email email = new Email();
		
		email.setTitle("이메일 제목 ");
		email.setContents("이메일 내용입니다.  ");
		email.setEmailType("NW");
		email.setSenderEmail("hyunlae.kim@ohjic.com");
		email.setSenderName("김현래");
		email.setReceiverEmail("gusfot@gmail.com");
		email.setReceiverName("김온유");
		email.setSendStatus("R");
		
		boolean isAuth =false;
		

		
		boolean sendResult = false;
		
		try {
			Mailer emailSender = MailFatory.getInstance(isAuth, "hyunlae.kim@ohjic.com");
			
			String to = "gusfot@gmail.com";
			String toName = "김현래";
			String from="hyunlae.kim@ohjic.com";
			String fromName ="김현래";
			String text ="메일본문";
			String subject = "메일제목";
			String msgType="1";
			
			sendResult = emailSender.send(to, toName, from, fromName,msgType, subject, text);
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
