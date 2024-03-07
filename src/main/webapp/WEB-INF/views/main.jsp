<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문밤</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

</head>

<body class="bg-light" style="height: 100vh;">
	<!-- 네비게이션 바 -->
	<jsp:include page="common/navBar.jsp"></jsp:include>
	
	<!-- 바디 -->
	<div class="container-fluid" style="height: 100%; width: 100%;">
		<div class="d-flex align-content-center justify-content-center"
			style="height: 100%;">
			<div class="align-self-center" style="background-color: transparent;">
				<a href="?cg=movie"><img class="rounded-2"
					src="images/camera-reels.svg" width="250px" height="250px"></a>
			</div>
			<!-- <a>누르면 MoveToContentsHomeServlet로 이동 -->
			<div class="align-self-center px-5"
				style="background-color: transparent">
				<a href="?dg=tv"><img class="rounded-2"
					src="images/tv.png" width="250px" height="250px"></a>
			</div>
			<div class="align-self-center" style="background-color: transparent">
				<a href="?cg=book"><img class="rounded-2"
					src="images/book.svg" width="250px" height="250px"></a>
			</div>
		</div>
	</div>
	
	<!-- 푸터 -->
	<jsp:include page="common/footer.jsp"></jsp:include>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>