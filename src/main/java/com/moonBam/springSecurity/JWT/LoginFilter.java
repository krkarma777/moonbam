package com.moonBam.springSecurity.JWT;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;


import com.moonBam.dto.member.RestoreRestrictedMember;
import com.moonBam.service.member.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.moonBam.springSecurity.SpringSecurityUser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final Long expiredMs;
    private String userIdSave;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    	userIdSave = request.getParameter("userIdSave");
    	System.out.println("LoginFilter: 아이디 저장: " + userIdSave);				//체크되면 on

		//클라이언트 요청에서 username, password 추출
    	// String username = obtainUsername(request);
    	// String password = obtainPassword(request);
    	String username = request.getParameter("userId");
    	String password = request.getParameter("userPw");
        System.out.printf("LoginFilter: Username: "+ username+" Password: "+ password);

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

        //사용자 정보 중 권한 추출
	        //사용자 정보 중 사용자 권한 목록 추출
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	
	        //사용자 권한 목록을 순회하는 iterator
	        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
	
	        //iterator 중 첫번째 권한을 auth에 저장
	        GrantedAuthority auth = iterator.next();
	
	        //role에 권한 객체에서 실제 권한을 추출한 것을 저장 
	        String role = auth.getAuthority();

        //아이디와 역할을 통해 JWT 생성(10시간 유지)
        String token = jwtUtil.createJwt(username, role, expiredMs);
        
        //JWT 쿠키 생성(key: AuthToken // value: JWT)
        //유효시간을 기입하지 않으면 쿠키는 브라우저 세션 동안만 유지(브라우저를 닫으면 쿠키가 삭제)
        //
        String cookieValue = "AuthToken=" + token + "; Path=/acorn; HttpOnly";
        if (request.isSecure()) { 
            cookieValue += "; Secure";
        }
        response.addHeader("Set-Cookie", cookieValue);
        
//        System.out.println("LoginFilter: 아이디 저장 확인_쿠키 생성: " + userIdSave);			//체크되면 on
//        System.out.println("LoginFilter: role 확인: " + role);
        
        //아이디 저장 기능
        if(userIdSave != null) {
        	Cookie cookie = new Cookie("userId", username);
	        	cookie.setMaxAge(60*60*24*1);								//1일 간 아이디 저장 유지
	        	cookie.setPath("/acorn");									//Path=/acorn
//	        	cookie.isHttpOnly();										//HttpOnly
	        	cookie.setSecure(false);									//request.isSecure()
	        	response.addCookie(cookie);									//response.addHeader("Set-Cookie", cookieValue);
        }
        if(userIdSave == null || role.equals("ROLE_ADMIN")) {				//저장되지 않으면 쿠키 삭제
        	Cookie cookie = new Cookie("userId", null);						//등급이 관리자면 쿠키 삭제
        	cookie.setMaxAge(0);											
        	response.addCookie(cookie);
        }
        
        //사이트 로그인 통계를 확인하기 위한 함수
        //setCount(); 
        	
        // 루트 주소로 리다이렉트
        response.sendRedirect("/acorn");
    }

	//로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {

        String message = failed.getLocalizedMessage();
        
        //자진탈퇴복귀 유저일 경우:    messagejava.lang.Exception: myself설정 + 이메일
        System.out.println("message" + message);
        String[] messageArr = message.split("설정");

        if (messageArr[0].equals("java.lang.Exception: myself")){
            // 아이디 출력
            System.out.println("userId=" + messageArr[1]);
            // 복귀 여부 묻는 페이지로 이동
            sendPostRequest(response, "/acorn/restoreUser", messageArr[1]);
        } else {
            response.sendRedirect("/acorn/badRequest");
        }
    }

    private void sendPostRequest(HttpServletResponse response, String url, String data) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<script type='text/javascript'>");
        out.println("function submitForm() {");
        out.println("var form = document.createElement('form');");
        out.println("form.setAttribute('method', 'post');");
        out.println("form.setAttribute('action', '" + url + "');");
        out.println("var hiddenField = document.createElement('input');");
        out.println("hiddenField.setAttribute('type', 'hidden');");
        out.println("hiddenField.setAttribute('name', 'userId');");
        out.println("hiddenField.setAttribute('value', '" + data + "');");
        out.println("form.appendChild(hiddenField);");
        out.println("document.body.appendChild(form);");
        out.println("form.submit();");
        out.println("}");
        out.println("window.onload = submitForm;");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
    }


}