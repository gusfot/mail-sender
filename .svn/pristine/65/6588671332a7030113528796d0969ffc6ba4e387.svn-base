package com.wadiz.client.ibk.persistence.mapper;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import com.wadiz.client.common.util.StringUtil;
import com.wadiz.client.ibk.model.InvestIfHeader;
import com.wadiz.client.ibk.model.InvestIfTrailer;
import com.wadiz.client.ibk.model.OfferIfHeader;
import com.wadiz.client.ibk.model.OfferIfTrailer;
import com.wadiz.client.ibk.model.TbIbkInvestIfInfo;
import com.wadiz.client.ibk.model.TbIbkOfferIfInfo;
import com.wadiz.client.ibk.model.WDataList;
import com.wadiz.client.ibk.model.WMessage;
import com.wadiz.client.ibk.parser.IbkParser;
import com.wadiz.client.ibk.parser.InvestIfInfoParser;
import com.wadiz.client.ibk.parser.OfferIfInfoParser;

//@RunWith(SpringJUnit4ClassRunner.class)
public class WMessageTest {

	@Test
	public void testCreateOffertIfInfoMsg() throws Exception {
		
		WMessage investIfInfoMsg = new WMessage();
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
		System.out.println("header 전문길이: " + header.getMessage().length());
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
		
		System.out.println("dataList 전문: " + dataList.getMessage().length());
		System.out.println("dataList 전문: " + dataList.getMessage());
		
		OfferIfTrailer trailer = new OfferIfTrailer();
		trailer.setIfCode(ifCode);
		trailer.setDataType(dataType);
		trailer.setSeq(seq);
		trailer.setBankCode(bankCode);
		trailer.setTotalDataRecordCount(totalDataRecordCount);
		trailer.setFiller(filler);
		
		System.out.println("trailer 전문길이: " + trailer.getMessage().length());
		System.out.println("trailer 전문내용: " + trailer.getMessage());
		
		
		investIfInfoMsg.setHeader(header);
		investIfInfoMsg.setDataList(dataList);
		investIfInfoMsg.setwTrailer(trailer);
		
		System.out.println("investIfInfoMsg 전문길이: " + investIfInfoMsg.getMessage().length());
		System.out.println("investIfInfoMsg 전문내용: " + investIfInfoMsg.getMessage());
		
		String content =  investIfInfoMsg.getMessage();
		String basepath = "d:/msg/depth1/depth2";
		File basepathDir = new File(basepath);
		
		if(!basepathDir.exists()) {
			basepathDir.mkdirs();
		}
		
		String fileName = "OFR" + "_" +DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyyMMdd");
		String fullpath = basepath+File.separator +fileName;

		Files.write( Paths.get(fullpath), content.getBytes(), StandardOpenOption.CREATE);
		
		
	}
	
	@Test
	public void testCreateInvestIfInfoMsg() throws Exception {
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
		System.out.println("header 전문: " + StringUtil.byteLength(header.getMessage()));
		
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
		
		System.out.println("dataList 전문: " + StringUtil.byteLength(dataList.getMessage()));
		System.out.println("dataList 전문: " + dataList.getMessage());
		
		InvestIfTrailer trailer = new InvestIfTrailer();
		trailer.setIfCode(ifCode);
		trailer.setDataType(dataType);
		trailer.setSeq(seq);
		trailer.setBankCode(bankCode);
		trailer.setTotalDataRecordCount(totalDataRecordCount);
		trailer.setFiller(filler);
		
		System.out.println("trailer 전문: " + StringUtil.byteLength(trailer.getMessage()));
		System.out.println("trailer 전문: " + trailer.getMessage());
		
		
		investIfInfoMsg.setHeader(header);
		investIfInfoMsg.setDataList(dataList);
		investIfInfoMsg.setwTrailer(trailer);
		
		System.out.println("investIfInfoMsg 전문: " + StringUtil.byteLength(investIfInfoMsg.getMessage()));
		System.out.println("investIfInfoMsg 전문: " + investIfInfoMsg.getMessage());
		
		String content =  investIfInfoMsg.getMessage();
		String basepath = "d:/msg/depth1/depth2";
		File basepathDir = new File(basepath);
		
		if(!basepathDir.exists()) {
			basepathDir.mkdirs();
		}
		
		String fileName = "INV" + "_" +DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyyMMdd");
		String fullpath = basepath+File.separator +fileName;

		Files.write( Paths.get(fullpath), content.getBytes("MS949"), StandardOpenOption.CREATE);
		
		
	}
	
