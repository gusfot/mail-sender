package com.wadiz.client.ibk.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadiz.client.ibk.batch.model.TbServiceHolidayInfo;
import com.wadiz.client.ibk.batch.model.TbServiceHolidayInfoKey;
import com.wadiz.client.ibk.batch.persistence.TbServiceHolidayInfoMapper;
import com.wadiz.client.ibk.service.WadizHolidayService;

@Service
public class WadizHolidayServiceImpl implements WadizHolidayService {

	private static final Logger logger = LoggerFactory.getLogger(WadizHolidayServiceImpl.class);
	
	@Autowired
	private TbServiceHolidayInfoMapper tbServiceHolidayInfoMapper;
	
	@Override
	public boolean isHoliday(String date) {
		
		boolean result = false;
		
		String[] dateArr = date.split("-");
		String year = dateArr[0];
		String month = dateArr[1];
		String day = dateArr[2];
		
		TbServiceHolidayInfoKey key = new TbServiceHolidayInfoKey();
		key.setYear(year);
		key.setMonth(month);
		key.setDay(day);
		
		TbServiceHolidayInfo  tbServiceHolidayInfo  = tbServiceHolidayInfoMapper.selectByPrimaryKey(key);
		
		if(tbServiceHolidayInfo !=null) {
			result = true;
			logger.debug("{} 는 {} 입니다.", date, tbServiceHolidayInfo.getDescription());
		}else {
			result = false;
			logger.debug("{} 는 {} 입니다.", date, "영업일");
		}
		
		return result;
	}

}
