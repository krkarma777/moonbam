package com.moonBam.controller.review;

import com.moonBam.dto.ContentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.ReviewDTO;
import com.moonBam.dto.ReviewPageDTO;
import com.moonBam.service.ReviewService;
import com.moonBam.service.member.MemberLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

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
		String likeUserId=null; // 로그인 유저아이디
		if(loginUser!=null) {
			likeUserId = loginUser.getUserId();
			request.setAttribute("member", loginUser);
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
			request.setAttribute("loginUserId", likeUserId); //
			request.setAttribute("mesg", (String)request.getAttribute("mesg"));
			nextPage = "review/reviewViewer";
		}

		return nextPage;
	}
	
	@RequestMapping("/allReview")
	public String allReview(Principal principal, Model model, String contId, HttpSession session, HttpServletRequest request,
							String curPage) {
		System.out.println("in ShowReviewController allReview()");
		if(curPage==null) {
			curPage="1";
		}
		
		MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
		String likeUserId = null;
		if(loginUser!=null) {
			// 자신이 누른좋아요 정보 가져오기 위해 본인의 유저아이디 저장
			likeUserId= loginUser.getUserId();
			request.setAttribute("member", loginUser);
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("contId", contId);
			map.put("likeUserId", likeUserId);
			String nickname = loginUser.getNickname();
			map.put("nickname", nickname);
			ReviewDTO myReview = service.myReview(map);
			model.addAttribute("myreview", myReview);
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("contId", contId);
		map.put("likeUserId", likeUserId);
		map.put("curPage", curPage);
		ReviewPageDTO rpDTO = service.allReview(map);
		
		model.addAttribute("rpDTO", rpDTO);
		model.addAttribute("contId", contId);
		
		return "review/allReview";
	}
}
