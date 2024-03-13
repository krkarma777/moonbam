<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 회원가입 3단계로 유저 정보를 입력하는 페이지의 jsp -->

<head>
	<meta charset="UTF-8">
	<title>회원 가입</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/register_input.css'/>">
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.1/dist/js.cookie.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>

	<div class="container">
		<h1>회원가입</h1>
		<form id="registerForm" action="<c:url value='/InsertData'/>" method="post">
			
			<!-- 아이디 입력칸(영어+숫자로 4글자 이상)(반드시 입력되어야 함)(직접 입력은 불가하며, 자식창을 통해서만 입력 가능) -->
			<label for="userId">아이디 (영어 + 숫자, 최소 4글자)</label> 
				<input type="text" id="userId" name="userId" pattern="[a-zA-Z0-9]{4,}" required readonly>
				<button id = "userIdButton" type="button" onclick="openIdWindow()">아이디 확인</button>

			<!-- 비밀번호 입력칸(6글자 이상)(반드시 입력되어야 함) -->
			<label for="userPw">비밀번호 (최소 6글자)</label> 
				<input type="password" id="userPw" name="userPw" class="pw" minlength="6" required> 

			<!-- 비밀번호 재입력칸(6글자 이상)(반드시 입력되어야 함) -->
			<label for="userPwConfirm">비밀번호 재입력</label> 
				<input type="password" id="userPwConfirm" name="userPwConfirm" class="pw" minlength="6" required>
				<!-- 비밀번호와 비밀번호 재입력이 상이할 경우, 문구 출력 -->
				<span id="pwMismatch" style="color: red;"></span> 
			
			<!-- 유저 이름 입력칸(회원가입 2단계에서 입력한 값을 통해 입력)(직접 입력 불가) -->
			<label for="userName">유저 이름</label> 
				<input type="text" id="userName" name="userName" value="${userName}" readonly> 
				
			<!-- 유저 SSN 입력칸(회원가입 2단계에서 입력한 값을 통해 입력)(직접 입력 불가) -->
			<label for="userSSN1">주민등록번호</label>
				<div style="display: flex; gap: 5px;">
					<input type="text" id="userSSN1" name="userSSN1" value="${ssn1}" readonly> 
						&nbsp;&nbsp;-&nbsp;&nbsp; 
					<input type="password" id="userSSN2" name="userSSN2" value="${ssn2}" readonly>
				</div>

			<!-- 유저 성별 Select칸(반드시 입력)(기본값 남성) -->
			<label for="userGender">성별</label> 
				<select id="userGender" name="userGender" required>
					<option value="male" selected>남성</option>
					<option value="female">여성</option>
				</select> 
			
			<!-- 유저 닉네임 입력칸(최소 2글자 이상)(반드시 입력) -->
			<label for="nickname">유저 닉네임 (최소 2글자)</label> 
				<input type="text" id="nickname" name="nickname" maxlength="10" minlength="2" required>
				<!-- DB에 저장된 닉네임이 있을 경우, 문구 출력 --> 
				<span id="confirmNicknameError" style="color: red;"></span>
				<span id="loadingSpinner_for_nickname" class="loadingSpinner"></span>
			
			<!-- 유저 핸드폰 번호(반드시 입력)(첫 번째 3자리는 seleect)(두 번째/세 번째는 최대 4자리까지 직접 입력) -->
			<label for="userPhoneNum1">핸드폰 번호</label>
				<div style="display: flex; gap: 5px;">
					<select id="userPhoneNum1" name="userPhoneNum1" class="phoneNum" required>
				        <option value="010" selected>010</option>
				        <option value="011">011</option>
   					 </select> 
					<input type="text" id="userPhoneNum2" name="userPhoneNum2" class="phoneNum" required maxlength="4"> 
					<input type="text" id="userPhoneNum3" name="userPhoneNum3" class="phoneNum" required maxlength="4">
				</div>
				<!-- 핸드폰 번호란에 숫자만 입력되어 있지 않은 경우, 문구 출력 -->
				<span id="confirmPhoneNumError_notNumber" style="color: red;"></span>
				<!-- DB에 저장된 전체 핸드폰 번호가 일치하는 데이터가 있을 경우, 문구 출력 -->
				<span id="confirmPhoneNumError" style="color: red;"></span>
				<span id="loadingSpinner_for_PhoneNum" class="loadingSpinner"></span>

			<!-- 유저 이메일(반드시 입력)(select를 통해 도메인 입력 가능) -->
			<label for="userEmailDomain">이메일</label>
				<div style="display: flex; gap: 5px;">
					<input type="text" id="userEmailId" name="userEmailId" class ="userEmail" maxlength="30" required>
						@ 
					<input type="text" id="userEmailDomain" name="userEmailDomain" class ="userEmail" required> 
					<select id="domainSelect" name="domainSelect" class ="userEmail" onchange="domainSelectMethod(this.value)">
						<option id="domainDefault" value="" selected>직접 입력</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="hanmail.net">hanmail.net</option>
					</select>
				</div>
				<!-- 이메일 아이디에 영어나 숫자 외 다른 것이 입력될 경우, 문구 출력 -->
				<span id="confirmUserEmailIdError" style="color: red;"></span>
				<!-- DB에 저장된 이메인 아이디 + 이메일 도메인이 있을 경우, 문구 출력 -->
				<span id="confirmUserEmailError" style="color: red;"></span>
				<span id="loadingSpinner_for_Email" class="loadingSpinner"></span>
				
				<div style="display: flex; gap: 5px;">
					<input type="text" id="certification" name="certification" required="required">
					<input type="button" id="certificationBTN" value="인증번호 발송">
				</div>
				<span id = "certificationAnswer"></span>



			<button id="register_button" type="submit">가입</button>
		</form>
	</div>


	<script type="text/javascript">
	
		//페이지 로딩되면 기존 인증번호 쿠키 삭제
	   $(function(){
		   deleteCookeeFunction()
	    });

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
		
		//ID 새창 열기
		function openIdWindow() {
			var popup = window.open("<c:url value='/IdDupilicate'/>", "아이디 확인", "width=400,height=200");
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
		
		
		//phoneNum을 숫자로 입력 제한
		$(".phoneNum").on("input", function () {
		    
		    if (!/^\d*$/.test($(this).val())) {
		        alert("숫자만 입력 가능합니다.");
		        $(this).val("");
		        $(this).focus();
		    }
		});
		
		
		//두 번쨰 핸드폰 번호 4자리를 입력하면, 자동으로 세 번쨰 핸드폰 번호로 focus
		$("#userPhoneNum2").on('input', function() {
			var maxLength = 4;
			if ($(this).val().length >= maxLength) {
				$("#userPhoneNum3").val("")
				$(this).val($(this).val().slice(0, maxLength));
				$("#userPhoneNum3").focus();
			}
		});
		
		
		//핸드폰 번호 중복 확인
		var prevphoneNum = ""; 
		
		$(".phoneNum").on("focusout", function() {
		    var userPhoneNum1 = $("#userPhoneNum1").val();
		    var userPhoneNum2 = $("#userPhoneNum2").val();
		    var userPhoneNum3 = $("#userPhoneNum3").val();
		    var userPhoneNum = userPhoneNum1+userPhoneNum2+userPhoneNum3
		    var errorSpan = $("#confirmPhoneNumError");
		
		    //핸드폰 번호를 모두 입력했을 때 발동
		    if (userPhoneNum !== prevphoneNum) {
			    if (userPhoneNum1 && userPhoneNum2 && userPhoneNum3) {
			        $.ajax({
			            type: "POST",
			            url: "<c:url value='/AjaxPhoneNumDuplicate'/>", 
			            
			            beforeSend: function () {
		                    // AJAX 요청 전에 로딩 표시 보여주기
		                	$("#loadingSpinner_for_PhoneNum").show();
		                	// 가입 버튼 비활성화
		                	$("#register_button").prop("disabled", true);
		                	$("#userIdButton").prop("disabled", true);
		                },
			            
			            data: {
			            	userPhoneNum1: userPhoneNum1,
			            	userPhoneNum2: userPhoneNum2,
			            	userPhoneNum3: userPhoneNum3
			            },
			            success: function(response) {
			            	//핸드폰 번호 전체가 DB에 저장된 핸드폰 번호와 일치하는 데이터가 있을 경우, ajax 출력
			                if (response === "duplicate") {
			                	errorSpan.text("이미 사용 중인 핸드폰 번호입니다.");
			                } else {
			                	errorSpan.text("");
			                }
			            },
			            error: function(error) {
			                console.error("핸드폰 번호 중복 검사 에러:", error);
			            },
			            
			            complete: function () {
		                    // AJAX 요청 완료 후에 로딩 표시 숨기기
		                	$("#loadingSpinner_for_PhoneNum").hide();
		                	// 가입 버튼 활성화
			               	$("#register_button").prop("disabled", false);
			               	$("#userIdButton").prop("disabled", false);
		                }
			        })
			    } else {
			    	errorSpan.text("");
				};
			    prevphoneNum = userPhoneNum
		    } 
		});
		
		
		//도메인 readonly
		function domainSelectMethod(tv) {
			//직접 입력 토글이 아닐 때는 도메인을 select로만 입력할 수 있도록 지정
			var userEmailDomain = $("#userEmailDomain");
			if (tv == "") {
				userEmailDomain.val("").prop("readonly", false);
			} else {
				userEmailDomain.val(tv).prop("readonly", true);
			}
		}
		
		
	    // 이메일 아이디에 영어와 숫자만 입력되도록 제한
	    $("#userEmailId").on("input", function() {
	        var userEmailId = $(this).val();
	        var errorSpan = $("#confirmUserEmailIdError");

	        var pattern = /^[a-zA-Z0-9]+$/;		// 영어와 숫자만 허용

	        if (!pattern.test(userEmailId)) {	// 영어나 숫자 외 다른 것이 입력될 경우
	            errorSpan.text("영어와 숫자만 입력 가능합니다.");
	        } else {
	            errorSpan.text("");
	        }
	    });
		
	    
		//이메일 중복 확인(아이디/도메인을 입력할 경우)
		var prevUserEmail = ""; 
		
		$(".userEmail").on("focusout", function() {
		    var userEmailId = $("#userEmailId").val();
		    var userEmailDomain = $("#userEmailDomain").val();
		    var userEmail = userEmailId+userEmailDomain;
		    var errorSpan = $("#confirmUserEmailError");
		
		    //이메일 아이디와 이메일 도메인이 모두 있을 때 출력
		    if (userEmail !== prevUserEmail) {
			    if (userEmailId && userEmailDomain) {
			        $.ajax({
			            type: "POST",
			            url: "<c:url value='/AjaxEmailDuplicate'/>", 
			            
			            beforeSend: function () {
		                    // AJAX 요청 전에 로딩 표시 보여주기
		                	$("#loadingSpinner_for_Email").show();
		                	// 가입 버튼 비활성화
		                	$("#register_button").prop("disabled", true);
		                	$("#userIdButton").prop("disabled", true);
	                },
			            
			            data: {
			            	userEmailId: userEmailId,
			            	userEmailDomain: userEmailDomain,
			            },
			            success: function(response) {
			            	//이메일 아이디와 도메인이 DB에 저장된 값과 일치할 경우, ajax 출력
			                if (response === "duplicate") {
			                	errorSpan.text("이미 사용 중인 이메일입니다.");
			                } else {
			                	errorSpan.text("");
			                }
			            },
			            error: function(error) {
			                console.error("이메일 중복 검사 에러:", error);
			            }, 
			            
			            complete: function () {
		                    // AJAX 요청 완료 후에 로딩 표시 숨기기
		                	$("#loadingSpinner_for_Email").hide();
		                	// 가입 버튼 활성화
			               	$("#register_button").prop("disabled", false);
			               	$("#userIdButton").prop("disabled", false);
		                }
			            
			        });
			    } else {
			    	errorSpan.text("");
			    };
		    	prevUserEmail = userEmail
			}
		    
		    //인증 후 이메일 아이디 변경 시 발동
		    if ($("#certificationAnswer").text() == "확인되었습니다."){
		    	$("#certification").val("");
		    	$("#certificationAnswer").text("이메일이 변경되었습니다. 다시 인증해주세요.");
		    	deleteCookeeFunction();
		    }
		});
		
		
		//이메일 중복 확인(도메인 선택할 경우)
		$("#domainSelect").on("change", function() {
			var userEmailId = $("#userEmailId").val();
		    var userEmailDomain = $("#userEmailDomain").val();
		    var userEmail = userEmailId+userEmailDomain;
		    var errorSpan = $("#confirmUserEmailError");
		
		    //이메일 아이디와 이메일 도메인이 모두 있을 때 출력
		    if (userEmail !== prevUserEmail) {
			    if (userEmailId && userEmailDomain) {
			        $.ajax({
			            type: "POST",
			            url: "<c:url value='/AjaxEmailDuplicate'/>", 
			            data: {
			            	userEmailId: userEmailId,
			            	userEmailDomain: userEmailDomain,
			            },
			            success: function(response) {
			            	//이메일 아이디와 도메인이 DB에 저장된 값과 일치할 경우, ajax 출력
			                if (response === "duplicate") {
			                	errorSpan.text("이미 사용 중인 이메일입니다.");
			                } else {
			                	errorSpan.text("");
			                }
			            },
			            error: function(error) {
			                console.error("이메일 중복 검사 에러:", error);
			            }
			        });
			    } else {
			    	errorSpan.text("");
			    };
		    	prevUserEmail = userEmail
			}
		    
		    //인증 후 이메일 도메인  변경 시 발동
		    if ($("#certificationAnswer").text() == "확인되었습니다."){
		    	$("#certification").val("");
		    	$("#certificationAnswer").text("이메일이 변경되었습니다. 다시 인증해주세요.");
		    	deleteCookeeFunction();
		    }
		});
		
		
		
		//이메일 인증 번호 발송 에이젝스
		$("#certificationBTN").on("click", function() {
			var userEmailId = $("#userEmailId").val();
		    var userEmailDomain = $("#userEmailDomain").val();
		    var userEmail = userEmailId+"@"+userEmailDomain;
		    var errorSpan = $("#certificationAnswer");
		    var certification = $("#certification");
		    var userName = $("#userName").val();
		    
		    if($("#confirmUserEmailError").text() == "" && $("#confirmUserEmailIdError").text() == ""){
			    //이메일 아이디와 이메일 도메인이 모두 있을 때 출력
			    if (userEmailId && userEmailDomain) {
			        $.ajax({
			            type: "POST",
			            url: "<c:url value='/joinEmail'/>", 
			            data: {
			            	userEmail: userEmail,
			            	userName: userName
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
			    } else {
					alert("이메일을 입력해주세요.")
				    $("#certification").val("");
					errorSpan.text("");
					$("#userEmailId").focus();
			    }
		    } else {
		    	alert("중복된 이메일입니다.");
		    	$("#userEmailId").val("");
		    	$("#domainSelect").val("");
		    	domainSelectMethod("");
				$("#userEmailId").focus();
		    }   
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
			    if (userEmailId && userEmailDomain) {
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
			                } else {
			                	errorSpan.text("인증번호가 다릅니다. 확인해주세요.");
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
			    } else {
			    	errorSpan.text("");
			    };
			    prevCertification = certification
			}
		}

		//submit 제한규칙
		$("#registerForm").submit(function(event) {
			return validateForm(event);
		});
		
		function validateForm(event) {
			
		    var errorMessage = ""; // 에러 메시지를 저장할 변수

		    switch(true) {
		        case $("#userId").val() == "":
		            errorMessage = "아이디를 입력해주세요";
		            $("#userIdButton").focus();
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

		        case $("#confirmPhoneNumError").text() != "":
		            errorMessage = "핸드폰 번호 중복 여부를 확인해주세요";
		            $("#userPhoneNum2").focus();
		            break;

		        case $("#confirmUserEmailError").text() != "" && $("#confirmUserEmailIdError").text() != "":
		            errorMessage = "이메일을 재검토해주세요";
		            $("#userEmailId").focus();
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
