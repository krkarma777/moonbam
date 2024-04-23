<%@ page import = "java.util.*" %>
<%@ page import = "com.moonBam.dto.AdminDeletedPostDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
	<meta charset="UTF-8">
	<title>MoonBam</title>
</head>
<body>
<%
System.out.println("삭제된 게시글 관리 jsp페이지"); 
%>
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
			<th>완전삭제예정일</th>
			<th>복원</th>
		</tr>
		<c:forEach var="dto" items="${list }">
		<tr>
			<td >${dto.postid}</td>
			<td >${dto.posttitle}</td>
			<td >${dto.userid}</td>
			<td >${dto.cause}</td>
			<td >${dto.expiredate}</td>
			<td><input type="button" value="복원"></td>
		</tr>
		</c:forEach>
	</table>
</form>

</body>
</html>