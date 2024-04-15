<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>문밤</title>

  <!-- jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

  <!-- jQuery UI -->
  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<script>
	//달력
	$(function() {
	    $("#datepicker").datepicker({
	      dateFormat: "yy-mm-dd"
	    });
	  });
/*  */
	//모임 소개글 글자 제한수
	function checkLength() {
		const maxLength = 150;
		const text = document.getElementById("myTextarea").value;
		const count = text.length;
      
		if (count > maxLength) {
			document.getElementById("myTextarea").value = text.substring(0, maxLength);
			document.getElementById("charCount").textContent = maxLength;
		} else {
		document.getElementById("charCount").textContent = count;
		}
	}
</script>
</head>
<body>

<h1>모임만들기</h1>
<form action="saveChat" method="post">
	<b>카테고리</b>
		<select name="category">
			<option value="select">선택하세요</option>
			<option value="movie">영화</option>
			<option value="book">책</option>
			<option value="ect">기타</option>
		</select>
	<br><br>
	<b>모임 이름</b> <input type="text" placeholder="작품명 / 지역구 / 모임날짜" name="roomTitle">
	<br><br>
	<b>인원수</b>
		<select name="amount">
			<option value=2>2</option>
			<option value=3>3</option>
			<option value=4>4</option>
			<option value=5>5</option>
		</select>
	<br><br>
	<b>모임 장소</b> 지도 연동<input type="text" name="loc">
	<br><br>
	<b>모임 날짜</b> <input type="text" id="datepicker" placeholder="날짜를 선택하세요" name="cDate">
	<br><br>
	<b>모임 소개글</b><br>
	<textarea id="myTextarea" rows="10" cols="30" oninput="checkLength()" name = "roomText"></textarea>
	<span id="charCount">0</span> / 150
	<br><br>
	<input type="submit" value="만들기">
	<input type="reset" value="초기화">
</form>

</body>
</html>
