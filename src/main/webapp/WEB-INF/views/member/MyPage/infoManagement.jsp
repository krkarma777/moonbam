<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <br>
        <div class="center-align">
            <!-- 메인 컨텐츠 시작 -->
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        회원 정보 수정
                    </div>
                    <div class="card-body">
                        <form action="updateProfile" method="post">
                            <div class="mb-3">
                                <label for="userId" class="form-label">사용자 ID (읽기 전용)</label>
                                <input type="text" class="form-control" id="userId" name="userId"
                                       value="${loginUser.userId}" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="nickname" class="form-label">닉네임(읽기 전용)</label>
                                <input type="text" class="form-control" id="nickname" name="nickname"
                                       value="${loginUser.nickname}" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="secretCode" class="form-label">비밀번호 복구 코드 (수정 불가)</label>
                                <input type="text" class="form-control" id="secretCode" name="secretCode"
                                       value="${loginUser.secretCode}" disabled>
                            </div>
                            <div class="mb-3 form-check form-switch">
                                <input type="checkbox" class="form-check-input" id="googleConnected"
                                       name="googleConnected" ${loginUser.googleConnected == 1 ? 'checked' : ''}
                                       disabled>
                                <label class="form-check-label" for="googleConnected">구글 연동됨</label>
                            </div>
                            <div class="mb-3 form-check form-switch">
                                <input type="checkbox" class="form-check-input" id="naverConnected"
                                       name="naverConnected" ${loginUser.naverConnected == 1 ? 'checked' : ''} disabled>
                                <label class="form-check-label" for="naverConnected">네이버 연동됨</label>
                            </div>
                            <div class="mb-3 form-check form-switch">
                                <input type="checkbox" class="form-check-input" id="kakaoConnected"
                                       name="kakaoConnected" ${loginUser.kakaoConnected == 1 ? 'checked' : ''} disabled>
                                <label class="form-check-label" for="kakaoConnected">카카오 연동됨</label>
                            </div>
                        </form>
                        
                      <form action=<c:url value='/my-page/updatePwd'/> method="Post" >
                       <input type="submit" class="btn btn-primary" value="비밀번호 변경">
                       </form>
                       <form action=<c:url value='/my-page/withdraw'/> method="Post" >
                       <input type="submit" class="btn btn-primary" value="회원 탈퇴">
                       </form>
                    </div>
                </div>
            </div>
            <!-- 메인 컨텐츠 끝 -->
        </div>
    </div>

</div>

<script src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
