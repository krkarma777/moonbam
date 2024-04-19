//package com.moonBam.security_구.jwt;
//
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.moonBam.security_구.model.CustomUserDetails;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.Objects;
//
//@RequiredArgsConstructor
//public class LoginFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//    private final JWTUtil jwtUtil;
//    private final Long expiredMs;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        String username = request.getParameter("userId");
//        String password = request.getParameter("userPw");
//
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);
//        return authenticationManager.authenticate(authToken);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
//        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//
//        String username = customUserDetails.getUsername();
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//        GrantedAuthority auth = iterator.next();
//
//        String role = auth.getAuthority();
//
//        String token = jwtUtil.createJwt(username, role, expiredMs);
//        String cookieValue = "AuthToken=" + token + "; Path=/acorn; HttpOnly";
//        if (request.isSecure()) { // HTTPS인 경우에만 Secure 플래그 추가
//            cookieValue += "; Secure";
//        }
//        response.addHeader("Set-Cookie", cookieValue);
//        response.sendRedirect("/acorn");
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
//        response.sendRedirect("/login?error=true"); // 기본 로그인 페이지로 리디렉션
//    }
//}