package com.wadiz.client.ibk.realtime.msg.req;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.wadiz.client.ibk.batch.model.WMessageable;

/**
 * IBK 환불 전문
 * @author hyunlae
 *
 */
public class Refund implements WMessageable{

	public Refund() {
		this.ifCode = "CWF";
		this.tranCompId = "A00001";
		this.msgTypeCode = "E00400";
		this.flag = "C";
		this.resCode = "";
		this.sendDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		this.filler = "";
	}
	/**
	 * 전문관리번호(N, 15)
	 */
	private int refundId;

	/**
	 * 업무구분(A, 3)
	 */
	private String ifCode;
	
	/**
	 * 온라인중개사ID
	 */
	private String tranCompId;
	
	/**
	 * 전문구분코드(AN, 24)
	 */
	private String msgTypeCode;
	
	/**
	 * 송수신Flag(AN, 1)
	 */
	private String flag;
	
	/**
	 * 응답코드(AN, 4)
	 */
	private String resCode;
	
	/**
	 * 전문전송일시(N, 14)
	 */
	private String sendDate;
	
	/**
	 * FILLER(AN, 10)
	 */
	private String filler;
	
	
	/**
	 * 전문일련번호
	 */
	private int seq;
	
	/**
	 * 모집기업관리번호(AN,8)
	 */
	private String campaignId;
	
	/**
	 * 투자자입금관리번호(N,20)
	 */
	private String investId;
	
	/**
	 * 청약금액(N,19)
	 */
	private String investAmount;
	
	/**
	 * 환불금액(N, 19)
	 */
	private String refundAmount;
	
	/**
	 * 투자금환불사유코드(환불사유코드,N,2)
	 */
	private String refundResnCode;
	
	/**
	 * 환불코드(N,19)
	 */
	private String refundCode;
	/**
	 * 원거래 전문관리번호(AN, 9)
	 */
	private String orignInvestId;
	
	/**
	 * 원거래 전문전송일시(N, 14)
	 */
	private String orignInvestDate;
	

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public String getIssrAmount() {
		return investAmount;
	}

	public void setIssrAmount(String issrAmount) {
		this.investAmount = issrAmount;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRefundResnCode() {
		return refundResnCode;
	}

	public void setRefundResnCode(String refundResnCode) {
		this.refundResnCode = refundResnCode;
	}

	public String getRefundCode() {
		return refundCode;
	}

	public void setRefundCode(String refundCode) {
		this.refundCode = refundCode;
	}

	public String getOrignInvestId() {
		return orignInvestId;
	}

	public void setOrignInvestId(String orignInvestId) {
		this.orignInvestId = orignInvestId;
	}

	public String getOrignInvestDate() {
		return orignInvestDate;
	}

	public void setOrignInvestDate(String orignInvestDate) {
		this.orignInvestDate = orignInvestDate;
	}

	public String getIfCode() {
		return ifCode;
	}

	public void setIfCode(String ifCode) {
		this.ifCode = ifCode;
	}

	public String getTranCompId() {
		return tranCompId;
	}

	public void setTranCompId(String tranCompId) {
		this.tranCompId = tranCompId;
	}

	public String getMsgTypeCode() {
		return msgTypeCode;
	}

	public void setMsgTypeCode(String msgTypeCode) {
		this.msgTypeCode = msgTypeCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public int getRefundId() {
		return refundId;
	}

	public void setRefundId(int refundId) {
		this.refundId = refundId;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(String investAmount) {
		this.investAmount = investAmount;
	}

	
	@Override
	public String toString() {
		return "Refund [refundId=" + refundId + ", ifCode=" + ifCode + ", tranCompId=" + tranCompId + ", msgTypeCode="
				+ msgTypeCode + ", flag=" + flag + ", resCode=" + resCode + ", sendDate=" + sendDate + ", filler="
				+ filler + ", seq=" + seq + ", campaignId=" + campaignId + ", investId=" + investId + ", investAmount="
				+ investAmount + ", refundAmount=" + refundAmount + ", refundResnCode=" + refundResnCode
				+ ", refundCode=" + refundCode + ", orignInvestId=" + orignInvestId + ", orignInvestDate="
				+ orignInvestDate + "]";
	}

	@Override
	public String getMessage() {
		
		return  StringUtils.rightPad(ifCode, 3)+
				StringUtils.rightPad(tranCompId, 24)+
				StringUtils.rightPad(msgTypeCode, 6)+
				StringUtils.rightPad(flag, 1)+
				StringUtils.rightPad(resCode, 4)+
				StringUtils.rightPad(sendDate, 14)+
				StringUtils.leftPad(seq+"", 15, "0")+
				StringUtils.rightPad(filler, 10, "")+
				StringUtils.leftPad(refundId+"", 7, "0")+		// 전문일련번호
				StringUtils.rightPad(campaignId, 8)+			// 모집기업관리번호
				StringUtils.leftPad(investId, 20, "0")+			// 투자자입금관리번호
				StringUtils.leftPad(investAmount, 19, "0")+		// 청약금액
				StringUtils.leftPad(refundAmount, 19, "0")+		// 환불금액
				StringUtils.leftPad(refundResnCode, 2, "")+		// 환불금액
				StringUtils.leftPad(refundCode, 2, "")+			// 환불코드
				StringUtils.rightPad(orignInvestId, 9, "")+				// TODO : 원전문관리번호
				StringUtils.rightPad(orignInvestDate, 14, "")				// TODO : 원전문관리번호
				;
	}

	@Override
	public void messageInfo() {
		System.out.println(this.getMessage());
		
	}
	
	
}
