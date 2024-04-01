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
	<title>Found ID</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/ID.css'/>">
</head>

<body>

	<h2>찾은 아이디 정보</h2>
	<div>회원님의 아이디는
		${dto.getUsername()}입니다.
	</div>
	<div id="sitesShortCut">
		<a href="<c:url value='/Login'/>">로그인</a> | <a href="<c:url value='/FindInfo'/>">회원정보 찾기</a>
	</div>
</body>

</html>
