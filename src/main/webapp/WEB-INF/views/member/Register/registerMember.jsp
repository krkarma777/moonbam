<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 회원가입 3단계로 유저 정보를 입력하는 페이지의 jsp -->

<head>
	<meta charset="UTF-8">
	<title>문화인들의 밤</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/register_input.css'/>">
	<script src="https://fastly.jsdelivr.net/npm/js-cookie@3.0.1/dist/js.cookie.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>

<div id="header">
	<jsp:include page="/WEB-INF/views/common/navBar.jsp" flush="true"></jsp:include><br>
</div>
<div class = "body">
	<div class="container">
		<h1>회원가입</h1>
		<form id="registerForm" action="<c:url value='/InsertData'/>" method="post">
			
			<!-- 아이디 입력칸(회원가입 1단계 이메일 입력칸 값) -->
			<label for="userEmailDomain">이메일 인증</label>
				<input type="email" id="email" name="userId" class ="userEmail" maxlength="30" value="${userId}" required readonly maxlength="40">
			
			<!-- 이메일 인증 버튼 -->
			<div style="display: flex; gap: 5px;">
				<input type="text" id="certification" name="certification" required="required">
				<input type="button" id="certificationBTN" value="인증번호 발송">
			</div>
			<span id = "certificationAnswer"></span>
			
			<!-- 비밀번호 입력칸(6글자 이상)(반드시 입력되어야 함) -->
			<label for="userPw">비밀번호 (최소 6글자)</label> 
				<input type="password" id="userPw" name="userPw" class="pw" minlength="6" required maxlength="30"> 

			<!-- 비밀번호 재입력칸(6글자 이상)(반드시 입력되어야 함) -->
			<label for="userPwConfirm">비밀번호 재입력</label> 
				<input type="password" id="userPwConfirm" name="userPwConfirm" class="pw" required maxlength="30">
				<!-- 비밀번호와 비밀번호 재입력이 상이할 경우, 문구 출력 -->
				<span id="pwMismatch" style="color: red;"></span> 
			
			<!-- 유저 닉네임 입력칸(최소 2글자 이상)(반드시 입력) -->
			<label for="nickname">유저 닉네임 (최소 2글자)</label> 
				<input type="text" id="nickname" name="nickname" maxlength="10" minlength="2" required>
				<input type="button" id="autoNickname" name="clickType" value="자동 닉네임 생성" class="btn btn-success me-md-2">
				<!-- DB에 저장된 닉네임이 있을 경우, 문구 출력 --> 
				<span id="confirmNicknameError" style="color: red;"></span>
				<span id="loadingSpinner_for_nickname" class="loadingSpinner"></span><br>
			
			<button id="register_button" type="submit">가입</button>
		</form>
	</div>
