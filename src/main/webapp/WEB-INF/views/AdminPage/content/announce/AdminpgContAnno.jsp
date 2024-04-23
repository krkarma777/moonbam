<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		// 글쓰기
		$("#buttonAnnoWrite").on("click", buttonAnnoWrite);

		// 검색
		$("#buttonAnnoWord").on("click", buttonAnnoWord);

		// 글 보기
		$(".classAnnotitle").on("click", classAnnotitle);

		// 글쓰기
		function buttonAnnoWrite() {
			$("#formAnnoList").attr("action", "WriteAnnouncementController")
					.submit();
		}

		// buttonAnnoWord 검색
		function buttonAnnoWord() {
			var annoNum = "${dto.annoNum}";
		    window.location.href = "<%=request.getContextPath()%>/AdminPage/AdminPageAnnounce?annoNum=" + annoNum;
		}

		// 질문 왜 아래 코드 없으면 글쓰기로 이동하는가
		// Enter 키 입력 시 검색
		    $("#inputAnnoWord").keypress(function(event) {
		       // Enter 키의 keyCode는 13입니다.
		       if (event.keyCode === 13) {
		           buttonAnnoWord();
		       }
		   });
		

		// classAnnotitle 검색
		function classAnnotitle() {
			$("#formAnnoList").submit();
		}

	});
</script>
<style>
	.table {
		margin-top: 20px;
	}
	.header-row {
		background-color: #f8f9fa;
	}
	.page-link {
		margin: 0 5px;
		text-decoration: none;
		color: #007bff;
	}
	.page-link:hover {
		text-decoration: underline;
	}
	.input-group-text {
		width: 100%;
	}
</style>
${word }
<div class="container">
	<h1 class="mt-5">관리자페이지 공지사항</h1>
	<hr>
	<form action="<%=request.getContextPath()%>/AdminPage/RestrictedMemberList" method="post" class="mb-3" id="formAnnoList">
		<div class="input-group mb-3">
			<input type="text" class="form-control" placeholder="검색조건 입력" id="SearchValue" name="SearchValue" value="${word}">
			<button type="button" class="btn btn-primary" id="buttonAnnoWord" onclick ="ViewAnnouncementController?annoNum=${dto.annoNum}">검색</button>
			<button type="button" class="btn btn-secondary" id="buttonAnnoWrite">글쓰기</button>
		</div>
	</form>
	<table class="table table-hover">
		<thead class="header-row">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>작성자</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="dto" items="${list}">
			<tr>
				<td>${dto.annoNum}</td>
				<td><a href="ViewAnnouncementController?annoNum=${dto.annoNum}">${dto.annoTitle}</a></td>
				<td>${dto.writeDate}</td>
				<td>${dto.annoWriter}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<nav aria-label="Page navigation">
		<ul class="pagination justify-content-center">
			<c:if test="${current != 1}">
				<li class="page-item"><a class="page-link" href="AdminPageAnnounce?currentPage=1&inputAnnoWord=${word}">First</a></li>
			</c:if>
			<c:forEach var="pages" items="${pageArray}">
				<li class="page-item"><a class="page-link" href="AdminPageAnnounce?currentPage=${pages}&inputAnnoWord=${word}">${pages}</a></li>
			</c:forEach>
			<c:if test="${current != last}">
				<li class="page-item"><a class="page-link" href="AdminPageAnnounce?currentPage=${last}&inputAnnoWord=${word}">Last</a></li>
			</c:if>
		</ul>
	</nav>
</div>