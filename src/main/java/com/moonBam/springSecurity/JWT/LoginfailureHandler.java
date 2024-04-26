package com.moonBam.springSecurity.JWT;


import java.io.IOException;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginfailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		String message = exception.getMessage();
		String[] messageArr = message.split("설정");

		if (messageArr[0].equals("[닉네임")){
			String[] mesg = messageArr[1].split("]");
			String email = mesg[0];
			// 회원가입 후 '닉네임 설정' 페이지로 리다이렉트
			response.sendRedirect("/acorn/forChangeNickname?userId="+email);
		} else

		if(messageArr[0].equals("[관리자")){
			// 로그인 실패 후 '관리자 아이디의 소셜로그인 접속시도가 탐지' 페이지로 리다이렉트
			response.sendRedirect("/acorn/doNotSocialLoginByAdminID");

		} else {

			// 로그인 실패 후 '유저 정보 찾을 수 없음' 페이지로 리다이렉트
			response.sendRedirect("/acorn/OAuth2Error");
		}
	}
}