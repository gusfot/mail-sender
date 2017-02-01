package com.wadiz.client.schedule;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wadiz.client.ibk.batch.service.IbkBatchService;

@Component("investIfInfoAccountBatchScheduler")
public class InvestIfInfoAccountBatchScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(InvestIfInfoAccountBatchScheduler.class);
	
	@Autowired
	private IbkBatchService ibkBatchService;
 
	public void run() {
		
		String date = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
		logger.info("############   InvestIfInfoAccountBatchScheduler("+date+") is started...    ############");
		
		try {
			ibkBatchService.registAccountInvest(date);
		}catch(Exception e) {
			logger.error("############   InvestIfInfoAccountBatchScheduler("+date+") is failed...   ############");
			logger.error(e.getMessage());
		}
		
		logger.info("############   InvestIfInfoAccountBatchScheduler("+date+") is finished...   ############");
	}
	
}
