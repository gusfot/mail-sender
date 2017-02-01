package com.wadiz.client.ibk.batch.model;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWMessage implements  WMessageable{

	private static final Logger logger = LoggerFactory.getLogger(AbstractWMessage.class);
	@Override
	public void messageInfo() {
		try {
			logger.debug(this.getClass().getSimpleName() + " length: " + this.getMessage().getBytes("MS949").length);
			logger.debug(this.getClass().getSimpleName() + " content: " + this.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
