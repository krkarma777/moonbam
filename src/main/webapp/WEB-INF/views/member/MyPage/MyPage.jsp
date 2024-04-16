<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>문밤</title>
    
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="resources/css/member/Main.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
</head>
<body>

<div id="header">
    <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
</div>

<div class="container">
    <h1>유저 정보 수정</h1>

  <!-- 닉네임 수정 폼 -->
<form id="nicknameForm" action="<c:url value='/updateNickname'/>" method="post">
    <!-- 기존 닉네임 출력 -->
    <p>현재 닉네임: ${loginUser.nickname}</p>
 
    <!-- 새로운 닉네임 입력 -->
    <label for="newNickname">새로운 닉네임 (최소 2글자)</label>
    <input type="text" id="newNickname" name="newNickname" maxlength="10" minlength="2">
    <span id="confirmNicknameError" style="color: red;"></span>
    <span id="loadingSpinner_for_nickname" class="loadingSpinner"></span>

    <!-- 닉네임 수정 버튼 -->
    <button type="button" id="updateNicknameButton">닉네임 수정</button>
</form>


</div>

<script type="text/javascript">
$(function() {
    var prevNickname = ""; // 이전에 입력된 닉네임 저장 변수

    // 닉네임 필드 포커스를 잃었을 때 이벤트 핸들러
    $("#newNickname").on("focusout", function() { // 수정: #nickname -> #newNickname
        var nickname = $("#newNickname").val(); // 수정: #nickname -> #newNickname
        var errorSpan = $("#confirmNicknameError");
        var userId = "${loginUser.getUserId()}";
        // 이전에 입력된 닉네임과 다를 경우에만 AJAX 요청을 보냄
        if (nickname !== prevNickname) {
            $.ajax({
                type: "POST",
                url: "<c:url value='/AjaxNicknameDuplicate'/>",
                data: { newNickname: nickname, userId: userId }, // 수정: nickname -> newNickname

                beforeSend: function() {
                    // AJAX 요청 전에 로딩 표시 보여주기
                    $("#loadingSpinner_for_nickname").show();
                    // 가입 버튼 비활성화
                    $("#register_button").prop("disabled", true);
                    $("#userIdButton").prop("disabled", true);
                },

                success: function(response) {
                    // 닉네임이 DB에 저장된 닉네임과 일치하는 데이터가 있을 경우, ajax 출력
                    if (response === "duplicate") {
                        errorSpan.text("이미 사용 중인 닉네임입니다.");
                    } else {
                        errorSpan.text("");
                    }
                },
                error: function(error) {
                    console.error("닉네임 중복 검사 에러:", error);
                },

                complete: function() {
                    // AJAX 요청 완료 후에 로딩 표시 숨기기
                    $("#loadingSpinner_for_nickname").hide();
                    // 가입 버튼 활성화
                    $("#register_button").prop("disabled", false);
                    $("#userIdButton").prop("disabled", false);
                }
            });
            prevNickname = nickname; // 이전 닉네임 값을 갱신
        }
    });

    // 닉네임 수정 버튼 클릭 시 실행되는 함수
    $("#updateNicknameButton").on("click", function() {
        updateNickname();
    });

   
    // 수정된 닉네임 정보를 서버에 전송하는 함수
    function updateNickname() {
        var newNickname = $("#newNickname").val(); // 수정: #nickname -> #newNickname
        var userId = "${loginUser.getUserId()}";
        $.ajax({
            type: "POST",
            url: "<c:url value='/updateNickname'/>",
            data: { newNickname: newNickname, userId: userId },
            success: function(response) {
                alert("닉네임이 성공적으로 수정되었습니다.");
                // 수정된 정보를 화면에 반영할 수 있도록 필요한 작업 수행
            },
            error: function(error) {
                console.error("닉네임 수정 에러:", error);
            }
        });
    }

   
});


function validateForm(event) {
    var errorMessage = ""; // 에러 메시지를 저장할 변수

    switch(true) {
        case $("#confirmNicknameError").text() != "":
            errorMessage = "닉네임 중복 여부를 확인해주세요";
            $("#newNickname").focus(); // 수정: #nickname -> #newNickname
            break;

        default:
            // 모든 제약을 통과하면 submit
            $("#restoreEmailForm")[0].submit();
            return true;
    }

    // 에러 메시지가 있는 경우 경고창 출력
    alert(errorMessage);
    return false;
};
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
