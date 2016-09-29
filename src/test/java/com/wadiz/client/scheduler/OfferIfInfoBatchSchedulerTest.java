package com.wadiz.client.scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wadiz.client.schedule.OfferIfInfoBatchScheduler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/META-INF/client-context.xml"})
public class OfferIfInfoBatchSchedulerTest {

	@Autowired
	private OfferIfInfoBatchScheduler offerIfInfoBatchScheduler;
	
	
	@Test
	public void testRun() {
		offerIfInfoBatchScheduler.run();
	}
}
