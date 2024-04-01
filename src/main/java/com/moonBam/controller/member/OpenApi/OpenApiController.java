package com.moonBam.controller.member.OpenApi;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
			MemberDTO nDTO  = serv.selectOneAPIMember(dto.getUsername());
	        
	        Map<String, String> map = new HashMap<>();
	        	map.put("username", dto.getUsername());
	        	map.put("nickname", nickname);
	        
	        serv.updateAPIMemberNickname(map);
	        MemberDTO nnDTO  = serv.selectOneAPIMember(dto.getUsername());
			session.setAttribute("loginUser", dto);
		} 
		
		return "member/Login/apiRegisterSuccess";
	}
	
	//랜덤 이름 생성기
	@PostMapping("/randomNickname")
    public @ResponseBody String randomNickname(){
    	List<String> adjectives = Arrays.asList("행복한", "슬픈", "게으른", "슬기로운", "수줍은",
                "그리운", "더러운", "섹시한", "배고픈", "배부른", "부자", "재벌", "웃고있는", "깨발랄한",
                "못난", "날렵한", "긍정적인", "부정적인", "똑똑한", "멋진");
        List<String> names = Arrays.asList("광래", "미지", "재국", "아야토", "성준", "유준",
                "민기", "수정", "현희", "송하");

        String number = (int)(Math.random() * 99)+1 +"";
        
        Collections.shuffle(adjectives);
        Collections.shuffle(names);
        String adj = adjectives.get(0);
        String name = names.get(0);
        
        return adj+"_"+name+"_"+number;
    }

}
