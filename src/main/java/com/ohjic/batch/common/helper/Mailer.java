package com.ohjic.batch.common.helper;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

public interface Mailer {

	boolean send(String to, String toName, String from, String fromName, String msgType, String Subject, String text)
			throws UnsupportedEncodingException, MessagingException;
}
