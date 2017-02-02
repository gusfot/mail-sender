package com.ohjic.batch.scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ohjic.batch.schedule.MailBatchScheduler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/META-INF/client-context.xml"})
public class MailBatchSchedulerTest {

	@Autowired
	private MailBatchScheduler mailBatchScheduler;
	
	
	@Test
	public void testRun() {
		mailBatchScheduler.run();
	}
}
