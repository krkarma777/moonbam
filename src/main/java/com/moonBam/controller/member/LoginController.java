package com.moonBam.controller.member;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.OpenApiService;


@Controller
public class LoginController {

	@Autowired
	LoginService serv;
	
	@Autowired
	MailController mc;
	
	@Autowired
	OpenApiService openApiService;
	
	@Autowired
	PasswordEncoder encoder;
	
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
	
	@RequestMapping("/cantFindUserdata")   
	public String cantFindUserdata(Principal principal) {
		//null이면 로그인 처리 X
		//System.out.println("principal: "+ principal);
		return "member/Find_Info/cantFindUserdata";
	}
	
	@RequestMapping("/NotAuthentic")   
	public String notAuthentic() {
		return "member/Find_Info/notAuthentic";
	}
	
	
	//로그인
//	SpringSecurity_SecurityConfig에서 처리
	
	//로그아웃
//	SpringSecurity_SecurityConfig에서 처리
	
	
	//아이디 찾기
	@PostMapping("/SearchID")
	public String SearchID(Model model, String secretCode) {

		String userId = serv.findUserId(secretCode);
		if (userId != null) {
			model.addAttribute("userId", userId);
			return "member/Find_Info/viewID";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	//비밀번호 변경을 위한 메일 송신
	@PostMapping("/MailingPW")
	public String MailingPW(Model model, String secretCode, String userId) throws Exception {
		
		Map<String, String> map = new HashMap<>();
			map.put("secretCode", secretCode);
			map.put("userId", userId);
		
		MemberDTO dto = serv.mailingPW(map);
		
		if (dto != null) {

			String[] emailparts = dto.getUserId().split("@");
			
			model.addAttribute("userId", dto.getUserId());
			model.addAttribute("nickname", dto.getNickname());
			model.addAttribute("emailDomain", emailparts[1]);
			mc.sendEmail(dto.getUserId(), dto);

			return "member/Find_Info/viewAllPW";
		} 
		return "member/Find_Info/emailErrorPage";
	}
	
	//메일 전송 완료 화면에서 새로고침 시, 이동
	@GetMapping("/MailingPW")
	public String registerErrorPage(){
		return "member/Register/registerErrorPage";
	}
		
	//이메일 하이퍼링크를 통해 들어오는 비밀번호 변경 페이지
	@GetMapping("/UpdatePasswordPage")
	public ModelAndView UpdatePasswordPage(String userId) {
		ModelAndView mav = new ModelAndView();
			mav.addObject("userId", userId);
			mav.setViewName("member/Find_Info/updatePassword");
		return mav;
	}
	
	//비밀번호 변경
	@PostMapping("/UpdatePassword")
	public String UpdatePassword(String userId, String userPw) {
		
		String realPassword = encoder.encode(userPw);
		
		Map<String, String> map = new HashMap<>();
			map.put("userId", userId);
			map.put("userPw", realPassword);
			serv.updatePassword(map);
		return "redirect:/UpdateComplete";
	}
	
	//비밀번호 변경 완료
	@GetMapping("UpdateComplete")
	public String UpdateComplete() {
		return "member/Find_Info/updateComplete";
	}
	
}
