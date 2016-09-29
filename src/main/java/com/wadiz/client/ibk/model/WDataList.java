package com.wadiz.client.ibk.model;

import java.io.UnsupportedEncodingException;

public class WDataList<E> extends AbstractWDataList<E > {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		
		StringBuilder sb = new StringBuilder();
		int size = this.size();
		
		for (int i = 0; i < size; i++) {
			String message = ((WMessageable)this.get(i)).getMessage();
			sb.append(message+"\r\n");
		}
		
		return sb.toString();
	}
	
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