</div>

	<script type="text/javascript">
	
		//새로고침, 뒤로가기, 나가기 시 경고창 함수
		function f5Control(event){
			event.preventDefault();
		    event.returnValue = '';
		}
	
	   $(function(){
		 	//페이지 로딩되면 기존 인증번호 쿠키 삭제
		   deleteCookeeFunction();
		   
		   //새로고침, 뒤로가기, 나가기 시 경고창 함수 출력
		   window.addEventListener('beforeunload', f5Control);
		   
		   //뒤로가기 단축키을 누르면 로그인 메인으로 이동(Alt + <- 기능)(브라우저 뒤로가기 버튼은 막히지 않음)
		   //브라우저 세션 히스토리 스택에 항목 추가(사용자 데이터 전달X(첫번째 null) / title요소 업데이트X(두 번째 null), event발생 시 이동할 url)
		   window.history.pushState(null, null, window.location.href);
		   //뒤로가기 / 앞으로가기 동작 감시
		   window.onpopstate = function(event) {
		   	window.history.pushState(null, null, window.location.href);
		    window.location.href= "<c:url value='/mainLogin'/>"; 
		    };
		    
		 	//자동 닉네임 생성기
        	$("#autoNickname").on("click", function(){
        		var nicknameSpace = $("#nickname");
        		$.ajax({
                    type: "POST",
                    url: "<c:url value='/randomNickname'/>", 
                    success: function (response) {
                    	nicknameSpace.val(response);
                    },
                    error: function (error) {
                        console.error("닉네임 자동 생성기 에러:", error);
                    }
                })
        	})
	    });

	 	//페이지 로딩되면 기존 인증번호 쿠키 삭제
		function deleteCookeeFunction(){
			 $.ajax({
		            type: "GET",
		            url: "<c:url value='/deleteCookiee'/>", 
		            success: function(response) {
		                if (response === "complete") {
		                	console.log("쿠키 삭제")
		                } else {
		                	console.log("쿠키 삭제  XXXXXXXXXXXXX")
		                }
		            },
		            error: function(error) {
		                console.error("쿠키 삭제 에러:", error);
		            }
		        });

			   //쿠키값 삭제 확인 콘솔
	/* 	       
				var myCookieValue = Cookies.get('confirmNum');
		        if (myCookieValue) {
		            console.log('쿠키 값:', myCookieValue);
		        } else {
		            console.log('쿠키가 없습니다.');
		        } 
	*/
		}
		
		//PW입력하면 에러 문구 삭제
		$(".pw").on("input", function(){
			$("#pwMismatch").text("");
		});
		
		//닉네임 중복 확인
		var prevNickname = ""; 
		
		$("#nickname").on("focusout", function() {
			var nickname = $("#nickname").val();
		    var errorSpan = $("#confirmNicknameError");
		    
		    if (nickname !== prevNickname) {
				$.ajax({
	                type: "POST",
	                url: "<c:url value='/AjaxNicknameDuplicate'/>", 
	                data: { nickname: nickname },
	                
	                beforeSend: function () {
	                    // AJAX 요청 전에 로딩 표시 보여주기
	                	$("#loadingSpinner_for_nickname").show();
	                	// 가입 버튼 비활성화
	                	$("#register_button").prop("disabled", true);
	                	$("#userIdButton").prop("disabled", true);
	              },
	                
	                success: function (response) {
	                	//닉네임이 DB에 저장된 닉네임과 일치하는 데이터가 있을 경우, ajax 출력
	                    if (response === "duplicate") {
	                        errorSpan.text("이미 사용 중인 닉네임입니다.");
	                    } else {
	                        errorSpan.text("");
	                    } 
	                },
	                error: function (error) {
	                    console.error("닉네임 중복 검사 에러:", error);
	                }, 
	                
	                complete: function () {
	                    // AJAX 요청 완료 후에 로딩 표시 숨기기
	                	$("#loadingSpinner_for_nickname").hide();
	                	// 가입 버튼 활성화
		               	$("#register_button").prop("disabled", false);
		               	$("#userIdButton").prop("disabled", false);
	                }
				})
				prevNickname = nickname;
            };
		});
		
		
		//이메일 인증 번호 발송 에이젝스
		$("#certificationBTN").on("click", function() {
		    var userEmail = $("#email").val();
		    var errorSpan = $("#certificationAnswer");
		    var certification = $("#certification");
		    
			    //이메일 아이디와 이메일 도메인이 모두 있을 때 출력
			        $.ajax({
			            type: "POST",
			            url: "<c:url value='/joinEmail'/>", 
			            data: {
			            	userEmail: userEmail
			            },
			            success: function(response) {
			            	alert("인증 번호가 발송되었습니다. 이메일을 확인해주세요.")
			            	certification.val("");
			            	errorSpan.text("");
			            	certificationFocusout()
			            },
			            error: function(error) {
			                console.error("이메일 인증 번호 발송 에러:", error);
			            }
					});
		});
		
		//이메일 인증 번호 확인 
		var prevCertification = ""; 
		
		$("#certification").on("focusout", function() {
			certificationFocusout()
		});
		
		function certificationFocusout(){
			var certification = $("#certification").val();
		    var errorSpan = $("#certificationAnswer");
		    
		    //이메일 아이디와 이메일 도메인이 모두 있을 때 출력
		    if (certification != prevCertification) {
			        $.ajax({
			            type: "POST",
			            url: "<c:url value='/CertificationAnswer'/>", 
			            
			            beforeSend: function () {
		                    // AJAX 요청 전에 로딩 표시 보여주기
		                	$("#loadingSpinner_for_Email").show();
	                },
			            data: {
			            	certification: certification,
			            },
			            success: function(response) {
			            	//이메일 인증번호와 쿠키가 된 인증번호가 일치할 경우
			                if (certification == "") {
			                	errorSpan.text("");
			                } else if (response === "confirm") {
			                	errorSpan.text("확인되었습니다."); 
			                	errorSpan.css("color", "black"); 
			                } else {
			                	errorSpan.text("인증번호가 다릅니다. 확인해주세요.");
			                	errorSpan.css("color", "red"); 
			                }
			            },
			            error: function(error) {
			                console.error("이메일 인증 번호 검사 에러:", error);
			            }, 
			            
			            complete: function () {
		                    // AJAX 요청 완료 후에 로딩 표시 숨기기
		                	$("#loadingSpinner_for_Email").hide();
		                }
			        });
			    prevCertification = certification
			}
		}

		//submit 시에는 경고창이 뜨지 않음(나가기/새로고침)
		$("#register_button").on("click", function(){
			window.removeEventListener('beforeunload', f5Control);
		})
		
		//submit 제한규칙
		$("#registerForm").submit(function(event) {
			return validateForm(event);
		});

		function validateForm(event) {
			
		    var errorMessage = ""; // 에러 메시지를 저장할 변수

			//문구 출력 및 문구 확인
		    switch(true) {
			    case $("#confirmUserEmailError").text() != "" && $("#confirmRestoreUserEmailIdError").text() != "":
		            errorMessage = "이메일 입력 오류입니다. 처음부터 다시 진행해주세요.";
		            break;

			    case $("#userPw").val() !== $("#userPwConfirm").val():
		            errorMessage = "입력한 비밀번호가 일치하지 않습니다.";
		            $("#pwMismatch").text(errorMessage);
		            $("#userPw").focus();
		            break;

		        case $("#confirmNicknameError").text() != "":
		            errorMessage = "닉네임 중복 여부를 확인해주세요";
		            $("#nickname").focus();
		            break;

		        case $("#certificationAnswer").text() != "확인되었습니다.":
		            errorMessage = "이메일 인증을 확인해주세요";
		            $("#certification").focus();
		            break;

		        default:
		            // 모든 제약을 통과하면 submit
		            $("#registerForm")[0].submit();
		            return true;
		    }

		    // 에러 메시지가 있는 경우 경고창 출력
		    alert(errorMessage);
		    return false;
		};
	</script>



</body>

</html>
