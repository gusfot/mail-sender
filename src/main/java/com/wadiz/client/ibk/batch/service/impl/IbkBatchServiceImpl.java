package com.wadiz.client.ibk.batch.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.wadiz.client.ibk.batch.model.InvestIfHeader;
import com.wadiz.client.ibk.batch.model.InvestIfTrailer;
import com.wadiz.client.ibk.batch.model.OfferIfHeader;
import com.wadiz.client.ibk.batch.model.OfferIfTrailer;
import com.wadiz.client.ibk.batch.model.TbIbkInvestIfInfo;
import com.wadiz.client.ibk.batch.model.TbIbkOfferIfInfo;
import com.wadiz.client.ibk.batch.model.TbInvest;
import com.wadiz.client.ibk.batch.model.WDataList;
import com.wadiz.client.ibk.batch.model.WMessage;
import com.wadiz.client.ibk.batch.msg.MessageFileGenerator;
import com.wadiz.client.ibk.batch.msg.MessageGenerator;
import com.wadiz.client.ibk.batch.parser.IbkParser;
import com.wadiz.client.ibk.batch.parser.InvestIfInfoParser;
import com.wadiz.client.ibk.batch.persistence.TbIbkInvestIfInfoMapper;
import com.wadiz.client.ibk.batch.persistence.TbIbkOfferIfInfoMapper;
import com.wadiz.client.ibk.batch.persistence.TbInvestMapper;
import com.wadiz.client.ibk.batch.service.IbkBatchService;
import com.wadiz.client.ibk.common.config.IbkClientConfig;

@Service("ibkBatchService")
public class IbkBatchServiceImpl implements IbkBatchService {

	private static final Logger logger = LoggerFactory.getLogger(IbkBatchServiceImpl.class);
	
	private static final String INVEST_RESULT_CODE = "000";

	@Autowired
	private TbIbkOfferIfInfoMapper tbIbkOfferIfInfoMapper;
	
	@Autowired
	private TbIbkInvestIfInfoMapper tbIbkInvestIfInfoMapper;
	
	@Autowired
	private TbInvestMapper tbInvestMapper;
	
	@Override
	@Transactional
	public String registOffer(String date) {
	
		boolean isExecute = false; 	// 모집정보 조회 실행여부. true이면 Db에서 조회하여 파일생성, false이면 header, trailer만 파일생성
		Map returnObj = new HashMap<>();
		List<TbIbkOfferIfInfo> offerIfInfoList = new ArrayList<>();
		
		if(isExecute) {
			offerIfInfoList = tbIbkOfferIfInfoMapper.selectConfirmedOfferList(date);
		}
		
		insertOfferInInfoLog(offerIfInfoList);
		
		WMessage msg = getOfferIfInfoMessage(offerIfInfoList);
		msg.messageInfo();
		
		MessageGenerator generator = new MessageFileGenerator();
		boolean result = generator.generate(msg, "offer");
		
		if(result) {
			result = tbIbkOfferIfInfoMapper.updateProcStatus("Q") == 1 ? true:false;
		}
		
		returnObj.put("success", result);
		
		return new Gson().toJson(returnObj);
	}

	// 모집정보 전송로그
	private void insertOfferInInfoLog(List<TbIbkOfferIfInfo> offerIfInfoList) {
		for (TbIbkOfferIfInfo tbIbkOfferIfInfo : offerIfInfoList) {
			tbIbkOfferIfInfoMapper.insertSelective(tbIbkOfferIfInfo);
		}
	}

	@Override
	@Transactional
	public String registAccountInvest(String date) {
		
		logger.info("기업은행 수기 등록");
		Map<String, Object> returnObj = new HashMap<>();

		String payType = "P20";
		List<TbIbkInvestIfInfo> investIfInfoList = tbIbkInvestIfInfoMapper.selectSbcrList(payType , date);
		
		if(investIfInfoList != null && investIfInfoList.size() > 0) {
			
			// 기업은행 전송이력 기록
			insertInvestInInfoLog(investIfInfoList);
			
			WMessage msg = getInvestIfInfoMessage("0220", investIfInfoList);
	//		WMessage msg = getInvestIfInfoMessage(getSampleInvestDataList());
			
			msg.messageInfo();
			
			// 청약 배치파일 생성
			MessageGenerator generator = new MessageFileGenerator();
			boolean result = generator.generate(msg, "accountInvest");
			
			// 청약 배치 완료처리
			if(result) {
				result = completeInvestIbk(investIfInfoList);
			}
			
			returnObj.put("success", result);
			
		}else {
			returnObj.put("success", false);
		}
		
		return new Gson().toJson(returnObj);
	}
	
