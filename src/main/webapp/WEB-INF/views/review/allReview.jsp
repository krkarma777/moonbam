<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="js/fullpage/jquery.fullPage.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="js/fullpage/jquery.fullPage.js"></script>
<script>
$(document).ready(function() {
	$('#fullpage').fullpage({
		//options here
		autoScrolling:true,
		scrollHorizontally: true,
        navigation:true,
	});
});
</script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

</head>

<body class="bg-light" style="height: 100vh;">
	
	<!-- 바디 -->
	<main id="fullpage" class="">
	<!-- <table>
		<tr> -->
			<!-- for문 시작 -->
			<% int count = 0;
			int totalReview = 27;
			int totalPage = 27/12;
			if(27%12>0)totalPage++;
			for(int i=1; i<=totalPage; i++){ %>
        	<section class="section">
            	<table align="center">
            		<%for(int j=1; j<=3; j++){ %><!-- 3줄 -->
					<tr>
						<%for(int k=1; k<=4; k++){ //한 줄에 4개
							if(totalReview==count)break;
							count++;%>
						<td>
							<div class="border rounded-2">
								<div class="border-bottom" style="height: 25px; width: 250px;">글쓴이 정보</div>
								<div style="height: 180px; width: 250px;">내용</div>
								<div class="border-top " style="height: 30px; width: 250px;">좋아요</div>
							</div>
						</td>
						<%} %>
					</tr> 
					<%} %>
				</table>
        	</section>
        	<%} %>
        <!-- </tr>
	</table> -->
	</main>
	
	<!-- 네비게이션 바 -->
	<jsp:include page="../common/navBar.jsp"></jsp:include>
	<!-- 푸터 -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>