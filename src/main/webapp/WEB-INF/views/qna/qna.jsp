<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>내 문의</h1>
    <hr>
    <br>
    <br>
    <br>
    <br>
    <hr>
    <button>back</button><button onclick="toNewQNA()">new qna</button>

    <script type="text/javascript">
        
        function toNewQNA(){
            window.location.href = "<%=request.getContextPath()%>/newQNA";
        }
        
    </script>
</body>
</html>
