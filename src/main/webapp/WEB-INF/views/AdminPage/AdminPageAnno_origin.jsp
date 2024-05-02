<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- 시작화면 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>문화인들의 밤</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- Bootstrap CSS -->
    <link href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="/acorn/resources/css/myPage.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
<div class="moonBam-container">
    <jsp:include page="common/AdminMenu.jsp" flush="true"/>

    <div class="shadow">
        <jsp:include page="content/announce/AdminpgContAnno.jsp" flush="true"/>
    </div>
</div>
<!-- Bootstrap JS -->
<script src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
