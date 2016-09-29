package com.wadiz.client;

/**
 * 응답객체 인터페이스
 * @author hyunlae
 *
 */
public interface Response {

	/**
	 * 응답된 데이터를 가져온다.
	 * @return
	 */
	public ResponseData getResponseData();
	
	/**
	 * 응답할 데이터를 세팅한다.
	 * @param data
	 */
	public void setResponseData(ResponseData data);
	
	/**
	 * 응답데이터의 성공여부
	 * @return
	 */
	public boolean isSuccess();
	
	/**
	 * 응답데이터의 성공여부를 세팅한다. 
	 * @param success
	 */
	public void setSuccess(boolean success);
	
	/**
	 * 응답메지시를 가져온다. (ex: 에러메세지)
	 * @return
	 */
	public String getMessage();
	
	/**
	 * 응답메세지를 세팅한다.
	 * @param message
	 */
	public void setMessage(String message);
	
	/**
	 * 응답데이터를 JSON 문자열로 변환한다.
	 * @return
	 */
//	public String getJsonData();
	
	
}
