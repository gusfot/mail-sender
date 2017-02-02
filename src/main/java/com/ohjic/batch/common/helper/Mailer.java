package com.ohjic.batch.common.helper;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

public interface Mailer {

	boolean send(String to, String toName, String from, String fromName, boolean isAuth, String Subject, String text)
			throws UnsupportedEncodingException, MessagingException;

	boolean send(String to, String toName, String from, String fromName, String password, boolean isAuth,
			String subject, String text) throws UnsupportedEncodingException, MessagingException;
}
