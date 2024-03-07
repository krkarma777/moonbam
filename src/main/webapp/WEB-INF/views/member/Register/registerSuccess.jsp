<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 회원가입에 성공할 경우, 나타나는 페이지의 jsp -->

<head>
    <meta charset="UTF-8">
    <title>회원가입 성공</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/member/register_result.css'/>">
	<script type="text/javascript">
		<!-- 5초 뒤, 로그인 메인화면으로 이동 -->
	   setTimeout(function () {
            window.location.href = "<c:url value='/Login'/>";
        }, 5000);
    </script>
</head>

<body>
    <div id="successMesg">
        회원가입 성공
    </div>

    <div id="sitesShortCut">
        <a href="<c:url value='/Login'/>">로그인</a>
    </div>
</body>
</html>
