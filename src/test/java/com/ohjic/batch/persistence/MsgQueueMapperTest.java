package com.ohjic.batch.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ohjic.batch.model.MsgQueue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/META-INF/client-context.xml"})
public class MsgQueueMapperTest {

	@Autowired
	MsgQueueMapper msgQueueMapper;
	
	/*
	 * msg_queue 기본 정보 insert
	 */
	@Test
	public void testInsertSelective() {
		MsgQueue mq = new MsgQueue();
		
		mq.setMsgType("1"); 					// 1:ohjic의 SMTP 서버, 2: portal의 SMTP 서버
		mq.setSendType("1");					// 1:비동보, 2:동부
		mq.setDstaddr("");						// 수신자 이메일 주소, 동보시 미참조
		mq.setCallback("gusfot@gmail.com");		// 수신자 이메일 주소, 동보시 미참조
		mq.setStat("0");						// 0:전송대기, 1:송신중, 2:송신완료, 3:결과수신
		mq.setSubject("이메일 제목");				// 이메일 제목
		mq.setTextType("1");					// 0:plain, 1:html
		mq.setText("이메일 내용");					// 이메일 내용
		mq.setServerId("OHJIC00001");			// 서버 아이디
		
		int result = msgQueueMapper.insertSelective(mq);
		
		assertTrue(result==1);
	}
	
	/**
	 * stat=0 인 건들 조회:전송대기중
	 */
	@Test
	public void testSelectMsgQueueListByStatForReady() {
		
		String stat = "0";
		List<MsgQueue> result = msgQueueMapper.selectMsgQueueListByStat(stat );
		assertTrue(result.size()==1);
	}
	
	/**
	 * stat=1 인 건들 조회:전송중
	 */
	@Test
	public void testSelectMsgQueueListByStatForSending() {
		
		String stat = "1";
		List<MsgQueue> result = msgQueueMapper.selectMsgQueueListByStat(stat );
		assertTrue(result.size()==1);
	}
	
	/**
	 * stat=1 인 건들 조회:전송중
	 */
	@Test
	public void testUpdateStat() {
		
		String stat = "0";
		MsgQueue mq = new MsgQueue();
		mq.setStat(stat);
		mq.setMseq(1);
		int result = msgQueueMapper.updateByPrimaryKeySelective(mq );
		assertTrue(result==1);
	}
	
}
