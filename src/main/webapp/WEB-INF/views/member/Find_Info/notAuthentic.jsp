<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 아이디 찾기 실패 / 비밀번호 찾기 실패 / 모종의 이유로 로그인 진입에는 성공했으나 로그인 자체에는 실패했을 경우 -->

<head>
	<meta charset="UTF-8">
	<title>문화인들의 밤</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/unfound.css'/>">
</head>

<body>
    <div class="notFound">
        <h1>권한 없음</h1>
        <p class="mesg">해당 페이지는 제한된 페이지입니다. <br>
        권한이 있음에도 접속할 수 없다면 관리자에게 문의해주세요.</p>
        <div id="sitesShortCut">
	        <a href="<%=request.getContextPath()%>/mainLogin" class="links">로그인</a>
        </div>
    </div>
</body>

</html>
