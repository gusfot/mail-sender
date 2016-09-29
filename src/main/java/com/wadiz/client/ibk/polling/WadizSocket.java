package com.wadiz.client.ibk.polling;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.UnknownHostException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wadiz.client.ibk.common.config.IbkClientConfig;

//@Component("wadizSocket")
//@Scope("singleton")
public class WadizSocket extends Socket{

	private final static String HOST_IP = IbkClientConfig.get("ibk.socket.host.ip");
	private final static int HOST_PORT = IbkClientConfig.getInteger("ibk.socket.host.port");
	
	public WadizSocket() throws IOException {
			this(HOST_IP, HOST_PORT);
	}

	public WadizSocket(InetAddress address, int port, InetAddress localAddr, int localPort) throws IOException {
		super(address, port, localAddr, localPort);
		// TODO Auto-generated constructor stub
	}

	public WadizSocket(InetAddress address, int port) throws IOException {
		super(address, port);
		// TODO Auto-generated constructor stub
	}

	public WadizSocket(Proxy proxy) {
		super(proxy);
		// TODO Auto-generated constructor stub
	}

	public WadizSocket(SocketImpl impl) throws SocketException {
		super(impl);
		// TODO Auto-generated constructor stub
	}

	public WadizSocket(String host, int port, InetAddress localAddr, int localPort) throws IOException {
		super(host, port, localAddr, localPort);
		// TODO Auto-generated constructor stub
	}

	public WadizSocket(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
		// TODO Auto-generated constructor stub
	}

}
