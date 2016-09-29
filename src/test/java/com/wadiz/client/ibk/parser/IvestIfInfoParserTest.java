package com.wadiz.client.ibk.parser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.wadiz.client.ibk.model.WMessage;

public class IvestIfInfoParserTest {

	/**
	 * 청약정보 Text 파싱테스트
	 */
	@Test
	public void testParseInvest() {
		
		String tmp2 = "0210010000000A00001003000000120160106                                                                                                                                                                   02100500000010100000001 이상한 세상                            000000001000000000000000000010000001000000010000000ABAB0000000000000001N20150105131200                                                   02100900000000030000001                                                                                                                                                                                 ";
		String wmp2 = "0210010000001A00001003000000120150106                                                                                                                                                                   020005000000301      11                        가나다라마바사아자타카타파하종록000000101000000000000000002450000245000000000100000                 123Y20150106203023                                                   02100100000010030000001                                                                                                                                                                                 ";
		String wmp3 = "0210010000001A00001003000000120150106                                                                                                                                                                   020005000000301      11가나다라마바사아자타카타파하종록000000101000000000000000002450000245000000000100000                 123Y20150106203023                                                   02100100000010030000001                                                                                                                                                                                                 ";
		String text = wmp2;
		
		IbkParser parser = new InvestIfInfoParser();
		WMessage message = parser.parse(text);
		
		assertTrue(message !=null);
		
	}
	
	
	/**
	 * 청약정보  파일 파싱테스트
	 */
	@Test
	public void testParseInvestFile() throws UnsupportedEncodingException{
		
		WMessage message = null;
		
		try {
			String fileName = "D:/msg/depth1/depth2/INV_20160110";
//			String fileName = "C:/Users/hyunlae/Downloads/cbk_agn_mngm_scur_0200_20160106_day";
			IbkParser parser = new InvestIfInfoParser();
			File file = new File(fileName);
			message = parser.parse(file);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		int messageLength = message.getMessage().getBytes("MS949").length;
		
		assertTrue(messageLength == 600);
		
	}
	
	
}
