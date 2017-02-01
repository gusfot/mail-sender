package com.wadiz.client.ibk.batch.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadiz.client.ibk.batch.model.TbIbkBatchWokingDay;
import com.wadiz.client.ibk.batch.persistence.TbIbkBatchWokingDayMapper;
import com.wadiz.client.ibk.batch.service.IbkBatchWorkingdayService;

@Service
public class IbkBatchWorkingdayServiceImpl implements IbkBatchWorkingdayService {

	@Autowired
	private TbIbkBatchWokingDayMapper tbIbkBatchWokingDayMapper;
	
	@Override
	public boolean regist(String date) {
		
		TbIbkBatchWokingDay record = new TbIbkBatchWokingDay();
		record.setProcStatus("O");
		record.setWorkingDate(date);
		record.setRegDate(new Date());
		
		return tbIbkBatchWokingDayMapper.insertSelective(record ) == 1? true:false;
	}

	@Override
	public boolean complete(String date) {
		
		TbIbkBatchWokingDay record = new TbIbkBatchWokingDay();
		record.setWorkingDate(date);
		record.setProcStatus("Y");
		return tbIbkBatchWokingDayMapper.updateByPrimaryKeySelective(record) == 1 ? true:false;
	}

	@Override
	public TbIbkBatchWokingDay getWorkingday(String procStatus) {
		return tbIbkBatchWokingDayMapper.selectWorkingdayByProcStatus(procStatus);
	}

	@Override
	public boolean modify(TbIbkBatchWokingDay workingDay) {
		return tbIbkBatchWokingDayMapper.updateByPrimaryKeySelective(workingDay) == 1 ? true:false;
	}

}
