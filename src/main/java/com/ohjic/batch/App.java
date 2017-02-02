package com.ohjic.batch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	public static void main(String[] args) {
		String springConfig = "/META-INF/client-context.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
	}
}
