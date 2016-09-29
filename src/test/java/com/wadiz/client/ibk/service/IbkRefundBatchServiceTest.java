package com.wadiz.client.ibk.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wadiz.client.ibk.batch.model.WMessage;
import com.wadiz.client.ibk.batch.parser.IbkParser;
import com.wadiz.client.ibk.batch.parser.RefundResultInfoParser;
import com.wadiz.client.ibk.batch.service.IbkRefundBatchService;
import com.wadiz.client.ibk.realtime.msg.req.Refund;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/META-INF/server-context.xml"})
public class IbkRefundBatchServiceTest {

	@Autowired
	private IbkRefundBatchService ibkRefundBatchService;
	
	@Test
	public void testResponse() {
		System.out.println(ibkRefundBatchService.toString());
		IbkParser parser = new RefundResultInfoParser();
		String text = "0180HDRCWFA00001                  E004102    20160129100435000000000000599          000002087      00000000000000000599000000000200000.000000000000200000.0000201599      20160128102839";
		WMessage wMsg = parser.parse(text);
//		System.out.println("wMsg... : " + wMsg.getMessage());\
		System.out.println("1");
		
		Refund refund = (Refund) wMsg.getHeader();
		System.out.println("refund : " + refund.toString());
		ibkRefundBatchService.response(refund);
	}
}
