<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 전체 비밀번호를 확인하는 jsp -->

<head>
	<meta charset="UTF-8">
	<title>전체 비밀번호 찾기</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/child.css'/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script type="text/javascript">
	
		var username;
		
		$(function () {
			//진행 중인 Ajax 요청을 저장할 변수
			var currentAjaxRequest = null;
			
			// 사용자 답변 확인 함수
			function checkUserAnswer(event) {
			    // 이전 Ajax 요청이 진행 중이라면 취소(4면 요청이 완료되었음을 의미)
			    if (currentAjaxRequest && currentAjaxRequest.readyState !== 4) {
			        //현재 진행중인 ajax요청을 중단
			    	currentAjaxRequest.abort();
			    }
			}
			
			// 엔터 키 누를 때 확인 버튼 클릭
		    $("#confirmForm").keydown(function (event) {
		        if (event.which == 13) { 		// 13: 엔터 키의 keyCode
		            event.preventDefault(); 	// 기본 엔터 동작 방지
		            $("#check_answer").click(); // 확인 버튼 클릭
		        }
		    });
		});																		
																			
		//질문에 따른 대답과 관련된 ajax와 method
		function checkUserAnswer(event) {
			 username = $("#username").val()
			 
			 var userInfo = $("#confirmUserInfo").val();
	         var answer = $("#userAnswer").val();
	         var errorSpan = $("#confirmAnswerError");
	         
	         //이메일 주소를 찾는 질문일 때, @가 포함되어 있지 않은 경우, 경초창 + 이벤트 중지
	         if (userInfo === "restoreUserEmail" && !/@/.test(answer)) {
	             alert("이메일 주소는 @를 포함해야 합니다.");
	             return false;
	         } else {
	             
				//질문에 대한 답변 타당성 판정
				$.ajax({
					type : "POST",
					url : "AjaxMatchQnA",
					data : {
						userInfo : userInfo,
						answer : answer,
						username: username
					},
					
					success : function(response) {
						
						//질문에 따른 해당 유저의 답변이 올바르지 않을 경우, ajax출력
						if (response == "wrong_Answer") {
							errorSpan.text("질문에 대한 답변이 올바르지 않습니다.");
							
						//질문에 따른 해당 유저의 답변이 올바를 경우, 자식창을 닫고 부모창으로 이동시키는 함수 발동
						} else if (response == "correct_Answer") {
							moveToParentPage();
						}
					},
					error : function(error) {
						console.error("비밀번호 출력 검사 에러:", error);
					}, 
					
				});
			}
		}
		
		//질문에 따른 해당 유저의 답변이 올바를 경우, 자식창을 닫고, 부모창을 전체 비밀번호를 보여주는 페이지로 이동
		function moveToParentPage() {		
			window.opener.location.href = "<c:url value='/SearchAllPW'/>";
	        window.close(); 
	    }
		
		//오류창에서 부모창을 로그인 화면으로 보내는 기능
		function redirectToLogin() {
	        window.opener.location.href = "<c:url value='/Login'/>";
	        window.close(); 
	    }
		
		//오류창에서 부모창을 회원정보 찾기 화면으로 보내는 기능
		function redirectToFindInfo() {
	        window.opener.location.href = "<c:url value='/FindInfo'/>";
	        window.close(); 
	    }
		
	</script>

</head>

<body>

<%
    String usernameFromPartPW = (String) request.getAttribute("username");
    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("findPW_username")) {
                usernameFromPartPW = cookie.getValue();
                break;
            }
        }
    }

    System.out.println("전체 비밀번호 페이지에서 불러온 아이디: " + usernameFromPartPW);

	if(usernameFromPartPW != null){
%>
	<input type="hidden" id="username" name="username" value="<%=usernameFromPartPW%>">
	
		<form id="confirmForm">
	        <label for="confirmUsername">전체 비밀번호 확인을 위한 질문</label>
	        <select id="confirmUserInfo" name="confirmUserInfo">
	            <option value="nickname">본인의 닉네임은?</option>
	            <option value="restoreUserEmail">본인의 복구 이메일 주소는?</option>
	        </select>
	        <input type="text" id="userAnswer" name="userAnswer" autofocus>
	        <button id="check_answer" type="button" onclick="checkUserAnswer(event)">확인</button><br>
	        <span id="confirmAnswerError"></span>
	    </form>

    <% } else { %>
    
	    <div class="notFound">
	        <h1>잘못된 접근입니다.</h1>
	        <p class="mesg">비정상적인 접근입니다. 회원정보를 다시 입력해주시기 바랍니다.</p>
			<div style="text-align: center;">
				<span onclick="redirectToLogin()" style="margin-right: 10px; cursor: pointer;">로그인</span>
				 | 
				 <span onclick="redirectToFindInfo()" style="margin-left: 10px; cursor: pointer;">회원정보화면</span>
			</div>
	    </div>
    

<% } %>










</body>

</html>
