<%@page import="com.moonBam.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
    nav {
        background-color: gray;
    }

</style>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
  
  	<!-- 메인 -->
    <a class="navbar-brand" href="<c:url value='/'/>">메인</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
		
		<!-- 로그인메인 -->
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/Login'/>">로그인창</a>
        </li>

		<!-- 익명게시판 -->        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">익명 게시판(정렬🤮🤮🤮)</a>

          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
			<li><a class="dropdown-item" href="<c:url value='/viewDBoardList'/>">메인</a></li>
			<li><hr class="dropdown-divider"></li>
			
			<li><a class="dropdown-item" href="<c:url value='/viewDBoardList?orderBy=viewCount'/>">인기글</a></li>
			<li><a class="dropdown-item" href="<c:url value='/viewDBoardList?orderBy=recommendNum'/>">추천글</a></li>
			
			<li><hr class="dropdown-divider"></li>
			<li><a class="dropdown-item" href="<c:url value='/viewDBoardList?orderBy=정보'/>">정보</a></li>
			<li><a class="dropdown-item" href="<c:url value='/viewDBoardList?orderBy=문제'/>">문제</a></li>
			<li><a class="dropdown-item" href="<c:url value='/viewDBoardList?orderBy=질문'/>">질문</a></li>
			<li><a class="dropdown-item" href="<c:url value='/viewDBoardList?orderBy=정리'/>">정리</a></li>
			<li><a class="dropdown-item" href="<c:url value='/viewDBoardList?orderBy=잡담'/>">잡담</a></li>
          </ul>
        </li>
      </ul>

      <ul class="navbar-nav">
      	<!-- 회원리스트 -->
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/memberList'/>">회원리스트</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
