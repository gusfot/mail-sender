package com.wadiz.client.ibk;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IbkBatchApp {

	public static void main(String[] args) {
		String springConfig = "/META-INF/client-context.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
	}
}
