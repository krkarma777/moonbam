<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 회원가입 3단계의 자식창으로 아이디 중복 확인하는 페이지의 html -->

<head>
    <meta charset="UTF-8">
    <title>아이디 확인</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/child.css'/>">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
    <form id="confirmForm">
        <label for="confirmUserId">아이디 (영어 + 숫자, 최소 4글자):</label>
        <input type="text" id="confirmUserId" name="confirmUserId" pattern="[a-zA-Z0-9]{4,}" required autofocus>
        <button id="check_id" type="button" onclick="checkDuplicate()">확인</button><br>
        <span id="confirmIdError"></span>
    </form>

    <script type="text/javascript">
		
        function checkDuplicate() {
            var userId = $("#confirmUserId").val();
            var errorSpan = $("#confirmIdError");
            
			//아이디 생성 규칙(영어/숫자로 4글자 이상)
            var regex = /^[a-zA-Z0-9]{4,}$/;
            if (!regex.test(userId)) {
                errorSpan.text("아이디는 영어와 숫자로 4글자 이상이어야 합니다.");
                return false;
            }
            errorSpan.text("");

			//아이디 중복 체크
            $.ajax({
                type: "POST",
                url: "<c:url value='/AjaxIDDuplicate'/>", 
                data: { userId: userId },
                
                beforeSend: function() {
                    // AJAX 요청 전에 수행할 작업 (로딩 표시 등)
                    $("#check_id").prop("disabled", true); // 버튼 비활성화
                },
                
                success: function (response) {
                	
                	//아이디가 중복일 경우, ajax 출력
                    if (response === "duplicate") {
                        errorSpan.text("이미 사용 중인 아이디입니다.");
                    
                    //아이디가 중복이 아닐 경우, 입력된 아이디를 부모창의 아이디 입력칸으로 옮기고, 자식창 닫기
                    } else {
                        window.opener.$("#userId").val(userId);
                        window.close();
                    }
                },
                error: function (error) {
                    console.error("아이디 중복 검사 에러:", error);
                },
                
                complete: function() {
                    // AJAX 요청 완료 후 수행할 작업 (로딩 표시 해제 등)
                    $("#check_id").prop("disabled", false); // 버튼 활성화
                }
            });
        }
        
    	// 엔터 키 누를 때 확인 버튼 클릭
        $("#confirmUserId").keydown(function (event) {
            if (event.which == 13) { // 13: 엔터 키의 keyCode
                event.preventDefault();
                checkDuplicate(); // 확인 버튼 클릭
            }
        })
    </script>
</body>

</html>
