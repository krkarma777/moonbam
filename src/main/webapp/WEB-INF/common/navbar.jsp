<%@page import="java.util.List"%>
<%@page import="com.moonBam.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<nav class="navbar navbar-expand" style="background-color:transparent;">
        <div class="container-fluid">
            <a class="navbar-brand" href="<c:url value='/'/>">문밤</a>
            <form class="d-flex" role="search">
                <input class="form-control me-1" type="search" placeholder="영화 정보 입력" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <ul class="navbar-nav">
            <c:choose>
                <c:when test="${not empty sessionScope.loginUser}">
                    <li class="nav-item"><a href="<c:url value='/logout'/>" class="nav-link">로그아웃</a></li>
                    <li class="nav-item"><a href="<c:url value='/MyPageServlet'/>" class="nav-link">마이페이지</a></li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item"><a href="<c:url value='/mainLogin'/>" class="nav-link">로그인</a></li>
                    <!-- <li class="nav-item"><a href="#" class="nav-link">회원가입</a></li> -->
                </c:otherwise>
            </c:choose>             
            </ul>
        </div>
</nav>

<!-- <nav class="" style="background-color:transparent; position:fixed; top:0; left:0; height: 50px; width:100%">
	<div class="d-flex container-fluid px-0">
		<div class="position-relative top-0 start-0"><a href="#">문밤</a></div>
		<form class="d-flex " role="search">
        	<input class="form-control me-1" type="search" placeholder="영화 정보 입력" aria-label="Search">
    		<button class="btn btn-outline-success" type="submit">Search</button>
    	</form>
    	<div class="position-fixed top-0 end-0"><a href="LoginServlet">로그인</a></div>
    </div>
</nav> -->