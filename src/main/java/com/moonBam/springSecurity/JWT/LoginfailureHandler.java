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

		// 로그인 실패 후 '유저 정보 찾을 수 없음' 페이지로 리다이렉트
		response.sendRedirect("/acorn/OAuth2Error");
	}
}