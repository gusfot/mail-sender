package com.wadiz.client.ibk.batch.model;

import org.apache.commons.lang3.StringUtils;

public class OfferIfTrailer extends AbstractWMessage {

	/**
	 * 업무구분(N, 4)
	 */
	private String ifCode;
	
	/**
	 * 데이타구분(N,2)
	 */
	private String dataType;
	
	/**
	 * 일련번호(N,7)
	 */
	private String seq;
	
	/**
	 * 은행코드
	 */
	private String bankCode;
	
	/**
	 * 총 DATA RECORD 수
	 */
	private String totalDataRecordCount;
	
	/**
	 * FILLER(AN, 377)
	 */
	private String filler;
	
	public String getIfCode() {
		return ifCode;
	}

	public void setIfCode(String ifCode) {
		this.ifCode = ifCode;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getTotalDataRecordCount() {
		return totalDataRecordCount;
	}

	public void setTotalDataRecordCount(String totalDataRecordCount) {
		this.totalDataRecordCount = totalDataRecordCount;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}
	
	
	@Override
	public String getMessage() {
		String delimeter = "";
		return StringUtils.rightPad(ifCode,4)+delimeter
				+StringUtils.rightPad(dataType,2)+delimeter
				+StringUtils.rightPad(seq,7,"0")+delimeter
				+StringUtils.rightPad(bankCode,3)+delimeter
				+StringUtils.leftPad(totalDataRecordCount,7,"0")+delimeter
				+StringUtils.rightPad(filler, 377)+delimeter + "\r\n";
		
	}
	

}
