package com.ohjic.batch.persistence;

import com.ohjic.batch.model.MsgQueue;
import com.ohjic.batch.model.MsgResult;

public interface MsgResultMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mail_result
	 * @mbg.generated  Wed Apr 05 10:14:25 KST 2017
	 */
	int insert(MsgResult record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mail_result
	 * @mbg.generated  Wed Apr 05 10:14:25 KST 2017
	 */
	int insertSelective(MsgResult record);

	int insertSelectiveMsgQueue(MsgQueue msgQueue);
}