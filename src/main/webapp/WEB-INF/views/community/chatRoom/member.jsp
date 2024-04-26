<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>문화인들의 밤</title>
</head>
<body>
	<form id="reportForm" action="chatReport" method="post">
		<input type="button" onclick="newLeader('newLeader')" value="정보보기"> 
		<input type="button" onclick="newLeader('newLeader')" value="방장위임"> 
		<input type="button" onclick="memberRemove('memberRemove')" value="강퇴하기">
	</form>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
	function newLeader(path) {
		 $.ajax({
             type: "POST",
             url: path,
			//전달 데이터 수정 필요
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
	}
	   
</script>
</body>
</html>