<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>문화인들의 밤</title>
</head>
<body>
<h1>chatRoom_miji_test</h1>
<form action="/acorn/Chatmore" method="get">
<input type="hidden" name="chatNum" value="${ChatRoomDTO.chatNum }">
<button >더보기</button>
</form>
채팅방Num => ${ChatRoomDTO.chatNum }
채팅방LeaderId => ${ChatRoomDTO.leaderId }

<!--  -->
</body>
</html>