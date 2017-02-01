package com.wadiz.client.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wadiz.client.ibk.polling.service.IbkPollingClientService;

@Component("ibkPollingClientScheduler")
public class IbkPollingClientScheduler {

private static final Logger logger = LoggerFactory.getLogger(InvestIfInfoRealtimeBatchScheduler.class);
	
	@Autowired
	private IbkPollingClientService ibkPollingClientService;
 
//	@PostConstruct
	public void run() {
		
		logger.info("############   IbkPollingClientScheduler is started...    ############");
		
		try {
			ibkPollingClientService.polling();
		} catch (InterruptedException e) {
//			e.printStackTrace();
			logger.info("############   IbkPollingClientScheduler is failed...   ############");
			logger.error(e.getMessage());
		}
		
		logger.info("############   IbkPollingClientScheduler is finished...   ############");
	}
}
