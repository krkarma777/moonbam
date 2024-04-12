<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 시작화면 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type = "text/javascript">

</script>
</head>
<body>

<div style = "">
<jsp:include page = "common/top.jsp" flush ="true"></jsp:include><br>
</div>
<hr>
<div style="display: flex;">

    <!-- Include menu.jsp on the left side -->
    <div style="width: 200px;">
        <jsp:include page = "common/AdminMenu.jsp"  flush ="true"></jsp:include>
    </div>

    <!-- Include content.jsp next to menu.jsp -->
    <div style="flex-grow: 1; padding: 10px;">
        <jsp:include page = "content/post/AdminPageDeletedPost.jsp"  flush ="true"></jsp:include>
    </div>
</div>

<script type = "text/javascript">
$(document).ready(function(){
	
})

</script>

</body>
</html>
