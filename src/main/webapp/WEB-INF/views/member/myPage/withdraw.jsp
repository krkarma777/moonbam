<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 탈퇴</title>
    
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.1/dist/js.cookie.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
    <h1>회원 탈퇴</h1>
    <form action="<c:url value='/my-page/confirm'/>"    method="post" modelAttribute="withdrawForm">
    <input type="hidden" name="userId" value="${loginUser.userId }">
    
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" class="pw" required>
        <br>
        <label for="confirmPassword">비밀번호 확인:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" class="pw" required>
          <span id="pwMismatch" style="color: red;"></span> 
        <br>
        
        <input type="submit" id="submitbutton" value="탈퇴">
    </form>
    
      <script  type="text/javascript">
        $(function(){
            $(".pw").on("input", function(){
                $("#pwMismatch").text("");
            });
            <!-- 출력된 ajax를 지우는 함수 -->
        	
        	<!--  틀린 기존 비밀번호 입력하면 submit안되게 하는 ajax-->         
            $("#submitbutton").on("click", function(event){
                // 비밀번호와 비밀번호 재입력이 일치하지 않는 경우
                if ($("#password").val() !== $("#confirmPassword").val()) {
                    errorMessage = "입력한 비밀번호가 일치하지 않습니다.";
                    $("#pwMismatch").text(errorMessage);
                    $("#userPw").focus();
                    event.preventDefault();
                }
            });
        });
    </script>
</body>
</html>
