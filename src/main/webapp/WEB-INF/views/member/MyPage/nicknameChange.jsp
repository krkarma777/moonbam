<%--
  Created by IntelliJ IDEA.
  User: krkarma777
  Date: 2024-04-17
  Time: 오전 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>문화인들의 밤</title>
    <!-- Bootstrap CSS -->
    <link href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="/acorn/resources/css/myPage.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
<div class="container moonBam-container">
    <div class="row">
        <jsp:include page="sideBar.jsp" flush="true"/>

        <div class="shadow form-section">
            <!-- 닉네임 수정 폼 -->
            <form id="nicknameForm" action="<c:url value='/my-page/updateNickname'/>"   method="post">
                <div class="mb-3">
                    <p>현재 닉네임: ${loginUser.nickname}</p>
                    <label for="newNickname" class="form-label">새로운 닉네임 (최소 2글자)</label>
                    <input type="text" id="nickname" name="nickname" class="form-control" maxlength="10" minlength="2" required>
                    <span id="confirmNicknameError" style="color: red;"></span>
                </div>
                <div class="spinner-container" style="display:none;">
                    <span id="loadingSpinner_for_nickname" class="loadingSpinner"></span>
                </div>
                <button type="submit" id="updateNicknameButton" class="btn-outline">닉네임 수정</button>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
$(function() {
    var prevNickname = ""; // 이전에 입력된 닉네임 저장 변수

    // 닉네임 필드 포커스를 잃었을 때 이벤트 핸들러
    $("#nickname").on("focusout", function() {
        var nickname = $("#nickname").val();
        var errorSpan = $("#confirmNicknameError");
        var userId = "${loginUser.getUserId()}";
        // 이전에 입력된 닉네임과 다를 경우에만 AJAX 요청을 보냄
        if (nickname !== prevNickname) {
            $.ajax({
                type: "POST",
                url: "<c:url value='/AjaxNicknameDuplicate'/>",
                data: { nickname: nickname, userId: userId },

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
    $("#nicknameForm").on("submit", validateForm);
   
    // 수정된 닉네임 정보를 서버에 전송하는 함수
    function updateNickname() {
        var nickname = $("#nickname").val();
        var userId = "${loginUser.getUserId()}";
       
        $.ajax({
            type: "POST",
            url: "<c:url value='/updateNickname'/>",
            data: { nickname: nickname, userId: userId },
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
            $("#nickname").focus();
            break;

        default:
            // 모든 제약을 통과하면 submit
            $("#nicknameForm")[0].submit();
            return true;
    }

    // 에러 메시지가 있는 경우 경고창 출력
    alert(errorMessage);
    return false;
};
</script>
<script src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
