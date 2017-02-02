package com.ohjic.batch.model;

import java.util.Date;

public class Email {

	private Integer emailSeq;
	
	private String title;
	
	private String contents;
	
	private String sendStatus;		// 전송상태(R:대기, P:전송중, C:완료) 
	
	private String emailType;		// NW:즉시발송, RS:예약발송, NR:재발송 

	private String reservedTime; 		// 예약 시간 
		
	private String senderEmail;
	
	private String senderName;
	
	private String receiverEmail;

	private String receiverName;
	
	private String resultCode;
	
	private String isDeleted;
	
	private Date regDate;

	public Integer getEmailSeq() {
		return emailSeq;
	}

	public void setEmailSeq(Integer emailSeq) {
		this.emailSeq = emailSeq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}


	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getReservedTime() {
		return reservedTime;
	}

	public void setReservedTime(String reservedTime) {
		this.reservedTime = reservedTime;
	}

	@Override
	public String toString() {
		return "Email [emailSeq=" + emailSeq + ", title=" + title + ", contents=" + contents + ", sendStatus="
				+ sendStatus + ", emailType=" + emailType + ", reservedTime=" + reservedTime + ", senderEmail="
				+ senderEmail + ", senderName=" + senderName + ", receiverEmail=" + receiverEmail + ", receiverName="
				+ receiverName + ", resultCode=" + resultCode + ", isDeleted=" + isDeleted + ", regDate=" + regDate
				+ "]";
	}
	
	
}
