<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--  -->
	<h1>채팅방 더보기 창</h1>
	<!-- 나중에 이거 새창이 띄워져야함  -->
	<!-- 참여인원정보, 내정보보기, 방나가기, 방삭제하기(방장만 볼 수 있음) -->
	
	<table border="1">
		<tr>
			<th colspan="3">[방장]</th>
		</tr>
		<tr>
			<td>
			<ul>
				<span style="font-size: 13px; color: gray;">가입한 날짜 ${leadermemberDto.userSignDate }</span><br>
					<li>
						${leadermemberDto.nickname } ( ${leadermemberDto.userId } )
					</li>
				</ul>
			</td>
		</tr>
	
		<tr>
			<th colspan="3">[대화상대]</th>
		</tr>
		
		<c:forEach items="${memberDtoList }" var="memberDto"><!-- 현재 채팅방 안에 있는 member들만 모였음 -->
		
			<c:if test="${memberDto.userId != sessionScope.loginUser.userId }"> <!-- 근데 본인은 제외하고 출력 -->
				<tr>
					<td>
					<ul>
					<span style="font-size: 13px; color: gray;">가입한 날짜 ${memberDto.userSignDate }</span><br>
						<li>
							${memberDto.nickname } ( ${memberDto.userId } ) <button onClick="fnReport('${memberDto.userId }')">신고하기</button>
						</li>
					</ul>
					</td>
				</tr>
			</c:if>		
		</c:forEach>
			
		<tr>
			<th>[내 정보]</th>
		</tr>
		
			<tr>
				<td>
					<ul>
						<li>
							${sessionScope.loginUser.nickname} ( ${sessionScope.loginUser.userId} ) <button>정보 수정하기</button>
						</li>	 
					</ul>
				</td>
			</tr>
	
		<tr align="center">
		
			<td>
				<form id="helloForm" method="post" action="#">
				<input type="hidden" name="userId" value="${sessionScope.loginUser.userId}">
				<input type="hidden" name="chatNum" value="${chatNum}">
				</form>	
				<button onclick="fnGoOut()">방 나가기</button>
				<c:if test="${leadermemberDto.userId == sessionScope.loginUser.userId}">
				<!-- 방 삭제하기는 방장만 보이게 처리했음  -->
				<button>방 삭제하기</button>
				</c:if>
					
			</td>
			
		</tr>	
	
	</table>
	
	<!-- 신고가 정상 진행되면 신고 잘 됐다고 알림창 뜨기   -->
	<% String mesg = (String) session.getAttribute("mesg");
	if(mesg != null ){	
		
		%>	
		
		<script>
		alert("<%= mesg %>");
		</script>
	
	<%} 
	
	session.removeAttribute("mesg");
	
	%>
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script type="text/javascript">
	

		

		var child; //자식팝업창 변수 선언
		
		
		//회원 신고하기 눌렀을 때 작동되는 fn
		function fnReport(userId) {
			
			var openUrl = "/acorn/Chatmore/ChatmoreReport?userId="+userId+"&chatNum="+${sessionScope.chatRoomDto.chatNum}
			
			childOpen(openUrl);
			
		}
		
		
		//방나가기 눌렀을 때 작동되는 fn
		function fnGoOut() {
			console.log("helloForm");
			
			$("#helloForm").attr("action","/acorn/chatRoom/out").submit();
			
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
		
		//자식창닫기
		function childClose(){ 
			child.close();
		}

	
	
	
	</script>
</body>
</html>