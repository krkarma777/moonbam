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
	<title>비밀번호 변경 페이지</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/ID.css'/>">
</head>

<body>

	<h2>새로운 비밀번호를 입력하세요!</h2>
	<form style="width: 300px; height: 300px" method="post" action="UpdatePassword">
		<input type="hidden" name="userId" value="${userId}">
		비밀번호: <input type="text" id="userPw" name="userPw" class="pw" placeholder="신규 비밀번호" minlength="6" required maxlength="30">
		비밀번호 확인: <input type="text" id="password_confirm" name="password_confirm" class="pw" placeholder="신규 비밀번호 확인" required>
		<span id="pwMismatch" style="color: red;"></span> 

		<input type="submit" id="update" value="변경하기">
	</form>

<script type="text/javascript">

	//PW입력하면 에러 문구 삭제
	$(".pw").on("input", function(){
		$("#pwMismatch").text("");
	});
	
	$("#pwMismatch").on("click", function(){
		if($("#userPw").val() != $("#password_confirm").val()){
			event.preventDefault();
			$("#pwMismatch").text("입력한 비밀번호가 일치하지 않습니다.");
		}
	})

</script>


</body>

</html>
