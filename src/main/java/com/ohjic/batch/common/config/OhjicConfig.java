package com.ohjic.batch.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OhjicConfig {

	public static String get(String key) {
		
		Properties prop = new Properties();
		InputStream input =null;
		
		try {
			
			String filename="ohjic.properties";
			input = OhjicConfig.class.getClassLoader().getResourceAsStream(filename);
			if(input ==null) {
				System.out.println("could not find "+ filename);
				return null;
			}
			
			prop.load(input);
			
			return prop.getProperty(key);
		
		}catch(IOException e) {
			
			e.printStackTrace();
			return null;
			
		}finally {
			
			if(input !=null) {
				
				try {
					input.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static int getIngeger(String key) {
		return Integer.parseInt(get(key));
	}
	
	public static String getString(String key) {
		return String.valueOf(get(key));
	}
}
