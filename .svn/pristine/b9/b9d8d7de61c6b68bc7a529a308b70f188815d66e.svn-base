package com.wadiz.client.ibk.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/META-INF/client-context.xml"})
public class HolydayServiceTest {

	@Autowired
	private WadizHolidayService wadizHolydayService;
	
	@Test
	public void testIsHolyday1() {
		String date = "2016-02-04";
		boolean result = wadizHolydayService.isHoliday(date);
	
		assertTrue(!result);
	}
	
	@Test
	public void testIsHolyday2() {
		String date = "2016-02-08";
		boolean result = wadizHolydayService.isHoliday(date);
	
		assertTrue(result);
	}
}
