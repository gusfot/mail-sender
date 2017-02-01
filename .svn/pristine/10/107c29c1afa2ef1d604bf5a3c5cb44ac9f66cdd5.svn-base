package com.wadiz.client.ibk;

import com.google.gson.Gson;
import com.wadiz.client.ResponseData;

/**
 * Ibk 응답데이터
 * @author hyunlae
 *
 */
public class IbkResponseData implements ResponseData {

	// KDS에서 받은 전문데이터
	private Object data;
	
	public IbkResponseData(Object responseData) {
		this.data = responseData;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toJson() {
		return new Gson().toJson(data);
	}

}