	@Override
	@Transactional
	public String registRealtimeInvest(String date) {
		
		logger.info("기업은행 실시간 계좌이체 등록");
		Map<String, Object> returnObj = new HashMap<>();

		String payType = "P10";
		List<TbIbkInvestIfInfo> investIfInfoList = tbIbkInvestIfInfoMapper.selectSbcrList(payType , date);
		
		// 기업은행 전송이력 기록
		insertInvestInInfoLog(investIfInfoList);
		
		WMessage msg = getInvestIfInfoMessage("0200", investIfInfoList);
//		WMessage msg = getInvestIfInfoMessage(getSampleInvestDataList());
		
		msg.messageInfo();
		
		// 청약 배치파일 생성
		MessageGenerator generator = new MessageFileGenerator();
		boolean result = generator.generate(msg, "realTimeInvest");
		
		// 청약 배치 완료처리
		if(result) {
			result = completeInvestIbk(investIfInfoList);
		}
		
		returnObj.put("success", result);
		
		return new Gson().toJson(returnObj);
	}
	
	/**
	 * 기업은행에 청약정보 전송완료
	 * @param investIfInfoList
	 */
	private boolean completeInvestIbk(List<TbIbkInvestIfInfo> investIfInfoList) {
		boolean result = false;
		
		try{
			for (TbIbkInvestIfInfo tbIbkInvestIfInfo : investIfInfoList) {
				// 배치파일 전송 완료 이력 업데이트
				TbIbkInvestIfInfo record = new TbIbkInvestIfInfo();
				record.setIfId(tbIbkInvestIfInfo.getIfId());
				record.setProcStatus("Q");
				tbIbkInvestIfInfoMapper.updateByPrimaryKeySelective(record);
				
			}
			
			result = true;
			logger.info("completeInvestIbk: O.K.");
		}catch(Exception e) {
			logger.error("completeInvestIbk: Failed.");
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 기업은행에 청약정보이력 기록
	 * @param investIfInfoList
	 */
	private void insertInvestInInfoLog(List<TbIbkInvestIfInfo> investIfInfoList) {
		for (TbIbkInvestIfInfo tbIbkOfferIfInfo : investIfInfoList) {
			tbIbkInvestIfInfoMapper.insertSelective(tbIbkOfferIfInfo);
		}
		
	}

	@Override
	public String payInvest(String date) {
		int campaignId = 192;
		List<TbIbkInvestIfInfo> investIfInfoList = tbIbkInvestIfInfoMapper.selectCompletedInvestList(campaignId);
		
		WMessage msg = getInvestIfInfoMessage("0210", investIfInfoList);
//		WMessage msg = getInvestIfInfoMessage(getSampleInvestDataList());
		
		msg.messageInfo();
		MessageGenerator generator = new MessageFileGenerator();
		boolean result = generator.generate(msg, "pay");
		
		if(result) {
//			result = ibkInvestIfInfoMapper.updateProcStatus("Q") == 1 ? true:false;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("success", result);
		
		return new Gson().toJson(map);
	}
	

	
	

	private WMessage getInvestIfInfoMessage(String ifCode, List<TbIbkInvestIfInfo> dataList) {
		WMessage msg = new WMessage();
		
		String dataType = ("0200".equals(ifCode)||"0210".equals(ifCode)) ? "01" :"02";		// 고정
		String seq = "0";
		String relayId ="A00001";
		String bankCode = "003";
		String totalDataRecordCount = dataList.size()+"";
		String dealDate = DateFormatUtils.format(new Date(), "yyyyMMdd");
		String filler = "";
		
		String trailerDataType = "09";		// 고정
		
		InvestIfHeader header = new InvestIfHeader();
		header.setIfCode(ifCode);
		header.setDataType(dataType);
		header.setSeq(seq);
		header.setRelayId(relayId);
		header.setBankCode(bankCode);
		header.setTotalDataRecordCount(totalDataRecordCount);
		header.setDealDate(dealDate);
		header.setFiller(filler);
		
		InvestIfTrailer trailer = new InvestIfTrailer();
		trailer.setIfCode(ifCode);
		trailer.setDataType(trailerDataType);
		trailer.setSeq(seq);
		trailer.setBankCode(bankCode);
		trailer.setTotalDataRecordCount(totalDataRecordCount);
		trailer.setFiller(filler);
		
		WDataList list = new WDataList<>();
		list.addAll(dataList);
		
		msg.setHeader(header);
		msg.setDataList(list);
		msg.setwTrailer(trailer);
		
		return msg;
	}


	private WDataList<TbIbkInvestIfInfo> getSampleInvestDataList() {
		
		WDataList<TbIbkInvestIfInfo> dataList = new WDataList<>();
		
		int ifid = 101;
		
		String bodyIfCode="0200";
		String bodyDataType="05";
		String payType = "01";
		// 프로젝트코드
		String offerName ="asdf";
		// 원거래센터 전문관리번호
		// 투자자입금관리번호(고객식별자)
		// 전문일련번호
		
		
		Byte dataSeq1 = 1;
		String investAmount1="660000";
		Integer userId1 = 132182;
		String paymentsOfStocksYn1 = "N";		// 0200:고정
		String payReqDate1 = "20160114";
		String payReqTime1 = "140000";
		String campaignId1="2";
		Integer investId1=100;
		String filler = "";
		
		TbIbkInvestIfInfo data1 = new TbIbkInvestIfInfo();
		data1.setIfCode(bodyIfCode);
		data1.setDataType(bodyDataType);
		data1.setSeq(dataSeq1);
		data1.setPayType(payType);
		data1.setCampaignId(campaignId1);
		data1.setOfferName(offerName);
		data1.setInvestId(investId1);
		data1.setIfId(ifid);
		data1.setInvestAmount(investAmount1);
		data1.setUserId(userId1);
		data1.setPaymentsOfStocksYn(paymentsOfStocksYn1);
		data1.setPayReqDate(payReqDate1);
		data1.setPayReqTime(payReqTime1);
		data1.setFiller(filler);
		
		dataList.add(data1);
		
		Byte dataSeq2 = 2;
		String investAmount2="380000";
		Integer userId2 = 132164;
		String paymentsOfStocksYn2 = "N";		// 0200:고정
		String payReqDate2 = "20160114";
		String payReqTime2 = "150000";
		String campaignId2="2";
		Integer investId2=101;
		
		TbIbkInvestIfInfo data2 = new TbIbkInvestIfInfo();
		data2.setIfCode(bodyIfCode);
		data2.setDataType(bodyDataType);
		data2.setSeq(dataSeq2);
		data2.setPayType(payType);
		data2.setCampaignId(campaignId2);
		data2.setOfferName(offerName);
		data2.setInvestId(investId2);
		data2.setIfId(ifid);
		data2.setInvestAmount(investAmount2);
		data2.setUserId(userId2);
		data2.setPaymentsOfStocksYn(paymentsOfStocksYn2);
		data2.setPayReqDate(payReqDate2);
		data2.setPayReqTime(payReqTime2);
		data2.setFiller(filler);
		
		dataList.add(data2);
		/*
		Byte dataSeq3 = 3;
		String investAmount3="660000";
		Integer userId3 = 132181;
		String paymentsOfStocksYn3 = "N";		// 0200:고정
		String payReqDate3 = "20160114";
		String payReqTime3 = "150000";
		String campaignId3="3";
		Integer investId3=103;
		
		TbIbkInvestIfInfo data3 = new TbIbkInvestIfInfo();
		data3.setIfCode(bodyIfCode);
		data3.setDataType(bodyDataType);
		data3.setSeq(dataSeq3);
		data3.setPayType(payType);
		data3.setCampaignId(campaignId3);
		data3.setOfferName(offerName);
		data3.setInvestId(investId3);
		data3.setIfId(ifid);
		data3.setInvestAmount(investAmount3);
		data3.setUserId(userId3);
		data3.setPaymentsOfStocksYn(paymentsOfStocksYn3);
		data3.setPayReqDate(payReqDate3);
		data3.setPayReqTime(payReqTime3);
		data3.setFiller(filler);
		
		dataList.add(data3);
		
		Byte dataSeq4 = 4;
		String investAmount4="660000";
		Integer userId4 = 132180;
		String paymentsOfStocksYn4 = "N";		// 0200:고정
		String payReqDate4 = "20160114";
		String payReqTime4 = "150000";
		String campaignId4="3";
		Integer investId4=104;
		
		TbIbkInvestIfInfo data4 = new TbIbkInvestIfInfo();
		data4.setIfCode(bodyIfCode);
		data4.setDataType(bodyDataType);
		data4.setSeq(dataSeq4);
		data4.setPayType(payType);
		data4.setCampaignId(campaignId4);
		data4.setOfferName(offerName);
		data4.setInvestId(investId4);
		data4.setIfId(ifid);
		data4.setInvestAmount(investAmount4);
		data4.setUserId(userId4);
		data4.setPaymentsOfStocksYn(paymentsOfStocksYn4);
		data4.setPayReqDate(payReqDate4);
		data4.setPayReqTime(payReqTime4);
		data4.setFiller(filler);
		
		dataList.add(data4);
		
		Byte dataSeq5 = 5;
		String investAmount5="660000";
		Integer userId5 = 132179;
		String paymentsOfStocksYn5 = "N";		// 0200:고정
		String payReqDate5 = "20160114";
		String payReqTime5 = "150000";
		String campaignId5="3";
		Integer investId5=105;
		
		TbIbkInvestIfInfo data5 = new TbIbkInvestIfInfo();
		data5.setIfCode(bodyIfCode);
		data5.setDataType(bodyDataType);
		data5.setSeq(dataSeq5);
		data5.setPayType(payType);
		data5.setCampaignId(campaignId5);
		data5.setOfferName(offerName);
		data5.setInvestId(investId5);
		data5.setIfId(ifid);
		data5.setInvestAmount(investAmount5);
		data5.setUserId(userId5);
		data5.setPaymentsOfStocksYn(paymentsOfStocksYn5);
		data5.setPayReqDate(payReqDate5);
		data5.setPayReqTime(payReqTime5);
		data5.setFiller(filler);
		
		dataList.add(data5);
		
		Byte dataSeq6 = 6;
		String investAmount6="660000";
		Integer userId6 = 132178;
		String paymentsOfStocksYn6 = "N";		// 0200:고정
		String payReqDate6 = "20160114";
		String payReqTime6 = "150000";
		String campaignId6="3";
		Integer investId6=106;
		
		TbIbkInvestIfInfo data6 = new TbIbkInvestIfInfo();
		data6.setIfCode(bodyIfCode);
		data6.setDataType(bodyDataType);
		data6.setSeq(dataSeq6);
		data6.setPayType(payType);
		data6.setCampaignId(campaignId6);
		data6.setOfferName(offerName);
		data6.setInvestId(investId6);
		data6.setIfId(ifid);
		data6.setInvestAmount(investAmount6);
		data6.setUserId(userId6);
		data6.setPaymentsOfStocksYn(paymentsOfStocksYn6);
		data6.setPayReqDate(payReqDate6);
		data6.setPayReqTime(payReqTime6);
		data6.setFiller(filler);
		
		dataList.add(data6);
		
		Byte dataSeq7 = 7;
		String investAmount7="660000";
		Integer userId7 = 132177;
		String paymentsOfStocksYn7 = "N";		// 0200:고정
		String payReqDate7 = "20160114";
		String payReqTime7 = "150000";
		String campaignId7="3";
		Integer investId7=108;
		
		TbIbkInvestIfInfo data7 = new TbIbkInvestIfInfo();
		data7.setIfCode(bodyIfCode);
		data7.setDataType(bodyDataType);
		data7.setSeq(dataSeq7);
		data7.setPayType(payType);
		data7.setCampaignId(campaignId7);
		data7.setOfferName(offerName);
		data7.setInvestId(investId7);
		data7.setIfId(ifid);
		data7.setInvestAmount(investAmount7);
		data7.setUserId(userId7);
		data7.setPaymentsOfStocksYn(paymentsOfStocksYn7);
		data7.setPayReqDate(payReqDate7);
		data7.setPayReqTime(payReqTime7);
		data7.setFiller(filler);
		
		dataList.add(data7);
		*/
		
		return dataList;
	}


	@Autowired
	private TbIbkInvestIfInfoMapper ibIbkInvestIfInfoMapper;
	
	@Override
	public boolean getResultOffer(String date) {
		WMessage message = null;
		boolean result = false;
		
		try {
			String fileName = "C:/home/ibkuser/senddata/cbk_agn_mngm_scur_0300_20160120_day";
			IbkParser parser = new InvestIfInfoParser();
			File file = new File(fileName);
			message = parser.parse(file);
			
			WDataList<TbIbkInvestIfInfo> dataList= (WDataList<TbIbkInvestIfInfo>)message.getDataList();
			for (TbIbkInvestIfInfo data :dataList) {
				
				TbIbkInvestIfInfo resultInvest = new TbIbkInvestIfInfo();
				String resultCode = data.getResultCode();

				String procStatus = "0000".equals(resultCode) ? "S":"F";
				resultInvest.setProcStatus(procStatus);
				resultInvest.setCampaignId(data.getCampaignId());
				resultInvest.setInvestId(data.getInvestId());
				ibIbkInvestIfInfoMapper.updateByPrimaryKey(resultInvest);
			}
			
			result = true;
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean getResultInvest(String date) {
		
		WMessage message = null;
		boolean result = false;
		String recvDir = IbkClientConfig.get("ibk.msg.recvdata.dir");
		String resultFileName = "/cbk_agn_mngm_scur_0300_"+date+"_day";
		String completedFileName = recvDir +"/recv.done";
		String fileName = "";
		
		try {
			fileName = recvDir + resultFileName;
			IbkParser parser = new InvestIfInfoParser();
			File file = new File(fileName);
			message = parser.parse(file);
			
			WDataList<TbIbkInvestIfInfo> dataList= (WDataList<TbIbkInvestIfInfo>)message.getDataList();
			for (TbIbkInvestIfInfo data :dataList) {
				
				TbIbkInvestIfInfo resultInvest = new TbIbkInvestIfInfo();
				String resultCode = data.getResultCode();

				String procStatus = INVEST_RESULT_CODE.equals(resultCode) ? "S":"F";
				resultInvest.setProcStatus(procStatus);
				resultInvest.setCampaignId(data.getCampaignId());
				resultInvest.setInvestId(data.getInvestId());
				ibIbkInvestIfInfoMapper.updateResultInvest(resultInvest);
				
				// 기업은행에 청약정보 전송 완료 업데이트
				TbInvest invest = new TbInvest();
				invest.setInvestId(data.getInvestId());
				invest.setUserIvstSt("ID10");
				tbInvestMapper.updateByPrimaryKeySelective(invest);
			}
			
			
			complete(completedFileName, fileName);
			
			result = true;
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
		
	}

	private void complete(String completedFileName, String fileName) throws IOException {
		Path oldFile = Paths.get(fileName);
		Path movePath = Paths.get(completedFileName);
		Files.move(oldFile , movePath .resolve(oldFile.getFileName()));
	}
	

	WDataList<TbIbkOfferIfInfo> getSampleOfferDataList() {
		
		WDataList<TbIbkOfferIfInfo> dataList = new WDataList<>();
		
		String bankCode = "003";			// 은행코드, 고정
		String bodyIfCode ="0100";		// 업무구분
		String bodyDataType ="05";		// 데이타구분, 고정
		Byte dataSeq = 1;				// 일련번호
		String offerName1 ="가나다라마바사아자타카타파하종목1";
		String campaignId1="12";
		String tranCompId = "A00001";
		String issuCorpName1="가나다라마바사아자차카타파하주식회사1";
		String businessRegNumber="1270875379";
		String securtType="01";
		String targetAmount="200000000";
		String periodStartDate="20160106";
		String periodFinishDate="20160110";
		String refundDate="20160111";
		String paymentDate="20160112";
		String receiptAccountNo="29106161604011";
		String receiptDepositor="홍길동";
		String procType="00";
		String filler = "";
		
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
		String offerName2 ="가나다라마바사아자타카타파하종목2";
		String issuCorpName2="가나다라마바사아자차카타파하주식회사2";
		String businessRegNumber2 = "1242555105";
		String targetAmount2 = "300000000";
		String receiptAccountNo2="42206000504013";
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
		
		
		dataList.add(data2);
		
		return dataList;
	}
	
	private WMessage getOfferIfInfoMessage(List dataList) {
		
		WMessage msg = new WMessage();
		
		String ifCode = "0100";				// 업무구분
		String dataType = "01";				// 데이타구분, 고정
		String seq = "0";					// 일련번호
		String bankCode = "003";			// 은행코드, 고정
		String totalDataRecordCount= dataList != null ? dataList.size()+"" :"0";	// 총 DATA  RECORD 수
		String dealDate = DateFormatUtils.format(new Date(), "yyyyMMdd");		// 거래발생일
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
		
		OfferIfTrailer trailer = new OfferIfTrailer();
		trailer.setIfCode(ifCode);
		trailer.setDataType(trailerDataType);
		trailer.setSeq(seq);
		trailer.setBankCode(bankCode);
		trailer.setTotalDataRecordCount(totalDataRecordCount);
		trailer.setFiller(filler);
		
		WDataList list = new WDataList<>();
		list.addAll(dataList);
		
		msg.setHeader(header);
		msg.setDataList(list);
		msg.setwTrailer(trailer);
		
		return msg;
	}

	@Override
	public String resultInvest(String date) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
