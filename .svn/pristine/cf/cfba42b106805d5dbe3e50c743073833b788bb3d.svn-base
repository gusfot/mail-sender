package com.wadiz.client.ibk.batch.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wadiz.client.ibk.batch.model.TbIbkBatchWokingDay;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/META-INF/client-context.xml"})
public class IbkBatchWorkingdayServiceTest {

	@Autowired
	private IbkBatchWorkingdayService ibkBatchWorkingdayService;
	
	@Test
	public void testRegist() {
		String date = "2016-02-04";
		boolean result = ibkBatchWorkingdayService.regist(date);
		
		assertTrue(result);
	}
	
	@Test
	public void testComplete() {
		String date = "2016-02-04";
		boolean result = ibkBatchWorkingdayService.complete(date);
		
		assertTrue(result);
	}
	
	@Test
	public void testGetWorkingday() {
		
		TbIbkBatchWokingDay  workingday = ibkBatchWorkingdayService.getWorkingday("O");
		
		assertTrue("2016-02-04".equals(workingday.getWorkingDate()));
	}
}
