package com.moonBam.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonBam.dto.ChatgptResponseDTO;

@Repository
public class ChatGPTDAO {
	
	@Autowired
	SqlSessionTemplate session;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RestTemplate restTemplate;
	@Value("${openai.api.key}")
	private String apiKey;
	
	// maxTokens 파라미터값
	// 일반적으로 100~200 토큰으로 설정
	// 사용목적: 버그나 이상형상으로 인한 비정상적으로 높은 토큰소모만 방지
	// 설정치에 따라 간소하게, 또는 세세하게 나옴
	private int maxTokens = 200;
	
	// RestTemplate을 통한 api 요청 구현
	public String getResponse(String prompt) throws JsonProcessingException {
		
		System.out.println("prompt: " + prompt + " maxTokens: "+ maxTokens);
	    String url = "https://api.openai.com/v1/chat/completions";
	    HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(apiKey);
	    headers.add("Content-Type", "application/json");

	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("model", "gpt-3.5-turbo"); //모델 설정 주의 - 잘못 설정시 404 반환 / gpt-3.5-turbo / gpt-4 / gpt-4-1106-preview
	    requestBody.put("max_tokens", maxTokens);
	    requestBody.put("messages", Collections.singletonList(Map.of("role", "user", "content", prompt)));

	    // JSON 안전 생성
	    String requestJson = objectMapper.writeValueAsString(requestBody);

	    HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
	    try {
	    	// 응답을 전용DTO에 저장 
	        ResponseEntity<ChatgptResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, ChatgptResponseDTO.class);
	        System.out.println(response);
	        
	        // 응답에서 내용만 추출
	        String content = response.getBody().getChoices().get(0).getMessage().getContent();
//	        System.out.println(content);
	        
	        return content;
	    } catch (RestClientException e) {
	        // 예외 처리 로직 추가
	        e.printStackTrace();
	        return "An error occurred while calling the API.";
	    }
	}

	public Integer getUserAIUsage(String userId) {
		return session.selectOne("getUserAIUsage",userId);
	}

	public void upsertUsageCount(String userId) {
		session.update("upsertUsageCount", userId);
	}

	public void resetUsageCount() {
		session.delete("resetUsageCount");
	}
}
