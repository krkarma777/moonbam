<%@page import="com.moonBam.dto.ChatRoomDTO"%>
<%@page import="com.moonBam.dto.board.PageDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

%>
<html>
<head>
<meta charset="UTF-8">
<title>문밤</title>
<link rel="stylesheet" href="resources/js/fullpage/jquery.fullPage.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="resources/js/fullpage/jquery.fullPage.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		//풀페이지 시작
		$('#fullpage').fullpage({
			//options here
			autoScrolling:true,
			scrollHorizontally: true,
	        navigation:true,
		});
		
		//community개설로 이동
		$("#createCommunity").click(function(){
			console.log("클릭");
		})
		
	})
</script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
}
button {
	border: 1.111;
	padding-top: 4; padding-bottom: 4;
	padding-left: 8; padding-right: 8;
}
.pagination{
	justify-content: center;
}
.pagination .page-link{
	border: none;
	color:black; 
	background-color: #ff416c; 
	opacity:0.8;
	color: white;
}
.pagination .page-item.active .page-link{
	color:black; 
	background-color: #ff416c;
	opacity:0.8; 
	color: black;
	border: none;
}
</style>

</head>
<body class="bg-light" style="height: 100vh;">

	<!-- 바디 -->
	<main id="fullpage" class="">
	
	<!-- 1section -->
	<section class="section">
	<div style="height: 910px; width: 1200px; margin: auto;">
		<!-- 개설 버튼 -->
		<div style="display: flex">
			<button type="button" class="btn" style="background-color: #ff416c; color:white; margin-left: auto; opacity : 0.8;" id="createCommunity"><b>개설</b></button>
		</div>
		
		<!-- community목록 -->
		<table style="margin-top: 5px; width:1200px; height:">
			<%	int count = 0;
			for(int i=1; i<=12; i++){ 
				for(int j=1; j<=3;j++){	%>
				<tr id="<%=i%>">
					<%for(int k=1; k<=4; k++) {
						if(count==12)break;
						count++;%>
					<td style="width: 300px; height: 265px;">
						<div class="border rounded-2">
							<div class="border-bottom" style="height: 30px; width: 100%; background-color: #ff416c; color:white; opacity : 0.3;"></div>
							<div class="border-top" style="height: 241.5px; width: 100%;"></div>
						</div>
					</td>
					<%} %>
				</tr>
			<%	}
			} %>
		</table>
		
		<!-- 페이지네이션 -->
		<div style="margin-top: 5px; width:auto;">
			<jsp:include page="communityPage.jsp"></jsp:include>
		</div>
	</div>
	</section>
	
	<!-- 2section -->
	<section class="section">
	<div style="height: 910px; width: 1200px; margin: auto;">
		<h1>지도</h1>
	</div>
	</section>
	
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