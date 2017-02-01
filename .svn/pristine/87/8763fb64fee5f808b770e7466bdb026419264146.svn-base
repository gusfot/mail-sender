package com.wadiz.client.ibk.batch.parser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.batch.model.WMessage;

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
	
	/**
	 * 주금납입  파일 파싱테스트
	 */
	@Test
	public void testParseInvestResultFile() throws UnsupportedEncodingException{
		
		WMessage message = null;
		
		try {
			String fileName = "C:/home/ibkuser/senddata/cbk_agn_mngm_scur_0300_20160120_day";
			IbkParser parser = new InvestIfInfoParser();
			File file = new File(fileName);
//			String text = "0300010000000A00001003000000220160120                                                                                                                   0300050000001012       ASDF                                    234      000000000000000002340000234000000000250000132166              N201601191845360000300050000002012       ASDF                                    237      000000000000000002370000237000000000130000121351              N2016011909354900003000900000000030000002                                                                                                                                 ";
			message = parser.parse(file);
//			message = parser.parse(text);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		int messageLength = message.getMessage().getBytes("MS949").length;
		System.out.println("messageLength: " + messageLength);
		assertTrue(messageLength == 808);
		
	}
	
	@Test
	public void testText() {
		String text = "0300010000000A00001003000000220160120                                                                                                                   0300050000001012       ASDF                                    234      000000000000000002340000234000000000250000132166              N201601191845360000300050000002012       ASDF                                    237      000000000000000002370000237000000000130000121351              N2016011909354900003000900000000030000002                                                                                                                                 ";
		System.out.println(StringUtil.substringByte(text, 0, 152));
		System.out.println(StringUtil.substringByte(text, 152, 152+152));
		System.out.println(StringUtil.substringByte(text, 152+152, 152+152+152));
		System.out.println(StringUtil.substringByte(text, 152+152+152, 152+152+152+152));
	}
	
	
}
