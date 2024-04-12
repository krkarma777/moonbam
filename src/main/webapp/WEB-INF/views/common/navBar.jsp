<%@page import="java.util.List"%>
<%@page import="com.moonBam.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");

	List<String> categoryList = (List<String>)request.getAttribute("categoryList");
	String category = (String)request.getAttribute("category");
	
	//카테고리에 따라 검색 action을 다르게
	String formAction = null;
	if("movie".equals(category)){
		formAction="movieSearch";
	}else if("community".equals(category)){
		formAction="communitySearch";
	}
%>
<style>
a{
	color: #ff416c;
	font-size: 30px;
	text-decoration: none;
}
</style>

<nav class=""
	style="background-color: transparent; position: fixed; top: 0; left: 0; height: 40px; width: 100%">
	<div class="d-flex container-fluid justify-content-center px-0">
		<div class="position-fixed top-0 start-0">
			<a href="/acorn">문밤</a>
		</div>
		<div style="width: 500px">
			<form class="d-flex " role="search" action="<%=formAction%>" method="post">
				<%if(null!=category){ %>
				<select name="searchCategory" class="form-select" style="width: 130px;">
					<%for(int i=0; i<categoryList.size(); i++){ 
					%>
						<option value="<%=categoryList.get(i) %>"><%=categoryList.get(i) %></option>
					<%} %>
				<%} %>
				</select> 
				<input name="searchValue" class="form-control me-1" type="search" placeholder="정보 입력" aria-label="Search">
				<button class="btn" style="background-color: #ff416c; color:white; opacity: 0.8;" type="submit"><b>Search</b></button>
			</form>
		</div>
		<div class="position-fixed top-0 end-0">
<<<<<<< HEAD
			<!-- 비로그인 -->
			<%if(null==dto){ %>
			<a href="Login">로그인</a>
			<%}else {%>
			<!-- 로그인 -->
			<a href="Logout">로그아웃</a>
			<a href="/acorn/userinfo">마이페이지</a>
			<%} %>
=======
				<!-- 비로그인 -->
			<%if (null == dto) {%>
				<a href="Login">로그인</a>
			<%} else {%>
				<!-- 로그인 -->
				<a href="Logout">로그아웃/</a><a href="MypageServlet">마이페이지</a>
			<%}%>
>>>>>>> branch 'community' of https://github.com/krkarma777/moonbam
			<!-- 관리자인 경우 -->
			<!-- <a href="Logout">로그아웃</a>
			<a href="AdminServlet">관리자페이지</a> -->
		</div>
	</div>
</nav>