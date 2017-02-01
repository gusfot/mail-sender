package com.wadiz.client.ibk.batch.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/META-INF/client-context.xml"})
public class IbkBatchServiceTest {

	@Autowired
	private IbkBatchService ibkBatchService;
	
	@Test
	public void registOffer() {
		
		String date = "20160111";
		String result = ibkBatchService.registOffer(date);
		
		assertTrue(result != null);
	}
	
	@Test
	public void testRegistRealtimeInvest() {
		
		String date = "2016-01-13";
		String result = ibkBatchService.registRealtimeInvest(date);
		
		assertTrue(result != null);
	}
	
	@Test
	public void testRegistAccountInvest() {
		
		String date = "2016-01-13";
		String result = ibkBatchService.registAccountInvest(date);
		
		assertTrue(result != null);
	}
	
	@Test
	public void testGetResultInvest() {
		
		String date = "20160126";
		boolean result = ibkBatchService.getResultInvest(date);
		
		assertTrue(result );
	}
	
	@Test
	public void testPayInvest() {
		
		String date = "20160126";
		String result = ibkBatchService.payInvest(date);
		
		//assertTrue(result );
	}
	
	
}
