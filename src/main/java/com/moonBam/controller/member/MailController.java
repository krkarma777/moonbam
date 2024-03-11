package com.moonBam.controller.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.MailService;

@RestController
public class MailController {

	@Autowired
	MailService serv;
	private String expireDate;
	
	private static final String FROM_EMAIL = "cjstkrhdfk666@gmail.com";

	//단순 메일 전송
    public void sendEmail(String userEmail, MemberDTO dto) throws Exception {
        	String TO_EMAIL = userEmail;
        	String EMAIL_SUBJECT = "[회원정보알림] 문화인의 밤을 이용해주셔서 감사합니다.";
        	String EMAIL_BODY = 
        			"<p>안녕하세요? 문화인의 밤입니다.</p><br>"
					+ "<p>"+dto.getUserName()+"님의 아이디는 "+dto.getUserId()+", 비밀번호는 "+dto.getUserPw()+"입니다.</p><br>"
					+ "<p style='text-align: right;'>감사합니다.<br>"
					+ "문화인의 밤 드림.</p>";
            serv.sendEmail(FROM_EMAIL, TO_EMAIL, EMAIL_SUBJECT, EMAIL_BODY);
    }
    
    //단순 메일 전송
    @RequestMapping(value =  "/joinEmail", method = RequestMethod.POST)
    public String joinEmail(String userEmail) throws Exception {
    		
    		String authNumber = makeRandomNumber();
        	String TO_EMAIL = userEmail;
        	String EMAIL_SUBJECT = "[회원정보알림] 문화인의 밤을 이용해주셔서 감사합니다.";
        	String EMAIL_BODY = 
        			"<p>안녕하세요? 문화인의 밤입니다.</p><br>"
					+ "<p>인증 번호는 [ " + authNumber + " ] 입니다.</p>" 
					+ "<p>인증번호의 유효기간은 [ " + expireDate + " ] 입니다.</p><br>"
					+ "<p style='text-align: right;'>감사합니다.<br>"
					+ "문화인의 밤 드림.</p>";
            serv.sendEmail(FROM_EMAIL, TO_EMAIL, EMAIL_SUBJECT, EMAIL_BODY);
            return "send";
    }
    
    
    //로컬 데이터만 전송됨********************************************************************************************************************
    //파일 첨부 메일 전송
    @RequestMapping(value =  "/sendFileEmail", method = RequestMethod.GET)
    public String sendFileEmail(String userEmail) throws Exception {
        	String TO_EMAIL = 
        			"cjstkrhdfk@naver.com"; 
        			//userEmail;
        	String EMAIL_SUBJECT = "[회원정보알림] 문화인의 밤을 이용해주셔서 감사합니다.";
        	String EMAIL_BODY = "Email body";
        	serv.sendEmailWithFiles(FROM_EMAIL, TO_EMAIL, EMAIL_SUBJECT, EMAIL_BODY);
        	return "send";
    }
    //로컬 데이터만 전송됨*********************************************************************************************************************
    
    
    
    ////////////////// ////////////////// ////////////////// ////////////////// 
    //								 함수와 ajax								 //
    ////////////////// ////////////////// ////////////////// ////////////////// 
    
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