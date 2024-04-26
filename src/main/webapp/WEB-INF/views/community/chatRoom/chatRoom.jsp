<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html>
<head>
<meta charset="EUC-KR">
<title>Chat Room</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
rel="stylesheet"
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
crossorigin="anonymous">
<style type="text/css">


/*내가 보낸 채팅 말풍선  */
.my-chat {
    position:relative;
    width:200px;
    padding:20px;
    background-color:#ffb2c4;
    border-radius:4px;
    color:black;
    top: 200%; left: 75%; margin-left: -100px;
 }
 
 /*내가 보낸 채팅 말풍선 꼬리  */
.my-chat:after {
	content:'';
    position:absolute;
    top:50%;
    right:-10px;
    border-left:16px solid #ffb2c4;
    border-top:10px solid transparent;
    border-bottom:10px solid transparent;
    transform:translateY(-50%);
}


/*남이 보낸 채팅 말풍선  */
.target-chat{
    position:relative;
    width:200px;
    padding:20px;
    background-color:#FFE0E7;
    border-radius:4px;
    color:black;

 }
 
 /*남이 보낸 채팅 말풍선 꼬리 */
 .target-chat:after {
	content:'';
    position:absolute;
    top:50%;
    left:-10px;
    border-right:16px solid #FFE0E7;
    border-top:10px solid transparent;
    border-bottom:10px solid transparent;
    transform:translateY(-50%);
}
 
 /* 말풍선 디자인  */
 .chat_box{
 	padding: 20px;
 	
 }

/*스크롤*/
.chat_wrap{
   padding-left:0;

    margin:0;

    list-style-type:none;

    display:flex;

    flex-direction: column-reverse;

    overflow-y:scroll;

    height:600px;

}

