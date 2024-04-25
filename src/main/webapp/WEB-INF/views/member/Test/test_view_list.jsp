<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.moonBam.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<!-- 디버그를 위한 회원 리스트 출력 페이지의 jsp -->

<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>
#for_Login {
	margin-top: 20px;
	text-align: center;
}

#loginForm {
	display: inline-block;
	border: 1px solid #ddd;
	padding: 10px;
	border-radius: 5px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table {
	margin: 0 auto; /* 중앙 정렬 */
}

td {
	padding: 8px;
	text-align: left;
}

.loginSet {
	width: 200px; /* 입력 필드의 너비 조정 */
	padding: 5px;
	margin-right: 5px;
}

#Find_Member_One_button {
	padding: 8px 20px;
	background-color: #007BFF;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	cursor: pointer;
}

#Find_Member_One_button:hover {
	background-color: #0056b3;
}

#sitesShortCut, #debugLink {
	margin-top: 10px;
}

#sitesShortCut a, #debugLink a {
	text-decoration: none;
	color: #007BFF;
	font-weight: bold;
	margin-right: 10px;
}

#sitesShortCut a:hover, #debugLink a:hover {
	text-decoration: underline;
}
</style>

</head>
<body>

	<h1>[회원 목록]</h1>
	<hr>

	<table border=1>
		<tr>
			<th>아이디</th>
			<th>비밀번호(클릭해서 확인)</th>
			<th>닉네임</th>
			<th>보안코드</th>
			<th>구글 연동 여부</th>
			<th>네이버 연동 여부</th>
			<th>카카오 연동 여부</th>
			<th>가입일</th>
			<th>권한</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="dto" items="${memberList}">
			<tr>
				<td>${dto.userId}</td>
				<td><div class="pw" data-pw="${dto.getUserPw()}">${dto.getUserPw()}</div></td>
				<td>${dto.getNickname()}</td>
				<td>${dto.getSecretCode()}</td>
				<td>${dto.getGoogleConnected()}</td>
				<td>${dto.getNaverConnected()}</td>
				<td>${dto.getKakaoConnected()}</td>
				<td>${dto.getUserSignDate()}</td>
				<td>${dto.getRole()}</td>
				<td><button class="deleteBtn" data-id="${dto.getUserId()}">삭제(참조 시 X)</button></td>
			</tr>
		</c:forEach>
	</table>

	<div id="sitesShortCut">
		<a href="<%=request.getContextPath()%>/mainLogin">로그인 폼</a>
	</div>
	
	<script type="text/javascript">
		$(function() {

			$(".deleteBtn").on("click", function() {
				var userId = $(this).attr("data-id");
				var tr = $(this)
				
				$.ajax({
					type: "GET",
					url: "<c:url value='/IDDelete'/>", 
					data: {
						userId : userId
						},
					dataType: "text",
					success: function(){
						tr.closest("tr").remove();
						},
					error: function(xhr, status, error){
						console.log(error)
					}
				})
			})

			$(".pw").on("click", function() {

				var rp = $(this);
				var ecPW = rp.attr("data-pw");
				var userPw = rp.text();
				//console.log(ecPW);

				if (ecPW == userPw) {
					
					$.ajax({
						type : "GET",
						url : "<c:url value='/decrypt'/>",
						data : {
							mesg : userPw
						},
						dataType : "text",
						success : function(response) {
							rp.text(response)
						},
						error : function(xhr, status, error) {
							console.log(error)
						}
					})
					
				} else {
					
					$.ajax({
						type : "GET",
						url : "<c:url value='/encrypt'/>",
						data : {
							mesg : userPw
						},
						dataType : "text",
						success : function(response) {
							rp.text(response)
						},
						error : function(xhr, status, error) {
							console.log(error)
						}
					})

				}
			})
		}) 
	</script>

</body>
</html>