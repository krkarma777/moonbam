package com.moonBam.controller.member;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.LoginService;


@Controller
public class LoginController {

	@Autowired
	LoginService serv;
	
	@Autowired
	MailController mc;
	
	@Autowired
	SecurityController sc;
	
	@RequestMapping("/Login")   
	public String Login() {
		return "member/Login/loginMain";
	}
	
	@RequestMapping("/FindInfo")   
	public String FindInfo() {
		return "member/Find_Info/searchMemberData";
	}
	
	@RequestMapping("/FindAllPW")   
	public String FindAllPW() {
		return "member/Find_Info/childFindAllPW";
	}
	
	//로그인
	@PostMapping("/Logined")
	public String LoginToMypage(String userId, String userPw, HttpSession session, boolean userIdSave,  HttpServletResponse response, boolean autoLogin) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		String realUserPw = sc.encrypt(userPw);
		System.out.println("아이디 저장: " + userIdSave);					//체크되면 true
		System.out.println("자동 로그인: " + autoLogin);					//체크 안 되면 false
		MemberDTO dto = serv.login(userId, realUserPw);

		if (dto != null) {
			session.setAttribute("loginUser", dto);

			if(autoLogin) {
				
				Cookie autoId= new Cookie("userId", userId);
				Cookie autoPW= new Cookie("userPw", userPw);
				autoId.setMaxAge(60*60*24);
				autoPW.setMaxAge(60*60*24);
				response.addCookie(autoId);
				response.addCookie(autoPW);
				
//				System.out.println("등록 오토 아이디"+ autoId);
//				System.out.println("등록 오토 패스"+ autoPW);

			} else {
				
				Cookie autoId= new Cookie("userId", null);
				Cookie autoPW= new Cookie("userPw", null);
				autoId.setMaxAge(0);
				autoPW.setMaxAge(0);
				response.addCookie(autoId);
				response.addCookie(autoPW);

//				System.out.println("삭제 오토 아이디"+ autoId);
//				System.out.println("삭제 오토 패스"+ autoPW);
				
				if(userIdSave) {
					Cookie id= new Cookie("userId", userId);
					id.setMaxAge(60*60*24);
					response.addCookie(id);
					
//					System.out.println("등록 저장 아이디"+ id);
				} else {
					Cookie id= new Cookie("userId", null);
					id.setMaxAge(0);
					response.addCookie(id);
					
//					System.out.println("삭제 저장 아이디"+ id);
				}
				
			}
			
			return "main";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}
	}

	//로그아웃
	@GetMapping("/Logout")
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
	@PostMapping("/SearchID")
	public String SearchID(Model model, String userName, String ssn1, String ssn2) {
		MemberDTO dto = serv.findUserId(userName, ssn1, ssn2);
		if (dto != null) {
			model.addAttribute("dto", dto);
			return "member/Find_Info/viewID";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	//비밀번호 찾기
	@PostMapping("/SearchPartPW")
	public String SearchPartPW(Model model, HttpServletResponse response, String userId, String userName, String ssn1, String ssn2) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		MemberDTO dto = serv.findUserPW(userId, userName, ssn1, ssn2);
				
		if (dto != null) {
			String userPw = sc.decrypt(dto.getUserPw());
			dto.setUserPw(userPw);
			
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
	@GetMapping("/SearchAllPW")
	public String SearchAllPW(Model model, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		String userId = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("findPW_userid")) {
				userId = cookie.getValue();
			}
		}
		
		MemberDTO dto = serv.selectMemberData(userId);
		
		if (dto != null) {
			
			String userEmail = dto.getUserEmailId()+"@"+dto.getUserEmailDomain();
			String userPw = sc.decrypt(dto.getUserPw());
			dto.setUserPw(userPw);
			
			model.addAttribute("dto", dto);
			mc.sendEmail(userEmail, dto);

			Cookie userIdCookie = new Cookie("findPW_userid",null);
			userIdCookie.setMaxAge(0);
			response.addCookie(userIdCookie);
			
			return "member/Find_Info/viewAllPW";
		} else {
			return "member/Find_Info/emailErrorPage";
		}

	}
	
	
	
}
