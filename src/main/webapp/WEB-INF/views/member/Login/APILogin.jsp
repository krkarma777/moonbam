<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>문화인들의 밤</title>
    <link href="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>

<div id="header">
    <jsp:include page="/WEB-INF/views/common/navBar.jsp" flush="true"></jsp:include><br>
</div>

    <div class="container mt-5">
        <div class="card">
            <div class="card-body">
                <h3 class="card-title">닉네임을 변경하시겠습니까?</h3>
                <form action="<c:url value='/changeNickname'/>" method="post">
                    <input type="hidden" name="userId" value="${userId}">
                    <input type="text" id="nickname" name="nickname" minlength="2" required autofocus="autofocus" class="form-control mb-3">
                    <span id="confirmNicknameError" style="color: red;"></span>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
                    	<input type="button" id="autoNickname" name="clickType" value="자동 닉네임 생성" class="btn btn-success me-md-2">
                        <input type="submit" name="clickType" value="변경하기" class="btn btn-primary me-md-2">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script type="text/javascript">

	  	//새로고침, 뒤로가기, 나가기 시 경고창 함수
		function f5Control(event){
			event.preventDefault();
		    event.returnValue = '';
		}
    
    	$(function(){
    		
			//새로고침, 뒤로가기, 나가기 시 경고창 함수 출력
			window.addEventListener('beforeunload', f5Control);
			
			//뒤로가기 단축키을 누르면 로그인 메인으로 이동(Alt + <- 기능)(브라우저 뒤로가기 버튼은 막히지 않음)
			window.history.pushState(null, null, window.location.href);
			window.onpopstate = function(event) {
				window.history.pushState(null, null, window.location.href);
				window.location.href= "<c:url value='/'/>"; 
			};
   		    
			$(".btn").on("click", function(){
				window.removeEventListener('beforeunload', f5Control);
	 		})
        	
        	//자동 닉네임 생성기
        	$("#autoNickname").on("click", function(){
        		var nicknameSpace = $("#nickname");
                var errorSpan = $("#confirmNicknameError");
        		$.ajax({
                    type: "POST",
                    url: "<c:url value='/randomNickname'/>", 
                    success: function (response) {
                    	nicknameSpace.val(response);
                        errorSpan.text("");
                    },
                    error: function (error) {
                        console.error("닉네임 자동 생성기 에러:", error);
                    }
                })
        	})
        	
        	
            //닉네임 중복 확인
            var prevNickname = "";
            $("#nickname").on("focusout", function() {
                var nickname = $("#nickname").val();
                var errorSpan = $("#confirmNicknameError");
                var check = $("#check").val()

                if(check !== nickname){
                    if (nickname !== prevNickname) {
                        $.ajax({
                            type: "POST",
                            url: "<c:url value='/AjaxNicknameDuplicate'/>",
                            data: { nickname: nickname },
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
                            }
                        })
                        prevNickname = nickname;
                    };
                };
            });

            //닉네임 중복일 경우, 이벤트 중지
            $("form").on("submit", function(){
                if($("#confirmNicknameError").text() == "이미 사용 중인 닉네임입니다."){
                    event.preventDefault();
                    alert("중복된 닉네임입니다.");
                } else {
                    $("form")[0].submit();
                }
            })
        })
    </script>

    <script src="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
