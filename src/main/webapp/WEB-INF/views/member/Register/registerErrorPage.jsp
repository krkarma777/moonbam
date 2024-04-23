<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 이메일 찾기에서 뒤로가기 등을 통해 들어왔을 경우 -->

<head>
	<meta charset="UTF-8">
	<title>문화인들의 밤</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/unfound.css'/>">
</head>

<body>
    <div class="notFound">
        <h1>잘못된 접근입니다.</h1>
        <p class="mesg">비정상적인 접근입니다. 회원정보를 다시 입력해주시기 바랍니다.</p>
        <div id="sitesShortCut">
	        <a href="<%=request.getContextPath()%>/mainLogin" class="links">로그인</a>
        </div>
    </div>
</body>

</html>
