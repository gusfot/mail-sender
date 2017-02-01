package com.wadiz.client.ibk.batch.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wadiz.client.ibk.realtime.msg.req.Refund;
import com.wadiz.client.ibk.realtime.service.IbkRtService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/META-INF/kdsclient-context.xml"})
public class IbkRtServiceTest {

	@Autowired
	private IbkRtService ibkRtService;
	
	@Test
	public void registOffer() {
		
		String date = "20160111";
		Refund refund = new Refund();
		
		String ifCode = "CWR";
		String tranCompId = "A00001";
		String msgTypeCode = "E00400";
		String flag = "C";
		String resCode = "";
		String sendDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		String filler = "";
		refund.setIfCode(ifCode);
		refund.setTranCompId(tranCompId);
		refund.setMsgTypeCode(msgTypeCode);
		refund.setFlag(flag);
		refund.setResCode(resCode);
		refund.setSendDate(sendDate);
		refund.setFiller(filler);
		
		
		int refundId = 1;
		String campaignId = "";
		String investId = "";
		String investAmount = "";
		String refundAmount = "";
		String refundResnCode = "";
		String refundCode = "";
		String orignInvestId = "";
		String orignInvestDate = "";
		
		refund.setRefundId(refundId);
		refund.setCampaignId(campaignId);
		refund.setInvestId(investId);
		refund.setInvestAmount(investAmount);
		refund.setRefundAmount(refundAmount);
		refund.setRefundResnCode(refundResnCode);
		refund.setRefundCode(refundCode);
		refund.setOrignInvestId(orignInvestId);
		refund.setOrignInvestDate(orignInvestDate);
		
		String result = ibkRtService.refund(refund);
		
		assertTrue(result != null);
	}
	
	
}
