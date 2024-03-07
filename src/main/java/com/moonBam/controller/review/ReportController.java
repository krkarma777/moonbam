package com.moonBam.controller.review;


import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.ReportDTO;
import com.moonBam.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class ReportController {

	@Autowired
	ReviewService service;
	
	@RequestMapping(value="/report", method= RequestMethod.GET)
	public String Report(ReportDTO report, HttpSession session, RedirectAttributes flash) {
		// 세션에서 로그인 정보 파싱
		MemberDTO login = (MemberDTO) session.getAttribute("loginUser");
		
		// 로그인 정보가 존재할 때
		if(login!=null) {
			String userId = login.getUserId();
			// dto에 담아서 전달
			report.setUserId(userId);

			// db에 신고정보 저장
			service.reportReview(report);
			
			// 완료메세지
			flash.addFlashAttribute("mesg", "해당 리뷰를 신고하였습니다.");
		} else {
			flash.addFlashAttribute("mesg", "로그인 정보가 없습니다.");
		}

		return "redirect:review?postId="+report.getPostId();
	}
}
