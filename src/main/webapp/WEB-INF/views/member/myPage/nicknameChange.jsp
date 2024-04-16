<%--
  Created by IntelliJ IDEA.
  User: krkarma777
  Date: 2024-04-17
  Time: 오전 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>마이페이지</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="/acorn/resources/css/myPage.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
<div class="container mt-4">
    <div class="row">
        <jsp:include page="sideBar.jsp" flush="true"/>

        <div class="col-md-9 shadow form-section center-align">
            <!-- 닉네임 수정 폼 -->
            <form id="nicknameForm" action="<c:url value='/updateNickname'/>" method="post">
                <div class="mb-3">
                    <p>현재 닉네임: ${loginUser.nickname}</p>
                    <label for="newNickname" class="form-label">새로운 닉네임 (최소 2글자)</label>
                    <input type="text" id="newNickname" name="newNickname" class="form-control" maxlength="10" minlength="2" required>
                    <span id="confirmNicknameError" style="color: red;"></span>
                </div>
                <div class="spinner-container" style="display:none;">
                    <span id="loadingSpinner_for_nickname" class="loadingSpinner"></span>
                </div>
                <button type="button" id="updateNicknameButton" class="btn-outline">닉네임 수정</button>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
