package com.ohjic.batch.mail.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.ohjic.batch.common.helper.Mailer;

public class DaumMail implements Mailer {

	@Override
	public boolean send(String to, String toName, String from, String fromName, String Subject, String text,
			String msgType) throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		return false;
	}


}
