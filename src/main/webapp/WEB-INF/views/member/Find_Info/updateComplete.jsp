<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 비밀번호 변경에 성공할 경우, 나타나는 페이지의 jsp -->

<head>
    <meta charset="UTF-8">
    <title>문화인들의 밤</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/register_result.css'/>">
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript">
		<!-- 5초 뒤, 로그인 메인화면으로 이동 -->
	   setTimeout(function () {
            window.location.href = "<c:url value='/mainLogin'/>";
        }, 5000);
	   
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
</head>

<body>
    <div id="successMesg">
        비밀번호 변경 성공
    </div>

    <div id="sitesShortCut">
        <a href="<c:url value='/mainLogin'/>" id="goLogin">로그인</a>
    </div>
</body>
</html>
