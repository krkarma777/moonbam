package com.moonBam.springSecurity;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    
    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		//클라이언트 요청에서 username, password 추출
    	// String username = obtainUsername(request);
    	// String password = obtainPassword(request);
    	String username = request.getParameter("userId");
    	String password = request.getParameter("userPw");
        System.out.printf("Username: "+ username+" Password: "+ password);

		//스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);
        
		//token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

	//로그인 성공시 실행하는 메소드 (여기서 JWT 발급)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
				
		//인증객체에서 사용자 정보 추출(springSecurityUser - 아이디 / 패스워드 / 역할 / 활성화)
    	SpringSecurityUser springSecurityUser = (SpringSecurityUser) authentication.getPrincipal();

    	//사용자 정보 중 아이디 추출
        String username = springSecurityUser.getUsername();

        //사용자 정보 중 사용자 권한 목록 추출
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        //사용자 권한 목록을 순회하는 iterator
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

        //iterator 중 첫번째 권한을 auth에 저장
        GrantedAuthority auth = iterator.next();

        //role에 권한 객체에서 실제 권한을 추출한 것을 저장 
        String role = auth.getAuthority();

        //아이디와 역할을 통해 JWT토큰 생성(10시간 유지)
        String token = jwtUtil.createJwt(username, role, 60*60*10L);
//      System.out.println("LoginFilter token: "+ token);
        HttpSession session = request.getSession();
        session.setAttribute("LFtoken", token);
        
        // 루트 주소로 리다이렉트
        response.sendRedirect("logining/");
    }

	//로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

    	//로그인 실패시 401 응답 코드 반환
        response.setStatus(401);
    }
}