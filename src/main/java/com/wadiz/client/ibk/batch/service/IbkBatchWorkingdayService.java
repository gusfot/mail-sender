package com.wadiz.client.ibk.batch.service;

import com.wadiz.client.ibk.batch.model.TbIbkBatchWokingDay;

public interface IbkBatchWorkingdayService {

	/**
	 * 영업일이면 큐테이블에 등록
	 * @param date
	 * @return
	 */
	boolean regist(String date);
	
	/**
	 * 큐테이블에서 완료처리
	 * @param date
	 * @return
	 */
	boolean complete(String date);

	TbIbkBatchWokingDay getWorkingday(String procStatus);

	boolean modify(TbIbkBatchWokingDay workingDay);

}
