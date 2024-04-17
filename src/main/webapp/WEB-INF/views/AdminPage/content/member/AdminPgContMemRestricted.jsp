<%@ page import = "java.util.*" %>
<%@ page import = "com.moonBam.dto.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	.table {
		margin-top: 20px;
	}
	.table th, .table td {
		text-align: center;
		padding: 8px;
		border: 1px solid #ffccd5; /* Soft pink borders */
	}
	.header th {
		background-color: #ffccd5; /* Soft pink for header */
		color: black;
	}
	.btn-custom {
		color: #fff;
		background-color: #ff416c;
	}
	.btn-custom:hover {
		background-color: #ff416c;
	}

</style>
<div class="container">
	<h2 class="mt-4 mb-3">이용제한된 회원 관리</h2>
	<hr>
	<form action="<%=request.getContextPath()%>/AdminPage/RestrictedMemberList" method="post" class="mb-3">
		<div class="input-group mb-3">
			<select name="SearchCondition" class="form-select">
				<option value="userid">회원ID</option>
				<option value="nickName">회원닉네임</option>
				<option value="signdate">가입일</option>
				<option value="">상태</option>
			</select>
			<input type="text" class="form-control" placeholder="검색조건 입력" name="SearchValue">
			<button type="submit" class="btn btn-custom">검색</button>
		</div>
	</form>
	<table class="table table-bordered">
		<thead class="header">
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
		</thead>
		<tbody>
		<c:forEach var="dto" items="${list}">
			<tr>
				<td>${dto.actno}</td>
				<td>${dto.userid}</td>
				<td>${dto.status}</td>
				<td>${dto.cause}</td>
				<td>${dto.action}</td>
				<td>${dto.actionstart}</td>
				<td>${dto.actionend}</td>
				<td>
					<button type="button" class="btn btn-custom">해제</button>
					<button type="button" class="btn btn-custom">연장</button>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${list == null}">
			<tr>
				<td colspan="8">검색조건을 입력하십시오.</td>
			</tr>
		</c:if>
		</tbody>
	</table>
</div>
