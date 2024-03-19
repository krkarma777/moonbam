<%@ page import = "java.util.*" %>
<%@ page import = "com.moonBam.dto.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type = "text/javascript">
	<%
	List<AdminRestrictedMemberDTO> list = (List<AdminRestrictedMemberDTO>)request.getAttribute("list"); 
	 /* System.out.println("in jsp : " + list); */%> 
</script>
</head>
<body>
관리자페이지 이용제한된 회원관리Content
<hr>
<form action = "<%=request.getContextPath() %>/AdminPage/RestrictedMemberList">
	<select name = "SearchCondition">
		<option value = "userid" class = "SearchStandard">회원ID</option>
		<option value = "nickName" class = "SearchStandard">회원닉네임</option>
		<option value = "signdate" class = "SearchStandard">가입일</option>
		<option value = "" class = "SearchStandard">상태</option>
	</select>
	<input type = "text" placeholder = "검색조건 입력"  id = "SearchValue" name = "SearchValue">
	<input type="submit" value = "검색"> 
	<hr>
	<table border = "1">
		<tr>
			<th>제재번호</th>
			<th>회원ID</th>
			<th>상태</th>
			<th>이용제한 사유</th>
			<th>처분</th>
			<th>처분시작일</th>
			<th>처분종료일</th>
			<th>조치</th>
		</tr>
		<%
		if (list ==null){
		%>
		<tr>
		<td colspan = "7">검색조건을 입력하십시오</td>
		</tr>
		<%
		}else{
			for(AdminRestrictedMemberDTO dto : list){
		%>
				<tr>
					<td><%=dto.getActno() %></td>
					<td><%=dto.getUserid() %></td>
					<td><%=dto.getStatus() %></td>
					<td><%=dto.getCause() %></td>
					<td><%=dto.getAction() %></td>
					<td><%=dto.getActionstart() %></td>
					<td><%=dto.getActionend() %></td>
					<td><input type = "button" value = "해제"><input type = "button" value = "연장"></td>
				</tr>
			<%} %>
		<%} %>
	</table>
</form>
</body>
</html>