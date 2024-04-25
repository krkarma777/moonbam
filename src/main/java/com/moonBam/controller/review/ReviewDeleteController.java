package com.moonBam.controller.review;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.ReviewDTO;
import com.moonBam.service.ReviewService;
import com.moonBam.service.member.MemberLoginService;

@Controller
public class ReviewDeleteController {
	
	@Autowired
	ReviewService service;
	
	@Autowired
	MemberLoginService memberLoginService;
	
	// 로그인 사용자와 리뷰 작성자가 동일한지 판단후 삭제
	@RequestMapping(value="/review", method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteReview(Principal principal, String postId) {
		// 로그인 정보 파싱
		MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
		String loginUserId=null; // 로그인 유저아이디 
		if(loginUser!=null) {
			loginUserId = loginUser.getUserId();
		}
		
		// 로그인 정보가 있을 때
		if(loginUser!=null) {
			ReviewDTO review= service.selectReviewByPostId(postId);
			String postUserId = review.getUserId();
			if(postUserId.equals(loginUserId)) {
				service.deleteReview(postId);
				return "";
			}
		}
		return null;
	}
}
