<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<link
	href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<style>
* {
	margin: 0;
	padding: 0;
}

.popupForm table {
	width: 100%;
	height: 100vh; /* 테이블의 높이도 폼 요소와 동일하게 설정합니다. */
}

#date {
	text-align: center;
	font-size: 11px;
}

.title {
	text-align: center;
}
</style>
</head>
<body>
	<form id="popup${dto.annoNum }" class="popupForm">
		<table border="1">
			<!-- top for title -->
			<tr>
				<th class="title" colspan="2">${dto.annoTitle }</th>
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
				<td colspan="2" style="text-align: right;"><input
					type="checkbox" value="closeToday" id='closeToday'>오늘 하루
					않보기
					<button type="button" id="close" class="btn"
						style="float: right; background-color: #ff416c; color: white; margin-left: auto;">닫기</button>
				</td>
			</tr>
		</table>
	</form>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
    $(document).ready(function() {
        // "닫기" 버튼 클릭 시 창 닫기
        $("#close").on("click", function() {
            window.close();
        });

        // "오늘 하루 보지 않기" 체크박스 클릭 시 쿠키 설정
        $("#closeToday").on("click", function() {
            if ($(this).prop("checked")) {
            	// 현재 날짜를 가져와서 24시간 뒤의 날짜를 계산
            	setCookie("popup"+${dto.annoNum }, "check", 1 )
            } else {
                // 체크 해제 시 쿠키 삭제
                document.cookie = "popup"+${dto.annoNum }+"=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            }
        });
    });
    
		// set cookie
	function setCookie( name, value, exDay ) {
        var todayDate = new Date();
        todayDate.setDate( todayDate.getDate() + exDay ); 
        document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
    }
		
</script>
</body>
</html>