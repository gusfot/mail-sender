package com.wadiz.client.ibk.batch.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.batch.model.WDataList;
import com.wadiz.client.ibk.batch.model.WMessageable;
import com.wadiz.client.ibk.realtime.msg.req.Refund;

public class RefundResultInfoParser extends AbstractIbkOnlineParser {

	private static final Logger logger = LoggerFactory.getLogger(RefundResultInfoParser.class);
	
	private final static int FIXED_LENGTH = 177;
	
	@Override
	public WMessageable parseHeader(String text) {
		
		Refund data = new Refund();
		
		String headerText = StringUtil.substringByte(text, 7, FIXED_LENGTH+7);
		logger.debug("전문내용 : {}", headerText);
		String ifCode       	= StringUtil.substringByte(headerText, 0, 3).trim();
		String tranCompId   	= StringUtil.substringByte(headerText, 3, 3+24).trim();
		String msgTypeCode  	= StringUtil.substringByte(headerText, 3+24, 3+24+6).trim();
		String flag				= StringUtil.substringByte(headerText, 3+24+6, 3+24+6+1).trim();
		String resCode      	= StringUtil.substringByte(headerText, 3+24+6+1, 3+24+6+1+4).trim();
		String sendDate 		= StringUtil.substringByte(headerText, 3+24+6+1+4, 3+24+6+1+4+14).trim();
		String refundId     	= StringUtil.substringByte(headerText, 3+24+6+1+4+14, 3+24+6+1+4+14+15).trim();
		String filler       	= StringUtil.substringByte(headerText, 3+24+6+1+4+14+15, 3+24+6+1+4+14+15+10).trim();
		String seq  	    	= StringUtil.substringByte(headerText, 3+24+6+1+4+14+15+10, 3+24+6+1+4+14+15+10+7).trim();
		String campaignId    	= StringUtil.substringByte(headerText, 3+24+6+1+4+14+15+10+7, 3+24+6+1+4+14+15+10+7+8).trim();
		String investId			= StringUtil.substringByte(headerText, 3+24+6+1+4+14+15+10+7+8, 3+24+6+1+4+14+15+10+7+8+20).trim();
		String investAmount		= StringUtil.substringByte(headerText, 3+24+6+1+4+14+15+10+7+8+20, 3+24+6+1+4+14+15+10+7+8+20+19).trim();
		String refundAmount		= StringUtil.substringByte(headerText, 3+24+6+1+4+14+15+10+7+8+20+19, 3+24+6+1+4+14+15+10+7+8+20+19+19).trim();
		String refundResnCode	= StringUtil.substringByte(headerText, 3+24+6+1+4+14+15+10+7+8+20+19+19, 3+24+6+1+4+14+15+10+7+8+20+19+19+2).trim();		
		String refundCode		= StringUtil.substringByte(headerText, 3+24+6+1+4+14+15+10+7+8+20+19+19+2, 3+24+6+1+4+14+15+10+7+8+20+19+19+2+2).trim();
		String orignInvestId	= StringUtil.substringByte(headerText, 3+24+6+1+4+14+15+10+7+8+20+19+19+2+2, 3+24+6+1+4+14+15+10+7+8+20+19+19+2+2+9).trim();
		String orignInvestDate	= StringUtil.substringByte(headerText, 3+24+6+1+4+14+15+10+7+8+20+19+19+2+2+9, 3+24+6+1+4+14+15+10+7+8+20+19+19+2+2+9+14).trim();
		
		
		// header
		data.setIfCode(ifCode);
		data.setTranCompId(tranCompId);
		data.setMsgTypeCode(msgTypeCode);
		data.setFlag(flag);
		data.setResCode(resCode);
		data.setSendDate(sendDate);
		data.setRefundId(Integer.parseInt(refundId));

		// data
		data.setSeq(Integer.parseInt(seq));
		data.setCampaignId(campaignId);
		data.setInvestId(investId);
		data.setInvestAmount((int)Double.parseDouble(investAmount)+"");
		data.setRefundAmount((int)Double.parseDouble(refundAmount)+"");
		data.setRefundResnCode(refundResnCode);
		data.setRefundCode(refundCode);
		data.setOrignInvestId(orignInvestId);
		data.setOrignInvestDate(orignInvestDate);
		
		
		data.setFiller("");
		
		return data;
	}

	@Override
	public WDataList parseDataList(String text) {
		logger.debug("this message is header = data");
		return null;
	}

	@Override
	public WMessageable parseTrailer(String text) {
		logger.debug("this message is header = data");
		return null;
	}
	
	
}
