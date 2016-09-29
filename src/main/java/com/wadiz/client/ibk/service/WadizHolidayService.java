package com.wadiz.client.ibk.service;

public interface WadizHolidayService {

	/**
	 * 날짜(yyyy-MM-dd) 로 공휴일 여부 확인
	 * true이면 공유일, false이면 영업일
	 * @param date
	 * @return
	 */
	boolean isHoliday(String date);

}
