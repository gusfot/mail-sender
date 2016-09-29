package com.wadiz.client.ibk.batch.service;

public interface IbkBatchService {

	String registOffer(String date);
	
	/**
	 * 실시간이체 청약목록 등록
	 * @param date
	 * @return
	 */
	String registRealtimeInvest(String date);
	
	/**
	 * 계좌이체
	 * @param date
	 * @return
	 */
	String registAccountInvest(String date);
	
	boolean getResultOffer(String date);
	
	boolean getResultInvest(String date);

	String payInvest(String date);
	
	String resultInvest(String date);

}
