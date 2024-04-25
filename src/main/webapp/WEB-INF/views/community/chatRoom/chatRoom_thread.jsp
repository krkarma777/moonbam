<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html>
<head>
<meta charset="EUC-KR">
<title>Chat Room</title>
	<!-- 방 삭제 시 실패했을 때 띄울 알림창  -->
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body onload="connect()">
_thread
	<table border="1" style="background: white; width: 500px">
		<thead>
			<tr>
				<td>
					<!-- 제목, 토글 버튼 --> <input type="checkbox" id="toggle" hidden>
					<label for="toggle" class="toggleSwitch"> <span
						id="toggleIcon" class="toggleButton">▶
							${ChatRoomDTO.roomTitle}</span>
				</label>
				</td>


				<td style="width: 20px;">
					<!-- 설정이 더보기, 더보기로 가는 주소 실행 -->
					<button
						onclick="location.href='/acorn/Chatmore?chatNum=${ChatRoomDTO.chatNum}'">설정</button>
				</td>
			</tr>
			<tr>
				<!-- 첫 화면부터 공간 차지함 -->
				<td colspan="2" class="text_align_c" id='toggle_state'><br>
					<c:if test="${sen.lastCv==1}">
					</c:if> <c:if test="${sen.lastCv==0}">
					</c:if></td>
			</tr>
		</thead>
		<tbody id="message1">
			<tr>
				<td>
					<form id="chatForm">
						<!-- new tag start -->
						<div class="chat_wrap">
							<div id="chat" class="chat"></div>
						</div>
					</form>
				</td>
			</tr>
			<!-- new tag end -->
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2"><input type="text" id="messageContent"
					name="messageContent" style="width: 85%"
					onkeydown="if (event.keyCode === 13) { sendMessage(); }"> <input
					type="button" id="send" value="전송" onclick="sendMessage()">
				</td>


			</tr>
		</tfoot>
	</table>

	<script>
	
		/* 토글 처리 */
		$('input[id="toggle"]')
				.change(
						function() {
							var value = $(this).val();
							var checked = $(this).prop('checked');
							var toggle_state;
							if (checked) {
								document.getElementById('toggleIcon').innerHTML = "▼ ${ChatRoomDTO.roomTitle}";
								document.getElementById('toggle_state').innerHTML = "${ChatRoomDTO.roomText}";
								toggle_state = "on";
							} else {
								document.getElementById('toggleIcon').innerHTML = "▶ ${ChatRoomDTO.roomTitle}";
								document.getElementById('toggle_state').innerHTML = "&nbsp;";
								toggle_state = "off";
							}
						});

		/* 신고하기 */
		function openReportWindow() {
			var url = "reportWindow";
			window.open(url, "_blank", "width=600,height=400");
		}

		/* 멤버 */
		function openMemberWindow() {
			var url = "memberWindow";
			window.open(url, "_blank", "width=600,height=400");
		}

		var stompClient = null;

		// 소켓 연결
		function connect() {
			var socket = new SockJS('/acorn/chat-socket');
			stompClient = Stomp.over(socket);
			var serverTime = new Date().toLocaleString();
			stompClient.connect({}, function(frame) {
				// 메세지 받는 주소
				stompClient.subscribe('/topic/messages/'+${ChatRoomDTO.chatNum},
// 아래 코드 안됨 왜?? ChatMessageController.sendMessage()에  @SendTo("/topic/messages/2") 지우고 @ResponseBody 선언
//				stompClient.subscribe('/acorn/chat/send/'+${ChatRoomDTO.chatNum},
						function(messageOutput) {
				showMessageOutput(JSON.parse(messageOutput.body));
						});
			// 입장 메세지 전송
				stompClient.send("/acorn/chat/send_thead/"+${ChatRoomDTO.chatNum}, {}, JSON.stringify({
					'type':'ENTER',
					'message' : `${nickNameInSession}` + ' 님이 입장했습니다.	' + serverTime,
					}));
			});
			event.preventDefault();
		}

		/* 메시지 전송 */
		function sendMessage() {
			// 여기서 "" 처리
			 if(document.getElementById('messageContent').value.trim() != '') {
				var chatNum = `${ChatRoomDTO.chatNum}`; // 방번호  
				var userId = `${userIdInSession}`; // 사용자 닉네임
				var message = escapeHtml($("#messageContent").val()); // 메세지 
				var serverTime = new Date().toLocaleString();
// 수정
				stompClient.send("/acorn/chat/send_thead/"+chatNum, {}, JSON.stringify({
					'type' : 'TALK',
					'userId' : userId,
					'message' : message,
					'serverTime' : serverTime}));
				document.getElementById('messageContent').value = ''; 
			 }
		}

		// 메세지 출력
		// 최신 메세지 추가(위치는 맨 뒤)
		// 이전 메세지 추가(위치는 맨위)
		function showMessageOutput(body) {
			let content = JSON.parse(body.chatContent);
			createMsgTag(content)
		}
		
	function createMsgTag(content) {
	    let chatLi;
	    let nickName = `${nickNameInSession}`;
		let time = content.serverTime;
		let message = content.message;
		
	    let align = (content.userId == `${userIdInSession}`) ? "right" : "left";
	    console.log(align)
	    
	    if (content.type == 'ENTER') {
	        // 입장 메시지일 경우
	        chatLi = "<li class='enter'><div class='message'><span>"+message+"</span></div></li>";
	    } else {
	    	console.log("talk")
	        // 일반 메시지일 경우
	         chatLi = "<li><div class='"+align+"'><span>"+nickName+"</span><span>"+time+"</span></div><div class='message'><span>"+message+"</span></div></li>"; 
	    }
	    $("#chat").append(chatLi);
	 // 스크롤바 아래 고정
	 // 하지만 동작 안함
	    $("#chat").scrollTop($('div.chat').prop('scrollHeight'));
	}

		// 취야점 보안
		// 스크립트 코드 정지
		function escapeHtml(unsafe) {
    		return unsafe.replace(/&/g, "&amp;")
	        .replace(/</g, "&lt;")
	        .replace(/>/g, "&gt;")
	        .replace(/"/g, "&quot;")
	        .replace(/'/g, "&#039;");
    };
	</script>
</body>
</html>