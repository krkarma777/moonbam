<%@ page import = "java.util.*" %>
<%@ page import = "com.moonBam.dto.AdminMemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type = "text/javascript">
	<%
	List<AdminMemberDTO> list = (List<AdminMemberDTO>)request.getAttribute("list");
	System.out.println("in memberGrade" + list);
	%>
</script>
<style>
	/* Custom CSS for Admin Member Grade Management */
	.admin-grade-management {
		color: #333;
		padding: 20px;
	}

	.admin-grade-management table {
		width: 100%;
		background-color: #fff;
		border-collapse: collapse;
	}

	.admin-grade-management th,
	.admin-grade-management td {
		text-align: center;
		padding: 8px;
		border: 1px solid #ffccd5; /* Soft pink borders */
	}

	.admin-grade-management .btn-pink {
		background-color: #ff416c; /* Bright pink color */
		color: #fff;
		border: none;
		margin-right: 5px;
	}

	.admin-grade-management .btn-pink:hover {
		background-color: #ff6392;
	}

	.admin-grade-management .form-control:focus {
		border-color: #ff416c;
		box-shadow: 0 0 0 0.25rem rgba(255, 65, 108, 0.25);
	}

	.admin-grade-management .header {
		background-color: #ffccd5; /* Soft pink for header */
		color: #fff;
	}

</style>
<div class="admin-grade-management">
	<h1>회원 등급 관리</h1>
	<hr>
	<form action="<%=request.getContextPath()%>/AdminMemberGradeServlet" method="post">
		<div class="input-group mb-3">
			<select name="criteria" class="form-select">
				<option value="userid">회원ID</option>
				<option value="nickName">회원닉네임</option>
				<option value="signdate">가입일</option>
				<option value="usertype">상태</option>
			</select>
			<input type="text" class="form-control" placeholder="검색조건 입력" name="SearchValue">
			<button type="submit" class="btn btn-pink">검색</button>
		</div>
	</form>
	<table>
		<thead class="header">
		<tr>
			<th>회원ID</th>
			<th>회원닉네임</th>
			<th>등급</th>
			<th>조치</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="dto" items="${list}">
			<tr>
				<td>${dto.userid}</td>
				<td>${dto.nickname}</td>
				<td>${dto.usertype}</td>
				<td>
					<button class="btn btn-pink">승급</button>
					<button class="btn btn-pink">강등</button>
					<button class="btn btn-pink">직접입력</button>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${list == null}">
			<tr>
				<td colspan="4">검색조건을 입력하십시오.</td>
			</tr>
		</c:if>
		</tbody>
	</table>
</div>
