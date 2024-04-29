
<%@page import="java.util.List"%>
<%@page import="com.moonBam.dto.MemberDTO"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<String> categoryList = (List<String>)request.getAttribute("categoryList");
	String category = (String)request.getAttribute("category");
	
	//카테고리에 따라 검색 action을 다르게
	String searchFormAction = null;
	if("movie".equals(category)){
		searchFormAction="movieSearch";
	}else if("community".equals(category)){
		searchFormAction="communitySearch";
	}else if("myChatList".equals(category)){
		searchFormAction="myChatList";
	}
	
%>
<style>
.top {
	color: #ff416c;
	font-size: 30px;
	text-decoration: none;
}
</style>

<nav class=""
	 style="background-color: transparent; position: fixed; top: 0; left: 0; height: 40px; width: 100%">
	<div class="d-flex container-fluid justify-content-center px-0">
		<div class="position-fixed top-0 start-0">
			<a href="/acorn" class="top">문밤</a>
		</div>

		<div style="width: 500px">
		<%if(null!=category){ %>
			<form class="d-flex " role="search" action="<%=searchFormAction%>" method="post">
				<select name="searchCategory" class="form-select" style="width: 130px;">
					<%for(int i=0; i<categoryList.size(); i++){ 
					%>
						<option value="<%=categoryList.get(i) %>"><%=categoryList.get(i) %></option>
					<%} %>
				</select> 
				<input name="searchValue" class="form-control me-1" type="search" 
						placeholder="<%if("movie".equals(category)){ %>영화 제목 입력<%}else{ %>정보입력<%} %>" aria-label="Search">
				<button class="btn" style="background-color: #ff416c; color:white; opacity: 0.8;" type="submit"><b>Search</b></button>
			</form>
		<%} %>
		</div>
		<div class="position-fixed top-0 end-0">

			<!-- 비로그인 -->
			<sec:authorize access="isAnonymous()">
				<a href="/acorn/mainLogin" class="top">로그인</a>
			</sec:authorize>
      		
      		<!-- 로그인 -->
			<sec:authorize access="isAuthenticated()">
				<a href="/acorn/logout"  class="top">로그아웃/</a>
				<a href="/acorn/my-page"  class="top">마이페이지</a>
			</sec:authorize>
			
			<!-- 관리자인 경우 -->
			<sec:authorize access="hasRole('ADMIN')">
                <a href="/acorn/AdminPage" class="top">/관리자페이지</a>
            </sec:authorize>
		</div>
	</div>
</nav>
