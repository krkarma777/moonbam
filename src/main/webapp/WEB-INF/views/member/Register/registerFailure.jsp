<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 회원가입에 실패할 경우, 나타나는 페이지.jsp -->

<head>
    <meta charset="UTF-8">
    <title>문화인들의 밤</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/register_result.css'/>">
</head>

<body>

	<!-- 회원가입에 왜 실패했는지 알려주는 메세지를 신규 회원정보 검증&등록 창에서 가져옴 -->
    <div id="errorMessage">
        회원가입 실패<br>
		<!-- 회원가입에 왜 실패했는지 알려주는 메세지 출력 -->
       	${mesg}
    </div>

    <div id="sitesShortCut">
        <a href="<c:url value='/mainLogin'/>">로그인 화면으로</a>
    </div>
</body>
</html>
