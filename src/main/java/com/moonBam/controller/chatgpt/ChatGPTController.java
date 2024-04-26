package com.moonBam.controller.chatgpt;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.ChatGPTService;
import com.moonBam.service.member.MemberLoginService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ChatGPTController {
	
	@Autowired
	MemberLoginService memberLoginService;
	
	@Autowired
	ChatGPTService service;
	
	// 서비스로 작업 위임
	@PostMapping("/chatgpt")
	@ResponseBody
	public String getMethodName(String prompt, Principal principal, HttpServletRequest request) {
//		System.out.println("/chatgpt 엔드포인트 진입");
		
		MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
		String userId = null;
		if(loginUser==null) {
			return "로그인 후 사용가능합니다.";
		}
		userId = loginUser.getUserId();
		request.setAttribute("member", loginUser);
		
		int maxUsage = 7;
		Integer UserAIUsage = service.getUserAIUsage(userId);
		if(UserAIUsage!=null && UserAIUsage>=maxUsage) {
			return "오늘 최대 사용치를 초과하였습니다. ("+UserAIUsage+"/"+maxUsage+")";
		}
		service.upsertUsageCount(userId);
		String content = null;
		try {
			content = service.getResponse(prompt);
		} catch (JsonProcessingException e) {
			e.printStackTrace(); // 오류발생시 프론트에서 에러메시지 출력
		}
		System.out.println("content 결과데이터: "+content);
		return content;
	}
	
}
