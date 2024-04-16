<%@ page import="com.moonBam.dto.CommentDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <!-- 메인 컨텐츠 시작 -->
        <div class="col-md-9 shadow">
            <div style="text-align: center;">
                <h1>내 댓글</h1>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Comment ID</th>
                        <th>Post ID</th>
                        <th>Comment Date</th>
                        <th>Comment Text</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cDTO.list}" var="comment">
                        <tr>
                            <td>${comment.comId}</td>
                            <td>${comment.postId}</td>
                            <td>${comment.comDate}</td>
                            <td>${comment.comText}</td>
                            <td><button class="btn btn-danger delBtn" data-xxx="${comment.comId}">삭제</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- 페이지네이션-->
            <div class="center-align">
            <jsp:include page="MyPagenationComm.jsp"/>
            </div>
        </div>
        <!-- 메인 컨텐츠 끝 -->
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
