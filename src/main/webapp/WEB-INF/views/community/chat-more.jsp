<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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

.title {
	font-size: 19px;
}

.btn {
	background-color: #ff416c;
	color: white;
}
</style>
</head>
<body class="bg-light"
	style="height: 100%; width: 100%; position: relative; border: 1px solid black;">
	<div
		style="height: 30px; background-color: #ffb2c4; font-size: 19px; margin-bottom: 5px; color: white;">
		<span onclick="history.back()"><b>뒤로가기</b></span>
	</div>
	<!-- 나중에 이거 새창이 띄워져야함  -->
	<!-- 참여인원정보, 내정보보기, 방나가기, 방삭제하기(방장만 볼 수 있음), 강퇴 -->

	<!-- <div style="margin-bottom: 15px; height: 108px; width: 100%;"> -->
	<span class="title"><b>${chatroomDTO.roomTitle }</b></span>
	<br> &nbsp&nbsp${chatroomDTO.roomText }
	<br>
	<b>&nbsp&nbsp모임 장소</b>
	<br>
	<span style="opacity: 0.7;">&nbsp&nbsp우편번호 ${chatroomDTO.post }</span>
	<span style="opacity: 0.7;">&nbsp&nbsp${chatroomDTO.addr1 }</span>
	<!-- </div> -->


	<!-- 내 정보 -->
	<div style="margin-bottom: 15px; height: 108px; width: 100%;">
		<span class="title"><b>내 정보</b></span><br> <b>&nbsp&nbsp${memberDTO.nickname}
			( ${memberDTO.userId} ) </b><br> <span style="opacity: 0.7;">&nbsp&nbsp가입한
			날짜 ${leadermemberDto.userSignDate }</span>
		<div>
			<form action="/acorn/my-page/info">
				<button class="btn btn-sm" style="float: right; height: 30px;">
					<b>정보 수정하기</b>
				</button>
			</form>
		</div>
	</div>

	<!-- 방장 -->
	<div style="margin-bottom: 15px; height: 80px;">
		<span class="title"><b>방장</b></span><br> <b>&nbsp&nbsp${leadermemberDto.nickname
			} ( ${leadermemberDto.userId } )</b><br> <span style="opacity: 0.7;">&nbsp&nbsp가입한
			날짜 ${leadermemberDto.userSignDate }</span>
	</div>

	<!-- 대화 상대 -->
	<div style="margin-bottom: 15px; width: 100%; height: 320px;">
		<span class="title" style="height: 20px;"><b>대화 상대</b></span>
		<!-- <button type="button" class="btn btn-sm"
			style="float: right; height: 30px;" onClick="#">
			<b>초대하기</b>
		</button> -->
		<br>
		<c:forEach items="${memberDtoList }" var="memberDtolist">
			<!-- 현재 채팅방 안에 있는 member들만 모였음 -->

			<c:if test="${memberDtolist.userId != memberDTO.userId }">
				<!-- 근데 본인은 제외하고 출력 -->
				<div style="height: 80px; width: 100%;">
					<b>&nbsp&nbsp${memberDtolist.nickname } (
						${memberDtolist.userId } )</b><br> <span style="opacity: 0.7;">&nbsp&nbsp가입한
						날짜 ${memberDtolist.userSignDate }</span>
					<div>
						<button type="button" class="btn btn-sm"
							style="float: right; height: 30px;"
							onClick="fnReport('${memberDtolist.userId }')">
							<b>신고하기</b>
						</button>
					</div>
					<c:if test="${leadermemberDto.userId == memberDTO.userId}">
						<div>
							<button type="button" class="btn btn-sm"
								style="float: right; height: 30px;"
								onClick="fnKick('${memberDtolist.userId }')">
								<b>강퇴하기</b>
							</button>
							<button type="button" class="btn btn-sm"
							style="float: right; height: 30px;"
							onClick="delegate('${memberDtolist.userId }')">
							<b>방장 넘기기</b>
							</button>
						</div>
					</c:if>
				</div>
			</c:if>
		</c:forEach>
	</div>

	<div style="width: 100%; height: 10px; position: relative;">
		<%-- <form id="goOutForm" method="post" action="#">
			<input type="hidden" name="userId" value="${memberDTO.userId}">
			<input type="hidden" name="chatNum" value="${chatroomDTO.chatNum}">
		</form> --%>
		<!-- <button type="button" class="btn" onclick="fnGoOut()" style="position: absolute; bottom:2px; right:0;"><b>방 나가기</b></button> -->
		<c:if test="${leadermemberDto.userId == memberDTO.userId}">
			<!-- 방 삭제하기는 방장만 보이게 처리했음  -->
			<button type="button" class="btn" onclick="fnRemove()"
				style="position: absolute; bottom: 2px; left: 0;">
				<b>방 삭제하기</b>
			</button>
		</c:if>
	</div>




	<!-- 신고가 정상 진행되면 신고 잘 됐다고 알림창 뜨기   -->
	<%
	String mesg = (String) session.getAttribute("mesg");
	if (mesg != null) {
	%>

	<script>
		alert("<%=mesg%>");
		</script>

	<%
	}

	session.removeAttribute("mesg");
	%>

	<%

	%>

	<script
		src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
	<script type="text/javascript">
	
		var child; //자식팝업창 변수 선언
		var userIdInSocket = `${userIdInSession}`; // 사용자 ID;
		
		//회원 신고하기 눌렀을 때 작동되는 fn
		function fnReport(userId) {
			
			var openUrl = "/acorn/Chatmore/ChatmoreReport?userId="+userId+"&chatNum="+${chatroomDTO.chatNum}
			
			childOpen(openUrl)
			
		}
	
		//방장이 방 삭제하기 눌렀을 때 작동되는 fn
		function fnRemove() {
			console.log("fnRemove");
			//$("#goOutForm").attr("action","/acorn/chatRoom/remove").submit();
			//window.history.back();
			
			$.ajax({

                type: "post",
                url: "/acorn/chatRoom/remove",
                data: {
                  "chatNum" : ${chatroomDTO.chatNum}
                },
                success: function (data, status, xhr) {
                
					
					if(data == "successToDelete"){
						
						
						alert("방을 삭제하였습니다.");
						window.close(); ///내 창 닫기
						opener.location.reload(); //내 창 닫고 부모창 새로고침하기
						
					}else if(data == "failToDelete"){
						
						alert("방을 삭제에 실패하였습니다. 다시 시도해주세요.");
						location.reload(true); ///새로고침
						
					}else if(data == "failToDeleteToCurrentNow"){
						alert("방장 제외 인원이 남아있다면 강퇴 및 나가기 안내 후 다시 시도해주세요.");
						location.reload(true); ///새로고침
						
					}
                    

                },
                error: function (xhr, status, error) {
						
                	console.log("퇴장하기 error 발생",error)
                }
            })//ajax
			
			
			
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
		
		var stompClient = null;
		var userIdInSocket = `${userIdInSession}`; // 사용자 ID;
		var serverTime = new Date().toLocaleString(); //서버 타임

		// 페이지 로드 시 초기화
		$(document).ready(function() {
		    connect(); // 소켓 연결
		});

		// 소켓 연결
		function connect() {
		
		    var socket = new SockJS('/acorn/chat-socket');
		    stompClient = Stomp.over(socket);
		    
		    stompClient.connect({}, function(frame) {
		        console.log("Connected to WebSocket", frame.headers['user-name']);
		        
		        // 연결된 사용자가 채팅 메시지를 보낼 때마다 호출되어야 함
		        sendChatMessage('ENTER', `${nickNameInSession} 님이 입장 ${serverTime}`);
		    });
		}

		function sendChatMessage(type, message, userIdInSocket) {
		    stompClient.send(`/acorn/chat/send/${ChatRoomDTO.chatNum}`, {}, JSON.stringify({
		        'type': type,
		        'message': message,
		        'userId': userIdInSocket
		    }));
		}

		
		
		//채팅 멤버 강퇴
		function fnKick(userId) {
		    stompClient.send("/acorn/Chatmore/ChatKickUser/" + `${chatroomDTO.chatNum}`, {}, JSON.stringify({
	        'type': "KICKED",
	        'userId': userId,	  }));
		}
		
		function delegate(userId){
			//location.href = "/acorn/delegateMaster?chatNum="+${chatroomDTO.chatNum}+"&userId="+userId;
			
			stompClient.send("/acorn/delegateMaster/" + `${chatroomDTO.chatNum}`, {}, JSON.stringify({
		        'type': "ANNOUNCE",
		        'userId': userId,	  }));
		}
		
	/* 	//방나가기 눌렀을 때 작동되는 fn (이거 메인화면으로 이동했음)
		function fnGoOut() {
			console.log("goOutForm");
			$("#goOutForm").attr("action","/acorn/chatRoom/out").submit();
			
		} */
		
</script>
</body>
</html>