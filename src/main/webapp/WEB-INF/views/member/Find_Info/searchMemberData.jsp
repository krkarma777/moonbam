<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 아이디 찾기 페이지의 jsp -->

<head>
	<meta charset="UTF-8">
	<title>문화인들의 밤</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/ID.css'/>">
	<link href="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript">
		
		$(function() {
	
			$("#findIdForm").submit(function(event) {
				//아이디 찾기 보안코드 공백 확인
				if ($("#secretCode").val().trim() === "") {
					alert("보안코드를 입력해주세요");
					$("#secretCode").focus();
					return false;
				}
			})
			$("#findPWForm").submit(function(event) {
	    		//비밀번호 찾기 아이디 공백 여부 확인
	    		if ($("#userId").val().trim() === "") {
					alert("아이디 이메일을 확인해주세요");
					$("#userId").focus();
					return false;
				}
	    		//비밀번호 찾기 보안코드 공백 확인
				if ($("#pw_secretCode").val().trim() === "") {
					alert("보안코드를 입력해주세요");
					$("#pw_secretCode").focus();
					return false;
				}
			})
		});
		
	</script>

</head>

<body>

<div id="header">
	<jsp:include page="/WEB-INF/views/common/navBar.jsp" flush="true"></jsp:include><br>
</div>

 <div class="row" id="rowBar">
  <div class="col">
	<h2>아이디 찾기</h2>
	<form id="findIdForm" action="<c:url value='SearchID'/>" method="post">
	 <table>
        <tr>
        	<td>
				<input type="text" id="secretCode" name="secretCode" autofocus placeholder="보안코드 입력" maxlength="50">
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
    <form id="findPWForm" action="<c:url value='/MailingPW'/>" method="post">
    <table>
        <tr>
        	<td>
				<input type="email" id="userId" name="userId" autofocus placeholder="아이디 이메일 입력" maxlength="40">
				<input type="text" id="pw_secretCode" name="secretCode" autofocus placeholder="보안코드 입력" maxlength="50">
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
		<a href="<c:url value='/mainLogin'/>">로그인</a>
	</div>
	<button type="button" class="btn btn-secondary" data-bs-toggle="tooltip" data-bs-placement="top" title="보안코드는 회원가입 시 모든 유저분들께 부여되는 고유코드입니다. MyPage에서 확인하실 수 있습니다. 또한 회원가입 시 입력하신 이메일 주소로 사이트에서 발송한 환영메일에도 기입되어 있습니다!">
	  보안코드가 뭔가요?
	</button>


	<!-- 부트스트랩 -->
	<script src="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<!-- Tooltip -->
    <script type="text/javascript">
	    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
	    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	      return new bootstrap.Tooltip(tooltipTriggerEl)
	    })
    </script>

</body>

</html>
