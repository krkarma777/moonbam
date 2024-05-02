<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문화인들의 밤</title>
    <!-- Bootstrap CSS -->
    <link href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="/acorn/resources/css/myPage.css">
	<script src="https://fastly.jsdelivr.net/npm/js-cookie@3.0.1/dist/js.cookie.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
<div class="container moonBam-container">
  <div class="center-align">
 <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
   비밀번호 변경
   </div>
     <div class="card-body">
    <form action="<c:url value='/my-page/UpdatePassword'/>" method="get" >
         <div class="mb-3">
        <input type="hidden" name="userId" value="${loginUser.userId }">
        <!-- 기존 비밀번호 확인 ajax 필요-->
        <label for="password"  class="form-label">비밀번호:</label>
        <input type="password" id="password" name="password" required>
        <span></span>
        <br>
        </div>
        <div class="mb-3">
        <!-- 비밀번호 입력칸(6글자 이상)(반드시 입력되어야 함) -->
        <label for="userPw" class="form-label">비밀번호 (최소 6글자)</label> 
        <input type="password" id="userPw" name="userPw" class="pw" minlength="6" required maxlength="30"> 
</div>
 <div class="mb-3">
        <!-- 비밀번호 재입력칸(6글자 이상)(반드시 입력되어야 함) -->
        <label for="userPwConfirm"  class="form-label">비밀번호 재입력</label> 
        <input type="password" id="userPwConfirm" name="userPwConfirm" class="pw" required maxlength="30">
        <!-- 비밀번호와 비밀번호 재입력이 상이할 경우, 문구 출력 -->
        <span id="pwMismatch" style="color: red;"></span> 
     </div>    
        <input type="submit" class="btn btn-primary" id="submitbutton" value="탈퇴">
    </form>
    </div>
     </div>
         </div>
            </div>
    </div>
    <script  type="text/javascript">
        $(function(){
            $(".pw").on("input", function(){
                $("#pwMismatch").text("");
            });
            <!-- 출력된 ajax를 지우는 함수 -->
        	
        	<!--  틀린 기존 비밀번호 입력하면 submit안되게 하는 ajax-->         
            $("#submitbutton").on("click", function(event){
                // 비밀번호와 비밀번호 재입력이 일치하지 않는 경우
                if ($("#userPw").val() !== $("#userPwConfirm").val()) {
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
