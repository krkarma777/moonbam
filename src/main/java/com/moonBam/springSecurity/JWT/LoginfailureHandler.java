package com.moonBam.springSecurity.JWT;


import java.io.IOException;
import java.io.PrintWriter;


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

		System.out.println(message);
		String[] messageArr = message.split("설정");

		if (messageArr[0].equals("[닉네임")){
			String[] mesg = messageArr[1].split("]");
			String email = mesg[0];
			// 회원가입 후 '닉네임 설정' 페이지로 리다이렉트
			sendPostRequest(response, "/acorn/forChangeNickname", "userId=" + email);
		} else

		if(messageArr[0].equals("[복귀유저")){
			String[] mesg = messageArr[1].split("]");
			String email = mesg[0];
			// 로그인 실패 후 '정지된 유저의 소셜로그인 접속시도 탐지' 페이지로 리다이렉트
			sendPostRequest(response, "/acorn/restoreUser", email);
		} else

		if(messageArr[0].equals("[정지유저")){
			// 로그인 실패 후 '정지된 유저의 소셜로그인 접속시도 탐지' 페이지로 리다이렉트
			response.sendRedirect("/acorn/restrictedMember");
		} else

		if(messageArr[0].equals("[관리자")){
			// 로그인 실패 후 '관리자 아이디의 소셜로그인 접속시도가 탐지' 페이지로 리다이렉트
			response.sendRedirect("/acorn/doNotSocialLoginByAdminID");
		} else {

			// 로그인 실패 후 '유저 정보 찾을 수 없음' 페이지로 리다이렉트
			response.sendRedirect("/acorn/OAuth2Error");
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