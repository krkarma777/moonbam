<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.moonBam.dto.board.PostDTO"%>
<%@ page import="java.util.List"%>

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
        <%@ include file="/WEB-INF/views/common/navibarForMember.jsp" %><br>
        <%@ include file="MypageMenu.jsp" %>
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
                        <td><%= dto.getCategoryId() %></td>
                        <td>
                            <form action="/acorn/postDel" method="post">
                                <input type="hidden" name="postId" value="<%=dto.getPostId()%>">
                                <button type="submit" class="delBtn">삭제</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            <% } %>
        </tbody>
    </table>
    
    <!-- 페이지네이션
    <div class="page-numbers text-center">
        <ul class="pagination"> -->
         <%--    <% int curPage = (Integer) request.getAttribute("curPage");
               int totalPage = (Integer) request.getAttribute("totalPage");
               int startPage = ((curPage - 1) / 10) * 10 + 1; // 시작 페이지 번호 계산
               int endPage = Math.min(startPage + 9, totalPage); // 끝 페이지 번호 계산

               // Calculate previous and next page numbers
               int prevPage = Math.max(startPage - 1, 1); // Ensure prevPage is never less than 1
               int nextPage = endPage + 1;
            %>--%>

            <%-- "이전" 버튼 
            <% if (curPage > 1) { %>
                <li class="page-item"><a class="page-link"
                    href="?curPage=<%=prevPage%>">&laquo; 이전 </a></li>
            <% } else { %>
                <li class="page-item disabled"><span class="page-link">&laquo; 이전</span></li>
            <% } %>--%>

            <%-- 페이지 번호 출력 
            <% for (int i = startPage; i <= endPage; i++) { %>
                <li class="page-item <%=i == curPage ? "active" : ""%>"><a class="page-link"
                    href="?curPage=<%=i%>"><%=i%></a></li>
            <% } %>--%>

            <%-- "다음" 버튼
            <% if (nextPage <= totalPage) { %>
                <li class="page-item"><a class="page-link"
                    href="?curPage=<%=nextPage%>">다음 &raquo; </a></li>
            <% } else { %>
                <li class="page-item disabled"><span class="page-link">다음 &raquo;</span></li>
            <% } %>
        </ul>
    </div>  --%>
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    
    <!-- Ajax로 삭제 기능 구현 -->
    <script type="text/javascript">
        $(document).ready(function() {
            $('#deleteForm').submit(function(e) {
                e.preventDefault(); // 폼 기본 동작 방지
                var form = $(this);
                $.ajax({
                    type: form.attr('method'),
                    url: form.attr('action'),
                    data: form.serialize(),
                    success: function(response) {
                        alert("게시물이 성공적으로 삭제되었습니다.");
                        window.location.href = "/myPost";
                    },
                    error: function(xhr, status, error) {
                        console.error(xhr.responseText);
                    }
                });
            });
        });
    </script>

</body>
</html>
