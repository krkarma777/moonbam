package com.moonBam.controller.member;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dao.member.LoginDAO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.OpenApiService;
import com.nimbusds.oauth2.sdk.Response;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class LoginController {

	@Autowired
	LoginService serv;
	
	@Autowired
	MailController mc;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	LoginDAO dao;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	SecurityController sc;

	@Autowired
	AnonymousBoardController abc;
	
	@RequestMapping("/mainLogin")   
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

	//OAuth2 로그인 에러 처리페이지(관리자 아이디로 소셜 로그인 시도)
	@GetMapping("/doNotSocialLoginByAdminID")
	public String doNotSocialLoginByAdminID() {
		return "member/Find_Info/doNotSocialLoginByAdminID";
	}

	//OAuth2 로그인 에러 처리페이지
	@GetMapping("/OAuth2Error")
	public String OAuth2Error() { return "member/Find_Info/emailErrorPage";
	}
	
	//비정상적 접근
	@GetMapping("/badRequest")
	public String badRequest() {
		return "member/Find_Info/emailErrorPage";
	}

	//정지된 유저의 접근
	@GetMapping("/restrictedMember")
	public String restrictedMember() {
		return "member/Find_Info/restrictedMember";
	}

	//로그인
//	SpringSecurity_SecurityConfig에서 처리
	
	//로그아웃
//	SpringSecurity_SecurityConfig에서 처리
	
	
	//아이디 찾기
	@PostMapping("/SearchID")
	public String SearchID(Model model, String secretCode) {

		System.out.println(secretCode);
		String realSecretCode = sc.encrypt(secretCode);
		System.out.println(realSecretCode);
		MemberDTO dto = serv.findDTOBySecretCode(realSecretCode);
		System.out.println(dto);
		if (dto != null) {
			model.addAttribute("dto", dto);
			return "member/Find_Info/viewID";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	//비밀번호 변경을 위한 메일 송신
	@PostMapping("/MailingPW")
	public String MailingPW(Model model, String secretCode, String userId) throws Exception {

		Map<String, String> map = new HashMap<>();
			map.put("userId", userId);
			map.put("secretCode", sc.encrypt(secretCode));

		MemberDTO dto = serv.mailingPW(map);

		if(dto==null){
			return "member/Find_Info/cantFindUserdata";
		}

		model.addAttribute("userId", dto.getUserId());
		model.addAttribute("nickname", dto.getNickname());

		//소셜네트워크 회원가입자의 경우
		if(dto.getKakaoConnected()==1) {
			System.out.println("카카오 회원가입자");
			model.addAttribute("kakaoRegister", true);
		}
		if(dto.getGoogleConnected()==1) {
			System.out.println("구글 회원가입자");
			model.addAttribute("googleRegister", true);
		}
		if(dto.getNaverConnected()==1) {
			model.addAttribute("naverRegister", true);
		}
		if(dto.getKakaoConnected()==1 || dto.getGoogleConnected()==1 || dto.getNaverConnected()==1) {
			return "member/Find_Info/socialRegsiter";
		}

		//ID와 SecretCode를 제대로 입력했고, 소셜 회원가입자가 아닌 경우
		if (dto != null) {
			String encodedPW = sc.encrypt(getNum(6));
			dto.setUserPw(encodedPW);

			String realPassword = encoder.encode(encodedPW);
			Map<String, String> map2 = new HashMap<>();
				map2.put("userId", userId);
				map2.put("userPw", realPassword);
				serv.updatePassword(map2);

			String[] emailparts = dto.getUserId().split("@");
			model.addAttribute("emailDomain", emailparts[1]);
			mc.sendEmail(dto.getUserId(), dto);
			return "member/Find_Info/viewAllPW";
		}
		
		return "member/Find_Info/emailErrorPage";
	}
	
	//비밀번호 변경을 위한 메일 송신
	@PostMapping("/SocialMailingPW")
	public String SocialMailingPW(Model model, String userId) throws Exception {

		//데이터 호출
		MemberDTO dto = dao.userDetail(userId);

		if (dto != null) {

			//임시 비밀번호 발급
			String encodedPW = sc.encrypt(getNum(6));
			dto.setUserPw(encodedPW);

			//임시 비밀번호 암호화
			String realPassword = encoder.encode(encodedPW);

			//임시 비밀번호로 암호 변경
			Map<String, String> map2 = new HashMap<>();
			map2.put("userId", userId);
			map2.put("userPw", realPassword);
			serv.updatePassword(map2);

			//변경된 데이터 호출
			dto = dao.userDetail(userId);

			//메일에 임시 비밀번호 전송을 위한 세팅
			dto.setUserPw(encodedPW);

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

	public String getNum(int num) {
		Random r = new Random();
		StringBuilder randomNumber = new StringBuilder();
		for (int i = 0; i < num; i++) {
			randomNumber.append(r.nextInt(10));
		}
		return randomNumber.toString();
	}
	
//	//비밀번호 변경
//	@PostMapping("/UpdatePassword")
//	public String UpdatePassword(String userId, String userPw) {
//
//		//userId
//		//Mapper에서 DB의 정보를 찾기 위한 userId
//
//		//userPw
//		//유저가 바꾸고 싶어서 입력한 비밀번호
//
//		//realPassword
//		//비밀번호를 SpringSecurity 시스템에서 사용하기 위해 암호화
//
//		//비밀번호 암호화
//		String realPassword = encoder.encode(userPw);
//
//		//비밀번호 변경을 위한 Map 생성
//		Map<String, String> map = new HashMap<>();
//			map.put("userId", userId);
//			map.put("userPw", realPassword);
//
//		//비밀번호 업데이트
//		serv.updatePassword(map);
//
//		//비밀번호 변경 후 jsp 이동
//		return "redirect:/UpdateComplete";
//	}
//
//	//비밀번호 변경 완료
//	@GetMapping("UpdateComplete")
//	public String UpdateComplete() {
//		return "member/Find_Info/updateComplete";
//	}

}
