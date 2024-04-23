<%@page import="com.moonBam.dto.ReviewDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	List<ReviewDTO> reviewList = (List<ReviewDTO>)request.getAttribute("reviewList");
	
%>
<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>

<body class="bg-light" style="height: 100vh;">
	<!-- 네비게이션바 -->
	<jsp:include page="../common/navBar.jsp"></jsp:include>

	<div style="height: 50px"></div>
	
	<!-- 바디 -->
	<div style="height: 910px; width: 1200px; margin: auto;"><b>
		<!-- 상단 버튼 -->
		<div style="">
			<!-- 리뷰 쓰기 -->
			<button type="button" class="btn" style="float:right; background-color: #ff416c; color:white; margin-left: auto;" id=""><b>리뷰 쓰기</b></button>
		</div>
		
		<!-- community목록 -->
		<table style="margin-top: 5px; width:1200px; height:795px">
			<%int count = 0;
			for(int j=1; j<=3;j++){	%>
				<tr style="width: 1200px; height: 265px;">
					<%for(int k=1; k<=4; k++) { 
						if(count==reviewList.size()) break;
						ReviewDTO review = reviewList.get(count);
						Long postId = review.getPostId();
						String postTitle = review.getPostTitle();
						String postdate = review.getPostDate();
						String postText = review.getPostText();
						String nickname = review.getNickname();
					%>
					<td id="<%=count%>" style="width: 300px; height: 265px; float: left">
						<div class="border" style="width: 300px; height: 265px;">
							<div class="border-bottom" style="height: 40px; width: 300px; background-color: #ffb2c4; align-content: center;">
								<a href="chatRoom?chatNum=<%=postId%>" style="color:black; font-size: 19px;"><%=postTitle %></a>
							</div>
							<div class="border-top" style="height: 225px; width: 300px;">
								<div style=" font-size: 18px; height: 195px;">
									<%=postText %>
								</div>
								<div style="width: 100%; height:20px; font-size: 18px; padding-left: 2px; padding-right: 5px;">
									<div style="float: left;"></div>
									<div style="float: right;"><%=postdate %></div>
								</div>
							</div>
						</div>
					</td>
					<%count++; } %>
				</tr>
			<%} %>
		</table>
		
	</b></div>
	<!-- 푸터 -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
	})
</script>
</body>
</html>