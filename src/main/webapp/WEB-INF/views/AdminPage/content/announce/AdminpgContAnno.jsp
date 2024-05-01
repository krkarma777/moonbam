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
			//window.open("WriteAnnouncementController", 'annoWrite' ,"width= 410px, height=390px");
			 $("#formAnnoList").attr("action", "WriteAnnouncementController").attr("method", "post")
					.submit();
		}


		// buttonAnnoWord 검색
		function buttonAnnoWord() {
		    window.location.href ="<%=request.getContextPath()%>/AdminPage/AdminPageAnnounce?word=" + $("#word").val();
		}

 
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
<div class="container">
	<h1 class="mt-5">공지사항</h1>
	<hr>
	<form method="get" class="mb-3" id="formAnnoList">
		<div class="input-group mb-3">
			<input type="text" class="form-control" placeholder="검색조건 입력" id="word" name="word" value="${word}" onkeydown="if (event.keyCode === 13) { buttonAnnoWord(); }">
			<button type="button" class="btn btn-primary" id="buttonAnnoWord" style="float:right; background-color: #ff416c; color:white; margin-left: auto;" >검색</button>
			<button type="button" class="btn" style="float:right; background-color: #ffb2c4; color:white; margin-left: auto;" id="buttonAnnoWrite" >글쓰기</button>
		</div>
	</form>
	<table class="table table-hover">
		<thead class="header-row">
		<tr>
			<th>글번호</th>
			<th >제목</th>
			<th>시작일</th>
			<th>종료일</th>
			<!-- <th>작성일</th> -->
			<th>작성자</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="dto" items="${list}">
			<tr>
				<td>${dto.annoNum}</td>
				<td style="width: 50%;"><a href="ViewAnnouncementController?annoNum=${dto.annoNum}" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"> [${dto.category}] ${dto.annoTitle}</a></td>
				<td>${dto.startDate}</td>
				<td>${dto.endDate}</td>
				<%-- <td>${dto.writeDate}</td> --%>
				<td>${dto.annoWriter}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<nav aria-label="Page navigation">
		<ul class="pagination justify-content-center">
			<c:if test="${current != 1}">
				<li class="page-item"><a class="page-link" style="float:right; background-color: #ff416c; color:white; margin-left: auto;" href="AdminPageAnnounce?currentPage=1&inputAnnoWord=${word}">First</a></li>
			</c:if>
			<c:forEach var="pages" items="${pageArray}">
				<li class="page-item"><a class="page-link" href="AdminPageAnnounce?currentPage=${pages}&inputAnnoWord=${word}">${pages}</a></li>
			</c:forEach>
			<c:if test="${current != last}">
				<li class="page-item"><a class="page-link" style="float:right; background-color: #ff416c; color:white; margin-left: auto;" href="AdminPageAnnounce?currentPage=${last}&inputAnnoWord=${word}">Last</a></li>
			</c:if>
		</ul>
	</nav>
</div>