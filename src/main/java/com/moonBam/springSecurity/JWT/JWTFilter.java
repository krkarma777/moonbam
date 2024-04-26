package com.moonBam.springSecurity.JWT;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.moonBam.dto.MemberDTO;
import com.moonBam.springSecurity.SpringSecurityUser;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

//    	System.out.println("doFilterInternal호출");    	
    	
    	//재로그인 무한 루프 오류 방지
    	//JWT가 만료된 상태에서 재로그인되면 OAuth2 로그인 실패 --> 재요청 --> 무한루프 발생
//    	String requestUri = request.getRequestURI();
//    	if (requestUri.matches("^\\/login(?:\\/.*)?$")) {
//    	    filterChain.doFilter(request, response);
//    	    return;
//    	}
//    	if (requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {
//    	    filterChain.doFilter(request, response);
//    	    return;
//    	}

    	
		//쿠키에 user 식별 key(AuthToken) 있는지 확인
    	String token = null;
    	Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("AuthToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        
        //AuthToken이 있고 유효기간이 만료되지 않았을 경우
        if (token != null && jwtUtil.validateToken(token)) {
            String username = jwtUtil.getUsername(token);
            String role = jwtUtil.getRole(token);	// 자동으로 접두사 제거

            MemberDTO memberDTO = new MemberDTO();
	            memberDTO.setUserId(username);
	            memberDTO.setUserPw("temppassword"); // 비밀번호는 사용되지 않으므로 임시 값 설정
	            memberDTO.setRole(role);

            SpringSecurityUser springSecurityUser = new SpringSecurityUser(memberDTO);
            Authentication authToken = new UsernamePasswordAuthenticationToken(springSecurityUser, null, springSecurityUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } 
		
        filterChain.doFilter(request, response);
    }
}