package com.wadiz.client.ibk.parser;

import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.model.InvestIfHeader;
import com.wadiz.client.ibk.model.InvestIfTrailer;
import com.wadiz.client.ibk.model.TbIbkInvestIfInfo;
import com.wadiz.client.ibk.model.WDataList;
import com.wadiz.client.ibk.model.WMessageable;

public class InvestIfInfoParser extends AbstractIbkParser {

	private final static int FIXED_LENGTH = 200;
	
	@Override
	public WMessageable parseHeader(String text) {
		
		InvestIfHeader header = new InvestIfHeader();
		
		String headerText = StringUtil.substringByte(text, 0, FIXED_LENGTH);
		
		System.out.println("header 전문길이: "  + StringUtil.byteLength(headerText));
		System.out.println("header 전문내용: "  + headerText);
		
		String ifCode               = StringUtil.substringByte(headerText, 0, 4).trim();
		String dataType             = StringUtil.substringByte(headerText, 4, 4+2).trim();
		String seq                  = StringUtil.substringByte(headerText, 4+2, 4+2+7).trim();
		String transId              = StringUtil.substringByte(headerText, 4+2+7,4+2+7+6).trim();
		String bankCode             = StringUtil.substringByte(headerText, 4+2+7+6,4+2+7+6+3).trim();
		String totalDataRecordCount = StringUtil.substringByte(headerText, 4+2+7+6+3,4+2+7+3+6+7).trim();
		String dealDate             = StringUtil.substringByte(headerText, 4+2+7+3+6+7, 4+2+7+3+6+7+8).trim();
		String filler               = StringUtil.substringByte(headerText, 4+2+7+3+6+7+8, 4+2+7+3+6+7+8+163).trim();
		
		header.setIfCode(ifCode);
		header.setDataType(dataType);
		header.setSeq(seq);
		header.setRelayId(transId);
		header.setBankCode(bankCode);
		header.setTotalDataRecordCount(totalDataRecordCount);
		header.setDealDate(dealDate);
		header.setFiller(filler);
		
		return header;
	}
	
	@Override
	public WDataList parseDataList(String text) {
		
		String headerTotRecordsSize = StringUtil.substringByte(text, 4+2+7+6+3,4+2+7+3+6+7);
		int dataSize = Integer.parseInt(headerTotRecordsSize);
		System.out.println("dataSize : " + dataSize);
		
		WDataList<TbIbkInvestIfInfo> dataList = new WDataList<>();
		
		String dataListText = StringUtil.substringByte(text, FIXED_LENGTH, FIXED_LENGTH*(1+dataSize)); // 헤더, 트레일러 제외한 데이터리스트 추출
		System.out.println("dataList 전문길이: "  + StringUtil.byteLength(dataListText));
		System.out.println("dataList 전문내용: "  + dataListText);
		
		for (int i = 0; i < dataSize; i++) {
			
			int dataStartIndex = FIXED_LENGTH*i;
			
			String ifCode             = StringUtil.substringByte(dataListText, dataStartIndex, dataStartIndex+4).trim();
			String dataType           = StringUtil.substringByte(dataListText, dataStartIndex+4, dataStartIndex+4+2).trim();
			String seq                = StringUtil.substringByte(dataListText, dataStartIndex+4+2, dataStartIndex+4+2+7).trim();
			String payType            = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7, dataStartIndex+4+2+7+2).trim();
			String campaignId	      = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7+2, dataStartIndex+4+2+7+2+8).trim();
			String offerName          = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7+2+8, dataStartIndex+4+2+7+2+8+40).trim();
			String ifId               = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7+2+8+40, dataStartIndex+4+2+7+2+8+40+9).trim();
			String investId1          = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7+2+8+40+9, dataStartIndex+4+2+7+2+8+40+9+20).trim();
			String investId2          = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7+2+8+40+9+20, dataStartIndex+4+2+7+2+8+40+9+20+7).trim();
			String investAmount       = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7+2+8+40+9+20+7, dataStartIndex+4+2+7+2+8+40+9+20+7+15).trim();
			String userId             = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7+2+8+40+9+20+7+15, dataStartIndex+4+2+7+2+8+40+9+20+7+15+20).trim();
			String paymentsOfStocksYn = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7+2+8+40+9+20+7+15+20, dataStartIndex+4+2+7+2+8+40+9+20+7+15+20+1).trim();
			String payReqDate         = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7+2+8+40+9+20+7+15+20+1, dataStartIndex+4+2+7+2+8+40+9+20+7+15+20+1+8).trim();
			String payReqTime         = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7+2+8+40+9+20+7+15+20+1+8, dataStartIndex+4+2+7+2+8+40+9+20+7+15+20+1+8+6).trim();
			String filler             = StringUtil.substringByte(dataListText, dataStartIndex+4+2+7+2+8+40+9+20+7+15+20+1+8+6, dataStartIndex+4+2+7+2+8+40+9+20+7+15+20+1+8+6+51).trim();
			
			TbIbkInvestIfInfo invest = new TbIbkInvestIfInfo();
			invest.setIfCode(ifCode);
			invest.setDataType(dataType);
			invest.setSeq(Byte.valueOf(seq));
			invest.setPayType(payType);
			invest.setCampaignId(campaignId);
			invest.setOfferName(offerName);
			invest.setIfId(Integer.parseInt(ifId));
			invest.setInvestId(Integer.parseInt(investId1));
			invest.setInvestAmount(investAmount);
			invest.setUserId(Integer.parseInt(userId));
			invest.setPaymentsOfStocksYn(paymentsOfStocksYn);
			invest.setPayReqDate(payReqDate);
			invest.setPayReqTime(payReqTime);
			invest.setFiller(filler);
			
			dataList.add(invest);
		}
		
		return dataList;
	}
	
	@Override
	public WMessageable parseTrailer(String text) {
		
		String headerTotRecordsSize = StringUtil.substringByte(text, 4+2+7+6+3,4+2+7+3+6+7).trim();
		int dataSize = Integer.parseInt(headerTotRecordsSize);
		System.out.println("beginIndex: " + FIXED_LENGTH*(1+dataSize));
		System.out.println("endIndex: " + FIXED_LENGTH*(1+dataSize)+FIXED_LENGTH);
		String trailerText = StringUtil.substringByte(text, FIXED_LENGTH*(1+dataSize), FIXED_LENGTH*(1+dataSize)+FIXED_LENGTH);
		
		System.out.println("1trailer 전문길이: "  + StringUtil.byteLength(trailerText));
		System.out.println("1trailer 전문내용: "  + trailerText);
		
		String ifCode               = StringUtil.substringByte(trailerText, 0, 4).trim();
		String dataType             = StringUtil.substringByte(trailerText, 4, 4+2).trim();
		String seq                  = StringUtil.substringByte(trailerText, 4+2, 4+2+7).trim();
		String bankCode             = StringUtil.substringByte(trailerText, 4+2+7, 4+2+7+3).trim();
		String totalDataRecordCount = StringUtil.substringByte(trailerText, 4+2+7+3, 4+2+7+3+7).trim();
		String filler               = StringUtil.substringByte(trailerText, 4+2+7+3+7, 4+2+7+3+7+177).trim();
		
		InvestIfTrailer trailer = new InvestIfTrailer();
		trailer.setIfCode(ifCode);
		trailer.setDataType(dataType);
		trailer.setSeq(seq);
		trailer.setBankCode(bankCode);
		trailer.setTotalDataRecordCount(totalDataRecordCount);
		trailer.setFiller(filler);
		
		return trailer;
	}

	
}
