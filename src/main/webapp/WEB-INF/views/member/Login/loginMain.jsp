<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>문화인들의 밤</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link href="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="resources/css/member/Main.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
</head>
<body>

<div id="header">
    <jsp:include page="/WEB-INF/views/common/navBar.jsp" flush="true"></jsp:include><br>
</div>

<div id="contentBody">
	<div class="container" id="container">
	<!-- 회원가입 컨테이너 -->
	  <div class="form-container sign-up-container">
	    <form id="registerForm" action="<c:url value='/RegisterTerms'/>" method="post">
	      <h1>회원가입</h1>
	      <div class="social-container">
	        <a href="/acorn/oauth2/authorization/kakao" class="social"><img src="<c:url value='/resources/images/member/kakao.png'/>" width="30" height="30"></a>
	        <a href="/acorn/oauth2/authorization/google" class="social"><img src="<c:url value='/resources/images/member/google.png'/>" width="30" height="30"></a>
	        <a href="/acorn/oauth2/authorization/naver" class="social"><img src="<c:url value='/resources/images/member/naver.png'/>" width="30" height="30"></a>
	      </div>
	      <span>외부 사이트 이용하실껀가요?</span>
	      <input type="email" id="register_userId" name="userId" required placeholder="가입할 이메일을 입력하세요" maxlength="40"/>
	      <div id="confirmUserEmailError" style="font-size: 14px; color: red;"></div>	
	      <button>회원가입으로</button>
	    </form>
	  </div>
	  
	<!-- 로그인 컨테이너 -->
	  <div class="form-container sign-in-container">
	  	<form id="loginForm" action="<c:url value='login'/>" method="post">
	      <h1>로그인</h1>
	      <div class="social-container">
	        <a href="/acorn/oauth2/authorization/kakao" class="social"><img src="<c:url value='/resources/images/member/kakao.png'/>" width="30" height="30"></a>
	        <a href="/acorn/oauth2/authorization/google" class="social"><img src="<c:url value='/resources/images/member/google.png'/>" width="30" height="30"></a>
	        <a href="/acorn/oauth2/authorization/naver" class="social"><img src="<c:url value='/resources/images/member/naver.png'/>" width="30" height="30"></a>
	      </div>
	      <span>외부 사이트를 이용하실껀가요?</span>
	      <input type="email" id="userId" name="userId" class="loginSet" autofocus placeholder="이메일" maxlength="40"/>
	      <input type="password" id="userPw" name="userPw" class="loginSet" placeholder="패스워드" maxlength="30"/>
	      <button type="button" id="showPasswd" class="loginButtons">비밀번호 보이기</button>
	      <button class="loginButtons">로그인</button>
		  <div id="confirmUserIdPwError" style="font-size: 14px; color: red;"></div>	    
	      
	      <div class="row" id="userIdSaveSection">
	      	  <div class="col-1"></div>
              <div class="col-3"><input type="checkbox" id="userIdSave" name="userIdSave" class="loginSet"></div>
              <div class="col-5 save-label cookieT">이메일 저장</div>
              <div class="col-3"></div>
		  </div>	 
	      <div class="row">
              <div class="col"><a href="<c:url value='/FindInfo'/>">회원정보 찾기</a></div>
		  </div>	 
	   </form>
	  </div>

	<!-- 안내 문구 컨테이너 -->	  
	  <div class="overlay-container">
	    <div class="overlay">
	      <div class="overlay-panel overlay-left">
	        <h1>환영합니다!</h1>
	        <p>회원가입을 위해<br>고객님의 정보를 입력해주세요!</p>
	        <button class="ghost" id="signIn">로그인 화면으로</button>
	      </div>
	      <div class="overlay-panel overlay-right">
	        <h1>문화인의 밤에<br>어서오세요!</h1>
	        <p>사이트 이용을 위해<br>아이디와 비밀번호를 입력해주세요!</p>
	        <button class="ghost" id="signUp">회원가입 화면으로</button>
	      </div>
	    </div>
	  </div>
	</div>
