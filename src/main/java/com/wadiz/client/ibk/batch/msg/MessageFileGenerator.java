package com.wadiz.client.ibk.batch.msg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wadiz.client.ibk.batch.model.WMessage;
import com.wadiz.client.ibk.common.config.IbkClientConfig;

/**
 * 전문메세지 파일 생성기
 * @author hyunlae
 *
 */
public class MessageFileGenerator implements MessageGenerator {

	private static final Logger logger = LoggerFactory.getLogger(MessageFileGenerator.class);
	
	// 기본 파일 디렉토리
	private static final String BASE_PATH = IbkClientConfig.get("ibk.msg.senddata.dir");
	
	@Override
	public boolean generate(WMessage msgObj, String type) {
		
		boolean result = true;
	
		String content =  msgObj.getMessage();
		File basepathDir = new File(BASE_PATH);
		
		if(!basepathDir.exists()) {
			basepathDir.mkdirs();
			logger.debug(basepathDir + " is created.");
		}
		
		String fileName = "";
		
		fileName = getFileName(type);
		
		String fullpath = BASE_PATH+"/" +fileName;
		
		try {
			
			Files.write( Paths.get(fullpath), content.getBytes("MS949"), StandardOpenOption.CREATE);
			logger.info("The file is created.");
		} catch (IOException e) {
			logger.debug(e.getMessage());
			result = false;
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	// 파일이름 생성
	private String getFileName(String type) {
		
		String date = DateFormatUtils.format(new Date(), "yyyyMMdd");
		
		String fileName = null;
		
		if("offer".equals(type)) {
			fileName = "cbk_agn_mngm_scur_0100_"+date+"_day";
		}else if("realTimeInvest".equals(type)) {
			fileName = "cbk_agn_mngm_scur_0200_"+date+"_day";
		}else if("accountInvest".equals(type)) {
			fileName = "cbk_agn_mngm_scur_0220_"+date+"_day";
		}else { 
			fileName = "cbk_agn_mngm_scur_0210_"+date+"_day";
		}
		logger.info("This fileName is " + fileName);
		return fileName;
	}

}
