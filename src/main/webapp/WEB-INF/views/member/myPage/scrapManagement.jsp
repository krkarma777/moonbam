<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <title>문화인들의 밤</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/acorn/resources/css/myPage.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
<div class="container moonBam-container">
    <div class="row">
        <jsp:include page="sideBar.jsp" flush="true"/>

        <div class="col-md-9">
            <h1 class="mt-4">내 스크랩</h1>
            <table class="table">
                <thead>
                <tr>
                    <th>Scrap ID</th>
                    <th>Post ID</th>
                    <th>User ID</th>
                    <th>Scrap Date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${scrapDTOs}" var="scrap">
                    <tr>
                        <td>${scrap.scrapId}</td>
                        <td>${scrap.postId}</td>
                        <td>${scrap.userId}</td>
                        <td><fmt:formatDate value="${scrap.scrapDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                                <a href="/acorn/my-page/scrap/${scrap.scrapId}" class="btn btn-danger">삭제</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
