package com.moonBam.springSecurity.JWT;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.moonBam.dto.MemberDTO;
import com.moonBam.springSecurity.SpringSecurityUser;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

    	//재로그인 무한 루프 오류 방지
    	//JWT가 만료된 상태에서 재로그인되면 OAuth2 로그인 실패 --> 재요청 --> 무한루프 발생
    	String requestUri = request.getRequestURI();
    	if (requestUri.matches("^\\/login(?:\\/.*)?$")) {
    	    filterChain.doFilter(request, response);
    	    return;
    	}
    	if (requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {
    	    filterChain.doFilter(request, response);
    	    return;
    	}
    	
		//쿠키에 user 식별 key 있는지 확인
    	String JWTtoken = "";
    	Cookie[] cookies = request.getCookies();
    	System.out.println("cookies: " + cookies);
    	if (cookies != null) {
    	    for (Cookie cookie : cookies) {
    	        System.out.println("cookie: " + cookie.getName());
    	        if (cookie.getName().equals("JWTtoken")) {
    	            JWTtoken = cookie.getValue();
    	            System.out.println("JWTtoken: " + JWTtoken);
    	            break; // 찾았으면 루프 종료
    	        }
    	    }
    	}
    	System.out.println("JWTtoken: " + JWTtoken);
		
	    // 사용자 식별 키가 없으면 다음 필터로 이동
	    if (JWTtoken.isEmpty()) {
	        filterChain.doFilter(request, response);
	        return;
	    }
        
	    String token = (String) JWTtoken;
        System.out.println("JWTFilter: "+token);
    	
        try {
            //토큰 소멸 시간 검증
        	jwtUtil.isExpired(token);
        } catch (ExpiredJwtException e) {
            // JWT가 만료된 경우 처리
        	System.out.println("token is expired!!!!!!!!!!!!!!!!!!!!");
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
		System.out.println("JTWFilter: "+authToken.getName());
        
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}