<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>

<!-- jQuery UI -->
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
 <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  
 <!-- 부트 스트랩 -->
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

  <style type="text/css">
    * {
      padding: 0;
      margin: 0;
      box-sizing: border-box;
    }
    .item {
      margin-top: 2px;
      margin-bottom: 5px;
      margin-left: 10px;
      width: 340px;
    }
 
    
    span {
      font-size: 18px;
    }
  </style>

</head>

<body>
<div align="center">
<h1>회원 신고 화면입니다.</h1>
<form action="<%=request.getContextPath() %>/Chatmore/ChatmoreReport" method="post">
<input type="hidden" name="targetId" value="chat${chatNum }"> 
<input type="hidden" name="chatNum" value="${chatNum }"> 
<input type="hidden" name="reporterId" value="${reporterId }"> 
<!--  고객이 볼 필요는 없으니 hidden으로 form에 포함시켜 넘기기-->
<!--  -->
	<table border="1" >
	
		<tr align="center" style=" background-color: #ffb2c4; color:black; margin-left: auto;">
		
			<td><b>회원 신고 양식</b><br>
			<span style="color: white; font-size: 13px">무분별한 신고행위는 사이트 이용에 제재를 받을 수 있습니다.</span></td>
			<br>
		</tr>
			<tr>
				<td>
				<hr>
					<span><b>신고 대상 ID</b></span>
					<input type="text" name="userId" value="${userId }" style="text-align: center" class="form-control form-control-sm item" readonly>
				</td>
			</tr>	
		
			<tr>
				<td>
				<hr>
				<span><b>신고 사유 선택</b></span><br>
						<input type="checkbox" name="lang" value="T">욕설/인신공격 채팅<br>	
						<input type="checkbox" name="sexual" value="T">음란성/선정성 채팅<br>	
						<input type="checkbox" name="ruleViolation" value="T">불법 사이트 공유 및 개인정보 유출 유도 채팅<br>
						<input type="checkbox" name="abusing" value="T">같은 내용의 반복 게시(도배성 채팅)<br>	
						<input type="checkbox" name="etc" value="T">기타<br>	
						<hr>
						<b>신고 사유 작성 :</b> <input type="text" id="cont" name="cont" value="" class="form-control form-control-sm item">
					<br>
				</td>
			</tr>
			<tr  align="center">
				<td>
				<hr>
				<button class="btn" style=" background-color: #ff416c; color:white; margin-left: auto;">작성</button>
				<button class="btn" style=" background-color: #ffb2c4; color:white; margin-left: auto;" onclick="mywindow()" >취소</button>
				</td>
			</tr>
	</table>
</form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">



	function mywindow(){ //본인창닫기
			close();
	}
				

</script>
</body>

</html>