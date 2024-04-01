<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 아이디 찾기 페이지의 jsp -->

<head>
	<meta charset="UTF-8">
	<title>아이디 찾기</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/ID.css'/>">
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript">
		
		$(function() {
	
			//*********userId 관련 JS*********************
			$("#findIdForm").submit(function(event) {
				//이름 공백 확인
				if ($("#ID_email").val().trim() === "") {
					alert("아이디를 확인해주세요");
					$("#ID_email").focus();
					return false;
				}
			})
			//*********userId 관련 JS*********************
	
			//*********Passwd 관련 JS*********************
			$("#findPWForm").submit(function(event) {
	    		//아이디 공백 여부 확인
	    		if ($("#PW_userId").val().trim() === "") {
					alert("아이디를 확인해주세요");
					$("#PW_userId").focus();
					return false;
				}
	    		if ($("#PW_email").val().trim() === "") {
					alert("이메일을 확인해주세요");
					$("#PW_email").focus();
					return false;
				}
			})
		});
	</script>

</head>

<body>

 <div class="row" id="rowBar">
  <div class="col">
	<h2>아이디 찾기</h2>
	<form id="findIdForm" action="<c:url value='SearchID'/>" method="post">
	 <table>
        <tr>
        	<td>
				<input type="email" id="ID_email" name="email" autofocus placeholder="이메일을 입력하세요">
			</td>
		</tr>
		<tr>
        	<td>
				<input type="submit"  value="확인">
			</td>
		</tr>
	</table>
	</form>
	 </div>	 
	
	
	<div class="col">
	<h2>비밀번호 찾기</h2>
    <form id="findPWForm" action="<c:url value='/SearchPartPW'/>" method="post">
    <table>
        <tr>
        	<td>
		        <input type="text" id="PW_userId" name="userId" pattern="[a-zA-Z0-9]{4,}" title="4자 이상의 영문 대소문자 또는 숫자를 입력하세요" placeholder="아이디를 입력하세요">
				<input type="email" id="PW_email" name="email" autofocus placeholder="이메일을 입력하세요">
			</td>
		</tr>
		<tr>
        	<td>
		        <input type="submit" value="확인">		
        	</td>
		</tr>
	</table>
    </form>
	 </div>	 
	  </div>	 
	
	<div id="sitesShortCut">
		<a href="<c:url value='/Login'/>">로그인</a>
	</div>



</body>

</html>
