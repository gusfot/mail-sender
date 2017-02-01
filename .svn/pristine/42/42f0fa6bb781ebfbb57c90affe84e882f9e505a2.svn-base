package com.wadiz.client.ibk.batch.parser;

import java.io.UnsupportedEncodingException;

import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.batch.model.OfferIfHeader;
import com.wadiz.client.ibk.batch.model.OfferIfTrailer;
import com.wadiz.client.ibk.batch.model.TbIbkOfferIfInfo;
import com.wadiz.client.ibk.batch.model.WDataList;
import com.wadiz.client.ibk.batch.model.WMessageable;

public class OfferIfInfoParser extends AbstractIbkBatchParser {

	
	private final static int FIXED_LENGTH = 400;
	
	@Override
	public WMessageable parseHeader(String text) {
		
		String headerText = StringUtil.substringByte(text, 0, FIXED_LENGTH);

		try {
			System.out.println("header text: " + headerText);
			System.out.println("header text length: " + headerText.getBytes("MS949").length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String ifCode               = StringUtil.substringByte(headerText, 0, 4);
		String dataType             = StringUtil.substringByte(headerText, 4, 4+2);
		String seq                  = StringUtil.substringByte(headerText, 4+2 ,4+2+7);
		String bankCode             = StringUtil.substringByte(headerText, 4+2+7, 4+2+7+3);
		String totalDataRecordCount = StringUtil.substringByte(headerText, 4+2+7+3, 4+2+7+3+7);
		String dealDate             = StringUtil.substringByte(headerText, 4+2+7+3+7, 4+2+7+3+7+8);
		String filler               = StringUtil.substringByte(headerText, 4+2+7+3+7+8, 4+2+7+3+7+369);
		
		OfferIfHeader header = new OfferIfHeader();
		header.setIfCode(ifCode);
		header.setDataType(dataType);
		header.setSeq(seq);
		header.setBankCode(bankCode);
		header.setTotalDataRecordCount(totalDataRecordCount);
		header.setDealDate(dealDate);
		header.setFiller(filler);
		
		return header;
	}
	
	@Override
	public WDataList parseDataList(String text) {
		
		
		String headerTotRecordsSize = text.substring(4+2+7+3,4+2+7+3+7);
		int dataSize = Integer.parseInt(headerTotRecordsSize);
		
		WDataList<TbIbkOfferIfInfo> dataList = new WDataList<>();
		
		String dataListText= "";
		
		dataListText = StringUtil.substringByte(text, FIXED_LENGTH, FIXED_LENGTH*(1+dataSize));	// 헤더, 트레일러 제외한 데이터리스트 추출
		
		try {
			System.out.println("dataListText text: " + dataListText);
			System.out.println("dataListText text length: " + dataListText.getBytes("MS949").length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < dataSize; i++) {
			
			int dataStartIndex = FIXED_LENGTH * i;
			
			String ifCode             = StringUtil.substringByte(dataListText,dataStartIndex, dataStartIndex+4).trim();
			String dataType           = StringUtil.substringByte(dataListText,dataStartIndex+4, dataStartIndex+4+2).trim();
			String seq                = StringUtil.substringByte(dataListText,dataStartIndex+4+2, dataStartIndex+4+2+7).trim();
			String bankCode           = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7, dataStartIndex+4+2+7+3).trim();
			String transCompId        = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3, dataStartIndex+4+2+7+3+6).trim();
			String campaignId         = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6, dataStartIndex+4+2+7+3+6+8).trim();
			String offerName          = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8, dataStartIndex+4+2+7+3+6+8+40).trim();
			String issuCorpName       = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40, dataStartIndex+4+2+7+3+6+8+40+100).trim();
			String bussinessRegNumber = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100, dataStartIndex+4+2+7+3+6+8+40+100+10).trim();
			String securtType         = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100+10, dataStartIndex+4+2+7+3+6+8+40+100+10+2).trim();
			String targetAmount       = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100+10+2, dataStartIndex+4+2+7+3+6+8+40+100+10+2+15).trim();
			String periodStartDate    = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100+10+2+15, dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8).trim();
			String periodFinishDate   = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8, dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8).trim();
			String refundDate         = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8, dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8).trim();
			String paymentDate        = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8, dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8+8).trim();
			String receiptAccountNo   = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8+8, dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8+8+20).trim();
			String receiptDepositor   = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8+8+20, dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8+8+20+50).trim();
			String procType           = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8+8+20+50, dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8+8+20+50+2).trim();
			String regDate            = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8+8+20+50+2, dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8+8+20+50+2+8).trim();
			String filler             = StringUtil.substringByte(dataListText,dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8+8+20+50+2+8, dataStartIndex+4+2+7+3+6+8+40+100+10+2+15+8+8+8+8+20+50+2+8+91).trim();
			
			TbIbkOfferIfInfo offer = new TbIbkOfferIfInfo();
			offer.setIfCode(ifCode);
			offer.setDataType(dataType);
			offer.setSeq(Byte.valueOf(seq));
			offer.setBankCode(bankCode);
			offer.setTranCompId(transCompId);
			offer.setCampaignId(campaignId);
			offer.setOfferName(offerName);
			offer.setIssuCorpName(issuCorpName);
			offer.setBusinessRegNumber(bussinessRegNumber);
			offer.setSecurtType(securtType);
			offer.setTargetAmount(targetAmount);
			offer.setPeriodStartDate(periodStartDate);
			offer.setPeriodFinishDate(periodFinishDate);
			offer.setRefundDate(refundDate);
			offer.setPaymentDate(paymentDate);
			offer.setReceiptAccountNo(receiptAccountNo);
			offer.setReceiptDepositor(receiptDepositor);
			offer.setProcType(procType);
//			offer.setRegDate(regDate);
			offer.setFiller(filler);
			
			dataList.add(offer);
		}
		
		return dataList;
	}

	@Override
	public WMessageable parseTrailer(String text) {
		
		
		String headerTotRecordsSize = text.substring(4+2+7+3,4+2+7+3+7);
		int dataSize = Integer.parseInt(headerTotRecordsSize);
		String trailerText = StringUtil.substringByte(text, FIXED_LENGTH + FIXED_LENGTH*dataSize, FIXED_LENGTH + FIXED_LENGTH*dataSize+FIXED_LENGTH);
		
		try {
			System.out.println("trailerText text: " + trailerText);
			System.out.println("trailerText text length: " + trailerText.getBytes("MS949").length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String ifCode               = StringUtil.substringByte(trailerText, 0, 4).trim();
		String dataType             = StringUtil.substringByte(trailerText, 4, 4+2).trim();
		String seq                  = StringUtil.substringByte(trailerText, 4+2, 4+2+7).trim();
		String bankCode             = StringUtil.substringByte(trailerText, 4+2+7, 4+2+7+3).trim();
		String totalDataRecordCount = StringUtil.substringByte(trailerText, 4+2+7+3, 4+2+7+3+7).trim();
		String filler               = StringUtil.substringByte(trailerText, 4+2+7+3+7, 4+2+7+3+7+377);
		
		OfferIfTrailer trailer = new OfferIfTrailer();
		trailer.setIfCode(ifCode);
		trailer.setDataType(dataType);
		trailer.setSeq(seq);
		trailer.setBankCode(bankCode);
		trailer.setTotalDataRecordCount(totalDataRecordCount);
		trailer.setFiller(filler);
		
		return trailer;
	}
	
}
