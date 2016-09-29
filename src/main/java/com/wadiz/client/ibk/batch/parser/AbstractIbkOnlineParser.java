package com.wadiz.client.ibk.batch.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.wadiz.client.ibk.batch.model.WMessage;
import com.wadiz.client.ibk.batch.model.WMessageable;

public abstract class AbstractIbkOnlineParser implements IbkParser {

	@Override
	public WMessage parse(String text) {
		/**
		 * IBK 온라인전문은 HEADER에서 전체 처리한다.
		 */
		WMessageable header = parseHeader(text);
		
		WMessage message = new WMessage();
		message.setHeader(header);
		
		return message;
	}
	
	@Override
	public WMessage parse(File file) throws IOException {
		
		BufferedReader br =  new BufferedReader(new InputStreamReader(new FileInputStream(file),"euc-kr"));
		String text = "";
		
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
//		        sb.append(System.lineSeparator());
		        line = br.readLine();
//		        System.out.println("line : " + line);
		    }
		    text = sb.toString();
		    System.out.println("file content length: " + text.getBytes("MS949").length);
		    System.out.println("file content       : " + text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    br.close();
		}
		
		return parse(text);
	}

}
