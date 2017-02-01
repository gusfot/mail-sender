package com.wadiz.client.ibk.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import com.wadiz.client.Request;
import com.wadiz.client.ibk.IbkRequest;
import com.wadiz.client.ibk.polling.IbkSocketClient;

public class IbkClientTest {

	private static final String POLLING_PREFIX = "0020HDRREQPOLL";
	
	@Test
	public void polling() throws InterruptedException {
		
		IbkSocketClient client = new IbkSocketClient();
		
		Request request = new IbkRequest("polling");
		
		String time = DateFormatUtils.format(new Date(), "MMddHHmmss");
		String reqMsg = POLLING_PREFIX+time;  // 0020HDRREQPOLLmmddhhmmss
		
		Map<Object, Object> parameters = new HashMap<>();
		parameters.put("message", reqMsg);
		request.setParameters(parameters );
		
		
		try {
			client.invoke(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
