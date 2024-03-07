<%@page import="com.moonBam.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 정보</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%
MemberDTO member = (MemberDTO)request.getAttribute("member");
String userId=member.getUserId();
String userName=member.getUserName();
String userGender=member.getUserGender();
String nickname=member.getNickname();
String userSignDate=member.getUserSignDate();
%>

<div class="container mt-5">
    <h2 class="mb-4">회원 정보</h2>
    <table class="table table-bordered">
        <tbody>
            <tr>
                <th scope="row">아이디</th>
                <td><%= userId %></td>
            </tr>
            <tr>
                <th scope="row">이름</th>
                <td><%= userName %></td>
            </tr>
            <tr>
                <th scope="row">성별</th>
                <td><%= userGender %></td>
            </tr>
            <tr>
                <th scope="row">닉네임</th>
                <td><%= nickname %></td>
            </tr>
            <tr>
                <th scope="row">가입일</th>
                <td><%= userSignDate %></td>
            </tr>
        </tbody>
    </table>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
