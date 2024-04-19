<%@ page import="com.moonBam.dto.board.PostDTO" %>
<%@ page import="com.moonBam.dto.MyPageDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: krkarma777
  Date: 2024-04-17
  Time: 오전 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MyPageDTO mDTO = (MyPageDTO) request.getAttribute("mDTO");
    List<PostDTO> selectMyPostPaged = mDTO.getList();
    System.out.println("myPageArticle: ");
    for (int i = 0; i < selectMyPostPaged.size(); i++) {
        System.out.println(selectMyPostPaged.get(i));
    }
%>
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
<div class="container moonBam-container">
    <div class="row">
        <jsp:include page="sideBar.jsp" flush="true"/>

        <!-- 메인 컨텐츠 시작 -->
        <div class="shadow">
            <div style="text-align: center;">
                <h1>내 게시글</h1>
            </div>
            <table class="table table-custom">
                <thead>
                <tr>
                    <th>Post ID</th>
                    <th>Post Board</th>
                    <th>Post Title</th>
                    <th>Post Date</th>
                    <th>Post Text</th>
                    <th>Category ID</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${mDTO.list}" var="post">
                    <tr>
                        <td>${post.postId}</td>
                        <td>${post.postBoard}</td>
                        <td>${post.postTitle}</td>
                        <td><fmt:formatDate value="${post.postDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>${post.postText}</td>
                        <td>${post.categoryId}</td>
                        <td>
                            <form action="/acorn/postDel" method="post">
                                <input type="hidden" name="postId" value="${post.postId}">
                                <button type="submit" class="btn btn-danger delBtn">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 페이지네이션-->
            <div class="center-align">
                <jsp:include page="MyPagenation.jsp"/>
            </div>
        </div>
        <!-- 메인 컨텐츠 끝 -->
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
