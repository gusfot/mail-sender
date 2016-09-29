package com.wadiz.client.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wadiz.client.ibk.batch.service.IbkRefundBatchService;

@Component("ibkRefundScheduler")
public class IbkRefundScheduler {

private static final Logger logger = LoggerFactory.getLogger(IbkRefundScheduler.class);
	
	@Autowired
	private IbkRefundBatchService ibkRefundBatchService;
 
	public void run() {
		
		logger.info("############   IbkRefundScheduler is started...    ############");
		
		try {
			ibkRefundBatchService.request();
		}catch(Exception e) {
			logger.info("############   IbkRefundScheduler is failed...   ############");
			logger.error(e.getMessage());
		}
		
		logger.info("############   IbkRefundScheduler is finished...   ############");
	}
}
