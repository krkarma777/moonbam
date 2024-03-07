package com.moonbam.controller.member;


import com.moonbam.dto.MemberDTO;
import com.moonbam.service.member.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

@Controller
public class LoginController {

	@Autowired
	LoginService serv;
	
	@Autowired
	SecurityController sc;
	
	@Autowired
	MailController mc;
	
	//로그인
	@RequestMapping(value = "/Logined", method = RequestMethod.POST)
	public String LoginToMypage(String userId, String userPw, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		String realUserPw = sc.EncodePW(userPw);
		System.out.println(realUserPw);
		MemberDTO dto = serv.login(userId, realUserPw);

		if (dto != null) {
			session.setAttribute("loginUser", dto);
			return "main";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}

	//로그아웃
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String Logout(HttpSession session) {
		MemberDTO dto = (MemberDTO) session.getAttribute("loginUser");
		if (dto != null) {
			session.removeAttribute("loginUser");
			return "main";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	//아이디 찾기
	@RequestMapping(value = "/SearchID", method = RequestMethod.POST)
	public String SearchID(Model model, String userName, String ssn1, String ssn2) {
		MemberDTO dto = serv.findUserId(userName, ssn1, ssn2);
		System.out.println(dto);
		if (dto != null) {
			model.addAttribute("dto", dto);
			return "member/Find_Info/viewID";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	//비밀번호 찾기
	@RequestMapping(value = "/SearchPartPW", method = RequestMethod.POST)
	public String SearchPartPW(Model model, HttpServletResponse response, String userId, String userName, String ssn1, String ssn2) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		MemberDTO dto = serv.findUserPW(userId, userName, ssn1, ssn2);
		String userPw = sc.DecodePW(dto.getUserPw());
		System.out.println(userPw);
		dto.setUserPw(userPw);
		
		if (dto != null) {
			Cookie userIdCookie = new Cookie("findPW_userid",userId);
			userIdCookie.setMaxAge(30*60);
			response.addCookie(userIdCookie);
			
			model.addAttribute("dto", dto);
			return "member/Find_Info/viewPartPW";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}
	}
	
	//전체 비밀번호 출력용
	@RequestMapping(value = "/SearchAllPW", method = RequestMethod.GET)
	public String SearchAllPW(Model model, String userId, HttpSession session) throws Exception {
		MemberDTO dto = serv.selectMemberData(userId);
		String userEmail = dto.getUserEmailId()+"@"+dto.getUserEmailDomain();
		String userPw = sc.DecodePW(dto.getUserPw());
		dto.setUserPw(userPw);
		System.out.println(userPw);
		System.out.println(userEmail);
		System.out.println(dto);
		
		if (dto != null) {
			model.addAttribute("dto", dto);
			mc.sendEmail(userEmail, dto);
			return "member/Find_Info/viewAllPW";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	
	
	
	
	
	
}
