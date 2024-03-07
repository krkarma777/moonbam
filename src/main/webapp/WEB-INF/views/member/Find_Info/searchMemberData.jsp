<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 아이디 찾기 페이지의 jsp -->

<head>
	<meta charset="UTF-8">
	<title>아이디 찾기</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/member/ID.css'/>">
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript">
		
		$(function() {
	
			//*********userId 관련 JS*********************
			$("#findIdForm").submit(function(event) {
				//이름 공백 확인
				if ($("#ID_userName").val().trim() === "") {
					alert("이름를 확인해주세요");
					$("#ID_userName").focus();
					return false;
				}
				
				//SSN 길이 확인
				if ($("#ID_ssn1").val().length != 6 || $("#ID_ssn2").val().length != 7) {
					alert("주민등록번호를 확인해주세요");
					$("#ID_ssn1").focus();
					return false;
				}
			})
	
			//SSN1이 6자리까지 입력했을 경우, SSN2로 focus
			$("#ID_ssn1").on('input', function() {
				var maxLength = 6;
				if ($(this).val().length >= maxLength) {
					$(this).val($(this).val().slice(0, maxLength));
					$("#ID_ssn2").val("");
					$("#ID_ssn2").focus();
				}
			});
			
			//ssn들 숫자로 입력 제한
			$(".ssn").on("input", function () {
				
				if (!/^\d*$/.test($(this).val())) {
					alert("숫자만 입력 가능합니다.");
					$(this).val("");
					$(this).focus();
				}
			});
			//*********userId 관련 JS*********************
	
			
			
			
			
			//*********Passwd 관련 JS*********************
			$("#findPWForm").submit(function(event) {
	    		//아이디 공백 여부 확인
	    		if ($("#PW_userId").val().trim() === "") {
					alert("아이디를 확인해주세요");
					$("#PW_userId").focus();
					return false;
				}
				
	    		//이름 공백 여부 확인
	    		if ($("#PW_userName").val().trim() === "") {
					alert("이름를 확인해주세요");
					$("#PW_userName").focus();
					return false;
				}
				
	    		//SSN 길이 여부 확인
				if ($("#PW_ssn1").val().length != 6 || $("#PW_ssn2").val().length != 7) {
					alert("주민등록번호를 확인해주세요");
					$("#PW_ssn1").focus();
					return false;
				}
			})
	
			//SSN1이 6자리까지 입력했을 경우, SSN2로 focus
			$("#PW_ssn1").on('input', function() {
				var maxLength = 6;
				if ($(this).val().length >= maxLength) {
					$(this).val($(this).val().slice(0, maxLength));
					$("#PW_ssn2").val("");
					$("#PW_ssn2").focus();
				}
			});
			//*********Passwd 관련 JS*********************
			
			
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
				<input type="text" id="ID_userName" name="userName" autofocus placeholder="성함">
				<input type="text" class="ssn" id="ID_ssn1" name="ssn1" maxlength="6" placeholder="주민등록번호 앞자리"> 
				<input type="text" class="ssn" id="ID_ssn2" name="ssn2" maxlength="7" placeholder="주민등록번호 뒷자리">
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
		        <input type="text" id="PW_userId" name="userId" pattern="[a-zA-Z0-9]{4,}" title="4자 이상의 영문 대소문자 또는 숫자를 입력하세요" placeholder="아이디">
				<input type="text" id="PW_userName" name="userName" placeholder="성함">
		        <input type="text" class="ssn" id="PW_ssn1" name="ssn1" maxlength="6" placeholder="주민등록번호 앞자리">
		        <input type="password" class="ssn" id="PW_ssn2" name="ssn2" maxlength="7" placeholder="주민등록번호 뒷자리">
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
