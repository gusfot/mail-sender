package com.wadiz.client.ibk.msg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wadiz.client.ibk.common.config.IbkClientConfig;
import com.wadiz.client.ibk.model.OfferIfHeader;
import com.wadiz.client.ibk.model.WMessage;
import com.wadiz.client.ibk.model.WMessageable;

public class MessageFileGenerator implements MessageGenerator {

	private static final Logger logger = LoggerFactory.getLogger(MessageFileGenerator.class);
	
	// 기본 파일 디렉토리
	private static final String BASE_PATH = IbkClientConfig.get("ibk.msg.senddata.dir");
	
	@Override
	public boolean generate(WMessage msg, String date) {
		
		if(date == null) {
			date = DateFormatUtils.format(new Date(), "yyyyMMdd");
		}
		
		return generateOfDate(msg, date);
	}
	
	private boolean generateOfDate(WMessage msgObj, String date) {
		
		boolean result = true;
	
		String content =  msgObj.getMessage();
		File basepathDir = new File(BASE_PATH);
		
		if(!basepathDir.exists()) {
			basepathDir.mkdirs();
			logger.debug(basepathDir + " is created.");
		}
		
		String prefix = "";
		String fileName = "";
		
		fileName = getFileName(msgObj.getHeader(), date);
		
		String fullpath = BASE_PATH+"/" +fileName;
		
		try {
			
			Files.write( Paths.get(fullpath), content.getBytes("MS949"), StandardOpenOption.CREATE);
			
		} catch (IOException e) {
			logger.debug(e.getMessage());
			result = false;
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	private String getFileName(WMessageable header, String date) {
		
		String fileName;
		
		if(header instanceof OfferIfHeader) {
			fileName = "cbk_agn_mngm_scur_0100_"+date+"_day";
		}else {
			fileName = "cbk_agn_mngm_scur_0200_"+date+"_day";
		}
		
		return fileName;
	}

}
