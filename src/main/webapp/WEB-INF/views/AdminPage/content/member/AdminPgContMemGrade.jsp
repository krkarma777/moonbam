<%@ page import = "java.util.*" %>
<%@ page import = "com.moonBam.dto.AdminMemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type = "text/javascript">
	<%
	List<AdminMemberDTO> list = (List<AdminMemberDTO>)request.getAttribute("list");
	System.out.println("in memberGrade" + list);
	%>
</script>
</head>
<body>
관리자페이지 회원등급관리Content
<hr>
<form action = "<%=request.getContextPath() %>/AdminMemberGradeServlet">
	<select name = "criteria">
		<option value = "userid" class = "criteria">회원ID</option>
		<option value = "nickName" class = "criteria">회원닉네임</option>
		<option value = "signdate" class = "criteria">가입일</option>
		<option value = "usertype" class = "criteria">상태</option>
	</select>
	<input type = "text" placeholder = "검색조건 입력"  id = "SearchValue" name = "SearchValue">
	<input type="submit" value = "검색"> 
</form>
	<hr>
	<table border = "1">
		<tr>
			<th>회원ID</th>
			<th>회원닉네임</th>
			<th>등급</th>
			<th>조치</th>
		</tr>
		<%
		if (list ==null){
		%>
		<tr>
		<td colspan = "5">검색조건을 입력하십시오</td>
		</tr>
		<%
		}else{
			for(AdminMemberDTO dto : list){
		%>
				<tr>
					<td><%=dto.getUserid() %></td>
					<td><%=dto.getNickname() %></td>
					<td><%=dto.getUsertype() %></td>
					<td><input type = "button" value = "승급"><input type = "button" value = "강등"><input type = "button" value = "직접입력"></td>
				</tr>
			<%} %>
		<%} %>
		<%session.removeAttribute("list"); %>	
	</table>

</body>
</html>