package com.wadiz.client.ibk.realtime.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadiz.client.Request;
import com.wadiz.client.Response;
import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.polling.RtIbkSocketClient;
import com.wadiz.client.ibk.realtime.IbkRequest;
import com.wadiz.client.ibk.realtime.msg.req.Refund;
import com.wadiz.client.ibk.realtime.service.IbkRtService;

@Service
public class IbkRtServiceImpl implements IbkRtService {

	private static final Logger logger = LoggerFactory.getLogger(IbkRtServiceImpl.class);
	
	private static final String POLLING_PREFIX = "0020HDRREQPOLL";

	@Autowired
	private RtIbkSocketClient ibkSocketClient;
	
	@Override
	public String refund(Refund refund) {
		
		Response response = null;
		
		try {
			
			Request request = new IbkRequest("cancel");
			String message = refund.getMessage();
			String header = StringUtils.leftPad(StringUtil.byteLength(message+"HDR")+"", 4 , "0")+"HDR";
			String fullMessage =  header+message;
			
			logger.debug("fullMessage :" + fullMessage);
			
			Map<Object, Object> parameters = new HashMap<>();
			parameters.put("message", fullMessage);
			request.setParameters(parameters );
			
			response = ibkSocketClient.invoke(request);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return response.toString();
	}
	

	@Override
	public void polling() {
		
		Request request = new IbkRequest("polling");
	
		while(true) {
			try {
				
				String message = DateFormatUtils.format(new Date(), "MMddHHmmss");
				String header = POLLING_PREFIX;
				String fullMessage = header+message;  // 0020HDRREQPOLLmmddhhmmss
				Map<Object, Object> parameters = new HashMap<>();
				parameters.put("message", fullMessage);
				request.setParameters(parameters );
		
				ibkSocketClient.invoke(request);
				
				Thread.sleep(1000*60*30);		// 30분마다 polling request 전송 
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
