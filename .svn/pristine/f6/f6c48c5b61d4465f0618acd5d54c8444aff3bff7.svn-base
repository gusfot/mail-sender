package com.wadiz.client.ibk.polling.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.wadiz.client.Request;
import com.wadiz.client.ibk.IbkRequest;
import com.wadiz.client.ibk.polling.RtIbkSocketClient;
import com.wadiz.client.ibk.polling.service.IbkPollingClientService;

@Service
public class IbkPollingClientServiceImpl implements IbkPollingClientService {

	private static final Logger logger = LoggerFactory.getLogger(IbkPollingClientServiceImpl.class);
	
	private static final String POLLING_PREFIX = "0020HDRREQPOLL";
	
	@Autowired
	private RtIbkSocketClient client;
	
	@Async
	@Override
	public void polling() throws InterruptedException {
		
		logger.debug("@@@@@@@@  IbkPollingClientService is started......  @@@@@@@@");
		
		Request request = new IbkRequest("polling");
		
		try {
			
			String message = DateFormatUtils.format(new Date(), "MMddHHmmss");
			String header = POLLING_PREFIX;
			String fullMessage = header+message;  // 0020HDRREQPOLLmmddhhmmss
			Map<Object, Object> parameters = new HashMap<>();
			parameters.put("message", fullMessage);
			request.setParameters(parameters );
			
			client.invoke(request);
			
			Thread.sleep(1000*60*1);		// 30분마다 polling request 전송 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.debug("@@@@@@@@  IbkPollingClientService is ready......  @@@@@@@@");
	}
	
	@PreDestroy
	public void socketClose() {
		logger.debug("@@@@@@@@  IbkPollingClientService is stopped......  @@@@@@@@");
		
		//client.socketClose();
	}

}
