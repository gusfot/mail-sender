package com.wadiz.client.ibk.batch.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public abstract class AbstractWDataList<E> extends ArrayList<E> implements WMessageable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
