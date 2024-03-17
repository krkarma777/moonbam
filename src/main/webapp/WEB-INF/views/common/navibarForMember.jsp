<%@page import="com.moonBam.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
    nav {
        background-color: gray;
    }

</style>

<nav class="navbar navbar-expand" style="background-color:transparent;">
        <div class="container-fluid">
            <a class="navbar-brand" href="<c:url value='/'/>">메인</a>
            <a class="navbar-brand" href=<c:url value='/Login'/>>로그인창</a>
            <a class="navbar-brand" href=<c:url value='/viewDBoardList'/>>디버그 익명 게시판(정렬🤮🤮🤮)</a>
            <a class="navbar-brand" href=<c:url value='/memberList'/>>회원리스트(디버그)</a>
        </div>
</nav>