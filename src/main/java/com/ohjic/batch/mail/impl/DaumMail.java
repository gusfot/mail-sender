package com.ohjic.batch.mail.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.ohjic.batch.common.helper.Mailer;

public class DaumMail implements Mailer {

	@Override
	public boolean send(String to, String toName, String from, String fromName, boolean isAuth, String Subject, String text)
			throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean send(String to, String toName, String from, String fromName, String password, boolean isAuth,
			String subject, String text) throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		return false;
	}



}
