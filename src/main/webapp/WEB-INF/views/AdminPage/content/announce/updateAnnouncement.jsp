<!-- // jQuery 기본 js파일 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- // jQuery UI CSS파일  -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<!-- // jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<!--  datetimepicker -->
<script type="text/javascript"
	src="https://fastly.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="https://fastly.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://fastly.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://fastly.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link
	href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<style>
.btn {
	background-color: #ff416c;
	color: white;
}

#formUpdate {
	margin: 0 auto; /* 가운데 정렬 */
	text-align: left; /* 내용물 왼쪽 정렬 */
	width: 100%; /* 원하는 폭으로 조절 */
}

#btnTd {
	text-align: center;
}
</style>

<script type="text/javascript">
	$(function() {

		$("#close").on("click", closeBack);

		//closeBack
		function closeBack() {
			$("#formWrite").attr("action", "AdminPageAnnounce");
			$("#formWrite").submit();
		}

		// dateTimePicker
		$('.dateTimePicker').daterangepicker({
			"timePicker" : true,
			"timePicker24Hour" : true,
			"timePickerIncrement" : 30,
			"startDate" : "${dto.startDate}", // 모델에서 전달된 startDate 사용
			"endDate" : "${dto.endDate}", // 모델에서 전달된 endDate 사용
			locale : {
				format : 'YYYY/MM/DD HH:mm:ss'
			}
		}).attr('readonly', 'readonly'); // read only
	});
</script>

<form id="formUpdate" action="UpdateAnnouncementController"	enctype="multipart/form-data">
	<input type="hidden" name="annoWriter" value="${dto.annoWriter}">
	<input type="hidden" name="annoNum" value="${dto.annoNum}">
	<table style="width: 100%;">
		<tr>
			<td colspan="4">
				<div style="background-color: #ffb2c4; color: white; margin-left: auto;">공지상항 수정</div>
			</td>
		</tr>

		<tr>
			<td style="width: 150px">제목</td>
			<td colspan="4"><input type="text" value="${dto.annoTitle}" name="annoTitle" maxlength="30" class="form-control form-control-sm item" placeholder="제목을 입력하세요."></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="4"><textarea rows="10" name="annoText" placeholder="내용을 입력하세요"
					maxlength="500" class="form-control form-control-sm item" > ${dto.annoText}</textarea></td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td colspan="4"><select name="category" class="form-select form-select-sm">
					<option value="전체"
						<c:if test="${dto.category == '전체'}">selected</c:if>>전체</option>
					<option value="영화"
						<c:if test="${dto.category == '영화'}">selected</c:if>>영화</option>
					<option value="소모임"
						<c:if test="${dto.category == '소모임'}">selected</c:if>>소모임</option>
			</select></td>
		</tr>
		<tr>
			<th style="width: 150px">작성일</th>
			<td><c:forEach var="times" items="${dto.writeDateArr}"
					varStatus="status" >
					<span style="width: 25px" >${times}</span> ${dto.dateName[status.index] }
			</c:forEach></td>
			<th style="width: 150px">수정일</th>
			<td><c:forEach var="times" items="${dto.updateDateArr}"
					varStatus="status">
					<span style="width: 25px">${times}</span> ${dto.dateName[status.index] }
			</c:forEach></td>
		</tr>
		<tr>
		<tr>
			<th>시작일 - 종료일</th>
			<td colspan="3"><input type="text" id="datePicker"
				class="dateTimePicker" name="dateTimePicker" style="width: 100%" class="form-select form-select-sm"></td>

		</tr>
		<tr>
			<th>팝업 허용</th>
			<td ><input type="checkbox" id="popup" name="popup"	value="on" class="form-check"
				<c:if test="${dto.popup == 'on'}">checked="checked"</c:if>>
			</td>
		</tr>
		<tr>
			<td id="btnTd" colspan="4"><input type="submit" value="저장"
				style="float: right; background-color: #ff416c; color: white; margin-left: auto;">
				<button id="close"
					style="float: right; background-color: #ffb2c4; color: white; margin-left: auto;">취소</button></td>
		</tr>
	</table>
</form>
