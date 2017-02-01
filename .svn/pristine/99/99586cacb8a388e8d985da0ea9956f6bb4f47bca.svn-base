package com.wadiz.client.ibk;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wadiz.client.ibk.polling.WadizSocketServer;

public class IbkBatchSocketServerApp {

	public static void main(String[] args) {
		String springConfig = "/META-INF/server-context.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		WadizSocketServer socketServer = (WadizSocketServer) context.getBean("wadizSocketServer");
		socketServer.start();
	}
}
