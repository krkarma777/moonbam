<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 글 목록 -->

<head>
	<meta charset="UTF-8">
	<title>글</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/member/unfound.css'/>">
</head>

<body>
		
작성자:	${dto.nickname}
제목:		${dto.title}
카테고리:	${dto.category}
	
작성 날짜:	${dto.edittedDate}
조회수:	${dto.viewCount}
추천수:	${dto.recommendNum}

내용:		${dto.content}

<br>
<button id="update">글 수정</button>
<button id="delete">글 삭제</button>
<button id="list">글 목록</button>

<script type="text/javascript">

	$(function(){
		
		$("#update").on("click", function(){
			console.log("update")
		})
		
		$("#delete").on("click", function(){
			console.log("delete")
		})
				
		$("#list").on("click", function(){
			window.location.href ="<c:url value='/viewDBoardList'/>";
			
		})
	})

</script>

		
</body>

</html>
