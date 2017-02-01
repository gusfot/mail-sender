package com.wadiz.client.mail;

import com.wadiz.client.Sender;
import com.wadiz.client.model.Email;

public class EmailSender implements Sender {

	@Override
	public Object send(Object obj) {
		Email email = (Email)obj;
		// TODO Auto-generated method stub
		
		
		System.out.println("이메일 전송: " + email.toString());
		return "00";
	}

}
