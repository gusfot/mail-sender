package com.wadiz.client.ibk.polling;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wadiz.client.Client;
import com.wadiz.client.Request;
import com.wadiz.client.Response;
import com.wadiz.client.ResponseData;
import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.IbkRequest;
import com.wadiz.client.ibk.IbkResponse;
import com.wadiz.client.ibk.IbkResponseData;

public class IbkSocketClient implements Client {

	private static final Logger logger = LoggerFactory.getLogger(IbkSocketClient.class);
	
	private static final String POLLING_PREFIX = "0020HDRREQPOLL";
	private static final String HOST_IP = "203.235.70.167";
//	private static final String HOST_IP = "125.141.145.230";
	private static final int HOST_PORT = 11311;
	
//	private Socket socket = null;
	
//	@Autowired
//	@Qualifier("wadizSocket")
	private Socket socket;
	
	private OutputStream outputStream = null;
	private InputStream inputStream =  null;
	
	public IbkSocketClient() {
		while (true) {
			try {
				
				if (socket != null) { 
					break; 
				}else {
//					socket = new WadizSocket();
					socket = new Socket(HOST_IP, HOST_PORT);
					logger.debug("1socket is connected...");
				}
		       
				
			}catch (IOException e) { 
				logger.debug(e.getMessage());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		}
	}
	@Override
	public Response invoke(Request request) throws Exception {
		
		Response response = new IbkResponse();
		String  message = (String) request.getParameters().get("message");

        try {
        		// 소켓 연결이 안될겨우 연결 재시도
        		while (true) {
					try {
						
						if (socket != null) { 
							logger.info(socket.toString());
							break; 
						}else {
//							socket = new WadizSocket();
							socket = new Socket(HOST_IP, HOST_PORT);
							logger.debug("2socket is connected...");
						}
				       
						
					}catch (IOException e) { 
						logger.debug(e.getMessage());
						Thread.sleep(1000); 
					}
				}
        		
				// Send the message to the server
//				 OutputStream os = socket.getOutputStream();
//				
//				if(outputStream == null) {
					outputStream = socket.getOutputStream();
//				}
				
//				if(inputStream == null) {
					inputStream = socket.getInputStream();
//				}
				logger.debug(outputStream.toString());
				logger.debug(inputStream.toString());
				OutputStreamWriter osw = new OutputStreamWriter(outputStream);

				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(message);
				bw.newLine();
//				bw.flush();
//				bw.close();
				
				logger.debug("[req msg] : "+message);
				String responseMsg= null;
				
				 if(isPollingMessage(message)) {
					 logger.debug("this is polling message...");
					//Get the return message from the server
						byte[] b = new byte[1024*10];
						//int size = socket.getInputStream().read(b);
						int size = inputStream.read(b);
						
						
						if(size != -1) {
							responseMsg = new String(b, 0, size);
							logger.debug("[res msg] : "+responseMsg);
						}
				 }else {
					 logger.debug("this is refund message...");
				 }
				
				
				ResponseData data = new IbkResponseData(responseMsg);
				response.setResponseData(data );
//				outputStream.close();
//				inputStream.close();
        	
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


	@PreDestroy
	public void socketClose() {
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
	

	public static void main(String[] args) {
		
		try {
			polling();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * polling request 전송
	 * @throws InterruptedException
	 */
	public static void polling() throws InterruptedException {
		
		IbkSocketClient client = new IbkSocketClient();

		while(true) {
			
			try {
				
				Request request = new IbkRequest("polling");
				String message = POLLING_PREFIX+DateFormatUtils.format(new Date(), "MMddHHmmss");  // 0020HDRREQPOLLmmddhhmmss
				Map<Object, Object> parameters = new HashMap<>();
				parameters.put("message", message);
				request.setParameters(parameters );
				
				client.invoke(request);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Thread.sleep(1000*10*1);		// 30분마다 polling request 전송 
		}
	}

	

}
