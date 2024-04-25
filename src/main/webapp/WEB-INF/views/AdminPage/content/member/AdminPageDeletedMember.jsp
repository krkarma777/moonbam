<%@ page import = "java.util.*" %>
<%@ page import = "com.moonBam.dto.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
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
	<h2 class="mt-4 mb-3">삭제된 회원데이터 관리</h2>
	<hr>
	<form action="<%=request.getContextPath()%>/AdminPage/toAdminPageDeletedMember" method="get" class="mb-3">
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
			<th>회원ID</th>
			<th>삭제 사유</th>
			<th>최종삭제일</th>
			<th>조치</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${list != null}">
			<c:forEach var="dto" items="${list}">
				<tr>
					<td>${dto.userId}</td>
					<td>${dto.cause}</td>
					<td>${dto.expdate}</td>
					<td>
						<button type="button" class="btn btn-custom">복원</button>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${list == null}">
			<tr>
				<td colspan="4">검색조건을 입력하십시오.</td>
			</tr>
		</c:if>
		</tbody>
	</table>
</div>
