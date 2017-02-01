package com.wadiz.client.mail;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.wadiz.client.Sender;
import com.wadiz.client.model.Email;

public class EmailSenderTest {

	@Test
	public void testSend() {
		Sender emailSender = new EmailSender();
		
		Email email = new Email();
		
		email.setTitle("이메일 제목 ");
		email.setContents("이메일 내용입니다.  ");
		email.setEmailType("NW");
		email.setSenderEmail("hyunlae.kim@ohjic.com");
		email.setSenderName("김현래");
		email.setReceiverEmail("gusfot@gmail.com");
		email.setReceiverName("김온유");
		email.setSendStatus("R");
		
		String sendResult = (String) emailSender.send(email );
		
		assertTrue("00".equals(sendResult));
	}
}
