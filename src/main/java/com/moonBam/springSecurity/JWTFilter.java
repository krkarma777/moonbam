package com.moonBam.springSecurity;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.moonBam.dto.MemberDTO;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

    	HttpSession session = request.getSession();
        Object lfTokenObj = session.getAttribute("LFtoken");
//        System.out.println(lfTokenObj);
    	if (lfTokenObj == null) {
            // LFtoken이 없는 경우 처리할 내용 추가
            filterChain.doFilter(request, response);
            return;
        }
        
        String token = (String) lfTokenObj;
//        System.out.println("JWTFilter: "+token);
    	
        try {
            //토큰 소멸 시간 검증
        	jwtUtil.isExpired(token);
        } catch (ExpiredJwtException e) {
            // JWT가 만료된 경우 처리
            filterChain.doFilter(request, response);
            return;
        }

		//토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);
				
		//MemberDTO를 생성하여 값 set
        MemberDTO dto = new MemberDTO();
	        dto.setUserId(username);
	        dto.setUserPw("temppassword");
	        dto.setRole(role);
//	        System.out.println(dto);
				
		//SpringSecurityUser에 회원 정보 객체 담기
	    SpringSecurityUser customUserDetails = new SpringSecurityUser(dto);

		//스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
		System.out.println("authToken: "+authToken.getName());
        
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}