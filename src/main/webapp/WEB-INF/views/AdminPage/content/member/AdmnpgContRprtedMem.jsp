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

/* Parsing List */
	<%List<AdminRprtdDTO> list = (List<AdminRprtdDTO>)request.getAttribute("list");
	System.out.println("in Rprtedmember.jsp" + list);%>
</script>
</head>
<body>
관리자페이지 신고된 회원관리 Content
<hr>
<form action = "<%=request.getContextPath() %>/AdminPage/AdminMemberReported">

	<!-- 검색기능 -->
	<select name = "criteria">
		<option value = "userid" class = "criteria">회원ID</option>
		<option value = "signdate" class = "criteria">가입일</option>
		<option value = "" class = "criteria">처리상태</option>
	</select>
	<input type = "text" placeholder = "검색조건 입력"  id = "SearchValue" name = "SearchValue">
	<input type="submit" value = "검색"> 
	<hr>
	
	<!-- 리스트 출력 -->
	<table border = "1">
		<tr>
			<th rowspan = "2">&nbsp;&nbsp;&nbsp;&nbsp;</th>
			<th rowspan = "2">신고대상</th>
			<th colspan = "5">신고건수</th>
		</tr>
		<tr>
			<th>음란물</th>
			<th>언어</th>
			<th>도배</th>
			<th>규정위반</th>
			<th>기타</th>
		</tr>
		<%
		
		/* 리스트 없으면 검색조건 입력하시오  */
		
		if (list ==null){
		%>
		<tr>
		<td colspan = "7">검색조건을 입력하십시오</td>
		</tr>
		<%
		}else{
			/* 리스트 있으면 출력 */
			for(AdminRprtdDTO dto : list){
		%>
				<tr>
					<td><input type = "checkbox"></td>
					<td><%=dto.getUserid() %></td>
					<td><%=dto.getSexual() %></td>
					<td><%=dto.getLang() %></td>
					<td><%=dto.getAbusing() %></td>
					<td><%=dto.getRuleviolation() %></td>
					<td><%=dto.getEtc() %></td>
					<td><input type = "button" value = "정지"><input type = "button" value = "강퇴"></td>
				</tr>
			<%} %>
		<%} %>	
	</table>
</form>
<input type = "button" value = "강등">
<input type = "button" value = "정지">
<input type = "button" value = "강퇴">
</body>
</html>