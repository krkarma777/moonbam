<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>문화인들의 밤</title>
</head>
<body>
	이 대화를 신고합니다.
	<br>
	<form id="reportForm" action="chatReport" method="post">
		<input type="submit" value="신고"> 
		 <input type="button" onclick="cancelReport()" value="취소">
	</form>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
	// 취소하기 - 창 닫기   
	function cancelReport() {
           window.close();
       }
	// 신고하기 - 창 닫기
	// 폼 제출 이벤트 처리
        $("#reportForm").submit(function(event) {
            event.preventDefault(); // 기본 제출 동작 방지
            var formData = $(this).serialize(); // 폼 데이터 직렬화

            // Ajax를 사용하여 데이터를 chatReport 페이지로 전송
            $.ajax({
                type: "POST",
                url: "chatReport",
// 전달 데이터 수정 필요
                data: formData,
                success: function(response) {

                    // 전송이 성공하면 현재 창을 닫음
                    window.close();
                },
                error: function(xhr, status, error) {
                    // 전송 오류 처리
                    console.error(xhr.responseText);
                }
            });
        });
	   
</script>
</body>
</html>