package com.moonBam.controller.content;

import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moonBam.dto.ContentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.RateDTO;
import com.moonBam.dto.ReviewDTO;
import com.moonBam.service.ReviewService;

@Controller
public class ShowContentController {
	
	@Autowired
	ReviewService service;
	
	// 컨텐츠아이디 받음
	// 컨텐츠 데이터, 컨텐츠에 해당하는 리뷰들 얻어서 응답
	@RequestMapping("/content-page")
	public String contentPage(String contId, HttpSession session, HttpServletRequest request) {
		
		
		MemberDTO login = (MemberDTO)session.getAttribute("loginUser");
		
		// 자신이 누른좋아요 정보 가져오기 위해 본인의 유저아이디 저장
		String likeUserId = null;
		if(login!=null) {
			likeUserId = login.getUserId();
		}
		
		//임시 컨텐츠 데이터 생성 (나중에 삭제)
		if(contId==null) {
			contId = "1";
		}
		
		String nextPage = "";
		// 예외처리 : contId 부재시
		if(contId==null) {
			// 영화홈 이동
			nextPage = "main?cg=movie";
		} else {
			// DB에서 컨텐츠정보 가져오기
			ContentDTO content = service.selectContent(contId);
			request.setAttribute("content", content);
			
			// DB에서 컨텐츠에 해당하는 리뷰리스트 가져와서 전달
			// 최신순으로 8개 select
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("contId", contId);
			map.put("likeUserId", likeUserId);//페이지 사용중인 유저id (각 리뷰에 좋아요 눌렀는지 불러오기 위하여 전달)
			List<ReviewDTO> reviewList = service.selectReviews(map);
			request.setAttribute("reviewList", reviewList);
			
			// 별점 리스트 가져와서 전달 (평균별점 계산용)
			List<RateDTO> rateList = service.selectRates(contId);
			request.setAttribute("rateList", rateList);
			
			nextPage = "content/contentViewer";
		}
		
		return nextPage;
	}
	
	@RequestMapping("/showContent")
	public String showContent(String contId, Model m, HttpSession session) {
		ContentDTO content = service.selectContent(contId);
		m.addAttribute("content", content);
		
		MemberDTO login = (MemberDTO)session.getAttribute("loginUser");
		
		String likeUserId = null;
		if(login!=null) {
			likeUserId = login.getUserId();
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("contId", contId);
		map.put("likeUserId", likeUserId);
		List<ReviewDTO> reviewList = service.selectReviews(map);
		m.addAttribute("reviewList", reviewList);
		return "content/showContent";
	}
}
