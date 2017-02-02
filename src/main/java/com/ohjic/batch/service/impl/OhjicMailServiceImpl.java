package com.ohjic.batch.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohjic.batch.common.UnkownSmtpHostException;
import com.ohjic.batch.common.helper.Mailer;
import com.ohjic.batch.mail.impl.MailFatory;
import com.ohjic.batch.model.MsgQueue;
import com.ohjic.batch.persistence.MsgQueueMapper;
import com.ohjic.batch.service.MailService;

/**
 * 오직 sendmail smtp 메일서버로 발송
 * @author ohjic
 *
 */
@Service
public class OhjicMailServiceImpl implements MailService {

	private static final Logger logger = LoggerFactory.getLogger(OhjicMailServiceImpl.class);
	
	@Autowired
	private MsgQueueMapper msgQueueMappepr;
	
	@Override
	public void send() {
		
		// 전송대기중인 목록 조회
		List<MsgQueue> readyList = getReadyMailList();
		
		for (MsgQueue msgQueue : readyList) {
			
			// 전송 중 상태로 변경
			modifyToReady(msgQueue);
			
			String from = msgQueue.getCallback();
			String to = msgQueue.getDstaddr();
			logger.debug("보내는 사람:" + from +", 받는사람: " + to);
			
			boolean isAuth = true;
			boolean sendResult =false;
			
			try {
				
				Mailer mailer = MailFatory.getInstance(isAuth , from);
				sendResult =  mailer.send(msgQueue.getDstaddr(), "받는사람", msgQueue.getCallback(), "보내는사람", "1", msgQueue.getSubject()
						, msgQueue.getText());
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnkownSmtpHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
				// 완료처리
				modifyCompleted(msgQueue, sendResult);
			}
		}
		
	}
	/**
	 * 전송상태로 변경
	 * @param msgQueue
	 */
	private void modifyToReady(MsgQueue msgQueue) {
		MsgQueue ready = new MsgQueue();
		ready.setMseq(msgQueue.getMseq());
		ready.setStat("1");
		msgQueueMappepr.updateByPrimaryKeySelective(ready);
	}

	/**
	 * 완료 처리
	 * @param msgQueue
	 * @param sendResult
	 */
	private void modifyCompleted(MsgQueue msgQueue, boolean sendResult) {
		MsgQueue completed = new MsgQueue();
		completed.setMseq(msgQueue.getMseq());
		completed.setStat("2");
		completed.setResult(sendResult ? "0000":"9999");
		
		msgQueueMappepr.updateByPrimaryKeySelective(completed);
	}

	/**
	 * 전송 대기중인 목록 조회
	 * @return
	 */
	private List<MsgQueue> getReadyMailList() {
		String stat = "0";
		return msgQueueMappepr.selectMsgQueueListByStat(stat );
	}

}
