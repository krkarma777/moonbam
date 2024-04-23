<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<style>
	 * {
       margin: 0;
       padding: 0;
    }

    #popupForm table {
    	width: 100%;
        height: 100vh; /* 테이블의 높이도 폼 요소와 동일하게 설정합니다. */
    }
    #date {
    	text-align: center;
    	font-size: 11px;
    }
    
</style>
</head>
<body>
	<form id="popupForm">
	<table border="1">
			<!-- top for title -->
			<tr>
				<th colspan="2">${dto.annoTitle }</th>
			</tr>
			<!-- middle for text -->
			<tr id="date">
				<td>시작일 : ${dto.startDate}</td>
				<td>종료일 : ${dto.endDate }</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center; height: 50%;">${dto.annoText }</td>
			</tr>
			
			<!-- bottom for close -->
			<tr>
				<td colspan="2" style="text-align: right;">
					<button type="button" 
        onclick="window.open('', '_self', ''); window.close();">닫기</button></td>
			</tr>
		</table>
	</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(function (){
	event.preventDefault();
}
$("#close").on("click", function() {
	window.close();
});


/* 능력밖ㄲㄲㄲㄲㄲㄲㄲㄲ */
/* //창 크기를 고정하는 코드
$(document).ready(function() {
    var screenWidth = window.screen.availWidth;
    var screenHeight = window.screen.availHeight;
    
    // 팝업 창 크기 설정
    var popupWidth = screenWidth / 4; // 전체 화면 폭의 80%로 설정
    var popupHeight = screenHeight / 3; // 전체 화면 높이의 80%로 설정
    window.resizeTo(popupWidth, popupHeight);
    
    // 사용자가 창 크기를 변경하지 못하도록 설정
    $(window).resize(function() {
        window.resizeTo(popupWidth, popupHeight);
    });
}); */
</script>
</body>
</html>