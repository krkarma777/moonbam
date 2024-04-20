<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 탈퇴</title>
</head>
<body>
    <h1>회원 탈퇴</h1>
    <form:form action="${pageContext.request.contextPath}/withdraw/confirm" method="post" modelAttribute="withdrawForm">
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <label for="confirmPassword">비밀번호 확인:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
        <br>
        <input type="submit" value="탈퇴">
    </form:form>
</body>
</html>
