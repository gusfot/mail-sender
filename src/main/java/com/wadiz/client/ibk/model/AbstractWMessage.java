package com.wadiz.client.ibk.model;

import java.io.UnsupportedEncodingException;

public abstract class AbstractWMessage implements  WMessageable{

	@Override
	public void messageInfo() {
		try {
			System.out.println(this.getClass().getSimpleName() + " length: " + this.getMessage().getBytes("MS949").length);
			System.out.println(this.getClass().getSimpleName() + " content: " + this.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
