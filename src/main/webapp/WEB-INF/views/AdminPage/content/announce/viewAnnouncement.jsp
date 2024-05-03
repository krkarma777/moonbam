<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link
	href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
	h1 {
		text-align: center;
	}
	
	.btn {
		background-color: #ff416c;
		color: white;
	}
	
	#formView {
		margin: 0 auto; /* 가운데 정렬 */
		text-align: left; /* 내용물 왼쪽 정렬 */
		width: 50%; /* 원하는 폭으로 조절 */
	}
	
	#btnTd {
    text-align: center;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#buttonBack").on("click", backList);
		$("#buttonUpdate").on("click", updateAnnouncement);
		$("#buttonDelete").on("click", deleteAnnouncement);
	});

	function backList() {
		$("#formView").attr("action", "AdminPageAnnounce");
		$("#formView").submit();
	}

	function updateAnnouncement() {
		$("#formView").attr("action", "RetrieveAnnouncementController");
		$("#formView").submit();
	}

	function deleteAnnouncement() {
		$("#formView").attr("action", "DeleteAnnouncementController");
		$("#formView").submit();
	}

	let dates = [ '년', '월', '일', '시', '분', '초' ];
</script>

<div style="background-color: #ffb2c4; color: white; margin-left: auto;"> 공지상항</div>

<form id="formView" style=" width: 100%">
	<input type="hidden" name="annoNum" value="${dto.annoNum}"> <input
		type="hidden" name="annoTitle" value="${dto.annoTitle}"> <input
		type="hidden" name="annoText" value="${dto.annoText}"> <input
		type="hidden" name="annoWriter" value="${dto.annoWriter}"> <input
		type="hidden" name="popup" value="${dto.popup}">
	<%-- <input type="hidden" name="map" value="${dto.getMap()}"> --%>
	<table border='1' style="width: 100%">
		<tr>
			<th style="width: 150px">제목</th>
			<td colspan="3"><span><h5>${dto.annoTitle}</h5></span></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3" style="text-align: left;"><span>${dto.annoText}</span></td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td colspan="3"><span>${dto.category}</span></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><c:forEach var="times" items="${dto.writeDateArr}"
					varStatus="status">
					<span style="width: 25px">${times}</span> ${dto.dateName[status.index] }
			</c:forEach></td>
			<th>수정일</th>
			<td><c:forEach var="times" items="${dto.updateDateArr}"
					varStatus="status">
					<span style="width: 25px">${times}</span> ${dto.dateName[status.index] }
			</c:forEach></td>
		</tr>
		<tr>
			<th>시작일</th>
			<td><c:forEach var="times" items="${dto.startDateArr}"
					varStatus="status">
					<span style="width: 25px">${times}</span> ${dto.dateName[status.index] }
			</c:forEach></td>
			<th>종료일</th>
			<td><c:forEach var="times" items="${dto.endDateArr}" varStatus="status">
					<span style="width: 25px">${times}</span> ${dto.dateName[status.index] }
			</c:forEach></td>
		</tr>
		<tr>
			<th>팝업 허용</th>
			<td colspan="3"><input type="checkbox" id="popup" name="popup"
				<c:if test="${dto.popup == 'on'}">
       checked="checked" 
    </c:if>
				disabled="disabled"></td>
		</tr>
		<tr>
			<td id=btnTd colspan="4" style="align-content: center"><button id="buttonBack"
					style="float: right; background-color: #ffb2c4; color: white; ">확인</button>
				<button id="buttonUpdate"
					style="float: right; background-color: #ff416c; color: white; ">수정</button>
				<button id="buttonDelete"
					style="float: right; background-color: #ffb2c4; color: white; ">삭제</button></td>
		</tr>
	</table>
</form>
