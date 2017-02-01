package com.wadiz.client.ibk.batch.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IBK 전문
 * @author hyunlae
 *
 */
public class WMessage extends AbstractWMessage {

	private static final Logger logger = LoggerFactory.getLogger(WMessage.class);
	/**
	 * HEADER RECORD
	 */
	private WMessageable header;
	
	/**
	 * DATA RECORD
	 */
	private WMessageable dataList;
	
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

	public WMessageable getDataList() {
		return dataList;
	}

	public void setDataList(WMessageable dataList) {
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
		String headerMsg   = "";
		String dataListMsg = "";
		String trailerMsg  = "";
		
		if(header != null) {
			headerMsg = header.getMessage();
			logger.debug("headerMsg : " + headerMsg);
		}else {
			logger.debug("header is null");
		}
		
		if(dataList != null) {
			dataListMsg = dataList.getMessage();
			logger.debug("dataListMsg : " + dataListMsg);
		}else {
			logger.debug("dataList is null");
		}
		
		if(trailer != null) {
			trailerMsg = trailer.getMessage();
			logger.debug("trailerMsg : " + trailerMsg);
		}else {
			logger.debug("trailer is null");
		}
		
		String message = headerMsg + dataListMsg + trailerMsg;
		logger.info(message);
		
		return message;
	}
}
