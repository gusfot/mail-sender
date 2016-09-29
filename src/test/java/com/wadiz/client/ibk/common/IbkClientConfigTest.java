package com.wadiz.client.ibk.common;

import org.junit.Test;

import com.wadiz.client.ibk.common.config.IbkClientConfig;

public class IbkClientConfigTest {

	@Test
	public void testGet(){
		String msgDir = IbkClientConfig.get("ibk.msg.dir");
		System.out.println(msgDir);
	}
}
