package com.wadiz.client.ibk.polling;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wadiz.client.Client;
import com.wadiz.client.Request;
import com.wadiz.client.Response;
import com.wadiz.client.ResponseData;
import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.IbkResponse;
import com.wadiz.client.ibk.IbkResponseData;
import com.wadiz.client.ibk.common.config.IbkClientConfig;

@Component
public class RtIbkSocketClient implements Client {

	private static final Logger logger = LoggerFactory.getLogger(RtIbkSocketClient.class);
	
	private static final String POLLING_PREFIX = "0020HDRREQPOLL";
	private static final String HOST_IP = IbkClientConfig.get("ibk.socket.host.ip");;
	private static final int HOST_PORT = IbkClientConfig.getInteger("ibk.socket.host.port");

	private static Socket socket =null;
	

	@Override
	public Response invoke(Request request) throws Exception {
		
		Response response = new IbkResponse();
		String  message = (String) request.getParameters().get("message");


        try {
        	
        		// 소켓 연결이 안될겨우 연결 재시도
        		while (true) {
					try {
						
						if (socket != null) { 
							break;
						}else {
							socket = new Socket(HOST_IP, HOST_PORT);
							logger.debug("retry.... connected to the Ibk Server");
						}
						
					}catch (IOException e) { 
						logger.debug(e.getMessage());
						Thread.sleep(1000); 
					}
				}
		        	 
				
				// Send the message to the server
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(message);
				bw.flush();
				 
				if(isPollingMessage(message)) {
					 logger.debug("this is polling message...");
					//Get the return message from the server
						byte[] b = new byte[1024*10];
						int size = socket.getInputStream().read(b);
						
						
						if(size != -1) {
							String responseMsg = new String(b, 0, size);
							logger.debug("[res msg] : "+responseMsg);
						}
				 }else {
					 logger.debug("this is refund message...");
				 }

				String responseMsg = "";
				ResponseData data = new IbkResponseData(responseMsg );
				response.setResponseData(data );
				
				logger.debug("client .............................................."); 
    	} catch (UnknownHostException e) {
		    e.printStackTrace();
    	} catch (IOException e) {
		    e.printStackTrace();
        }finally {
//            socketClose(socket);
        }
		 
		return response;
	}
	
	public Response polling(Request request) throws Exception {
		
		Response response = new IbkResponse();
		String  message = (String) request.getParameters().get("message");
		

        try {
        	
        		// 소켓 연결이 안될겨우 연결 재시도
        		while (true) {
					try {
						
						if (socket != null) { 
							break; 
						}else {
							socket = new Socket(HOST_IP, HOST_PORT);
							System.out.println("socket is connected...");
						}
				       
						
					}catch (IOException e) { 
						System.out.println(e.getMessage());
						Thread.sleep(1000); 
					}
				}
				
				// Send the message to the server
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(message);
				bw.flush();
				 
				 
				//Get the return message from the server
				byte[] b = new byte[4096];
				int size = socket.getInputStream().read(b);
				 
				String responseMsg = new String(b, 0, size);
				
				System.out.println("[req msg] : "+message);
				System.out.println("[res msg] : "+responseMsg);
				 
				ResponseData data = new IbkResponseData(responseMsg);
				response.setResponseData(data );
				
        	
    	} catch (UnknownHostException e) {
		    e.printStackTrace();
    	} catch (IOException e) {
		    e.printStackTrace();
        }finally {
        	
            // socketClose(socket);
        }
		 
		return response;
	}
	
	private boolean isPollingMessage(String message) {
		return "0020".equals(StringUtil.substringByte(message, 0, 4)) ? true: false;
	}

	public void socketClose(Socket socket) {
		//Closing the socket
		try
		{
		    socket.close();
		    logger.debug("disconnected to the Ibk Server");
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		}
	}
	

	public Socket getSocket() {
		return socket;
	}

}
