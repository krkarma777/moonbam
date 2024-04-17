package com.moonBam.controller.community.oh;

import java.beans.JavaBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
	
	public String currentTime() {
		 LocalDateTime currentTime = LocalDateTime.now();
	        
	        // 날짜와 시간을 yyyy/MM/dd HH:mm:ss 형식의 문자열로 변환합니다.
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	        String formattedTime = currentTime.format(formatter);
	        
	        // 결과를 출력합니다.
	        System.out.println("현재 시간: " + formattedTime);
	        return formattedTime;
	}
	
}
