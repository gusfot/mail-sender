package com.wadiz.client.ibk.batch.msg;

import java.util.Date;

import org.junit.Test;

import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.batch.model.OfferIfHeader;
import com.wadiz.client.ibk.batch.model.OfferIfTrailer;
import com.wadiz.client.ibk.batch.model.TbIbkOfferIfInfo;
import com.wadiz.client.ibk.batch.model.WDataList;
import com.wadiz.client.ibk.batch.model.WMessage;

//@RunWith(SpringJUnit4ClassRunner.class)
public class OfferMessageGeneratorTest {

	/**
	 * 모집정보 파일생성
	 */
	@Test
	public void testGenerateOfferIfInfoMsgFile() {
		
		WMessage offerIfInfoMsg = getSampleOfferIfInfo();
		offerIfInfoMsg.messageInfo();
		
		MessageGenerator generator = new MessageFileGenerator();
		generator.generate(offerIfInfoMsg, null);
		
		
	}

	
	private WMessage getSampleOfferIfInfo() {
		WMessage offerIfInfoMsg = new WMessage();
		WDataList<TbIbkOfferIfInfo> dataList = new WDataList<>();
		
		String ifCode = "0100";				// 업무구분
		String dataType = "01";				// 데이타구분, 고정
		String seq = "0";					// 일련번호
		String bankCode = "003";			// 은행코드, 고정
		String totalDataRecordCount="2";	// 총 DATA  RECORD 수
		String dealDate = "20150111";		// 거래발생일
		String filler = "";					// filler
		String trailerDataType = "09";
		
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
		String offerName1 ="가나다라마바사아자타카타파종목1";
		String campaignId1="12";
		String tranCompId = "A00001";
		String issuCorpName1="가나다라마바사아자차카타파주식회사1";
		String businessRegNumber="1270875379";
		String securtType="01";
		String targetAmount="200000000";
		String periodStartDate="20160106";
		String periodFinishDate="20160110";
		String refundDate="20160111";
		String paymentDate="20160112";
		String receiptAccountNo="123456789";
		String receiptDepositor="홍길동";
		String procType="00";
		Date regDate = new Date();
		
		TbIbkOfferIfInfo data1 = new TbIbkOfferIfInfo();
		
		data1.setIfCode(bodyIfCode);		// 업무구눈
		data1.setDataType(bodyDataType);	// 데이타구분
		data1.setSeq(dataSeq);		// 일련번호
		data1.setBankCode(bankCode); // 은행코드, 고정
		data1.setTranCompId(tranCompId );	// 중계사ID
		data1.setCampaignId(campaignId1);	// 모집기업관리번호
		data1.setOfferName(offerName1);	// 청약종목명
		data1.setIssuCorpName(issuCorpName1);	//발행기관명-발행사
		data1.setBusinessRegNumber(businessRegNumber);	// 발행사 사업자번호
		data1.setSecurtType(securtType);					// 발행증권종류 01:지분증권,02:채무증권,03:투자계약증권
		data1.setTargetAmount(targetAmount);				// 청약금액
		data1.setPeriodStartDate(periodStartDate);		// 청약시작일자-YYYYMMDD
		data1.setPeriodFinishDate(periodFinishDate);		// 청약종료일자-YYYYMMDD
		data1.setRefundDate(refundDate);					// 환불일자-YYYYMMDD
		data1.setPaymentDate(paymentDate);				// 납부일자-YYYYMMDD
		data1.setReceiptAccountNo(receiptAccountNo);		// 주금납입 계좌번호
		data1.setReceiptDepositor(receiptDepositor);		// 주금납입 예금주명
		data1.setProcType(procType);						// 변경구분코드 00:등록, 01:변경, 09:삭제
		data1.setRegDate(regDate);						// 발송(파일등록)시간
		data1.setFiller(filler);
		
		
		dataList.add(data1);
		
		Byte data2Seq =2;
		String campaignId2 ="13";
		String offerName2 ="가나다라마바사아자타카타파종목2";
		String issuCorpName2="가나다라마바사아자차카타파주식회사2";
		String businessRegNumber2 = "1242555105";
		String targetAmount2 = "300000000";
		String receiptAccountNo2="2424242424";
		String receiptDepositor2="춘향이";
		
		TbIbkOfferIfInfo data2 = new TbIbkOfferIfInfo();
		
		data2.setIfCode(bodyIfCode);		// 업무구눈
		data2.setDataType(bodyDataType);	// 데이타구분
		data2.setSeq(data2Seq );		// 일련번호
		data2.setBankCode(bankCode); // 은행코드, 고정
		data2.setTranCompId(tranCompId );	// 중계사ID
		data2.setCampaignId(campaignId2);	// 모집기업관리번호
		data2.setOfferName(offerName2);	// 청약종목명
		data2.setIssuCorpName(issuCorpName2);	//발행기관명-발행사
		data2.setBusinessRegNumber(businessRegNumber2);	// 발행사 사업자번호
		data2.setSecurtType(securtType);					// 발행증권종류 01:지분증권,02:채무증권,03:투자계약증권
		data2.setTargetAmount(targetAmount2);				// 청약금액
		data2.setPeriodStartDate(periodStartDate);		// 청약시작일자-YYYYMMDD
		data2.setPeriodFinishDate(periodFinishDate);		// 청약종료일자-YYYYMMDD
		data2.setRefundDate(refundDate);					// 환불일자-YYYYMMDD
		data2.setPaymentDate(paymentDate);				// 납부일자-YYYYMMDD
		data2.setReceiptAccountNo(receiptAccountNo2);		// 주금납입 계좌번호
		data2.setReceiptDepositor(receiptDepositor2);		// 주금납입 예금주명
		data2.setProcType(procType);						// 변경구분코드 00:등록, 01:변경, 09:삭제
		data2.setRegDate(regDate);						// 발송(파일등록)시간
		data2.setFiller(filler);
		
		
//		dataList.add(data2);
		
//		System.out.println("dataList 전문: " + StringUtil.byteLength(dataList.getMessage()));
//		System.out.println("dataList 전문: " + dataList.getMessage());
		
		OfferIfTrailer trailer = new OfferIfTrailer();
		trailer.setIfCode(ifCode);
		trailer.setDataType(trailerDataType);
		trailer.setSeq(seq);
		trailer.setBankCode(bankCode);
		trailer.setTotalDataRecordCount(totalDataRecordCount);
		trailer.setFiller(filler);
		
//		System.out.println("trailer 전문길이: " + StringUtil.byteLength(trailer.getMessage()));
//		System.out.println("trailer 전문내용: " + trailer.getMessage());
		
		
		offerIfInfoMsg.setHeader(header);
		offerIfInfoMsg.setDataList(dataList);
		offerIfInfoMsg.setwTrailer(trailer);
		
//		System.out.println("investIfInfoMsg 전문길이: " + StringUtil.byteLength(offerIfInfoMsg.getMessage()));
//		System.out.println("investIfInfoMsg 전문내용: " + offerIfInfoMsg.getMessage());
		return offerIfInfoMsg;
	}
	
}
