<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 모든 비밀번호를 출력하는 창(종료 창) -->

<head>
	<meta charset="UTF-8">
	<title>비밀번호 찾기</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/member/ID.css'/>">
</head>
<body>

<h1>이메일 발송 성공!</h1>

    <p>${dto.getUserName()}님의 비밀번호는  
    ${dto.getUserEmailId()}@${dto.getUserEmailDomain()}로 전송되었습니다.</p> 
    <p>확인 후 로그인 부탁드립니다.</p>

<div id="sitesShortCut">
    <a href="<c:url value='/Login'/>">로그인</a> 
</div>

</body>
</html>
