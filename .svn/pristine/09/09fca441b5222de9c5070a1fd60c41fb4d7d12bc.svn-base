package com.wadiz.client.ibk.realtime;

import java.io.File;

import com.wadiz.client.Client;
import com.wadiz.client.Request;
import com.wadiz.client.Response;
import com.wadiz.client.ResponseData;
import com.wadiz.client.ibk.batch.model.WMessage;
import com.wadiz.client.ibk.batch.msg.MessageFileGenerator;
import com.wadiz.client.ibk.batch.msg.MessageGenerator;
import com.wadiz.client.ibk.batch.parser.IbkParser;
import com.wadiz.client.ibk.batch.parser.OfferIfInfoParser;

public class IbkClient implements Client {

	@Override
	public Response invoke(Request request) throws Exception {

		Response response = new IbkResponse();
		
		response = call(request, response);
		
		return response;
	}

	private Response call(Request request, Response response) {
		boolean result = false;
		
		if("regist".equals(request.getCallMethod())) {					// 파일등록
			
			result = regist(request, response);
			
		}else if("getOfferResult".equals(request.getCallMethod())) {	// 모집정보 결과조회
			
			result = getOfferResult(request, response);
			
		}else if("getInvestResult".equals(request.getCallMethod())) {	// 청약결과조회
			
			result = getInvestResult(request, response);
			
		}else {
			
			result = false;
		}
		
		if(result) {
			//response.setCode("SUSS000");
			response.setMessage("정상");
		}else {
			response.setMessage("실패");
		}
		
		return response;
	}

	private boolean getInvestResult(Request request, Response response) {
		

		boolean result = false;
		
		try {
			
			IbkParser parser = new OfferIfInfoParser();
			String prefix = "INV_";
			String date = (String) request.getParameters().get("date");
			String fullfilepath = "D:/msg/depth1/depth2/"+prefix+date ;
			File file = new File(fullfilepath);
			WMessage message = parser.parse(file);
			ResponseData data = new IbkResponseData(message);
			response.setResponseData(data);
			result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private boolean getOfferResult(Request request, Response response) {
		
		boolean result = false;
		
		try {
			
			IbkParser parser = new OfferIfInfoParser();
			String prefix = "OFR_";
			String date = (String) request.getParameters().get("date");
			String fullfilepath = "D:/msg/depth1/depth2/"+prefix+date ;
			File file = new File(fullfilepath);
			WMessage message = parser.parse(file);
			ResponseData data = new IbkResponseData(message);
			response.setResponseData(data);
			result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private boolean regist(Request request, Response response) {
		
		boolean result = false;
		
		try {
			
			WMessage msg = (WMessage) request.getData().getData();
			MessageGenerator generator = new MessageFileGenerator();
			String date = "";
			result = generator.generate(msg, date );
			result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
