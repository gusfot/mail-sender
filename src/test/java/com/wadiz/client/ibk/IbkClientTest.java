package com.wadiz.client.ibk;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Map;

import org.junit.Test;

import com.wadiz.client.Client;
import com.wadiz.client.Request;
import com.wadiz.client.RequestData;
import com.wadiz.client.Response;
import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.batch.model.OfferIfHeader;
import com.wadiz.client.ibk.batch.model.OfferIfTrailer;
import com.wadiz.client.ibk.batch.model.TbIbkOfferIfInfo;
import com.wadiz.client.ibk.batch.model.WDataList;
import com.wadiz.client.ibk.batch.model.WMessage;

public class IbkClientTest {

	@Test
	public void testInvokeRegist() {

		
		WMessage offerIfInfoMsg = getSampleOfferIfInfo();
		
		Client client = new IbkClient();
		Request request = new IbkRequest("regist");
		Response response = null;
		RequestData reqData = new IbkRequestData();
		reqData.setData(offerIfInfoMsg);
		request.setData(reqData);
		
		
		try {
			response = client.invoke(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(response.isSuccess());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testInvokeGetOfferResult() {

		
		WMessage offerIfInfoMsg = getSampleOfferIfInfo();
		
		Client client = new IbkClient();
		Request request = new IbkRequest("getOfferResult");
		
		Map parameters = request.getParameters();
		parameters.put("date", "20160106"); 
		
		Response response = null;
		RequestData reqData = new IbkRequestData();
		reqData.setData(offerIfInfoMsg);
		request.setData(reqData);
		
		
		try {
			response = client.invoke(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(response.isSuccess());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testInvokeGetInvestResult() {

		
		WMessage offerIfInfoMsg = getSampleOfferIfInfo();
		
		Client client = new IbkClient();
		Request request = new IbkRequest("getInvestResult");
		
		Map parameters = request.getParameters();
		parameters.put("date", "20160106"); 
		
		Response response = null;
		RequestData reqData = new IbkRequestData();
		reqData.setData(offerIfInfoMsg);
		request.setData(reqData);
		
		
		try {
			response = client.invoke(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(response.isSuccess());
	}
	
	
	
	
	
	
	
	
	
	
	
	private WMessage getSampleOfferIfInfo() {
		WMessage offerIfInfoMsg = new WMessage();
		WDataList<TbIbkOfferIfInfo> dataList = new WDataList<>();
		
		String ifCode = "0200";				// 업무구분
		String dataType = "01";				// 데이타구분, 고정
		String seq = "1";					// 일련번호
		String bankCode = "003";			// 은행코드, 고정
		String totalDataRecordCount="1";	// 총 DATA  RECORD 수
		String dealDate = "20150106";		// 거래발생일
		String filler = "";					// filler
		
		OfferIfHeader header = new OfferIfHeader();
		header.setIfCode(ifCode);
		header.setDataType(dataType);
		header.setSeq(seq);
		header.setBankCode(bankCode);
		header.setTotalDataRecordCount(totalDataRecordCount);
		header.setDealDate(dealDate);
		header.setFiller(filler);
		System.out.println("header 전문길이: " + StringUtil.byteLength(header.getMessage()));
		System.out.println("header 전문내용: " + header.getMessage());
		
		String bodyIfCode ="0100";		// 업무구분
		String bodyDataType ="05";		// 데이타구분, 고정
		Byte dataSeq = 1;				// 일련번호
		String offerName ="가나다라마바사아자타카타파하종록";
		String campaignId="11";
		String tranCompId = "A00001";
		String issuCorpName="가나다라마바사아자차카타파하주식회사";
		String businessRegNumber="1168115961";
		String securtType="01";
		String targetAmount="100000000";
		String periodStartDate="20160106";
		String periodFinishDate="20160107";
		String refundDate="20160108";
		String paymentDate="20160109";
		String receiptAccountNo="0123456789";
		String receiptDepositor="홍길동";
		String procType="00";
		Date regDate = new Date();
		
//		Date reqDate= DateFormatUtils.format(new Date(), "yyyyMMdd");
		
		TbIbkOfferIfInfo data = new TbIbkOfferIfInfo();
		
		data.setIfCode(bodyIfCode);		// 업무구눈
		data.setDataType(bodyDataType);	// 데이타구분
		data.setSeq(dataSeq);		// 일련번호
		data.setBankCode(bankCode); // 은행코드, 고정
		data.setTranCompId(tranCompId );	// 중계사ID
		data.setCampaignId(campaignId);	// 모집기업관리번호
		data.setOfferName(offerName);	// 청약종목명
		data.setIssuCorpName(issuCorpName);	//발행기관명-발행사
		data.setBusinessRegNumber(businessRegNumber);	// 발행사 사업자번호
		data.setSecurtType(securtType);					// 발행증권종류 01:지분증권,02:채무증권,03:투자계약증권
		data.setTargetAmount(targetAmount);				// 청약금액
		data.setPeriodStartDate(periodStartDate);		// 청약시작일자-YYYYMMDD
		data.setPeriodFinishDate(periodFinishDate);		// 청약종료일자-YYYYMMDD
		data.setRefundDate(refundDate);					// 환불일자-YYYYMMDD
		data.setPaymentDate(paymentDate);				// 납부일자-YYYYMMDD
		data.setReceiptAccountNo(receiptAccountNo);		// 주금납입 계좌번호
		data.setReceiptDepositor(receiptDepositor);		// 주금납입 예금주명
		data.setProcType(procType);						// 변경구분코드 00:등록, 01:변경, 09:삭제
		data.setRegDate(regDate);						// 발송(파일등록)시간
		data.setFiller(filler);
		
		
		dataList.add(data);
		
		System.out.println("dataList 전문: " + StringUtil.byteLength(dataList.getMessage()));
		System.out.println("dataList 전문: " + dataList.getMessage());
		
		OfferIfTrailer trailer = new OfferIfTrailer();
		trailer.setIfCode(ifCode);
		trailer.setDataType(dataType);
		trailer.setSeq(seq);
		trailer.setBankCode(bankCode);
		trailer.setTotalDataRecordCount(totalDataRecordCount);
		trailer.setFiller(filler);
		
		System.out.println("trailer 전문길이: " + StringUtil.byteLength(trailer.getMessage()));
		System.out.println("trailer 전문내용: " + trailer.getMessage());
		
		
		offerIfInfoMsg.setHeader(header);
		offerIfInfoMsg.setDataList(dataList);
		offerIfInfoMsg.setwTrailer(trailer);
		
		System.out.println("investIfInfoMsg 전문길이: " + StringUtil.byteLength(offerIfInfoMsg.getMessage()));
		System.out.println("investIfInfoMsg 전문내용: " + offerIfInfoMsg.getMessage());
		return offerIfInfoMsg;
	}

}
