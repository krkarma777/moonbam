<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 시작화면 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type = "text/javascript">
</script>
</head>
<body>
<div style = "">
</div>

<div style="display: flex;">

    <!-- Include menu.jsp on the left side -->
    <div style="width: 200px;">
        <jsp:include page = "MypageMenu.jsp"  flush ="true"></jsp:include>
    </div>

    <!-- Include content.jsp next to menu.jsp -->
    <div style="flex-grow: 1; padding: 10px;">
        <jsp:include page = "MyPage.jsp"  flush ="true"></jsp:include>
    </div>

</div>


<script type = "text/javascript">

</script>
</body>
</html>