<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 회원가입에 성공할 경우, 나타나는 페이지의 jsp -->

<head>
    <meta charset="UTF-8">
    <title>회원가입 로딩 페이지</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/register_result.css'/>">
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/member/loading.css'/>">
	<script type="text/javascript">
		$(function(){
			$("#register").submit();
		})
	</script>
	
</head>

<body>

	<form id="register" action="<c:url value='/RegisterData'/>" method="POST">
		<input type="hidden" name="userId" value="${dto.getUserId()}">
		<input type="hidden" name="userPw" value="${dto.getUserPw()}">
		<input type="hidden" name="nickname" value="${dto.getNickname()}">
		<input type="hidden" name="userPwConfirm" value="${userPwConfirm}">
	</form>

	<h4>회원가입 중입니다</h4>
	<div id="content">
	  <div id="dot"></div>
	  <div class="step" id="s1"></div>
	  <div class="step" id="s2"></div>
	  <div class="step" id="s3"></div>
	</div>
</body>
</html>
