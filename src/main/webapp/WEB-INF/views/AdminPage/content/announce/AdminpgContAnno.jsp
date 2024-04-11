<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
관리자페이지 공지사항

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

		/* --------- */
		/* 함수 구현 */

		// 글쓰기
		function buttonAnnoWrite() {
			$("#formAnnoList").attr("action", "WriteAnnouncementController")
					.submit();
		}

		// buttonAnnoWord 검색
		function buttonAnnoWord() {
			$("#formAnnoList").attr("action", "AdminPageAnnounce")
					.submit();
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
${word }
<form action="" id="formAnnoList">
	<table border='1'>
		<tr>
			<!-- 글쓰기 -->
			<!-- <td><button id="buttonAnnoWrite" >글쓰기</button></td> -->
			<!-- 이 코드는 검색의 type="submit"보다 우선 순위가 높은 거 같다. -->
			<td><input type="button" id="buttonAnnoWrite" value="글쓰기"></input></td>
			<!-- 글 검색 -->
			<td colspan="2"><input type="text" width="100%"
				id="inputAnnoWord" name="word" value="${word}"></td>
			<td><input type="submit" value="검색" id="buttonAnnoWord" 	name="buttonAnnoWord"></td>
		</tr>
		<tr>
			<td>글번호</td>
			<td style="width: 500px">제목</td>
			<td>작성일</td>
			<td>작성자</td>
		</tr>
		<c:forEach var="dto" items="${list}" varStatus="status">
			<tr>
				<td>${dto.annoNum}</td>
				<td style="width: 500px">
				<a href="ViewAnnouncementController?annoNum=${dto.annoNum}">${dto.annoTitle} </a>
				</td>
				<td>${dto.writeDate}</td>
				<td>${dto.annoWriter}</td>
			</tr>
			<!-- for list -->
		</c:forEach>
		<tr>
	
			<td colspan="4" style="text-align: center;">
				
				<c:if test="${current !=1 }">
				<a href="AdminPageAnnounce?currentPage=1&inputAnnoWord=${word}" style="text-decoration: none;">&lt;</a>
				</c:if>
		
			<c:forEach
					var="pages" items="${pageArray }" varStatus="status">
					<a id="listPage"
						href="AdminPageAnnounce?currentPage=${pages}&inputAnnoWord=${word}"
				<c:if test="${current !=1 }">
						 style="text-decoration: none;"
						 </c:if>
						 >${pages}</a>
				&nbsp; </c:forEach>
			
				<c:if test="${current !=last }">
				<a href="AdminPageAnnounce?currentPage=${last }&inputAnnoWord=${word}" style="text-decoration: none;">&gt;</a>
				</c:if>
				</td>
		</tr>
	</table>
</form>
</body>
</html>