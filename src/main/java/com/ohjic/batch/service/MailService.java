package com.ohjic.batch.service;

import java.util.Date;

public interface MailService {

	public void send();
	
	public Object regist(String from, String fromName, String to, String toName, String subject, String textType, String text, String serverId, Date requestTime, String password, String isReserved);


}
