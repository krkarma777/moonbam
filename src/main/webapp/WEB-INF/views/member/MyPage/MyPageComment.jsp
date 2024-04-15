<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.moonBam.dto.CommentDTO"%>
<%@page import="com.moonBam.dto.MyCommentDTO"%>
<%@page import="com.moonBam.dto.board.PageDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

<%
MyCommentDTO cDTO = (MyCommentDTO)request.getAttribute("cDTO");
List<CommentDTO> selectmyComm = cDTO.getList();
System.out.println("myPageComment: ");
for(int i=0; i<selectmyComm.size(); i++){
	System.out.println(selectmyComm.get(i));
}
%>
<div id="header">
        <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
             <jsp:include page = "MypageMenu.jsp"  flush ="true"></jsp:include>
    </div>
    <br>
<br>
<br>
<div style="text-align: center;">
    <h1> 내 댓글</h1>
    <br>
    <br>
    <table class="table table-striped" border="1">
        <tr>
            <th>Comment ID</th>
            <th>Post ID</th>
            <th>Comment Date</th>
            <th>Comment Text</th>
            <th>Delete</th>
        </tr>

        <%
            for (int i = 0; i < selectmyComm.size(); i++) {
                CommentDTO dto = selectmyComm.get(i);
                int comId = dto.getComId();
                Long postId = dto.getPostId();
                String comDate = dto.getComDate();
                String comText = dto.getComText();
        %>

        <tr>
            <td><%= comId %></td>
            <td><%= postId %></td>
            <td><%= comDate %></td>
            <td><%= comText %></td>
            <td>
                <input type="button" value="삭제" class="delBtn" data-xxx="<%=comId%>">
            </td>
        </tr>

        <%
            }
        %>
    </table>
    
</div>
  <!-- 페이지네이션-->

            <jsp:include page="MyPagenationComm.jsp"></jsp:include>
<!-- 스타일 추가 -->
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".delBtn").on("click", function() {
            var comId = $(this).attr("data-xxx");
            location.href = "CommentDelServlet?comId=" + comId;
        });
    });
</script>
