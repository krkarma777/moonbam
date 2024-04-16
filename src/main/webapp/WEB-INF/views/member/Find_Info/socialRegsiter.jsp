<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>소셜로그인 회원가입자</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/unfound.css'/>">
</head>

<body>
    <h2>찾은 유저 정보</h2>
	<p>${nickname}님은 소셜 로그인 가입자이십니다.<br>
	<c:if test="${googleRegister == true}">
	&nbsp;&nbsp;- 구글 로그인 연결됨<br>
	</c:if>
	<c:if test="${naverRegister == true}">
	&nbsp;&nbsp;- 네이버 로그인 연결됨<br>
	</c:if>
	<c:if test="${kakaoRegister == true}">
	&nbsp;&nbsp;- 카카오 로그인 연결됨<br>
	</c:if>
		혹시 비밀번호를 입력해서도 로그인하고 싶으신가요?<br>
		<form method="POST" action="SocialMailingPW">
			<input type="hidden" name="userId" value="${userId}">
			<input type="submit" value="비밀번호 등록하기">
		</form>
	</p>
	<div>
		<a href="<c:url value='/mainLogin'/>">로그인</a> | 
		<a href="<c:url value='/FindInfo'/>">회원정보 찾기</a>
	</div>​
</body>

</html>
