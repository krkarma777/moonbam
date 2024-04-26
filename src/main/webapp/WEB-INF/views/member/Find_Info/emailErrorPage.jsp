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
        <p class="mesg">
            비정상적인 접근입니다. 회원정보를 다시 입력해주시기 바랍니다.<br>
        </p>
        <p>
            모종의 이유로 정상적인 접근에도 해당 페이지가 출력될 수 있습니다.<br>
            정상적인 사이트 이용에도 해당 페이지가 계속 출력된다면 관리자에게 문의 부탁드립니다.
        </p>
        <div id="sitesShortCut">
	        <a href="<%=request.getContextPath()%>/mainLogin" class="links">로그인</a>
            <a href="${pageContext.request.contextPath}/FindInfo" class="links">회원정보 찾기</a>
        </div>
    </div>
</body>

</html>
