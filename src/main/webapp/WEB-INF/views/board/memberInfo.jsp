<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문화인들의 밤</title>
    <link href="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f7f8fa;
            color: #333;
            min-height: 100vh;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            max-width: 600px;
            margin-top: 50px;
            background: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 15px 0 rgba(0, 0, 0, 0.1);
        }
        th {
            width: 30%;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .table-bordered th, .table-bordered td {
            border: 1px solid #dee2e6;
        }
        .table thead th {
            vertical-align: bottom;
            border-bottom: 2px solid #dee2e6;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="mb-4 text-center">회원 정보</h2>
    <table class="table table-bordered">
        <tbody>
        <tr>
            <th scope="row">아이디</th>
            <td>${member.userId}</td>
        </tr>
        <tr>
            <th scope="row">회원 ROLE</th>
            <td>${member.role}</td>
        </tr>
        <tr>
            <th scope="row">닉네임</th>
            <td>${member.nickname}</td>
        </tr>
        <tr>
            <th scope="row">가입일</th>
            <td>${member.userSignDate}</td>
        </tr>
        </tbody>
    </table>
</div>
<script src="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
