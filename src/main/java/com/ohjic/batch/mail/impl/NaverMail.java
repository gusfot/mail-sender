package com.ohjic.batch.mail.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ohjic.batch.common.helper.Mailer;

public class NaverMail implements Mailer {

	private static final Logger logger = LoggerFactory.getLogger(NaverMail.class);
	
	@Override
	public boolean send(String to, String toName, String from, String fromName, String msgType, String Subject,
			String text) throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		return false;
	}


}