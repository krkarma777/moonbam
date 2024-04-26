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
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />


<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	<%Date toDate = new Date();
toDate.setSeconds(00); // second : 00
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
String today = dateFormat.format(toDate);

Date nextWeekDate = new Date();
nextWeekDate.setSeconds(00); // second : 00
nextWeekDate.setDate(toDate.getDate() + 7);
SimpleDateFormat nWDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
String nextWeekDay = nWDateFormat.format(nextWeekDate);%>
	
	
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
				format : 'YYYY/MM/DD HH:mm'
			}
		});
	});
</script>

<hr>
공지 작성
<form id="formWrite" action="InsertAnnouncementController"
	enctype="multipart/form-data">
	<table border='1'>
		<tr>
			<td>제목</td>
			<td colspan="3"><input type="text" style="width: 100%" value=""
				name="annoTitle" maxlength="30"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3"><textarea style="width: 100%" rows="10"
					name="annoText" maxlength="500"> </textarea></td>
		</tr>
		<tr>
			<td>카테고리</td>
			<td colspan="3">
				<!-- select (option 전체, 영화, 소모임) --> <select name="category">
					<option value="main" >전체</option>
					<option value="movie">영화</option>
					<option value="community">소모임</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>시작일 - 종료일</td>
			<td><input type="text" id="datePicker" class="dateTimePicker"
				name="dateTimePicker" style="width: 100%"></td>
		</tr>
		<tr>
			<td>팝업 허용</td>
			<td colspan="3">
				<input type="checkbox" id="popup" name="popup" value="on">
       </td>
		</tr>
		<tr>
			<td>자료</td>
			<td><input type="file" name="img" accept="image/*"></td>
		</tr>
		<tr>
			<td colspan="4"><input type="submit" value="저장">
				<button id="close">취소</button></td>
		</tr>
	</table>
</form>
