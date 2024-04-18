package com.moonBam.controller.review;

import com.moonBam.dto.ContentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.ReviewDTO;
import com.moonBam.service.ReviewService;
import com.moonBam.service.member.MemberLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashMap;

@Controller
public class ShowReviewController {

	@Autowired
	ReviewService service;

	@Autowired
	MemberLoginService memberLoginService;
	
	@RequestMapping(value="/review", method=RequestMethod.GET)
	public String ShowReview(String postId, Principal principal, HttpServletRequest request) {
		
		// 세션에서 로그인 정보 파싱
		MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
		String likeUserId=null;
		if(loginUser!=null) {
			likeUserId = loginUser.getUserId();
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("postId", postId);
		map.put("likeUserId", likeUserId);
		ReviewDTO review = service.selectReview(map);
		
		String nextPage = "";
		if(review==null) {
			nextPage = "redirect:main?cg=movie";
		}else {
			String contId = Long.toString(review.getContId());
			ContentDTO content = service.selectContent(contId);
			request.setAttribute("content", content);
			request.setAttribute("review", review);
			request.setAttribute("likeUserId", likeUserId);
			request.setAttribute("mesg", (String)request.getAttribute("mesg"));
			nextPage = "review/reviewViewer";
		}

		return nextPage;
	}
	
	@RequestMapping("/allReview")
	public String allReview() {
		return "review/allReview";
	}
}
