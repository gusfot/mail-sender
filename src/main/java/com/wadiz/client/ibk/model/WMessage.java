package com.wadiz.client.ibk.model;

/**
 * IBK 전문
 * @author hyunlae
 *
 */
public class WMessage extends AbstractWMessage {

	/**
	 * HEADER RECORD
	 */
	private WMessageable header;
	
	/**
	 * DATA RECORD
	 */
	private WDataList dataList;
	
	/**
	 * TRAILER RECORD
	 */
	private WMessageable trailer;

	public WMessageable getHeader() {
		return header;
	}

	public void setHeader(WMessageable header) {
		this.header = header;
	}

	public WDataList getDataList() {
		return dataList;
	}

	public void setDataList(WDataList dataList) {
		this.dataList = dataList;
	}

	public WMessageable getTrailer() {
		return trailer;
	}

	public void setwTrailer(WMessageable trailer) {
		this.trailer = trailer;
	}

	@Override
	public String getMessage() {
		String mesage = header.getMessage() + dataList.getMessage() + trailer.getMessage();
		return mesage;
	}
}
