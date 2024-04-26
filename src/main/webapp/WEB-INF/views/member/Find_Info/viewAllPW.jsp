<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 모든 비밀번호를 출력하는 창(종료 창) -->

<head>
	<meta charset="UTF-8">
	<title>문화인들의 밤</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/ID.css'/>">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">

	//새로고침, 뒤로가기, 나가기 시 경고창 함수
	function f5Control(event){
		event.preventDefault();
	    event.returnValue = '';
	}
	
	//페이지 로딩되면 기존 인증번호 쿠키 삭제
	$(function(){
	   
	   //새로고침, 뒤로가기, 나가기 시 경고창 함수 출력
	   window.addEventListener('beforeunload', f5Control);
	   
	   //뒤로가기 단축키을 누르면 로그인 메인으로 이동(Alt + <- 기능)(브라우저 뒤로가기 버튼은 막히지 않음)
	   window.history.pushState(null, null, window.location.href);
	   window.onpopstate = function(event) {
	   	window.history.pushState(null, null, window.location.href);
	    window.location.href= "<c:url value='/mainLogin'/>"; 
	    };
	    
	    $("#goLogin").on("click", function(){
			window.removeEventListener('beforeunload', f5Control);
		})
	});

</script>

<body>

<div id="header">
    <jsp:include page="/WEB-INF/views/common/navBar.jsp" flush="true"></jsp:include><br>
</div>

<h1>이메일 발송 성공!</h1>

    <p>${nickname}님!<br> 
    <a href="http://www.${emailDomain}" target="_blank">${userId}로 임시 비밀번호 메일이 전송되었습니다.</a>
    <p>확인 후 로그인 부탁드립니다.</p>

<div id="sitesShortCut">
    <a href="<c:url value='/mainLogin'/>" id="goLogin">로그인</a> 
</div>

</body>
</html>
