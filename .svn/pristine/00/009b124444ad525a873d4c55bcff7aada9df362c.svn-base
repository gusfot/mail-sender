package com.wadiz.client.ibk.persistence.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wadiz.client.ibk.batch.model.TbIbkInvestIfInfo;
import com.wadiz.client.ibk.batch.persistence.TbIbkInvestIfInfoMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/META-INF/server-context.xml"})
public class TbIbkInvestIfInfoMapperTest {

	@Autowired
	private TbIbkInvestIfInfoMapper tbIbkInvestIfInfoMapper;
	
	/**
	 * 예치기관이 기은은행인 프로젝트 종료시
	 * 청약목록 조회
	 */
	@Test
	public void testSelectCompletedInvestList() {
		int campaignId = 192;
		List<TbIbkInvestIfInfo> list = tbIbkInvestIfInfoMapper.selectCompletedInvestList(campaignId );
		
		assertTrue(list.size() == 38);
	}
}
