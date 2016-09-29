package com.wadiz.client.ibk.polling.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.wadiz.client.ibk.polling.WadizSocketServer;
import com.wadiz.client.ibk.polling.service.IbkPollingServerService;

@Service
public class IbkPollingServerServiceImpl implements IbkPollingServerService {

	@Autowired
	@Qualifier("wadizSocketServer")
	private WadizSocketServer server;
	
	
	@Async
//	@PostConstruct
	@Override
	public void start() {
		server.start();
//		System.out.println("@@@@@ IbkPollingSocketServer is started!");
//		server.start();

	}
	
	@Override
	public void stop() {
		server.stop();
//		System.out.println("@@@@@ IbkPollingSocketServer is stopped!");
//		server.stop();

	}

}
