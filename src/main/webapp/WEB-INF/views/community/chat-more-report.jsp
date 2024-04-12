<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h1>회원 신고 화면입니다.</h1>
<form action="<%=request.getContextPath() %>/Chatmore/ChatmoreReport" method="post">
<input type="hidden" name="chatNum" value="${chatNum }"> <!--  고객이 볼 필요는 없으니 hidden으로 form에 포함시켜 넘기기-->
	<table border="1">
		<tr align="center">
			<td><b>회원 신고 양식</b><br>
			<span style="color: gray; font-size: 13px">무분별한 신고행위는 사이트 이용에 제재를 받을 수 있습니다.</span></td>
		</tr>
		
			<tr align="center">
				<td>
					<span><b>신고 대상 ID</b></span><br>
					<input type="text" name="userId" value="${userId }" style="text-align: center" readonly>
				</td>
			</tr>	
			<tr align="center">
				<td>
				<span><b>신고 사유 선택</b></span><br>
					<select name="reason" style="text-align: center">
						<option>욕설/인신공격 채팅</option>
						<option>음란성/선정성 채팅</option>
						<option>불법 사이트 공유 및 거래 유도</option>
						<option>영리목적/홍보성 채팅</option>
						<option>개인정보유출 유도성 채팅</option>
						<option>같은 내용의 반복 게시(도배성 채팅)</option>
					</select><br>
				</td>
			</tr>
	</table>
	<div>
	<br>
	<button>작성</button>
	<button onclick="mywindow()" >취소</button>
	</div>
</form>
</div>
<script type="text/javascript">

	
	function mywindow(){ //본인창닫기
		close();
	}
	
	
</script>
</body>

</html>