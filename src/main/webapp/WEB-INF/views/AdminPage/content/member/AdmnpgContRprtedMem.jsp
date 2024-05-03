<%@ page import="java.util.*" %>
<%@ page import="com.moonBam.dto.AdminReportDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<style>
		.container {
			padding-top: 20px;
		}
		table {
			width: 100%;
			background-color: #fff;
		}
		th, td {
			text-align: center;
		}
		.btn-pink {
			background-color: #ff416c; /* Bright pink color */
			color: #fff;
			border: none;
		}
		.btn-pink:hover {
			background-color: #ff6392;
		}
		.form-control:focus {
			border-color: #ff416c;
			box-shadow: 0 0 0 0.25rem rgba(255, 65, 108, 0.25);
		}
		.header {
			background-color: #ffccd5; /* Soft pink for header */
			color: #fff;
		}
	</style>
<div class="container">
	<h1 class="text-center">관리자 페이지: 신고된 회원 관리</h1>
	<hr>
	<form action="<%=request.getContextPath()%>/AdminPage/AdminMemberReported" method="post" class="mb-3">
		<div class="input-group mb-3">
			<select name="criteria" class="form-select">
				<option value="userid">회원ID</option>
				<option value="signdate">가입일</option>
				<option value="">처리상태</option>
			</select>
			<input type="text" class="form-control" placeholder="검색조건 입력" name="SearchValue">
			<button type="submit" class="btn btn-pink">검색</button>
		</div>
	</form>
	<table class="table table-bordered">
		<thead class="header">
		<tr>
			<th>&nbsp;</th>
			<th>신고대상</th>
			<th>음란물</th>
			<th>언어</th>
			<th>도배</th>
			<th>규정위반</th>
			<th>기타</th>
		</tr>
		</thead>
		<tbody>
		
		<c:if test="${list != null}">
			<c:forEach var="dto" items="${list}">
				<tr>
					<td><input type="checkbox" class="chkBox" data-xxx="${dto.userId}"></td>
					<td>${dto.userId}</td>
					<td>${dto.sexual}</td>
					<td>${dto.lang}</td>
					<td>${dto.abusing}</td>
					<td>${dto.ruleviolation}</td>
					<td>${dto.etc}</td>
					
				</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${list == null}">
			<tr>
				<td colspan="8">검색조건을 입력하십시오.</td>
			</tr>
		</c:if>
		
		</tbody>
	</table>
	<div>
		<button type="button" class="btn btn-pink" id="suspendChecked">정지</button>
		<button type="button" class="btn btn-pink" id="delChecked">강퇴</button>
	</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type = "text/javascript">

	$(document).ready(function(){
		
		$("#delChecked").on("click", delChecked);
		$("#suspendChecked").on("click", suspendChecked);
		
		function delChecked(){
			
			//디버깅용 확인
			console.log("chkArr");
			//삭제할 글id저장용 배열
			let dupUserArr = [];
			//체크된 글 id를 배열에 저장
			$(".chkBox:checked").each(function(i,e){
				dupUserArr.push($(this).attr("data-xxx"));
			});//each
			//확인
			console.log("dupUserArr: "+dupUserArr);
			
			//중복제거
			var userArr = [];
			for(userid of dupUserArr){
				if(!userArr.includes(userid)){
					userArr.push(userid);
				}	
			}
			
			//확인
			console.log("userArr: " + userArr);
	
			//전송
			var target = "<%=request.getContextPath()%>" + "/AdminPage/KickUser?userArr="+userArr;
			console.log(target);
			location.href = "<%=request.getContextPath()%>" + "/AdminPage/KickUser?userArr="+userArr;
		}//delChecked
		
		function suspendChecked(){
			
			//디버깅용 확인
			console.log("chkArr");
			//삭제할 글id저장용 배열
			let dupUserArr = [];
			//체크된 글 id를 배열에 저장
			$(".chkBox:checked").each(function(i,e){
				dupUserArr.push($(this).attr("data-xxx"));
			});//each
			//확인
			console.log("dupPostArr: "+dupUserArr);
			
			//중복제거
			var userArr = [];
			for(userid of dupUserArr){
				if(!userArr.includes(userid)){
					userArr.push(userid);
				}	
			}
			
			//확인
			console.log("userArr: " + userArr);
	
			//전송
			location.href = "<%=request.getContextPath()%>" + "/AdminPage/SuspendUser?userArr="+userArr;
		}//delChecked
		
	});//document
</script>
