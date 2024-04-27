package com.moonBam.controller.member;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.moonBam.service.member.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moonBam.service.member.OpenApiService;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class OpenApiController {

	@Autowired
	OpenApiService serv;

	@Autowired
	LoginService loginService;

	@PostMapping("/restoreUser")
	public ModelAndView restoreUser(String userId){
	//	System.out.println("restore user" + userId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", userId);
		mav.setViewName("member/Login/restoreUser");
		return mav;
	}

	@PostMapping("/checkRestoreMember")
	public String checkRestoreMember(String userId, String yesBtn, String noBtn){
		System.out.println("userId = " + userId + " yesBtn = " + yesBtn + " noBtn = " + noBtn);
		if (yesBtn != null){
			loginService.updateIsEnabled(userId);
			return "member/Login/RestoreSuccess";
		} else {
			return "member/Login/loginMain";
		}
	}

	@PostMapping("/forChangeNickname")
	public ModelAndView changeNickname(String userId){
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", userId);
		mav.setViewName("member/Login/changeNicknamePage");
		return mav;
	}

	@PostMapping("/goToAPILoginPage")
	public ModelAndView goToAPILoginPage(String userId){
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", userId);
		mav.setViewName("member/Login/APILogin");
		return mav;
	}

	// 닉네임 변경
	@PostMapping("/changeNickname")
	public String changeNickname(String userId, String nickname) {
		System.out.println(userId);
		System.out.println(nickname);

		Map<String, String> map = new HashMap<>();
			map.put("userId", userId);
			map.put("nickname", nickname);

		serv.updateAPIMemberNickname(map);

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
