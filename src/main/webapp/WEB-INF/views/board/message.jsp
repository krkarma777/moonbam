
<%@page import="com.moonBam.dto.board.MessageDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>문화인들의 밤</title>

<style>
/* 탭 버튼의 기본 스타일 */
.tab-button {
	padding: 10px;
	cursor: pointer;
	border: 1px solid #ccc;
	display: inline-block;
	background-color: #f1f1f1;
}

/* 현재 활성화된 탭 버튼의 스타일 */
.active-tab {
	background-color: white;
	border-bottom: none;
}

/* 탭 컨텐츠의 기본 스타일 */
.tab-content {
	display: none;
	padding: 20px;
	border: 1px solid #ccc;
	border-top: none;
}

/* 활성화된 탭 컨텐츠의 스타일 */
.active-content {
	display: block;
}
</style>

</head>
<body>


	

	<div>
		<div class="tab-button active-tab" onclick="changeTab('sendMessage')">쪽지보내기</div>
		<div class="tab-button" onclick="changeTab('inbox')">받은 쪽지 목록</div>
		<div class="tab-button" onclick="changeTab('outbox')">보낸 쪽지 목록</div>
	</div>

	<div id="sendMessage" class="tab-content active-content">
		<h2>쪽지 보내기</h2>
		<form action="" method="post">
			받는 사람: <input type="text" name="receiverId"><br> 쪽지 내용:
			<textarea name="messageContent"></textarea>
			<br> <input type="submit" value="보내기">
		</form>
	</div>

	<div id="inbox" class="tab-content">
		<h2>받은 쪽지 목록</h2>
		<table border="1">
			<tr>
				<th>보낸 사람</th>
				<th>내용</th>
				<th>받은 시간</th>
			</tr>

			<%
	List<MessageDTO> rList = (List<MessageDTO>)request.getAttribute("receivedMessage");
	for(MessageDTO dto : rList){
		String senderId = dto.getSenderId();
		String messageContent = dto.getMessageContent();
		String sendDate=dto.getSendDate();
	%>

			<tr>
				<td><%=senderId %></td>
				<td><%=messageContent %></td>
				<td><%=sendDate %></td>
			</tr>
			<%} %>
		</table>
	</div>

	<div id="outbox" class="tab-content">
		<h2>보낸 쪽지 목록</h2>
		<table border="1">
			<tr>
				<th>받는 사람</th>
				<th>내용</th>
				<th>받은 시간</th>
			</tr>

			<%
    List<MessageDTO> sList= (List<MessageDTO>)request.getAttribute("sendedMessage");
   	for(MessageDTO dto : sList){
   		String receiverId=dto.getReceiverId();
   		String messageContent=dto.getMessageContent();
   		String sendDate=dto.getSendDate();
   	
    %>

			<tr>
				<td><%=receiverId %></td>
				<td><%=messageContent %></td>
				<td><%=sendDate %></td>

			</tr>
			<%} %>
		</table>
	</div>


	<script>
// 탭을 변경하는 함수
function changeTab(tabId) {
  // 모든 탭 컨텐츠를 숨깁니다.
  var contents = document.getElementsByClassName('tab-content');
  for (var i = 0; i < contents.length; i++) {
    contents[i].style.display = 'none';
  }

  // 모든 탭 버튼을 비활성화합니다.
  var tabs = document.getElementsByClassName('tab-button');
  for (var i = 0; i < tabs.length; i++) {
    tabs[i].className = tabs[i].className.replace(" active-tab", "");
  }

  // 선택된 탭의 컨텐츠를 표시하고, 탭 버튼을 활성화합니다.
  document.getElementById(tabId).style.display = 'block';
  event.currentTarget.className += " active-tab";
}
</script>


</body>
</html>
