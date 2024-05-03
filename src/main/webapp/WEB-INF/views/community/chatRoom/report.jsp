<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>문화인들의 밤</title>
</head>
<link href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
rel="stylesheet"
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
crossorigin="anonymous">
<style type="text/css">

</style>
<body>
	<div align="center" style="margin: 20%">
	이 대화를 신고합니다.
	<br>
	<button onclick="fnReport()" class="btn" style="background-color: #ff416c; color:white; margin-left: auto;">신고</button>
	<button onclick="cancelReport()" class="btn" style="background-color: #ffb2c4; color:white; margin-left: auto;">취소</button>
	</div>	

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>


	var child; //자식팝업창 변수 선언
	
	//회원 신고하기 눌렀을 때 작동되는 fn
	function fnReport() {

		const userId = JSON.parse(localStorage.getItem('userId'));
		const chatNum = JSON.parse(localStorage.getItem('chatNum'));
		//console.log(":::",userId,":::",chatNum,":::",message);
		
		var openUrl = "/acorn/Chatmore/ChatmoreReport?userId="+userId+"&chatNum="+chatNum
		
		childOpen(openUrl);
		window.close(); ///
		
	}
	
	//button기능에 팝업으로 자식창 띄우기 openUrl 변수에 controller 주소 달면 됨
	function childOpen(openUrl){
		//열릴 창(자식 창)의 너비와 높이
		var width = 100;
		var height = 200;
		//열릴 창(자식 창)이 열리는 위치
		var left = Math.ceil(( window.screen.width - width )/2);
        var top = Math.ceil(( window.screen.height - height )/2);
		
		child = open(openUrl,"childName","width"+width+", height"+height+", left"+left+", top"+top);
	}
	
	
	// 취소하기 - 창 닫기   
	function cancelReport() {
           window.close();
       }

	   
</script>
</body>
</html>