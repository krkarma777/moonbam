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
        <h1>회원 가입 이력 없음</h1>
        <p class="mesg">회원 정보가 없습니다. 입력하신 정보를 다시 한번 확인해주세요.</p>
        <div id="sitesShortCut">
	        <a href="<%=request.getContextPath()%>/mainLogin" class="links">로그인</a>
            <a href="${pageContext.request.contextPath}/FindInfo" class="links">회원정보 찾기</a>
        </div>
    </div>
</body>

</html>
