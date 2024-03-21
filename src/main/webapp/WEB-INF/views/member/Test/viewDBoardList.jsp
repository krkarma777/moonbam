<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 게시판 리스트 -->

<head>
	<meta charset="UTF-8">
	<title>글 목록</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<!-- 외부파일 css -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/member/unfound.css'/>">
</head>

<body>

<!-- 헤더 네비게이션바 -->
<div id="header">
    <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
</div>

<!-- 글 정렬 -->
<button name="orderBy" value="boardNum" onClick="changeList('boardNum')">일반</button>
<button name="orderBy" value="viewCount" onClick="changeList('viewCount')">인기</button>
<button name="orderBy" value="recommendNum" onClick="changeList('recommendNum')">추천</button>		<!-- ********************************** -->

<!-- 카테고리 검색 -->
<span name="category" value="정보" onClick="changeList('정보')">정보</span> |
<span name="category" value="문제 보고" onClick="changeList('문제 보고')">문제 보고</span> |
<span name="category" value="질문" onClick="changeList('질문')">질문</span> |
<span name="category" value="정리" onClick="changeList('정리')">정리</span> |
<span name="category" value="잡담" onClick="changeList('잡담')">잡담</span>


<!-- 게시판 글 리스트업 -->
	<c:forEach var="db" items="${list}" varStatus="vs">
			<p>
			    글번호: 	<c:out value="${db.boardNum}"/> | 카테고리: <c:out value="${db.category}"/> |
			    제목: 	<span onclick="submitForm(${db.boardNum})">${db.title}</span> |
			    닉네임: 	<c:out value="${db.nickname}"/> |
			    작성 날짜:	<c:out value="${db.edittedDate}"/> |
			    조회수: 	<c:out value="${db.viewCount}"/> |
			    추천수: 	<c:out value="${db.recommendNum-db.disRecommendNum}"/>
		    </p>
		    <br>
	</c:forEach>

<div>

<!-- 글 검색 -->
<form action="<c:url value='/searchPost'/>" method="get" style="display: inline;">
  <select name="searchTag" >
    <option value="title_contents">제목 + 내용</option>
    <option value="title">제목</option>
    <option value="contents">내용</option>
    <option value="nickname">닉네임</option>
  </select>
  <input type="text" name="searchData">
  <input type="submit" value="검색">
</form>

<!-- 게시판 글쓰기 -->
<form action="<c:url value='newPost'/>" method="post" style="display: inline-block;">
	<input type="submit" id="insert" value="글 작성">
</form>
  
</div>


<script type="text/javascript">

	//글 보기로 이동
	function submitForm(boardNum) {
        window.location.href ="<c:url value='/viewDBoardContent'/>/"+boardNum;
    }
	
	//게시판 정렬 변경
	function changeList(changeRow) {
        window.location.href ="<c:url value='/viewDBoardList'/>/"+changeRow;
    }

</script>
</body>

</html>
