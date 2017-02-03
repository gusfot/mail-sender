package com.ohjic.batch.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohjic.batch.common.UnkownSmtpHostException;
import com.ohjic.batch.common.helper.Mailer;
import com.ohjic.batch.mail.impl.MailFatory;
import com.ohjic.batch.model.MsgQueue;
import com.ohjic.batch.persistence.MsgQueueMapper;
import com.ohjic.batch.persistence.MsgResultMapper;
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
	
	@Autowired
	private MsgResultMapper msgResultMapper;
	
	@Override
	public void send() {
		
		// 전송대기중인 목록 조회
		List<MsgQueue> readyList = getReadyMailList();
		
		for (MsgQueue msgQueue : readyList) {
			
			// 전송 중 상태로 변경
			toReady(msgQueue);
			
			String from = msgQueue.getCallback();
			String to = msgQueue.getDstaddr();
			Integer mseq = msgQueue.getMseq();
					
			logger.debug("보내는 사람:" + from +", 받는사람: " + to);
			
			boolean sendResult =false;
			
			try {
				
				String isAuth = msgQueue.getIsAuth();
				Mailer mailer = MailFatory.getInstance(isAuth  , from);
				sendResult =  mailer.send(msgQueue.getDstaddr(), msgQueue.getDstaddrUser(), msgQueue.getCallback(), msgQueue.getCallbackUser(), msgQueue.getPassword(), msgQueue.getIsAuth(), msgQueue.getSubject() , msgQueue.getText(), msgQueue.getTextType());
				
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
				toComplete(mseq, sendResult);
			}
		}
		
	}
	/**
	 * 전송상태로 변경
	 * @param msgQueue
	 */
	private void toReady(MsgQueue msgQueue) {
		MsgQueue ready = new MsgQueue();
		ready.setMseq(msgQueue.getMseq());
		ready.setStat("1");
		ready.setSendTime(new Date());
		msgQueueMappepr.updateByPrimaryKeySelective(ready);
	}

	/**
	 * 완료 처리
	 * @param msgQueue
	 * @param sendResult
	 */
	@Transactional
	private void toComplete(Integer mseq, boolean sendResult) {

		MsgQueue completed = msgQueueMappepr.selectByPrimaryKey(mseq );
		completed.setStat("2");
		completed.setTcprecvTime(new Date());
		completed.setResult(sendResult ? "0000":"9999");
		msgResultMapper.insertSelectiveMsgQueue(completed);
		
		msgQueueMappepr.deleteByPrimaryKey(mseq);
	}

	/**
	 * 전송 대기중인 목록 조회
	 * @return
	 */
	private List<MsgQueue> getReadyMailList() {
		String stat = "0";
		return msgQueueMappepr.selectMsgQueueListByStat(stat );
	}
	
	
	@Override
	public Object regist(String from, String fromName,String to, String toName, String subject, String textType, String text, String serverId, Date requestTime, String password   ) {
		
		MsgQueue mq = new MsgQueue();
		mq.setSendType("1");					// 1:비동보, 2:동부
		mq.setDstaddr(from);					// 수신자 이메일 주소, 동보시 미참조
		mq.setDstaddrUser(fromName);			// 수신자 이름
		mq.setCallback(to);						// 수신자 이메일 주소, 동보시 미참조
		mq.setCallbackUser(toName);				// 발신자 이름
		mq.setStat("0");						// 0:전송대기, 1:송신중, 2:송신완료, 3:결과수신
		mq.setSubject(subject);					// 이메일 제목
		mq.setTextType(textType);				// 0:plain, 1:html
		mq.setText(text);						// 이메일 내용
		mq.setServerId(serverId);				// 서버 아이디
		if(requestTime!=null) {
			mq.setRequestTime(requestTime);		// 요청시간
		}
		if(password!=null) {
			mq.setPassword(password);		// 요청시간
		}
		
		return msgQueueMappepr.insertSelective(mq) == 1;
	}

}
