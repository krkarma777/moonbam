package com.moonBam.controller.member.OpenApi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.OpenApiService;

@Controller
public class OpenApiController {

	@Autowired
	OpenApiService serv;
	
	// 닉네임 변경
	@PostMapping("/changeNickname")
	public String changeNickname(HttpSession session, MemberDTO dto, String nickname, String clickType) {
		if(clickType.equals("변경하기")) {
			MemberDTO nDTO  = serv.selectOneAPIMember(dto.getUserId());
	        
	        Map<String, String> map = new HashMap<>();
	        	map.put("userId", dto.getUserId());
	        	map.put("nickname", nickname);
	        
	        serv.updateAPIMemberNickname(map);
	        MemberDTO nnDTO  = serv.selectOneAPIMember(dto.getUserId());
			session.setAttribute("loginUser", dto);
		} 
		
		return "member/Login/apiRegisterSuccess";
	}

}
