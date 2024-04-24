package com.moonBam.springSecurity.JWT;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.moonBam.controller.adminpage.AdminCounter;
import com.moonBam.springSecurity.oauth2.OAuthUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	private final JWTUtil jwtUtil;
	
    public LoginSuccessHandler(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

		//인증객체에서 사용자 정보 추출(springSecurityUser - 아이디 / 패스워드 / 역할 / 활성화)
		OAuthUser oAuthUser = (OAuthUser) authentication.getPrincipal();

    	//사용자 정보 중 아이디 추출
        String username = oAuthUser.getUsername();

        //사용자 정보 중 사용자 권한 목록 추출
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        //사용자 권한 목록을 순회하는 iterator
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

        //iterator 중 첫번째 권한을 auth에 저장
        GrantedAuthority auth = iterator.next();

        //role에 권한 객체에서 실제 권한을 추출한 것을 저장 
        String role = auth.getAuthority();

        //아이디와 역할을 통해 JWT토큰 생성(10시간 유지)
        String token = jwtUtil.createJwt(username, role, 60*60*12000L);
        //System.out.println("LoginSuccessHandler token: "+ token);
        
        //JWT 쿠키 설정
        if (token != null) {
            String cookieValue = "AuthToken=" + token + "; Path=/acorn; HttpOnly";
            if (request.isSecure()) { // 여기에서 요청이 안전한지 확인합니다.
                cookieValue += "; Secure";
            }
            response.addHeader("Set-Cookie", cookieValue);
            response.sendRedirect("/acorn");
        }
        
    }
}