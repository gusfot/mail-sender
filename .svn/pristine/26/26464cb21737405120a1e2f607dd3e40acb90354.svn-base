package com.wadiz.client.ibk.realtime;

import java.util.HashMap;
import java.util.Map;

import com.wadiz.client.Request;
import com.wadiz.client.RequestData;

public class IbkRequest implements Request {

	private RequestData data;
	
	private String callMethod;
	
	private Map<Object, Object> parameters = new HashMap<>();
	
	public IbkRequest(String callMethod) {
		this.setCallMethod(callMethod);
	}

	@Override
	public void setData(RequestData data) {
		this.data = data;
	}

	@Override
	public RequestData getData() {
		return data;
	}

	@Override
	public String getCallMethod() {
		return callMethod;
	}

	@Override
	public void setCallMethod(String callMethod) {
		this.callMethod = callMethod;
	}

	@Override
	public Map<Object, Object> getParameters() {
		return parameters;
	}

	@Override
	public void setParameters(Map<Object, Object> parameters) {
		this.parameters = parameters;
	}

}
