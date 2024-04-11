package com.moonBam.controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.OpenApiService;
import com.moonBam.service.member.RegisterService;


@Controller
public class NewCommerController {

	@Autowired
	LoginService loginServ;

	@Autowired
	RegisterService serv;
	
	@Autowired
	OpenApiService openApiService;
	
	@RequestMapping("/IdDupilicate")   
	public String Login() {
		return "member/Register/childCheckIDDupilicate";
	}
	
	//로그인 메인에서 기존 유저 확인		//약관 동의 연결
	@PostMapping("/RegisterTerms")
	public String RegisterTerms(HttpServletRequest request, String userId, HttpSession session) {
		MemberDTO dto = openApiService.selectOneAPIMember(userId);
		
		//이름과 SSN이 모두 일치하는 DB정보가 있을 경우, 비정상적 접근 페이지로 이동
		if (dto != null) {
			request.setAttribute("dto", dto);
			return "member/Find_Info/emailErrorPage";

		//이름과 SSN이 모두 일치하는 DB정보가 없을 경우, 회원가입 2단계로 이동
		} else {
			request.setAttribute("userId", userId);
			return "member/Register/registerAgreeTerms";
		}
	}
	
	//약관 동의		//회원가입 연결
	@PostMapping("/CheckExistUser")
	public String CheckExistUser(HttpServletRequest request, String userId, 
			String checked_Agreement, String checked_Info, String checked_Withdraw, HttpSession session) {
		
																			//디버그 코드***************************************************
																			System.out.println("userId "+userId);
																			System.out.println(
																				"checked_Agreement : " + checked_Agreement+"\n"+ 
																				"checked_Info : " + checked_Info+"\n"+
																				"checked_Withdraw : " + checked_Withdraw);
																			//디버그 코드***************************************************		
			request.setAttribute("userId", userId);
		return "member/Register/registerMember";
	}
	
	@GetMapping("/CheckExistUser")
	public String registerErrorPage() {
		return "member/Register/registerErrorPage";
	}
	
	@PostMapping("/InsertData")
	public ModelAndView register(MemberDTO dto, HttpServletRequest request) {
		System.out.println(dto);
		String userPwConfirm = request.getParameter("userPwConfirm");
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.addObject("userPwConfirm", userPwConfirm);
		mav.setViewName("member/Register/registerLoading");
		return mav;
	}
}
