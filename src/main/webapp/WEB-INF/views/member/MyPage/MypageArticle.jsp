<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.moonBam.dto.board.PostDTO"%>
<%@page import="com.moonBam.dto.MyPageDTO"%>
<%@page import="com.moonBam.dto.board.PageDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<%
	MyPageDTO mDTO = (MyPageDTO)request.getAttribute("mDTO");
	List<PostDTO> selectMyPostPaged = mDTO.getList();
	System.out.println("myPageArticle: ");
	for(int i=0; i<selectMyPostPaged.size(); i++){
		System.out.println(selectMyPostPaged.get(i));
	}
%>
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
            background-color: #ff416c; /* Red color for delete button */
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }
        * {
	padding: 0px;
	margin: 0px;
}
a{
	text-decoration: none;
}
button {
	border: 1.111;
	padding-top: 4; padding-bottom: 4;
	padding-left: 8; padding-right: 8;
}
.pagination{
	justify-content: center;
}
.pagination .page-link{
	border: none;
	color:black; 
	background-color: #ff416c; 
	opacity:0.8;
	color: white;
}
.pagination .page-item.active .page-link{
	color:black; 
	background-color: #ff416c;
	opacity:0.8; 
	color: black;
	border: none;
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
           
            <% if (selectMyPostPaged != null) { %>
                <% for(PostDTO dto : selectMyPostPaged) { %>
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
    
    <!-- 페이지네이션-->

            <jsp:include page="MyPagenation.jsp"></jsp:include>

    
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
