package com.wadiz.client.schedule;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wadiz.client.ibk.batch.model.TbIbkBatchWokingDay;
import com.wadiz.client.ibk.batch.service.IbkBatchService;
import com.wadiz.client.ibk.batch.service.IbkBatchWorkingdayService;
import com.wadiz.client.ibk.service.WadizHolidayService;

@Component("investIfInfoRealtimeBatchScheduler")
public class InvestIfInfoRealtimeBatchScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(InvestIfInfoRealtimeBatchScheduler.class);
	
	@Autowired
	private IbkBatchService ibkBatchService;
 
	@Autowired
	private IbkBatchWorkingdayService ibkBatchWorkingdayService;
	
	@Autowired
	private WadizHolidayService wadizHolidayService;
	
	public void run() {
		
		logger.info("############   investIfInfoRealtimeBatchScheduler is started...    ############");
		
		try {
			String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
			boolean isHoliday = wadizHolidayService.isHoliday(today);
			
			if(!isHoliday) {		// 영업일일 경우 실행
				
				logger.info("오늘은 영업일!!!!!");
				TbIbkBatchWokingDay  workingDay = ibkBatchWorkingdayService.getWorkingday("I");
				
				if(workingDay != null ) {
					
					String date = workingDay.getWorkingDate();
					//String date = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
					logger.debug("기업은행에 전송항 청약내역(0200) 영업일: {}", workingDay.getWorkingDate());
					ibkBatchService.registRealtimeInvest(date);
					
					workingDay.setProcStatus("Y");
					workingDay.setUpdateDate(new Date());
					ibkBatchWorkingdayService.modify(workingDay);
					
				}else {
					logger.info("기업은행에 전송할 청약내역(0200) 영엉일이 없음");
				}
			
			}else {
				logger.info("오늘은 공유일!!!!!");
			}
		
		}catch(Exception e) {
			logger.error("############   investIfInfoRealtimeBatchScheduler is failed...   ############");
			logger.error(e.getMessage());
			
		}
		logger.info("############   investIfInfoRealtimeBatchScheduler is finished...   ############");
	}
	
}
