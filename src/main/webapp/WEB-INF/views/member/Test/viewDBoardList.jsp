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
	<!-- 부트 스트랩 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
</head>

<body>

<!-- 헤더 네비게이션바 -->
<div id="header">
    <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
</div>

<!-- 글 정렬 -->
<button name="orderBy" value="boardNum" onClick="changeList('boardNum')">일반</button>
<button name="orderBy" value="viewCount" onClick="changeList('viewCount')">인기</button>
<button name="orderBy" value="recommendNum" onClick="changeList('recommendNum')">추천</button>	

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


<!-- 페이지 번호 표시 -->
<div>
    <!-- 이전 버튼 -->
    <span>
    	<!-- 첫 페이지에서는 출력되지 않음 -->
        <c:if test="${currentPage > 1}">
	        <c:url var="prevPageURL" value="/viewDBoardList">
	            <c:param name="currentPage" value="${currentPage - 1}"/>
	            <c:param name="perPage" value="${perPage}"/>
	            <c:param name="orderBy" value="${orderBy}"/>
	        </c:url>
            <a href="${prevPageURL}">Prev</a>
        </c:if>
    </span>
    <!-- 페이지 번호 -->
	<c:choose>
	    <c:when test="${currentPage <= 5}">
	        <c:set var="startPage" value="1" />
	    </c:when>
	    <c:otherwise>
	        <c:set var="startPage" value="${currentPage - 5}" />
	    </c:otherwise>
	</c:choose>
	
	<c:forEach begin="${startPage}" end="${startPage + 9}" var="pageNum">
	    <c:if test="${pageNum > 0 && pageNum <= (totalPosts + perPage - 1) / perPage}">
	        <c:url var="pageURL" value="/viewDBoardList">
	            <c:param name="currentPage" value="${pageNum}"/>
	            <c:param name="perPage" value="${perPage}"/>
	            <c:param name="orderBy" value="${orderBy}"/>
	        </c:url>
	        <a href="${pageURL}">${pageNum}</a>
	    </c:if>
	</c:forEach>
    <!-- 다음 버튼 -->
    <span>
    	<!-- 마지막 페이지에서는 출력되지 않음 -->
        <c:if test="${currentPage < (totalPosts + perPage - 1) / perPage -1}">
	        <c:url var="nextPageURL" value="/viewDBoardList">
	            <c:param name="currentPage" value="${currentPage + 1}"/>
	            <c:param name="perPage" value="${perPage}"/>
	            <c:param name="orderBy" value="${orderBy}"/>
	        </c:url>
	        <a href="${nextPageURL}">Next</a>
		</c:if>
    </span>
</div>


<div>
	<!-- 글 검색 -->
	<form action="<c:url value='/searchPost'/>" method="post" style="display: inline;">
	<input type="hidden" name="orderBy" value="${orderBy}">
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
        window.location.href ="<c:url value='/viewDBoardContent'/>?boardNum="+boardNum;
    }
	
	//게시판 정렬 변경
	function changeList(orderBy) {
        window.location.href ="<c:url value='/viewDBoardList'/>?orderBy="+orderBy;
    }

</script>

<!-- 부트 스트랩 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script type="text/javascript">
var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
  return new bootstrap.Popover(popoverTriggerEl)
})
</script>


</body>

</html>
