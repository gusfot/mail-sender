package com.wadiz.client.ibk.parser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.wadiz.client.ibk.model.WMessage;

public class OfferIfInfoParserTest {

	
	@Test
	public void testParseOffer() {
		WMessage message = null;
		
		try {
			String tmp1 = "0100010000000003000000220160106                                                                                                                                                                                                                                                                                                                                                                                 0100050000001003A0000100000001 이상한 세상                             너명밤인                                                                                           1270875379010000000050000000000000002016010220160131201602052016020629106161604011       너명밤인                                         0020151229                                                                                           0100050000001003A0000100000002 E편한세상 만들기123                     어타초조                                                                                           1242555105030000000180000002016010520160130201602042016020542206000504013       어타초조                                         0020151229                                                                                           01000900000000030000002                                                                                                                                                                                                                                                                                                                                                                                         ";
			String wmp1 = "0200010000001003000000120150106                                                                                                                                                                                                                                                                                                                                                                                 0100050000001003A0000100000011        가나다라마바사아자타카타파하종록                                                                가나다라마바사아자차카타파하주식회사11681159610100000010000000020160106201601072016010820160109          0123456789                                            홍길동0020160108                                                                                           020001      10030000001                                                                                                                                                                                                                                                                                                                                                                                         ";
			String wmp2 = "0200010000001003000000120150106                                                                                                                                                                                                                                                                                                                                                                                 0100050000001003A0000100000011        가나다라마바사아자타카타파하종록                                                                가나다라마바사아자차카타파하주식회사11681159610100000010000000020160106201601072016010820160109          0123456789                                            홍길동0020160111                                                                                           020001      10030000001                                                                                                                                                                                                                                                                                                                                                                                         ";
			
			String text = wmp2;
			
			IbkParser parser = new OfferIfInfoParser();
			message = parser.parse(text);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(message !=null);
		
	}
	
	@Test
	public void testParseOfferFile() throws UnsupportedEncodingException {
		
		WMessage message = null;
		
		try {
			
//			String fileName = "C:/Users/hyunlae/Downloads/cbk_agn_mngm_scur_0100_20160106_day";
			String fileName = "D:/msg/depth1/depth2/OFR_20160110";
			
			IbkParser parser = new OfferIfInfoParser();
			File file = new File(fileName);
			message = parser.parse(file);
			message.messageInfo();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(message.getMessage().getBytes("MS949").length == 1200 );
		
	}
	
}
