package com.wadiz.client.schedule;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wadiz.client.ibk.batch.service.IbkBatchService;

@Component("investIfInfoResultBatchScheduler")
public class InvestIfInfoResultBatchScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(InvestIfInfoResultBatchScheduler.class);
	
	@Autowired
	private IbkBatchService ibkBatchService;
 
	public void run() {
		logger.info("############   investIfInfoResultBatchScheduler is started...    ############");
		String date = DateFormatUtils.format(new Date(), "yyyyMMdd");
		ibkBatchService.getResultInvest(date);
		logger.info("############   investIfInfoResultBatchScheduler is finished...   ############");
	}
	
}
