package com.ohjic.batch.mail.impl;

import com.ohjic.batch.common.UnkownSmtpHostException;
import com.ohjic.batch.common.helper.Mailer;

public class MailFatory {

	private static Mailer instance = null;

	public static Mailer getInstance(boolean isAuth, String from) throws UnkownSmtpHostException {
		
		String domain = from.substring(from.indexOf("@")+1);
		
		System.out.println("domain: " + domain);
		
		if(isAuth) {
			if("gmail.com".equals(domain)) {
				instance = new GoogleMail();
			}else if("naver.com".equals(domain)) {
				instance = new NaverMail();
			}else if("daum.com".equals(domain)) {
				instance = new DaumMail();
			}else {
				throw new UnkownSmtpHostException("Uknown mail server host...");
			}
			
		}else {
			instance = new OhjicSendEmail();
		}
		
		return instance;
	}

}
