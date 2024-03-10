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
<title>회원 리스트(테스트용)</title>
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
			<th>이름</th>
			<th>닉네임</th>
			<th>SSN1</th>
			<th>SSN2</th>
			<th>성별</th>
			<th>핸드폰1</th>
			<th>핸드폰2</th>
			<th>핸드폰3</th>
			<th>이메일 아이디</th>
			<th>이메일 도메인</th>
			<th>가입일</th>
			<th>유형</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="dto" items="${memberList}">
			<tr>
				<td>${dto.userId}</td>
				<td><div class="pw" data-pw="${dto.getUserPw()}">${dto.getUserPw()}</div></td>
				<td>${dto.getUserName()}</td>
				<td>${dto.getNickname()}</td>
				<td>${dto.getUserSSN1()}</td>
				<td>${dto.getUserSSN2()}</td>
				<td>${dto.getUserGender()}</td>
				<td>${dto.getUserPhoneNum1()}</td>
				<td>${dto.getUserPhoneNum2()}</td>
				<td>${dto.getUserPhoneNum3()}</td>
				<td>${dto.getUserEmailId()}</td>
				<td>${dto.getUserEmailDomain()}</td>
				<td>${dto.getUserSignDate()}</td>
				<td>${dto.getUserType()}</td>
				<td><button class="deleteBtn" data-id="${dto.getUserId()}">삭제(참조 시 X)</button></td>
			</tr>
		</c:forEach>
	</table>

	<div id="sitesShortCut">
		<a href="<%=request.getContextPath()%>/Login">로그인 폼</a>
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
				console.log(ecPW);

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