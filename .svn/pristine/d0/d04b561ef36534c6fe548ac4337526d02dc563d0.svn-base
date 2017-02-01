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

@Component("offerIfInfoBatchScheduler")
public class OfferIfInfoBatchScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(OfferIfInfoBatchScheduler.class);
	
	@Autowired
	private IbkBatchService ibkBatchService;
 
	@Autowired
	private IbkBatchWorkingdayService ibkBatchWorkingdayService;
	
	@Autowired
	private WadizHolidayService wadizHolidayService;
	
	public void run() {
		
		logger.info("############   offerIfInfoBatchScheduler is started...    ############");
		
		try {
			
			String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
			boolean isHoliday = wadizHolidayService.isHoliday(today);
			
			if(!isHoliday) {		// 영업일일 경우 실행
				
				logger.debug("오늘은 영업일입니다.");
				TbIbkBatchWokingDay  workingDay = ibkBatchWorkingdayService.getWorkingday("O");
			
				if(workingDay != null ) {
					String date = workingDay.getWorkingDate();
					//String date = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
					logger.debug("기업은행에 전송한 모집정보(0100) 영업일: {}", workingDay);
					ibkBatchService.registOffer(date);
					
					workingDay.setProcStatus("I");
					workingDay.setUpdateDate(new Date());
					ibkBatchWorkingdayService.modify(workingDay);
					
				}else {
					logger.debug("기업은행에 전송할 모집정보(0100) 영엉일이 없음");
				}
			
			}else {
				logger.debug("오늘은 공유일입니다.");
			}
			
		}catch(Exception e) {
			logger.error("############   offerIfInfoBatchScheduler is failed...   ############");
			logger.error(e.getMessage());
		}
		
		logger.info("############   offerIfInfoBatchScheduler is finished...   ############");
	}
	
}
