<%@page import="com.moonBam.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 이메일 하이퍼링크를 통해 나오는 비밀번호 변경 페이지의 jsp -->

<head>
	<meta charset="UTF-8">
	<title>Update Password</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/ID.css'/>">
</head>

<body>

	<h2>새로운 비밀번호를 입력하세요!</h2>
	<form style="width: 300px; height: 300px" method="post" action="UpdatePassword">
		<input type="hidden" name="username" value="${username}">
		비밀번호: <input type="text" id="password" name="password" class="pw" placeholder="신규 비밀번호" minlength="6" required>
		비밀번호 확인: <input type="text" id="password_confirm" name="password_confirm" class="pw" placeholder="신규 비밀번호 확인" minlength="6" required>
		<span id="pwMismatch" style="color: red;"></span> 

		<input type="submit" id="update" value="변경하기">
	</form>

<script type="text/javascript">

	//PW입력하면 에러 문구 삭제
	$(".pw").on("input", function(){
		$("#pwMismatch").text("");
	});
	
	$("#pwMismatch").on("click", function(){
		if($("#password").val() != $("#password_confirm").val()){
			event.preventDefault();
			$("#pwMismatch").text("입력한 비밀번호가 일치하지 않습니다.");
		}
	})

	//새로고침, 뒤로가기, 나가기 시 경고창 함수
	function f5Control(event){
		event.preventDefault();
	    event.returnValue = '';
	}
	
	//페이지 로딩되면 기존 인증번호 쿠키 삭제
	$(function(){
	   
	   //새로고침, 뒤로가기, 나가기 시 경고창 함수 출력
	   window.addEventListener('beforeunload', f5Control);
	   
	   //뒤로가기 단축키을 누르면 로그인 메인으로 이동(Alt + <- 기능)(브라우저 뒤로가기 버튼은 막히지 않음)
	   window.history.pushState(null, null, window.location.href);
	   window.onpopstate = function(event) {
	   	window.history.pushState(null, null, window.location.href);
	    window.location.href= "<c:url value='/Login'/>"; 
	    };
	    
	    $("#update").on("click", function(){
			window.removeEventListener('beforeunload', f5Control);
		})
	});

</script>


</body>

</html>
