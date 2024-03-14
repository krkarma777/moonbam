package com.moonBam.controller.review;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.RateDTO;
import com.moonBam.service.ReviewService;

@Controller
public class UpdateScoreController {

	@Autowired
	ReviewService service;
	
	// 비동기 별점 업데이트 서블릿
	@RequestMapping(value="/score", method=RequestMethod.POST)
	@ResponseBody
	public void UpdateScore(RateDTO rate, HttpSession session) {
		// 세션에서 로그인 정보 파싱
		MemberDTO login = (MemberDTO) session.getAttribute("loginUser");
		// 로그인 정보가 존재할 때
		if(login!=null) {
			//DB에 별점 업데이트 작업
			service.UpdateScore(rate);
			//반환값 따로 없음
		}	
	}
}
