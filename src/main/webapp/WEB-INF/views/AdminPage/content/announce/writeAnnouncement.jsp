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


<style>
.btn {
	background-color: #ff416c;
	color: white;
}

#formWrite {
	margin: 0 auto; /* 가운데 정렬 */
	text-align: left; /* 내용물 왼쪽 정렬 */
	width: 100%; /* 원하는 폭으로 조절 */
}

#btnTd {
	text-align: center;
}
</style>

<script type="text/javascript">
	<%
		Date toDate = new Date();
		toDate.setSeconds(00); // second : 00
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String today = dateFormat.format(toDate);
		
		Date nextWeekDate = new Date();
		nextWeekDate.setSeconds(00); // second : 00
		nextWeekDate.setDate(toDate.getDate() + 7);
		SimpleDateFormat nWDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nextWeekDay = nWDateFormat.format(nextWeekDate);
	%>
	
	
	$(function (){
		// get current day & time
		// day yyyy/mm/dd
		

		$("#close").on("click", closeBack);
		
		//closeBack
		function closeBack() {
			$("#formWrite").attr("action", "AdminPageAnnounce");
			$("#formWrite").submit();
		}
		
		// dateTimePicker
		$('.dateTimePicker').daterangepicker({
			"timePicker": true,
			"timePicker24Hour": true,
			"timePickerIncrement": 30,
			"startDate": "<%=today%>",
			"endDate": "<%=nextWeekDay%>",
			locale : {
				format : 'YYYY/MM/DD HH:mm:ss'
			}
		}).attr('readonly', 'readonly'); // read only
	});
</script>

<form id="formWrite" action="InsertAnnouncementController"	enctype="multipart/form-data">
	<table style="width: 100%;">
		<tr>
			<td colspan="2">
				<div style="background-color: #ffb2c4; color: white; margin-left: auto;"> 공지 작성</div>
			</td>
		</tr>
		<tr>
			<td style="width: 150px">제목</td>
			<td ><input type="text" value="" name="annoTitle" placeholder="제목을 입력하세요."
				maxlength="30" class="form-control form-control-sm item" ></td>
		</tr>
		<tr>
			<td>내용</td>
			<td ><textarea rows="10" name="annoText" placeholder="내용을 입력하세요"
					maxlength="500" class="form-control form-control-sm item" ></textarea></td>
		</tr>
		<tr>
			<td>카테고리</td>
			<td >
				<!-- select (option 전체, 영화, 소모임) --> <select name="category"
				class="form-select form-select-sm">
					<option value="main">전체</option>
					<option value="movie">영화</option>
					<option value="community">소모임</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>시작일 - 종료일</td>
			<td><input type="text" id="datePicker"
				class="dateTimePicker form-control form-control-sm item"
				name="dateTimePicker"></td>
		</tr>
		<tr>
			<td>팝업 허용</td>
			<td ><input type="checkbox" id="popup" name="popup" value="on" class="form-check"></td>
		</tr>
		<tr>
			<td colspan="3"><input id="btnTd" type="submit" value="저장" class="btn"
				style="float: right; align-content: center; background-color: #ff416c; color: white; ">
				<button id="close" class="btn"
					style="float: right; background-color: #ffb2c4; color: white; ">취소</button></td>
		</tr>
	</table>
</form>
