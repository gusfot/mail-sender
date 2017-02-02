package com.ohjic.batch.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ohjic.batch.service.MailService;

@Component("mailBatchScheduler")
public class MailBatchScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(MailBatchScheduler.class);
	
	@Autowired
	private MailService mailService;
 
		
	public void run() {
		
		logger.info("############   mailBatchScheduler is started...    ############");
		
		try {
			
			mailService.send();
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			logger.error("############   mailBatchScheduler is failed...   ############");
			
		}
		
		logger.info("############   mailBatchScheduler is finished...   ############");
	}
	
}
