package com.moonBam.controller.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.MailService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class MailController {

	@Autowired
	MailService serv;
	
	@Autowired
	SecurityController sc;
	
	private String expireDate;
	
	private static final String FROM_EMAIL = "cjstkrhdfk666@gmail.com";

	//단순 메일 전송
    public void RegisterCompleteEmail(String userEmail, String nickname, String secretCode) throws Exception {
    		Map<String, String> changeData = new HashMap<>();
    		System.out.println(secretCode);
    		
    		//******************복사 후 수정하는 부분******************
    		String emailPath = "static/emailFiles/RegisterCompleteEmail.html";	//HTML 경로

    		String EMAIL_SUBJECT = "[회원가입안내] 문화인의 밤에 오신 것을 환영합니다!";		//이메일 제목
    		
    		changeData.put("##유저_닉네임##", nickname);
    		changeData.put("##유저_보안코드##", secretCode);
    		
    		String innerImageName = "Register_Main";							//내부 이미지 파일 명
    		String innerImagePath = "/static/images/email/Register_Main.png";	//내부 이미지 파일 경로
    		//******************복사 후 수정하는 부분******************	   		
	
        	String TO_EMAIL = userEmail;
        	String EMAIL_BODY = serv.EmailBody(emailPath, changeData);
        			
        	serv.sendEmail(FROM_EMAIL, TO_EMAIL, EMAIL_SUBJECT, EMAIL_BODY, innerImageName, innerImagePath);
    }	
	
    //단순 메일 전송 - 비밀번호 전송
    public void sendEmail(String userEmail, MemberDTO dto) throws Exception {

    		Map<String, String> changeData = new HashMap<>();
    		
    		//******************복사 후 수정하는 부분******************
    		String emailPath = "static/emailFiles/PWEmail.html";				
	    	
    		String EMAIL_SUBJECT = "[회원정보알림] 문화인의 밤을 이용해주셔서 감사합니다.";		
    		
    		changeData.put("##유저_링크##", "http://localhost:8090/acorn/mainLogin");
			changeData.put("##유저_비밀번호##", dto.getUserPw());
    		changeData.put("##유저_닉네임##", dto.getNickname());

	    	String innerImageName = "FindPW";
    		String innerImagePath = "/static/images/email/FindPW.png";
	    	//******************복사 후 수정하는 부분******************
    	
    		String TO_EMAIL = userEmail;
        	//StringBuilder를 String으로 전환하고 이메일 본문으로 저장
        	String EMAIL_BODY = serv.EmailBody(emailPath, changeData);
        	serv.sendEmail(FROM_EMAIL, TO_EMAIL, EMAIL_SUBJECT, EMAIL_BODY, innerImageName, innerImagePath);
    }
    
    //단순 메일 전송 - 회원가입을 위한 인증법호 전송
    @PostMapping("/joinEmail")
    public String joinEmail(String userEmail) throws Exception {
    		String authNumber = makeRandomNumber();
    		Map<String, String> changeData = new HashMap<>();
    		
    		//******************복사 후 수정하는 부분******************
    		String emailPath = "static/emailFiles/JoinEmail.html";
			
    		String EMAIL_SUBJECT = "[회원가입알림] 문화인의 밤을 이용해주셔서 감사합니다.";
    		
    		changeData.put("##인증번호##", authNumber);
    		changeData.put("##인증번호_유효기간##", expireDate);

    		String innerImageName = "JoinNumber";
    		String innerImagePath = "/static/images/email/JoinNumber.png";
	    	//******************복사 후 수정하는 부분******************	    		
	
        	String TO_EMAIL = userEmail;
        	String EMAIL_BODY = serv.EmailBody(emailPath, changeData);
        			
        	serv.sendEmail(FROM_EMAIL, TO_EMAIL, EMAIL_SUBJECT, EMAIL_BODY, innerImageName, innerImagePath);
            return "send";
    }
    
    
    //파일 첨부 메일 전송
    @GetMapping("/sendFileEmail")												//*****이후 POST로 변경하여 사용*****
    public String sendFileEmail(String userEmail) throws Exception {
        	
        	Map<String, String> changeData = new HashMap<>();
    		
    		//******************복사 후 수정하는 부분******************
    		String emailPath = "static/emailFiles/FileEmail.html";				//HTML 경로
    		
    		String EMAIL_SUBJECT = "[파일전송알림] 문화인의 밤을 이용해주셔서 감사합니다.";		//이메일 제목
    		
    		changeData.put("##HTML내용##", "변경할 값");							//HTML에서 치환할 데이터
    		
    		String filePath = "/static/images/sample.jpg";						//전송할 파일 경로
    		String fileName = "샘플.jpg";											//파일 제목

    		String innerImageName = null;										//내부 이미지 파일 명
    		String innerImagePath = "/static/images/email/null";				//내부 이미지 파일 경로
	    	//******************복사 후 수정하는 부분******************	    		
	
        	String TO_EMAIL = "userEmail";										
        	String EMAIL_BODY = serv.EmailBody(emailPath, changeData);
        	
        	serv.sendEmailWithFiles(FROM_EMAIL, TO_EMAIL, EMAIL_SUBJECT, EMAIL_BODY, filePath, fileName, innerImageName, innerImagePath);
        	return "send";
        	
        	
    }
    
    
    
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

 		Cookie c= new Cookie("confirmNum", sc.encrypt(randomNumber));	// 인증번호 값을 암호화하여 쿠키로 생성
 		
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
	                confirmNumValue = sc.decrypt(cookie.getValue());
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