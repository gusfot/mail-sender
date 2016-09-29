package com.wadiz.client.ibk.batch.parser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.batch.model.WMessage;

public class RefundResultParserTest {

	/**
	 * 청약정보 Text 파싱테스트
	 */
	@Test
	public void testParseRefund() {
		
//		String text = "0180HDRCWFA00001                  E004102    20160122110710000000000000234          00002342       00000000000000000234000000000250000.000000000000200000.0000202234      20160119184536";
		String text = "0180HDRCWFA00001                  E004102    20160129100435000000000000599          000002087      00000000000000000599000000000200000.000000000000200000.0000201599      20160128102839";
		
		IbkParser parser = new RefundResultInfoParser();
		WMessage message = parser.parse(text);
		
		
		
		assertTrue(message !=null);
		
	}
	
	@Test
	public void testIsPolling() {
		String reqMsg = "0180HDRCWFA00001";
		String headerPrefix = StringUtil.substringByte(reqMsg, 7, 7+3);
		System.out.println("headerPrefix : " + headerPrefix);
	}
	
	
	
}
