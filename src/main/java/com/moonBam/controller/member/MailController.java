package com.moonBam.controller.member;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    	
    		//Resources 폴더부터 경로 설정
    		ClassPathResource resource = new ClassPathResource("static/emailFiles/PWEmail.html");
    		//BufferedReader를 통해 한줄씩 읽어옴 || InputStreamReader를 통해 byte를 String Stream으로 변경
    		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
    		//StringBuilder: 문자열 연산을 수행할 때마다, 기존 문자열에 변경사항을 반영하여 작업
    		StringBuilder emailBody = new StringBuilder();

    		String line;
    		//HTML의 각 줄을 읽어오고, 특정 글자는 치환
    		while ((line = br.readLine()) != null) {
    		    line = line.replace("##유저_아이디##", dto.getUserId())
    		               .replace("##유저_이름##", dto.getUserName())
    		               .replace("##유저_비밀번호##", dto.getUserPw());
    		    //각 줄을 StringBuilder에 더하고, 개행
    		    emailBody.append(line).append("\n");
    		}

    		String TO_EMAIL = userEmail;
        	String EMAIL_SUBJECT = "[회원정보알림] 문화인의 밤을 이용해주셔서 감사합니다.";
        	
        	//StringBuilder를 String으로 전환하고 이메일 본문으로 저장
        	String EMAIL_BODY = emailBody.toString();

            serv.sendEmail(FROM_EMAIL, TO_EMAIL, EMAIL_SUBJECT, EMAIL_BODY);
    }
    
    //단순 메일 전송
    @PostMapping("/joinEmail")
    public String joinEmail(String userEmail) throws Exception {
    	
    		String authNumber = makeRandomNumber();
    	
			ClassPathResource resource = new ClassPathResource("static/emailFiles/JoinEmail.html");
			BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			StringBuilder emailBody = new StringBuilder();
	
			String line;
			while ((line = br.readLine()) != null) {
			    line = line.replace("##인증번호##", authNumber)
			               .replace("##인증번호_유효기간##", expireDate);
			    emailBody.append(line).append("\n");
		}
    	
        	String TO_EMAIL = userEmail;
        	String EMAIL_SUBJECT = "[회원정보알림] 문화인의 밤을 이용해주셔서 감사합니다.";
        	String EMAIL_BODY = emailBody.toString();
        			
            serv.sendEmail(FROM_EMAIL, TO_EMAIL, EMAIL_SUBJECT, EMAIL_BODY);
            return "send";
    }
    
    
    //로컬 데이터만 전송됨********************************************************************************************************************
    //파일 첨부 메일 전송
    @GetMapping("/sendFileEmail")
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
	@GetMapping("/deleteCookiee")
	public String deleteCookiee(HttpServletResponse response){
		
		Cookie c = new Cookie("confirmNum", null); // 인증번호 값을 무작위 숫자로 지정
	
		c.setMaxAge(0); // 유효시간을 0으로 설정
	
		response.addCookie(c); // 응답 헤더에 추가해서 없어지도록 함
		
	return "complete";

	}
 		
	//인증번호 확인 AJAX
	@PostMapping("/CertificationAnswer")
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