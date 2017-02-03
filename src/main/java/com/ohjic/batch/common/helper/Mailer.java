package com.ohjic.batch.common.helper;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

public interface Mailer {

	@Deprecated
	boolean send(String to, String toName, String from, String fromName, String isAuth, String Subject, String text, String textType)
			throws UnsupportedEncodingException, MessagingException;

	boolean send(String to, String toName, String from, String fromName, String password, String isAuth,
			String subject, String text, String textType) throws UnsupportedEncodingException, MessagingException;
}
