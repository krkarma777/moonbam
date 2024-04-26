<%@page import="com.moonBam.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 아이디 찾기에서 아이디를 찾을 경우, 나오는 페이지의 jsp -->

<head>
	<meta charset="UTF-8">
	<title>문화인들의 밤</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/ID.css'/>">
</head>

<body>

<div id="header">
	<jsp:include page="/WEB-INF/views/common/navBar.jsp" flush="true"></jsp:include><br>
</div>

	<h2>찾은 아이디 정보</h2>
	<div>회원님의 아이디는
		${dto.getUserId()}입니다.
	</div><br>
	
	<c:if test="${dto.getGoogleConnected() == '1' || dto.getNaverConnected() == '1' || dto.getKakaoConnected() == '1'}">
	
		<c:if test="${dto.getGoogleConnected() == '1'}">
		&nbsp;&nbsp;- 구글 로그인 연결됨<br>
		</c:if>
		<c:if test="${dto.getNaverConnected() == '1'}">
		&nbsp;&nbsp;- 네이버 로그인 연결됨<br>
		</c:if>
		<c:if test="${dto.getKakaoConnected() == '1'}">
		&nbsp;&nbsp;- 카카오 로그인 연결됨<br>
		</c:if>
		<p>
			소셜 로그인을 확인해보세요!<br>
		</p>
	</c:if>

	
	<div id="sitesShortCut">
		<a href="<c:url value='/mainLogin'/>">로그인</a> | <a href="<c:url value='/FindInfo'/>">회원정보 찾기</a>
	</div>
</body>

</html>
