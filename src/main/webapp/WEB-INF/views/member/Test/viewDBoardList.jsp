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

<c:forEach var="db" items="${list}" varStatus="vs">
	    글번호: 	<c:out value="${db.boardNum}"/>
	    카테고리: <c:out value="${db.category}"/>
	    제목: 	<a href="<c:url value='/viewDBoardContent'/>/${db.boardNum}"><c:out value="${db.title}"/></a>
	    닉네임: 	<c:out value="${db.nickname}"/>
	    작성 날짜:	<c:out value="${db.edittedDate}"/>
	    조회수: 	<c:out value="${db.viewCount}"/>
	    추천수: 	<c:out value="${db.recommendNum}"/><br>
</c:forEach>

<br>
<button id="insert">글 작성</button>


<script type="text/javascript">

	$(function(){
		$("#insert").on("click", function(){
			window.location.href="<c:url value='/newPost'/>"
		})
	})

</script>
</body>

</html>
