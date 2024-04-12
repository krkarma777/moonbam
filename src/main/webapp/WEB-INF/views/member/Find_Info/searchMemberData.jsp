<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 아이디 찾기 페이지의 jsp -->

<head>
	<meta charset="UTF-8">
	<title>유저 정보 찾기 페이지</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/ID.css'/>">
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript">
		
		$(function() {
	
			$("#findIdForm").submit(function(event) {
				//이름 공백 확인
				if ($("#restoreEmail").val().trim() === "") {
					alert("예비 이메일을 입력해주세요");
					$("#restoreEmail").focus();
					return false;
				}
			})
			$("#findPWForm").submit(function(event) {
	    		//아이디 공백 여부 확인
	    		if ($("#userId").val().trim() === "") {
					alert("아이디 이메일을 확인해주세요");
					$("#userId").focus();
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
				<input type="email" id="restoreEmail" name="restoreEmail" autofocus placeholder="예비 이메일 입력" maxlength="40">
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
				<input type="email" id="userId" name="userId" autofocus placeholder="아이디 이메일 입력" maxlength="40">
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
