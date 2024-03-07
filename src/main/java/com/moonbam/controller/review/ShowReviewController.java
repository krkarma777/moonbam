package com.moonbam.controller.review;

import com.moonbam.dto.ContentDTO;
import com.moonbam.dto.MemberDTO;
import com.moonbam.dto.ReviewDTO;
import com.moonbam.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class ShowReviewController {

	@Autowired
	ReviewService service;
	
	@RequestMapping(value="/review", method= RequestMethod.GET)
	public String ShowReview(String postId, HttpSession session, HttpServletRequest request) {
		
		// 세션에서 로그인 정보 파싱
		MemberDTO login = (MemberDTO) session.getAttribute("loginUser");
		String likeUserId = null;
		// 로그인 정보가 존재할 때
		if(login!=null) {
			likeUserId = login.getUserId();
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
