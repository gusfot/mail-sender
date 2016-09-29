package com.wadiz.client.ibk.batch.service;

import com.wadiz.client.ibk.realtime.msg.req.Refund;

public interface IbkRefundBatchService {

	String request();

	boolean response(Refund result);
	

}
