<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<head>
<meta charset="EUC-KR">
<title>Chat Room</title>
<!-- 방 삭제 시 실패했을 때 띄울 알림창  -->
<% String mesg = (String) session.getAttribute("mesg");
	if(mesg != null ){	
		
		%>	
		
		<script>
		alert("<%= mesg %>");
		</script>
	
	<%} 
	
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
${content.userId } - 2 - ${nickNameInSession } - 3 - {userIdInSession}
	<form id="chatForm">
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
					<td class="text_align_c" id='toggle_state'><br> <c:if
							test="${sen.lastCv==1}">
						</c:if> <c:if test="${sen.lastCv==0}">
						</c:if></td>
				</tr>
			</thead>
			<tbody id="message">
				<tr>
					<td colspan="3" style="float: left; width: 50%;">
						<table>
							<tr>
								<td ><span id="user" style="cursor: pointer;"
									onclick="openMemberWindow()">user</span></td>
								<td>yy/mm/dd/hh:mm</td>
							</tr>
							<tr>
								<td><span id="msg" style="cursor: pointer;"
									onclick="openReportWindow()">your msg</span></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" style="float: right; width: 50%;">
						<table>
							<tr>
								<td>yy/mm/dd/hh:mm</td>
							</tr>
							<tr>
								<td style="word-wrap: break-word">my msg</td>
							</tr>
						</table>
					</td>
				</tr>
				
				
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2"><input type="text" id="messageContent"
						name="messageContent" style="width: 85%" onkeydown="if (event.keyCode === 13) { event.preventDefault(); sendMessage()}"> 
						<input type="button" id="send" value="전송"></td>
				</tr>
			</tfoot>
		</table>
	</form>



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
			stompClient.connect({}, function(frame) {
				console.log('Connected: ' + frame);
				// 메세지 받는 주소
				stompClient.subscribe('/topic/messages',
						function(messageOutput) {
				showMessageOutput(JSON.parse(messageOutput.body));
						});
			});
		}

		// 소켓 연결 해제
		function disconnect() {
			console.log("disconnect");
			if (stompClient !== null) {
				stompClient.disconnect();
			}
			console.log("Disconnected");
		}

		/* 메시지 전송 */
		function sendMessage() {
			var chatNum = `${ChatRoomDTO.chatNum}`; // 방번호  
			var userId = `${userIdInSession}`; // 사용자 닉네임
			var message = $("#messageContent").val(); // 메세지 */
			
			// 메시지가 비어 있는지 확인
		    if (message.trim() === "") {
		        // 메시지가 비어 있으면 전송 중지
		        return;
		    }
			
			var serverTime = new Date().toLocaleString();
			console.log(userId)
			stompClient.send("/acorn/chat/send", {}, JSON.stringify({
				'userId' : userId,
				'message' : message,
				'serverTime' : serverTime}));
			document.getElementById('messageContent').value = '';
		}

		// 메세지 출력
		// 최신 메세지 추가(위치는 맨 뒤)
		// 이전 메세지 추가(위치는 맨위)
		function showMessageOutput(body) {
			let content = JSON.parse(body.chatContent);
			let align;
			let userTag;
			let timeTag = `<span>` + content.serverTime + `</span>`;
			let msgTag;
			
			// my message
			if (`${userIdInSession}` == content.userId) {
				console.log("my")
				align = "right"
				className = "my"
				userTag = `<span>나<span>`;
				msgTag = `<span>` + content.message + `</span>`;
			}
			// other's message
			else {
				className="other"
				align = "left"
				userTag = `<tr><td class=`+ className+`><span id="user" style="cursor: pointer;" onclick="openMemberWindow()">` + content.nickName + `</span>`
				msgTag = `<span id="msg" style="cursor: pointer;" onclick="openReportWindow()">` + content.message + `</span>`;
			}
			console.log("add")
			$("#message").append(
					 `<tr><td class=`+ className +` colspan='3' style='float: '`+ align + `'; width: 50%;'><table><tr><td>` 
					 + userTag + `</td><td>` + timeTag + `</td></tr>`+ 
					 `<tr><td>` + msgTag + `</td></tr></table></td></tr>`);
		}

		$(function() {
			$("form").on('submit', function(e) {
				e.preventDefault();
			});
			$("#connect").click(function() {
				connect();
			});
			$("#disconnect").click(function() {
				disconnect();
			});
			$("#send").click(function() {
				sendMessage();
			});
		});
		
	</script>
</body>
</html>