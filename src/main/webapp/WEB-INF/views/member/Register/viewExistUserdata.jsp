<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.moonBam.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 회원가입 2단계에서 기존 유저임이 확인되면 나오는 페이지 -->

<head>
	<meta charset="UTF-8">
	<title>Found ID</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/ID.css'/>">
</head>

<body>

	<h2>찾은 유저 정보</h2>
	<p>${dto.getNickname()}님은 기존 회원이십니다.<br>
	<c:if test="${dto.getGoogleConnected() == 1}">
	&nbsp;&nbsp;- 구글 로그인 연결됨<br>
	</c:if>
	<c:if test="${dto.getNaverConnected() == 1}">
	&nbsp;&nbsp;- 네이버 로그인 연결됨<br>
	</c:if>
	<c:if test="${dto.getKakaoConnected() == 1}">
	&nbsp;&nbsp;- 카카오 로그인 연결됨<br>
	</c:if>
		아이디:	${dto.getUserId()}<br>
		가입일:	${dto.getUserSignDate()}
	</p>
	<div>
		<a href="<c:url value='/Login'/>">로그인</a> | 
		<a href="<c:url value='/FindInfo'/>">회원정보 찾기</a>
	</div>
</body>

</html>
