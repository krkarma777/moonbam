<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 아이디 찾기 실패 / 비밀번호 찾기 실패 / 모종의 이유로 로그인 진입에는 성공했으나 로그인 자체에는 실패했을 경우 -->

<head>
	<meta charset="UTF-8">
	<title>문화인들의 밤</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/doNotLoginByThisID.css'/>">
</head>

<body>
    <div class="notFound">
        <h2>관리자 아이디의 소셜로그인 접속시도가 탐지되었습니다.</h2>
        <p class="mesg">관리자 아이디는 보안 상의 이유로 소셜로그인을 할 수 없습니다.<br>
        만일 관리자 아이디가 아닐 경우, 고객센터로 문의바랍니다.</p>
        <div id="sitesShortCut">
	        <a href="<%=request.getContextPath()%>/mainLogin" class="links">로그인</a>
        </div>
    </div>
</body>

</html>