	@Test
	public void testParseInvest() {
		String tmp2 = "0210010000000A00001003000000120160106                                                                                                                                                                   02100500000010100000001 이상한 세상                            000000001000000000000000000010000001000000010000000ABAB0000000000000001N20150105131200                                                   02100900000000030000001                                                                                                                                                                                 ";
		String wmp2 = "0210010000001A00001003000000120150106                                                                                                                                                                   020005000000301      11                        가나다라마바사아자타카타파하종록000000101000000000000000002450000245000000000100000                 123Y20150106203023                                                   02100100000010030000001                                                                                                                                                                                 ";
		
		String text = wmp2;
		
		IbkParser parser = new InvestIfInfoParser();
		WMessage message = parser.parse(text);
		
		assertTrue(message !=null);
		
	}
	
	
	@Test
	public void testParseOffer() {
		WMessage message = null;
		
		try {
			String tmp1 = "0100010000000003000000220160106                                                                                                                                                                                                                                                                                                                                                                                 0100050000001003A0000100000001 이상한 세상                             너명밤인                                                                                           1270875379010000000050000000000000002016010220160131201602052016020629106161604011       너명밤인                                         0020151229                                                                                           0100050000001003A0000100000002 E편한세상 만들기123                     어타초조                                                                                           1242555105030000000180000002016010520160130201602042016020542206000504013       어타초조                                         0020151229                                                                                           01000900000000030000002                                                                                                                                                                                                                                                                                                                                                                                         ";
			String wmp1 = "0200010000001003000000120150106                                                                                                                                                                                                                                                                                                                                                                                 0100050000001003A0000100000011                        가나다라마바사아자타카타파하종록                                                                                  가나다라마바사아자차카타파하주식회사11681159610100000010000000020160106201601072016010820160109          0123456789                                               홍길동0020160107                                                                                           020001      10030000001                                                                                                                                                                                                                                                                                                                                                                                         ";
			
			String text = wmp1;
			
			IbkParser parser = new OfferIfInfoParser();
			message = parser.parse(text);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(message !=null);
		
	}
	
	@Test
	public void testParseOfferFile() {
		
		WMessage message = null;
		
		try {
			
			IbkParser parser = new OfferIfInfoParser();
			String text = "0100010000000003000000220160106                                                                                                                                                                                                                                                                                                                                                                                 0100050000001003A0000100000001 이상한 세상                             너명밤인                                                                                           1270875379010000000050000002016010220160131201602052016020629106161604011       너명밤인                                         0020151229                                                                                           0100050000001003A0000100000002 E편한세상 만들기123                     어타초조                                                                                           1242555105030000000180000002016010520160130201602042016020542206000504013       어타초조                                         0020151229                                                                                           01000900000000030000002                                                                                                                                                                                                                                                                                                                                                                                         ";
//			File file = new File("D:/msg/depth1/depth2/OFR_20160106");
			File file = new File("C:/Users/hyunlae/Downloads/cbk_agn_mngm_scur_0100_20160106_day");
			message = parser.parse(file);
//			message = parser.parse(text);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(message !=null);
		
	}
	
	@Test
	public void testParseInvestFile(){
		
		WMessage message = null;
		
		try {
			
//			String text = "0210010000001A00001003000000120150106                                                                                                                                                                   020005000000301      11가나다라마바사아자타카타파하종록000000101000000000000000002450000245000000000100000                 123Y20150106203023                                                   02100100000010030000001                                                                                                                                                                                 ";
			IbkParser parser = new InvestIfInfoParser();
			File file = new File("D:/msg/depth1/depth2/INV_20160107");
			message = parser.parse(file);
//			message = parser.parse(text);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(message !=null);
		
	}
	

	
}
