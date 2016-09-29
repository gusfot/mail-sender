package com.wadiz.client.ibk.realtime.msg;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wadiz.client.ibk.realtime.msg.SocketMessageGenerator;

public class SocketMessageGeneratorTest {

	@Test
	public void generate() {
		SocketMessageGenerator generator = new SocketMessageGenerator();
		String obj = "abcdefghijk";
		String msg = generator.generate(obj);
		
		System.out.println(msg);
		
		assertTrue(msg.length()>0);
	}
}