</div>


	<script type="text/javascript">
	$(function(){
	    
	    //로그인과 회원가입 탭 전환*******************************************
	    $('#signUp').on('click', function () {
	        $('#container').addClass("right-panel-active");
	        $('#register_userId').focus();
	    });
	    
	    function changeTab(){
	    	$('#container').removeClass("right-panel-active");
	        $('#email').val('');
	        $('#userId').focus();
	    }

	    $('#signIn').on('click', function () {
	    	changeTab()
	    });
	    //*************************************************************

	    
	    //로그인 관련 함수*************************************************
	    var currentAjaxRequest = null;

		//쿠키 불러오기
	    var cookieID = getCookie("userId");
	   
	    //아이디 쿠키가 있으면 아이디 창에 입력 + 아이디 저장 체크
	    if(cookieID){
	    	$("#userId").val(cookieID)
	    	$("#userIdSave").prop("checked",true);
	    } else {
	    	$("#userId").val("")
	    	$("#userIdSave").prop("checked",false);
	    }
	    
		//쿠키 불러오기 함수	    
	    function getCookie(name) {
    		var value = "; " + document.cookie;
    		var parts = value.split("; " + name + "=");
   		 if (parts.length === 2) return parts.pop().split(";").shift();
		}
	    
	    
	    //로그인 전송을 시도할 경우, 발동
	    $("#loginForm").on("submit", function(event) {
	    	event.preventDefault(); // 폼이 서버로 전송되지 않도록 기본 동작을 막음
	        
	        // 이전 Ajax 요청이 진행 중이라면 취소(4면 요청이 완료되었음을 의미)
	        if (currentAjaxRequest && currentAjaxRequest.readyState !== 4) {
	            //현재 진행중인 ajax요청을 중단
	            currentAjaxRequest.abort();
	        }
	        
	        var userId = $("#userId").val();
	        var userPw = $("#userPw").val();
	        var errorSpan = $("#confirmUserIdPwError");

	        //아이디와 비밀번호를 모두 입력한 경우
	        if (userId && userPw) {
	            $.ajax({
	                type: "POST",
	                url: "<c:url value='/AjaxCheckIDPW'/>", 
	                data: {
	                    userId: userId,
	                    userPw: userPw,
	                },
	                
	                beforeSend: function() {
	                    // AJAX 요청 전에 수행할 작업
	                    $("#loginButton").prop("disabled", true); // 버튼 비활성화
	                },
	               
	                success: function(response) {
	                    
	                    // 활동 정지된 유저일 경우
	                    if (response === "suspendedId") {
	                        errorSpan.html("활동이 정지된 유저입니다.<br>관리자에게 문의해주세요.");
	                    } else 
	                   	
	                    // 소셜 회원가입자일 경우	
	                    if (response === "socialLogin") {
		                        errorSpan.html("소셜 회원가입 유저입니다.");
	                    } else
		                        
		                // 입력한 아이디와 비밀번호가 DB 정보와 일치하지 않을 경우, ajax 출력
	                    if (response === "loginFail") {
	                        errorSpan.text("아이디나 비밀번호를 확인해주세요.");

		                // 입력한 아이디와 비밀번호가 DB 정보와 일치할 경우, submit 정상 작동
	                   	} else {
	                        errorSpan.text("");
	                        $("#loginForm")[0].submit();
	                    }
	                },
	                error: function(error) {
	                    console.error("아이디, 비밀번호 검사 에러:", error);
	                },
	              
	                complete: function() {
	                    // AJAX 요청 완료 후 수행할 작업
	                    $("#loginButton").prop("disabled", false); // 버튼 활성화
	                }
	            });
	        } else {
	            errorSpan.text("");
	        }
	        
	        // ID 공백 여부 확인
	        if (userId.trim() === "") {
	            alert("아이디를 입력하세요");
	            $("#userId").focus();
	            return false;
	        }

	        // PW 공백 여부 확인
	        if (userPw.trim() === "") {
	            alert("비밀번호를 입력하세요");
	            $("#userPw").focus();
	            return false;
	        }
	        
	        //에러 메세지가 있는지 확인
	        if($("#confirmUserIdPwError").text() != ""){
	            $("#userId").focus();
	            return false;
	        }
	            
	        return true;
	        
	    }); //$("#loginForm").on("submit", function(event)
	    
	    //비밀번호를 볼 수 있도록 해주는 method
	    $("#showPasswd").click(function () {
	        var showPW = $("#userPw");
	        showPW.attr("type", showPW.attr("type") == "password" ? "text" : "password");
	    });
	    
	    
	    
	    
	    
	    
	    
	    
	  //회원가입 진행을 시도할 경우, 발동
	    $("#registerForm").on("submit", function(event) {
	    	event.preventDefault(); // 폼이 서버로 전송되지 않도록 기본 동작을 막음
	        
	        var userId = $("#register_userId").val();
	        var errorSpan = $("#confirmUserEmailError");

	        //이메일을 입력
	        if (userId) {
	            $.ajax({
	                type: "POST",
	                url: "<c:url value='/AjaxCheckEmail'/>", 
	                data: {
	                    userId: userId,
	                },
	                
	                success: function(response) {
	                    
	                    // 입력한 아이디 이메일이 DB에 있을 경우, ajax 출력
	                    if (response === "RegisterFail") {
	                        errorSpan.text("이미 사용 중인 이메일입니다.");
	                    } else
	                    // 입력한 아이디 이메일이 소셜네트워크 회원가입자일 경우, ajax 출력
		                if (response === "socialRegister") {
		                        errorSpan.text("소셜네트워크 회원가입자입니다.");     
	                    
		                // 입력한 아이디 이메일이 DB에 없을 경우, submit 정상 작동
	                    } else {
	                        errorSpan.text("");
	                        $("#registerForm")[0].submit();
	                    }
	                },
	                error: function(error) {
	                    console.error("이메일 등록 검사 에러:", error);
	                },
	            });
	        } else {
	            errorSpan.text("");
	        }
	        
	        //에러 메세지가 있는지 확인
	        if($("#confirmUserEmailError").text() != ""){
	            $("#register_userId").focus();
	            return false;
	        }
	        return true;
	    }); 
	})
	    
		
	</script>

    <script src="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
        crossorigin="anonymous"></script>  
</body>
</html>













<!-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
　　　▅　　　　　　　　　　　　　 ▀█▅　　　▅
　▅▀　　　█　　　　　██　　　　　　　　▀　　　▀▅
▅▀　　　▀　　　　　█　　█　　　　　　　　　　　　▀▅
█　　　　　　　　　█　　  █　　　　　　　　　　　　 █
█　　　　　　　　 █　　　　█　　　　　　　　　　　　█
　█　　　　　　　 █▀▀▀▀▀▀▀█　　　　　　　　　　   █
　　▀▅　　　　▅▀　　　　　▀▅　　　　　　　   ▅▀

 -->


