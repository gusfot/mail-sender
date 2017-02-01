package com.wadiz.client.ibk.realtime.msg;

import org.apache.commons.lang3.StringUtils;

import com.wadiz.client.common.util.StringUtil;

public class SocketMessageGenerator {

	private static final String PREFIX="HDRKIUPBANK";
	
	public String generate(Object obj) {
		
		String text = obj.toString();
		int length = StringUtil.byteLength(PREFIX+text);
		String headerLength = StringUtils.leftPad(length+"", 4, "0");
		
		return headerLength + PREFIX +  text;
	}


}
