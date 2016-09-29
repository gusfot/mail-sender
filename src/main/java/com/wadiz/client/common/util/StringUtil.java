package com.wadiz.client.common.util;

import java.io.UnsupportedEncodingException;

/**
 * String 처리를 위한 함수 모음
 */
public class StringUtil {
	
	private static String charSet = "MS949";
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final String KOR_CHARSET = "EUC-KR";
	private static final String ENG_CHARSET = "ISO-8859-1";
	
	/**
	 * Byte(바이트)길이로 문자열자르기
	 * @param str
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static String substringByte(String str, int beginIndex, int endIndex) {
		
		int beginlen = 0, endlen = 0, bytelen = 0;
		char c;
		String tmp = str;
		
		try {
//			System.out.println("substringByte length: " + tmp.getBytes(charSet).length);
//			System.out.println("beginIndex : " + beginIndex);
//			System.out.println("endIndex   : " + endIndex);
			if(tmp.getBytes(charSet).length >= endIndex) {
				while (bytelen < endIndex) {
					c = tmp.charAt(endlen);
					if ( bytelen < beginIndex ) beginlen++;
					bytelen++;
					endlen++;
					if ( c > 127 ) bytelen++; //2-byte character..
				}
				tmp=tmp.substring(beginlen, endlen);
			}
		} catch(java.io.UnsupportedEncodingException e) {
			System.out.println("Unsupported Encoding:"+tmp);
		}
		
		return tmp;
	}
	
	/**
	 * Byte 길이 
	 * @param str
	 * @return
	 */
	public static int byteLength(String str) {
		
		int result = 0;
		try {
			result = str.getBytes(charSet).length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 문자열에서 한글개수
	 * @param str
	 * @return
	 */
	public static int getHangeulCount(String str) {
		
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c> 127) {
				count++;
			}
		}
		
		return count;
	}
}
