package com.wadiz.client.schedule;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wadiz.client.ibk.batch.service.IbkBatchWorkingdayService;
import com.wadiz.client.ibk.service.WadizHolidayService;

@Component("ibkBatchWorkingDayScheduler")
public class IbkBatchWorkingDayScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(IbkBatchWorkingDayScheduler.class);
	
	@Autowired
	private IbkBatchWorkingdayService ibkBatchWorkingdayService;
	
	@Autowired
	private WadizHolidayService wadizHolidayService;
 
	public void run() {
		
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

		try {
			
			logger.info("############   ibkBatchWorkingDayScheduler("+today+") is started...    ############");
			
			boolean isHoliday = wadizHolidayService.isHoliday(today);
			
			if(!isHoliday) {
				logger.info("오늘은 영업일!!!!!");
				ibkBatchWorkingdayService.regist(today);
			}else {
				logger.info("오늘은 공휴일!!!!!");
			}
		}catch(Exception e) {
			logger.error("############   ibkBatchWorkingDayScheduler("+today+") is failed...   ############");
			logger.error(e.getMessage());
		}
		
		logger.info("############   ibkBatchWorkingDayScheduler("+today+") is finished...   ############");
	}
	
}
