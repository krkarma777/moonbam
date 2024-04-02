<%@page import="com.moonBam.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 비밀번호 찾기에서 비밀번호를 찾을 경우, 나오는 페이지의 jsp -->

<head>
	<meta charset="UTF-8">
	<title>Found PW</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/ID.css'/>">
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript">
	
     	// 전체 비밀번호 찾기 새 창 열기
         function openAllPWWindow(userId) {
            window.open("<c:url value='/FindAllPW?userId='/>"+userId, "전체 비밀번호 확인", "width=600,height=300");
        }
        
    </script>
</head>

<body>



<c:if test="${dto.getGoogleConnected() == 1}">
    해당 아이디는 구글 로그인이 연결된 아이디입니다.<br>
</c:if>
<c:if test="${dto.getNaverConnected() == 1}">
    해당 아이디는 네이버 로그인이 연결된 아이디입니다.<br>
</c:if>
<c:if test="${dto.getKakaoConnected() == 1}">
    해당 아이디는 카카오 로그인이 연결된 아이디입니다.<br>
</c:if>
<c:if test="${dto.getGoogleConnected() == 0 && dto.getNaverConnected() == 0 && dto.getKakaoConnected() == 0}">
    <h2>찾은 비밀번호 정보</h2>
    
    <!-- 자식 창에 전달할 데이터를 숨겨진 input 태그로 설정 -->
    <input type="hidden" id="userId" name="userId" value="${dto.getUserId()}">
   
    <p>${dto.getUserId()}님의 비밀번호는 ${maskedPW}입니다.</p>
    
    <!-- 비밀번호 새창 찾기로 연결 -->
    <button type="button" onclick="openAllPWWindow('${dto.getUserId()}')">
       전체 비밀번호 확인
    </button>
</c:if>
	<br>
    <div id="sitesShortCut">
        <a href="<c:url value='/Login'/>">로그인</a> 
    </div>

</body>

</html>
