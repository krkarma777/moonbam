<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%> 
<%@page import="com.moonBam.dto.board.PostDTO"%> <!-- Replace "your.package.name" with your actual package name -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내 게시글</title>
    
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="resources/css/member/Main.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
    <style>
        .delBtn {
            background-color: #ff6961; /* Red color for delete button */
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div id="header">
        <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
             <jsp:include page = "MypageMenu.jsp"  flush ="true"></jsp:include>
    </div>
    <br>
    <br>
    <div style="text-align: center;">
        <h1>내 게시글</h1>
    </div>
    <br>
    <br>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Post ID</th>
                <th>Post Board</th>
                <th>Post Title</th>
                <th>Post Date</th>
                <th>Post Text</th>
                <th>Nickname</th>
                <th>Category ID</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            <% List<PostDTO> postList = (List<PostDTO>) request.getAttribute("postList"); %>
            <% if (postList != null) { %>
                <% for(PostDTO dto : postList) { %>
                    <tr>
                        <td><%= dto.getPostId() %></td>
                        <td><%= dto.getPostBoard() %></td>
                        <td><%= dto.getPostTitle() %></td>
                        <td><%= dto.getPostDate() %></td>
                        <td><%= dto.getPostText() %></td>
                        <td><%= dto.getNickname() %></td>
                        <td><%= dto.getCategoryId() %></td>
                        <td>
                            <button class="delBtn" data-xxx="<%=dto.getPostId()%>">삭제</button>
                        </td>
                    </tr>
                <% } %>
            <% } %>
        </tbody>
    </table>
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    
    <script type="text/javascript">
        $(document).ready(function() {
            $(".delBtn").on("click", function() {
                var postId = $(this).data("xxx");
                location.href = "ReviewDelServlet?postId=" + postId;
            });
        });
    </script>
</body>
</html>
