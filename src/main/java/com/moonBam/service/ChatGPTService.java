package com.moonBam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moonBam.dao.ChatGPTDAO;

@Service
public class ChatGPTService {

	@Autowired
	ChatGPTDAO dao;

	// 매일 영화데이터 업데이트
    // 매일 오전 5시에 실행 (초, 분, 시, 일, 월, 요일)
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetUsageCount() {
    	dao.resetUsageCount();
    	System.out.println("정각이 되어 모든 사용자의 ChatGPT 사용가능횟수가 초기화됩니다.");
    	
    }
    
	// 응답 데이터 얻기
	// 일정 크기 넘어가면 처리가 불가능하다는 문자열 반환
    public String getResponse(String prompt) throws JsonProcessingException {
    	if(prompt.length()>100) {
    		return "요청 데이터의 크기가 너무 큽니다!";
    	}
        String content = dao.getResponse(prompt);
        return content;
    }

    // chatgpt 사용횟수 조회
	public Integer getUserAIUsage(String userId) {
		return dao.getUserAIUsage(userId);
	}

	// chatgpt 사용횟수 증가
	public void upsertUsageCount(String userId) {
		dao.upsertUsageCount(userId);
	}
}
