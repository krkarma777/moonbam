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

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.RegisterService;


@Controller
public class NewCommerController {

	@Autowired
	LoginService loginServ;

	@Autowired
	RegisterService serv;
	
	@RequestMapping("/IdDupilicate")   
	public String Login() {
		return "member/Register/childCheckIDDupilicate";
	}
	
	//로그인 메인에서 기존 유저 확인		//약관 동의 연결
	@PostMapping("/RegisterTerms")
	public String RegisterTerms(HttpServletRequest request, String email, HttpSession session) {
		String[] emailParts = email.split("@");
		Map<String, String> map = new HashMap<>();
			map.put("userEmailId", emailParts[0]);
			map.put("userEmailDomain", emailParts[1]);
		
		MemberDTO dto = loginServ.findUserId(map);
		
		//이름과 SSN이 모두 일치하는 DB정보가 있을 경우, 기존 유저 있음 jsp로 이동
		if (dto != null) {
			request.setAttribute("dto", dto);
			return "member/Register/viewExistUserdata";

		//이름과 SSN이 모두 일치하는 DB정보가 없을 경우, 회원가입 2단계로 이동
		} else {
			request.setAttribute("email", email);
			return "member/Register/registerAgreeTerms";
		}
	}
	
	//약관 동의		//회원가입 연결
	@PostMapping("/CheckExistUser")
	public String CheckExistUser(HttpServletRequest request, String email, 
			String checked_Agreement, String checked_Info, String checked_Withdraw, HttpSession session) {
		
																			//디버그 코드***************************************************
																			System.out.println("email "+email);
																			System.out.println(
																				"checked_Agreement : " + checked_Agreement+"\n"+ 
																				"checked_Info : " + checked_Info+"\n"+
																				"checked_Withdraw : " + checked_Withdraw);
																			//디버그 코드***************************************************		
			request.setAttribute("email", email);
		return "member/Register/registerMember";
	}
	
	@GetMapping("/CheckExistUser")
	public String registerErrorPage() {
		return "member/Register/registerErrorPage";
	}
}
