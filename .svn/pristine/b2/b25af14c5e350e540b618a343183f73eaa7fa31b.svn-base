package com.wadiz.client.ibk.polling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.batch.model.WMessage;
import com.wadiz.client.ibk.batch.parser.IbkParser;
import com.wadiz.client.ibk.batch.parser.RefundResultInfoParser;
import com.wadiz.client.ibk.batch.service.IbkRefundBatchService;
import com.wadiz.client.ibk.realtime.msg.req.Refund;

@Component("wadizSocketServer")
public class WadizSocketServer {
	
	private static final Logger logger = LoggerFactory.getLogger(WadizSocketServer.class);
	
    private Map<String, DataOutputStream> clients;
    private ServerSocket serverSocket;
 
    @Autowired 
    private IbkRefundBatchService ibkRefundBatchService;
    
    public static void main(String[] args) {
        new WadizSocketServer().start();
    }
 
    public WadizSocketServer() {
        clients = new HashMap<String, DataOutputStream>();
 
        // 여러 스레드에서 접근할 것이므로 동기화
        Collections.synchronizedMap(clients);
    }
 
//    @Async
    public void start() {
        try {
            Socket socket;
 
            // 리스너 소켓 생성
            serverSocket = new ServerSocket(11311);
            logger.debug("서버가 시작되었습니다.");
 
            // 클라이언트와 연결되면
            while (true) {
                // 통신 소켓을 생성하고 스레드 생성(소켓은 1:1로만 연결된다)
                socket = serverSocket.accept();
                ServerReceiver receiver = new ServerReceiver(socket);
                receiver.start();
            }
        } catch (IOException e) {
        	logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
 
    public void stop() {
    	
    	try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		}
    }
    
    class ServerReceiver extends Thread {
        Socket socket;
        DataInputStream input;
        DataOutputStream output;
 
        public ServerReceiver(Socket socket) {
            this.socket = socket;
            try {
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
            }
        }
 
        @Override
        public void run() {
            String name = "";
            
            try {
                // 클라이언트가 서버에 접속하면 서버에 알린다.
                name = socket.getInetAddress().toString();
                logger.debug("client : " + name+":"+socket.getPort());
 
                clients.put(name, output);
                logger.debug("[" + name + "]" + "님이 서버에 접속하였습니다.");
                logger.debug("현재 " + clients.size() + "명이 서버에 접속 중입니다.");
 
                // 메세지 전송
                while (input != null) {
                	
                	//Get the return message from the server
    				byte[] b = new byte[1024*10];
    				int size = socket.getInputStream().read(b);
    				
    				if(size!=-1) {
    					
    					int length = 4;
    					String msgLengthString = new String(b, 0, length);
    					
    					if(!"".equals(msgLengthString)) {
    						
    						try {
    							
	    						int msgLength = Integer.parseInt(msgLengthString); 
	    						String reqMsg = new String(b, 0, (length+msgLength));
	    						
	    						logger.info("[reqMsg] [" + reqMsg + "]"); //"relay"
	    						if(isPolling(reqMsg)) {
	    							logger.info("this is polling response message...");
	    						}else {
	    							logger.info("this is refund response message...");
	    							IbkParser parser = new RefundResultInfoParser();
	    							WMessage wMsg = parser.parse(reqMsg);
	    							Refund result = (Refund)wMsg.getHeader();
	    							logger.info("refunt result... : " + result.toString());
	    							ibkRefundBatchService.response(result);
	    						}
	    						
	    						String resMsg = "0020HDRRESPOLL"+DateFormatUtils.format(new Date(), "MMddHHmmss");
	    						logger.info("[resMsg] [" + resMsg + "]"); //"relay"
	    						
	    						sendToAll(resMsg);
    						
    						}catch(Exception e) {
    							logger.error(e.getMessage());
    							e.printStackTrace();
    						}
    						
    					}
    				}
                    
                }
                
            } catch (Exception e) {
            	logger.error(e.getMessage());
            	e.printStackTrace();
            } finally {
                // 접속이 종료되면
                clients.remove(name);
                logger.debug("[" + name+":"+socket.getPort() + "]" + "님이 서버에서 나갔습니다.");
                logger.debug("현재 " + clients.size() + "명이 서버에 접속 중입니다.");
            }
        }
 
        private boolean isPolling(String reqMsg) {
			String headerPrefix = StringUtil.substringByte(reqMsg, 7, 7+3);
			return "REQ".equals(headerPrefix) ? true:false;
		}

		public void sendToAll(String message) {
            Iterator<String> it = clients.keySet().iterator();
 
            while (it.hasNext()) {
                try {
                    DataOutputStream dos = clients.get(it.next());
                    dos.writeBytes(message);
                } catch (Exception e) {
                }
            }
        }
    }
}