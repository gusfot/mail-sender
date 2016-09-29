package com.wadiz.client.ibk.batch.msg;

import org.junit.Test;

import com.wadiz.client.ibk.batch.model.InvestIfHeader;
import com.wadiz.client.ibk.batch.model.InvestIfTrailer;
import com.wadiz.client.ibk.batch.model.TbIbkInvestIfInfo;
import com.wadiz.client.ibk.batch.model.WDataList;
import com.wadiz.client.ibk.batch.model.WMessage;
import com.wadiz.client.ibk.batch.msg.MessageFileGenerator;
import com.wadiz.client.ibk.batch.msg.MessageGenerator;

//@RunWith(SpringJUnit4ClassRunner.class)
public class InvestMessageGeneratorTest {

	/**
	 * 청약정보 파일생성
	 */
	@Test
	public void testGenerateInvestIfInfoMsgFile() {
		
		WMessage msg = getSampleInvestIfInfo();
		msg.messageInfo();
		MessageGenerator generator = new MessageFileGenerator();
		generator.generate(msg, null);
		
		
	}

	private WMessage getSampleInvestIfInfo() {
		WMessage investIfInfoMsg = new WMessage();
		WDataList<TbIbkInvestIfInfo> dataList = new WDataList<>();
		
		String ifCode = "0210";		//
		String dataType = "01";		// 고정
		String seq = "0000001";
		String relayId ="A00001";
		String bankCode = "003";
		String totalDataRecordCount="1";
		String dealDate = "20150106";
		String filler = "";
		
		InvestIfHeader header = new InvestIfHeader();
		header.setIfCode(ifCode);
		header.setDataType(dataType);
		header.setSeq(seq);
		header.setRelayId(relayId);
		header.setBankCode(bankCode);
		header.setTotalDataRecordCount(totalDataRecordCount);
		header.setDealDate(dealDate);
		header.setFiller(filler);
//		System.out.println("header 전문: " + StringUtil.byteLength(header.getMessage()));
		
		int ifid = 101;
		String bodyIfCode="0200";
		String bodyDataType="05";
		Byte dataSeq = 3;
		String payType = "01";
		// 프로젝트코드
		String offerName ="가나다라마바사아자타카타파하종록";
		// 원거래센터 전문관리번호
		// 투자자입금관리번호(고객식별자)
		// 전문일련번호
		
		
		String investAmount="100000";
		Integer userId = 123;
		String paymentsOfStocksYn = "Y";
		String payReqDate = "20150106";
		String payReqTime = "203023";
//		String resultCode = "000";
		String campaignId="11";
		Integer investId=245;
		
		TbIbkInvestIfInfo data = new TbIbkInvestIfInfo();
		data.setIfCode(bodyIfCode);
		data.setDataType(bodyDataType);
		data.setSeq(dataSeq);
		data.setPayType(payType);
		data.setCampaignId(campaignId);
		data.setOfferName(offerName);
		data.setInvestId(investId);
		data.setIfId(ifid);
		data.setInvestAmount(investAmount);
		data.setUserId(userId);
		data.setPaymentsOfStocksYn(paymentsOfStocksYn);
		data.setPayReqDate(payReqDate);
		data.setPayReqTime(payReqTime);
		data.setFiller(filler);
		
		dataList.add(data);
		
//		System.out.println("dataList 전문: " + StringUtil.byteLength(dataList.getMessage()));
//		System.out.println("dataList 전문: " + dataList.getMessage());
		
		InvestIfTrailer trailer = new InvestIfTrailer();
		trailer.setIfCode(ifCode);
		trailer.setDataType(dataType);
		trailer.setSeq(seq);
		trailer.setBankCode(bankCode);
		trailer.setTotalDataRecordCount(totalDataRecordCount);
		trailer.setFiller(filler);
		
//		System.out.println("trailer 전문: " + StringUtil.byteLength(trailer.getMessage()));
//		System.out.println("trailer 전문: " + trailer.getMessage());
		
		
		investIfInfoMsg.setHeader(header);
		investIfInfoMsg.setDataList(dataList);
		investIfInfoMsg.setwTrailer(trailer);
		
//		System.out.println("investIfInfoMsg 전문: " + StringUtil.byteLength(investIfInfoMsg.getMessage()));
//		System.out.println("investIfInfoMsg 전문: " + investIfInfoMsg.getMessage());
		return investIfInfoMsg;
	}
	
}
