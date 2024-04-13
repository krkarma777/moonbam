<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class=""
	 style="background-color: transparent; position: fixed; top: 0; left: 0; height: 40px; width: 100%">
	<div class="d-flex container-fluid justify-content-center px-0">
		<div class="position-fixed top-0 start-0">
			<a href="/acorn">문밤</a>
		</div>
		<div class="">
			<form class="d-flex " role="search">
				<input class="form-control me-1" type="search"
					   placeholder="정보 입력" aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</div>
		<div class="position-fixed top-0 end-0">
			<!-- 비로그인 -->
			<sec:authorize access="isAnonymous()">
				<a href="Login">로그인</a>
			</sec:authorize>
			<!-- 로그인 -->
			<sec:authorize access="isAuthenticated()">
				<a href="Logout">로그아웃</a>
				<a href="/acorn/userinfo">마이페이지</a>
			</sec:authorize>
			<!-- 관리자인 경우 -->
			<!-- <sec:authorize access="hasRole('ADMIN')">
                <a href="Logout">로그아웃</a>
                <a href="AdminServlet">관리자페이지</a>
            </sec:authorize> -->
		</div>
	</div>
</nav>
