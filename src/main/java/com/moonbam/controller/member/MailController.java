package com.moonbam.controller.member;


import com.moonbam.dto.MemberDTO;
import com.moonbam.service.member.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Random;

@RestController
public class MailController {

	@Autowired
	private MailService mailService;
	
	private String expireDate;
	
	// 전체 회원 비밀번호 찾기 메일
	public void sendEmail(String userEmail, MemberDTO dto) throws Exception {

		String addr = "cjstkrhdfk666@gmail.com";
		String subject = "[회원정보알림] 문화인의 밤을 이용해주셔서 감사합니다.";
		String body = 	"안녕하세요? 문화인의 밤입니다.\n\n"
						+ dto.getUserName()+"님의 아이디는 "+dto.getUserId()+", 비밀번호는 "+dto.getUserPw()+"입니다.\n\n"
						+ "감사합니다.\n"
						+ "문화인의 밤 드림.";
		mailService.sendEmail(userEmail, addr, subject, body);
	}

	// 회원가입 인증을 위한 인증번호 메일
	@RequestMapping(value =  "/joinEmail", method = RequestMethod.POST)
	public String joinEmail(String userEmail) throws Exception {

		String authNumber = makeRandomNumber();
		String addr = "cjstkrhdfk666@gmail.com";
		String subject = "[인증번호알림] 문화인의 밤을 이용해주셔서 감사합니다.";


		String body = 	"안녕하세요? 문화인의 밤입니다.\n\n" 
				+ "인증 번호는 [ " + authNumber + " ] 입니다.\n" 
				+ "인증번호의 유효기간은 [ " + expireDate + " ] 입니다.\n\n"
				+ "감사합니다.\n"
				+ "문화인의 밤 드림.";
						
		mailService.sendEmail(userEmail, addr, subject, body);
		
		return "send";
	}

	// 임의의 6자리 양수를 반환
	public String makeRandomNumber() {
		Random r = new Random();
		String randomNumber = "";
		for (int i = 0; i < 6; i++) {
			randomNumber += Integer.toString(r.nextInt(10));
		}
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();

		Cookie c= new Cookie("confirmNum", randomNumber);	// 인증번호 값을 쿠키로 생성
		
		c.setMaxAge(60*3);	// 인증번호 유호시간 3분
		
		response.addCookie(c);
		
		 long expirationTimeMillis = System.currentTimeMillis() + (c.getMaxAge() * 1000);	//쿠키 만료 시간 확인
		 Date expirationDate = new Date(expirationTimeMillis);
		 
		// SimpleDateFormat을 사용하여 연월일시분초로 변환
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		    expireDate = sdf.format(expirationDate);
		
		    System.out.println("쿠키의 만료 시간: " + expireDate);
		
		return randomNumber;
	}
	
	//쿠키 삭제
	@RequestMapping(value="/deleteCookiee", method=RequestMethod.GET)

	public String deleteCookiee(HttpServletResponse response){
		
		String r = makeRandomNumber();
		
		Cookie c = new Cookie("confirmNum", null); // 인증번호 값을 무작위 숫자로 지정
	
		c.setMaxAge(0); // 유효시간을 0으로 설정
	
		response.addCookie(c); // 응답 헤더에 추가해서 없어지도록 함
		
	return "complete";

	}
	
	
	//인증번호 확인 AJAX
	@RequestMapping(value =  "/CertificationAnswer", method = RequestMethod.POST)
	public String CertificationAnswer(String certification, HttpServletRequest request) {
		Cookie[] cookies= request.getCookies();
		String confirmNumValue = "";
		String mesg = "";
		if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("confirmNum".equals(cookie.getName())) {
	                confirmNumValue = cookie.getValue();
	                break; 
	            }
	        }
	    }
		if(confirmNumValue.equals(certification.trim())) {
			mesg = "confirm";
		} else {
			mesg = "notConfirm";
		}
		return mesg;
	}

}
