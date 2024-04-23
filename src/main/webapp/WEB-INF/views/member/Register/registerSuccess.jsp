<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 회원가입에 성공할 경우, 나타나는 페이지의 jsp -->

<head>
    <meta charset="UTF-8">
    <title>문화인들의 밤</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/register_result.css'/>">
	<script type="text/javascript">
		<!-- 5초 뒤, 로그인 메인화면으로 이동 -->
 	   setTimeout(function () {
            window.location.href = "<c:url value='/mainLogin'/>";
        }, 5000);
    </script>
</head>

<body>
    <div id="successMesg">
        회원가입 성공!<br>
        또한 메일로 보안코드가 발송되었습니다. 확인해보세요!
    </div>

    <div id="sitesShortCut">
        <a href="<c:url value='/mainLogin'/>">로그인하러 가기!</a>
    </div>
</body>
</html>
