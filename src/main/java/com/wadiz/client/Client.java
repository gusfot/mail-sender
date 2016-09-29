package com.wadiz.client;

public interface Client {

	public Response invoke(Request req) throws Exception;
	
}