</style>



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
	${content.userId } - 2 - ${nickNameInSession } - 3 - {userIdInSession}

	<table style="background: white; width: 100%;">
	 
		<thead class="header">
			<tr >
				<td style="background-color: #ffb2c4; color:white;">
					<!-- 제목, 토글 버튼 --> <input type="checkbox" id="toggle" hidden>
					<label for="toggle" class="toggleSwitch"> <span 
						id="toggleIcon" class="toggleButton">▶
							${ChatRoomDTO.roomTitle}</span>
					</label>
				
					<!-- 설정이 더보기, 더보기로 가는 주소 실행 -->
					<%-- <button class="btn" style="float:right; background-color: #ff416c; color:white; margin-left: auto;"
						onclick="location.href='/acorn/Chatmore?chatNum=${ChatRoomDTO.chatNum}'">설정</button> --%>
					<span style="float:right; color:white; margin-left: auto;"
						onclick="location.href='/acorn/Chatmore?chatNum=${ChatRoomDTO.chatNum}'">더보기</span>
					<span style="float:right; color:white; margin-left: auto;"
						onclick="fnGoOut()">퇴장하기&nbsp;&nbsp;&nbsp;&nbsp;</span>	
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
						<div class="chat_wrap" >
							<div id="chat" >
								
							</div>
						</div>
					</form>
				</td>
			</tr>
			<!-- new tag end -->
		</tbody>
		<tfoot>
			<tr>
			<div>
				<td colspan="2"><input type="text" id="messageContent"
					name="messageContent" style="width: 86%"
					onkeydown="if (event.keyCode === 13) { sendMessage(); }" class="form-control-sm">
					<input type="button" id="send" value="전송" class="btn" onclick="sendMessage()" style="float:right; background-color: #ff416c; color:white; margin-left: auto;">
				</td>
			</div>	
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

		
////////////////////////		
		var stompClient = null;
		var userIdInSocket = `${userIdInSession}`; // 사용자 ID;
		var serverTime = new Date().toLocaleString(); //서버 타임

		// 소켓 연결
		function connect() {
			var socket = new SockJS('/acorn/chat-socket');
			stompClient = Stomp.over(socket);
			console.log("stompClient:::",stompClient)
			
			stompClient.connect({}, function(frame) {
				// 메세지 받는 주소
				stompClient.subscribe('/topic/messages/'+${ChatRoomDTO.chatNum},
// 아래 코드 안됨 왜?? ChatMessageController.sendMessage()에  @SendTo("/topic/messages/2") 지우고 @ResponseBody 선언
//				stompClient.subscribe('/acorn/chat/send/'+${ChatRoomDTO.chatNum},
						function(messageOutput) {
				showMessageOutput(JSON.parse(messageOutput.body));
						});
				stompClient.send("/acorn/chat/send/"+${ChatRoomDTO.chatNum}, {}, JSON.stringify({
					'type':'ENTER',
					'message' : `${nickNameInSession}` + ' 님이 입장했습니다.	' + serverTime,
					'userId' : userIdInSocket,
					}));
			});
			
		}
		
		 // 소켓 연결 끊기게 하는 함수
		function disconnect() {
			 
			  if (stompClient !== null) {
			        exit();
			        stompClient.disconnect();
			    }
			    
			    console.log("Disconnected");
		 }
		 
		 //연결 끊겼을 때 퇴장 메세지 띄우는 함수
		 function exit(){
		    stompClient.send("/acorn/chat/send/"+${ChatRoomDTO.chatNum}, {}, JSON.stringify({
		    	'type':'EXIT',
				'message' : `${nickNameInSession}` + ' 님이 퇴장했습니다.	' + serverTime,
				'userId' : userIdInSocket,
			}));
		}
	  
		 
		 
		 
		//방나가기 눌렀을 때 작동되는 fn
		function fnGoOut() {
			console.log("goOutForm");
			
			
			$.ajax({

                type: "post",
                url: "/acorn/chatRoom/out",
                data: {
                  "chatNum" : ${ChatRoomDTO.chatNum}
                },
                success: function (data, status, xhr) {
                	
                	//console.log("하이",data)
					
					if(data == "successToOut"){
						
						disconnect(); ////소켓 연결 끊고 퇴장 메세지 뿌리기
						alert("방을 나갔습니다.");
						window.close(); ///내 창 닫기
						
					}else if(data == "failToOut"){
						
						location.reload(true); ///새로고침
						
					}
                    

                },
                error: function (xhr, status, error) {
						
                	console.log("퇴장하기 error 발생",error)

                }

            })//ajax
			
			
		}
		

		/* 메시지 전송 */
		function sendMessage() {
			// 여기서 "" 처리
			 if(document.getElementById('messageContent').value.trim() != '') {
				var chatNum = `${ChatRoomDTO.chatNum}`; // 방번호  
				var userId = `${userIdInSession}`; // 사용자 닉네임
				//console.log("유저아이디 먼데",userId)
				var message = escapeHtml($("#messageContent").val()); // 메세지 
				var serverTime = new Date().toLocaleString();
				stompClient.send("/acorn/chat/send/"+chatNum, {}, JSON.stringify({
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
	    let nickName = content.nickName;
		let time = content.serverTime;
	
		let message = content.message;
		let userId =  content.userId; 
		
	    let whosMessage = (content.userId == `${userIdInSession}`) ? "my-chat" : "target-chat";
	   
	    if (content.type == 'ENTER') {
	        // 입장 메시지일 경우
	        chatLi = "<li class='enter' style='list-style: none; text-align:center; background-color:#ffdee9; color:black; border-radius: 2em;'><div class='message'><span>"+message+"</span></div></li><br>";
	    
	    }else if(content.type == 'EXIT') {
	    	// 퇴장 메세지일 경우
	    	chatLi = "<li class='enter' style='list-style: none; text-align:center; background-color:#ffdee9; color:black; border-radius: 2em;'><div class='message'><span>"+message+"</span></div></li><br>";
	   
	    }else {
	    	console.log("talk")
	        // 일반 메시지일 경우
	      	let timeShort = time.substr(13); //주고받는 대화에서는 시간만 보이게 잘랐음
			//console.log("시간 잘라서 확인하기 완료?",timeShort)
	        chatLi = "<div class='chat_box'><ul class='chatUl'><li class='"+whosMessage+"' style='list-style: none;'><div><span>"+nickName+"</span></div><div class='message'><span><b>"+message+"&nbsp;</b></span><span style='font-size:13px'>"+timeShort+"</span></div></li></ul></div>"; 
	         
	    }
	    $("#chat").append(chatLi);
	    

	}

		// 취야점 보안
		// 스크립트 코드 정지
		function escapeHtml(unsafe) {
    		return unsafe.replace(/&/g, "&amp;")
	        .replace(/</g, "&lt;")
	        .replace(/>/g, "&gt;")
	        .replace(/"/g, "&quot;")
	        .replace(/'/g, "&#039;");
		}
		
		
		//
		
		
		
		
	</script>
</body>
</html>