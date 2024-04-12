<%@ page import = "java.util.*" %>
<%@ page import = "com.moonBam.dto.AdminDeletedPostDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MoonBam</title>
</head>
<body>
<%List<AdminDeletedPostDTO> list = (List<AdminDeletedPostDTO>)request.getAttribute("list"); %>
<%System.out.println("in jsp"); %>
<%System.out.println(list); %>
<h1>삭제된 게시글 관리</h1>
<hr>
<form action = "<%=request.getContextPath() %>/AdminPage/AdminPageDeletedPost">
	<select name = "Criteria">
		<option value = "" class = "SearchStandard">글 아이디</option>
		<option value = "" class = "SearchStandard">글 제목</option>
		<option value = "" class = "SearchStandard">작성자</option>
		<option value = "" class = "SearchStandard">삭제사유</option>
		<option value = "" class = "SearchStandard">처분여부</option>
		<option value = "" class = "SearchStandard">완전삭제예정일</option>
	</select>
	<input type = "text" placeholder = "검색조건 입력"  id = "SearchValue" name = "SearchValue">
	<input type="submit" value = "검색"> 
	
	<hr>
	<table border = "1" width = "70%">
		<tr>
			<th >Post ID</th>
			<th >글 제목</th>
			<th >작성자</th>
			<th>삭제사유</th>
			<th>처분여부</th>
			<th>완전삭제예정일</th>
		</tr>
	</table>
</form>

</body>
</html>