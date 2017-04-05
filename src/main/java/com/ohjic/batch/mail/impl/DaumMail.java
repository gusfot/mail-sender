package com.ohjic.batch.mail.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import com.ohjic.batch.common.helper.Mailer;

public class DaumMail implements Mailer {

	@Override
	public boolean send(String to, String toName, String from, String fromName, String isAuth, String Subject, String text, String textType)
			throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean send(String to, String toName, String from, String fromName, String password, String isAuth,
			String subject, String text, String textType, List<String> fileList) throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		return false;
	}



}
