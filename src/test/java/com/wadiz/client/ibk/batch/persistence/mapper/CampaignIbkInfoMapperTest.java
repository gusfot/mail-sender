package com.wadiz.client.ibk.batch.persistence.mapper;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wadiz.client.ibk.batch.model.TbCampaignIbkInfo;
import com.wadiz.client.ibk.batch.persistence.TbCampaignIbkInfoMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/META-INF/client-context.xml"})
public class CampaignIbkInfoMapperTest {

	@Autowired
	private TbCampaignIbkInfoMapper campaignIbkInfoMapper;
	
	@Test
	public void testSelectByPrimaryKey() {
		
		Integer campaignId = 1;
		TbCampaignIbkInfo  campaignIbkInfo  = campaignIbkInfoMapper.selectByPrimaryKey(campaignId );
		
		assertTrue(campaignIbkInfo == null);
	}
}
