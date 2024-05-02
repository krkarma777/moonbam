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
    <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
</div>

<div id="contentBody">
	<h1>User Information</h1>
    
    <table border="1">
        <tr>
            <th>항목</th>
            <th>정보</th>
        </tr>
        <tr>
            <td>User ID</td>
            <td>${loginUser.userId}</td>
        </tr>
        <tr>
            <td>User Name</td>
            <td>${loginUser.userName}</td>
        </tr>
        <tr>
            <td>User Gender</td>
            <td>${loginUser.userGender}</td>
        </tr>
        <tr>
            <td>Nickname</td>
            <td>${loginUser.nickname}</td>
        </tr>
        <tr>
            <td>User Phone Number</td>
            <td>${loginUser.userPhoneNum1}-${loginUser.userPhoneNum2}-${loginUser.userPhoneNum3}</td>
        </tr>
        <tr>
            <td>User Email</td>
            <td>${loginUser.userEmailId}@${loginUser.userEmailDomain}</td>
        </tr>
    </table>
</div>


	<script type="text/javascript">
	$(function(){
	    
	    //로그인과 회원가입 탭 전환*******************************************
	    $('#signUp').on('click', function () {
	        $('#container').addClass("right-panel-active");
	        $('#userId').val('');
	        $('#userPw').val('');
	        $('#userName').focus();
	    });
	    
	    function changeTab(){
	    	$('#container').removeClass("right-panel-active");
	        $('#userName').val('');
	        $('#ssn1').val('');
	        $('#ssn2').val('');
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
	    var cookiePW = getCookie("userPw");
	   
													  //  쿠키 디버그 코드
													  console.log("아이디 쿠키: "+ cookieID);
													  console.log("비밀번호 쿠키: "+ cookiePW);
													  
	    //아이디 쿠키가 있으면 아이디 창에 입력 + 아이디 저장 체크
	    if(cookieID){
	    	$("#userId").val(cookieID)
	    	$("#userIdSave").prop("checked",true);
	    } else {
	    	$("#userId").val("")
	    	$("#userIdSave").prop("checked",false);
	    }
	    
	    //비밀번호 쿠키가 있으면 비밀번호 창에 입력 + 자동로그인 체크
	    if(cookiePW){
	    	$("#userPw").val(cookiePW)
	    	$("#autoLogin").prop("checked",true);
	    } else {
	    	$("#userPw").val("")
	    	$("#autoLogin").prop("checked",false);
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
	    
	    //패턴 속성에 문구 삽입
	    $("#loginButton").on("click", function(){
	        console.log("Submit button clicked");
	        var inputValue = $("#userId").val();
	        
	        // 유효한 경우 아무 동작도 하지 않음
	        if (/^[a-zA-Z0-9]+$/.test(inputValue)) {
	            $("#userId")[0].setCustomValidity(''); 
	        } else {
	            // 유효하지 않은 경우 오류 메시지를 직접 설정
	            $("#userId")[0].setCustomValidity('영문자 또는 숫자를 입력하세요.');
	        }
	    })    
	    
	    //비밀번호를 볼 수 있도록 해주는 method
	    $("#showPasswd").click(function () {
	        var showPW = $("#userPw");
	        showPW.attr("type", showPW.attr("type") == "password" ? "text" : "password");
	    });


	    //*************************************************************
	    
	    
	    //기존 회원 여부 확인 관련 함수****************************************
	    $("#confirmForm").submit(function(event) {
	        //이름 공백 확인 및 2글자
	        if ($("#userName").val().trim() === "" || $("#userName").val().length < 2) {
	            alert("이름를 확인해주세요");
	            $("#userName").focus();
	            return false;
	        }
	        
	        //SSN1 길이 확인
	        if ($("#ssn1").val().length != 6) {
	            alert("주민등록번호를 확인해주세요");
	            $("#ssn1").val('').focus();
	            return false;
	        }
	        
	        //SSN2의 첫 숫자 확인(1~4) + 길이 확인
	        if ($("#ssn2").val().length != 7) {
	            alert("주민등록번호를 확인해주세요");
	            $("#ssn2").val('').focus();
	            return false;
	        }
	        
	    })

	    //SSN1이 6자리 이상이면 SSN2로 focus
	    $("#ssn1").on('input', function() {
	        var maxLength = 6;
	        if ($(this).val().length >= maxLength) {
	            $(this).val($(this).val().slice(0, maxLength));
	            $("#ssn2").val("");
	            $("#ssn2").focus();
	        }
	    });
	})
	    
	//ssn을 숫자로 입력 제한
	$(".ssn").on("input", function () {
	    
	    if (!/^\d*$/.test($(this).val())) {
	        alert("숫자만 입력 가능합니다.");
	        $(this).val("");
	        $(this).focus();
	    }
	});
	//*************************************************************
		
	</script>

    <script src="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
        crossorigin="anonymous"></script>  
</body>
</html>













<!-- 
　　　▅　　　　　　　　　　　　　 ▀█▅　　　▅
　▅▀　　　█　　　　　██　　　　　　　　▀　　　▀▅
▅▀　　　▀　　　　　█　　█　　　　　　　　　　　　▀▅
█　　　　　　　　　█　　  █　　　　　　　　　　　　 █
█　　　　　　　　 █　　　　█　　　　　　　　　　　　█
　█　　　　　　　 █▀▀▀▀▀▀▀█　　　　　　　　　　   █
　　▀▅　　　　▅▀　　　　　▀▅　　　　　　　   ▅▀

 -->


