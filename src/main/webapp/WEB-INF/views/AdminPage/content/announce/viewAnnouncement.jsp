<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

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
		
		let dates =['년','월','일','시','분','초'];
	</script>

공지
<form id="formView">
	<input type="hidden" name="annoNum" value="${dto.annoNum}"> <input
		type="hidden" name="annoTitle" value="${dto.annoTitle}"> <input
		type="hidden" name="annoText" value="${dto.annoText}"> <input
		type="hidden" name="annoWriter" value="${dto.annoWriter}"> <input
		type="hidden" name="popup" value="${dto.popup}">
		<%-- <input type="hidden" name="map" value="${dto.getMap()}"> --%>
	<table border='1'>
		<tr>
			<td>제목</td>
			<td colspan="3"><span>${dto.annoTitle}</span></td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3"><span>${dto.annoText}</span></td>
		</tr>
		<tr>
			<td>카테고리</td>
			<td colspan="3"><span>${dto.category}</span></td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>
		 	<c:forEach var="times" items="${dto.writeDateArr}" varStatus="status">
				<span style="width: 25px">${times}</span> ${dto.dateName[status.index] }
			</c:forEach>
			</td>
			<td>수정일</td>
			<td>
		 	<c:forEach var="times" items="${dto.updateDateArr}" varStatus="status">
				<span style="width: 25px">${times}</span> ${dto.dateName[status.index] }
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td>시작일</td>
			<td>
		 	<c:forEach var="times" items="${dto.startDateArr}" varStatus="status">
				<span style="width: 25px">${times}</span> ${dto.dateName[status.index] }
			</c:forEach>
			</td>
			<td>종료일</td>
			<td>
		 	<c:forEach var="times" items="${dto.endDateArr}" varStatus="status">
				<span style="width: 25px">${times}</span> ${dto.dateName[status.index] }
			</c:forEach>
			</td>
		</tr>
			<tr>
				<td>팝업 허용</td><td colspan="3"><input type="checkbox" id="popup" name="popup"  
    <c:if test="${dto.popup == 'on'}">
       checked="checked" 
    </c:if>
    disabled="disabled"></td>



			</tr>
			<tr>
				<td colspan="4"><button id="buttonBack">확인</button>
					<button id="buttonUpdate">수정</button>
					<button id="buttonDelete">삭제</button></td>
			</tr> 
	</table>
</form>
