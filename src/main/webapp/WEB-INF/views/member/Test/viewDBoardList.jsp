<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 게시판 -->

<head>
	<meta charset="UTF-8">
	<title>글 목록</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/member/unfound.css'/>">
</head>

<body>

<div id="header">
    <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
</div>

<c:forEach var="db" items="${list}" varStatus="vs">
		<p>
		    글번호: 	<c:out value="${db.boardNum}"/><br>
		    카테고리: <c:out value="${db.category}"/><br>
		    <form id="form${db.boardNum}" action="<c:url value='/viewDBoardContent'/>/${db.boardNum}" method="post">
		    제목: 	<span onclick="submitForm(${db.boardNum})">${db.title}</span>
		    </form>
		    닉네임: 	<c:out value="${db.nickname}"/><br>
		    작성 날짜:	<c:out value="${db.edittedDate}"/><br>
		    조회수: 	<c:out value="${db.viewCount}"/><br>
		    추천수: 	<c:out value="${db.recommendNum}"/>
	    </p>
	    <br>
</c:forEach>

<br>
<button id="insert">글 작성</button>


<script type="text/javascript">

	$(function(){
		$("#insert").on("click", function(){
			window.location.href="<c:url value='/newPost'/>"
		})
	})
	
	function submitForm(boardNum) {
        $("#form" + boardNum).submit();
    }

</script>
</body>

</html>
