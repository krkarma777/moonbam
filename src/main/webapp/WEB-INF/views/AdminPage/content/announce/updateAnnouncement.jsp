<!-- // jQuery 기본 js파일 -->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- // jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- // jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 

<!--  datetimepicker -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
	
	<script type="text/javascript">
	
	$(function () {
		
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
			"startDate": "${dto.startDate}", // 모델에서 전달된 startDate 사용
		    "endDate": "${dto.endDate}",     // 모델에서 전달된 endDate 사용
		 	 locale: {
			      format: 'YYYY/MM/DD HH:mm:ss'
			 } 
		}); 
	});
</script>


글수정 
	 <form id="formUpdate" action="UpdateAnnouncementController">
		<input type="hidden" name="annoWriter" value="${dto.annoWriter}">
		<input type="hidden" name="annoNum" value="${dto.annoNum}">
		<table border='1'>
			<tr>
				<td>제목</td>
				<td colspan="3"> <input type="text" value="${dto.annoTitle}" name="annoTitle" style="width: 100%" maxlength="30"></td>
			</tr>
			<tr>
			<td>내용</td>
				<td colspan="3"><textarea style="width: 100%" rows="10" name="annoText" maxlength="500" >${dto.annoText}</textarea></td>
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
			<tr>
				<td>시작일 - 종료일</td>
				<td colspan="3"><input type="text" id="datePicker" class="dateTimePicker" name="dateTimePicker" style="width: 100%" ></td>
		
			</tr>
			<tr>
				<td>팝업 허용</td><td colspan="3"><input type="checkbox" id="popup" name="popup"  
    <c:if test="${dto.popup == 'popup'}">
       checked="checked" 
    </c:if>
    ></td>
			</tr>
			<tr>
				<td colspan="4"><input type="submit" value="저장">
				<button id="close">취소</button></td>
			</tr>
		</table>
	</form> 
