package com.moonbam.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

@RestController
public class SecurityController {

	@Autowired
	private BCryptPasswordEncoder encoder; 
	
	@Autowired
	private AES256Util aesutil;

//	단방향 암호화
//	@RequestMapping("/encodepassword")
//	public String bcript() {
//		String str = "password";							//DB에 있는 비밀번호
//		String encodingStr = encoder.encode(str);			//암호화 처리된 문자열로 리턴(로그인 할 때 비밀번호)
//		Boolean result = encoder.matches(str, encodingStr);	//비밀번호 비교
//		return "원래 비밀번호: " + str + "<br>--> 이런 식으로 바뀜: " + encodingStr + "<br>" + "str = encodingStr(2개가 같은 지 비교) --> " + result;
//	}
	
	//양방향 암호화
	@RequestMapping(value = "/EncodePW", method = RequestMethod.POST)
	public String EncodePW(String userPw) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		String encodingStr = aesutil.encrypt(userPw); 		// 암호화
		System.out.println(" 비밀번호: " + userPw + " 인코딩: " + encodingStr);
		return encodingStr;
	}
	
	@RequestMapping(value = "/DecodePW", method = RequestMethod.POST)
	public String DecodePW(String encodingStr) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		String decodingStr = aesutil.decrypt(encodingStr); // 복호화
		System.out.println(" 인코딩: " + encodingStr + " 디코딩: " + decodingStr);
		return decodingStr;
	}
	
	
}
