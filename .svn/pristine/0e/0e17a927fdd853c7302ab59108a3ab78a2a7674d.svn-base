package com.wadiz.client.ibk.batch.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadiz.client.ibk.batch.model.TbIbkInvestIfInfo;
import com.wadiz.client.ibk.batch.model.TbIbkInvestRefundIfInfo;
import com.wadiz.client.ibk.batch.persistence.TbIbkInvestIfInfoMapper;
import com.wadiz.client.ibk.batch.persistence.TbIbkInvestRefundIfInfoMapper;
import com.wadiz.client.ibk.batch.service.IbkRefundBatchService;
import com.wadiz.client.ibk.realtime.msg.req.Refund;
import com.wadiz.client.ibk.realtime.service.IbkRtService;

@Service
public class IbkRefundBatchServiceImpl implements IbkRefundBatchService {

	private static final Logger logger = LoggerFactory.getLogger(IbkRefundBatchServiceImpl.class);
	
	@Autowired
	private TbIbkInvestRefundIfInfoMapper tbIbkInvestRefundIfInfoMapper;
	
	@Autowired
	private TbIbkInvestIfInfoMapper tbIbkInvestIfInfoMapper;
	
	@Autowired
	private IbkRtService ibkRtService;
	
	@Override
	public String request() {
		
		logger.debug("IbkRefundBatchService is started!!");
		
		String res = "";
		List<TbIbkInvestRefundIfInfo>  refundList = tbIbkInvestRefundIfInfoMapper.selectRefundInvestList("R");
		
		for (TbIbkInvestRefundIfInfo tbIbkInvestRefundIfInfo : refundList) {

			Refund refund = new Refund();
			
			String refundCode = tbIbkInvestRefundIfInfo.getRefundCode();
			String resnCode = tbIbkInvestRefundIfInfo.getResnCode();
			int investId = tbIbkInvestRefundIfInfo.getInvestId();
			int refundId= tbIbkInvestRefundIfInfo.getRefundId();
			
			TbIbkInvestIfInfo investIfInfo = tbIbkInvestIfInfoMapper.selectInvestIfInfo(investId);
			String investAmount = investIfInfo.getInvestAmount();
			
			refund.setRefundId(refundId);
			refund.setSeq(investId);
			refund.setCampaignId(investIfInfo.getCampaignId());
			refund.setInvestId(String.valueOf(investId));
			refund.setInvestAmount(investAmount);
			refund.setRefundAmount(investAmount);
			refund.setRefundResnCode(resnCode);
			refund.setRefundCode(refundCode);
			refund.setOrignInvestId(String.valueOf(investId));
			refund.setOrignInvestDate(investIfInfo.getPayReqDate()+investIfInfo.getPayReqTime());
			
			TbIbkInvestRefundIfInfo record = new TbIbkInvestRefundIfInfo();
			record.setReqDate(new Date());
			record.setStat("P");
			record.setRefundId(refundId);
			tbIbkInvestRefundIfInfoMapper.updateByPrimaryKeySelective(record);
			
			res = ibkRtService.refund(refund);
			
			logger.debug("IbkRefundBatchService is finished!!");
		}
		return res;
	}

	@Override
	public boolean response(Refund refund) {
		boolean result = false; 
		String stat = "".equals(refund.getResCode()) ? "Y":"N";
		int investId = Integer.parseInt(refund.getInvestId());
		
		logger.debug("결과-investId: {}", investId);
		logger.debug("결과-stat    : {}", stat);
		TbIbkInvestRefundIfInfo record = new TbIbkInvestRefundIfInfo();
		record.setInvestId(investId);
		record.setStat(stat);
		
		try {
			result = tbIbkInvestRefundIfInfoMapper.updateByInvestId(record ) == 1? true:false;
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("update result: " + result);
		
		return result;
	}

}
