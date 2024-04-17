<%@page import="java.util.List"%>
<%@page import="com.moonBam.dto.CreditDTO"%>
<%@page import="com.moonBam.dto.ContentDTO"%>
<%@page import="com.moonBam.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
MemberDTO login = (MemberDTO) session.getAttribute("loginUser");
String userId = null;
String nickname = null;
if (login != null) {
	userId = login.getUserId();
	nickname = login.getNickname();
}
ContentDTO content = (ContentDTO) request.getAttribute("content");

//예외처리: DB에 contid에 해당하는 데이터가 없을 경우 -> 이전화면으로
Long contId = content.getContId();
String contTitle = content.getContTitle();
String description = content.getDescription();
String contImg = content.getContImg();
String releaseDate = content.getReleaseDate();

List<CreditDTO> creditList = (List<CreditDTO>) request.getAttribute("creditList");
%>
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
<link rel="stylesheet" href="resources/css/movieHome.css">

<style>
* {
	padding: 0;
	margin: 0;
}
</style>
</head>

<body class="bg-light" style="height: 100vh;">

	<!-- 네비게이션바 -->
	<jsp:include page="../common/navBar.jsp"></jsp:include>

	<div style="height: 50px"></div>

	<!-- 바디 -->
	<div style="width: 1200px; height: 910px; margin: auto;">
		
		<div style="display: flex; widht: 100%; height: 375px;">
			<!-- 영화 포스터 -->
			<div>
				<img src="http://image.tmdb.org/t/p/w342${content.getContImg() }"
					height="375px" width="250px">
			</div>
			
			<div style="width: 950px;">
				<!-- 영화 제목, 개봉일 -->
				<div style="font-size: 35px; background-color: #ffb2c4; color: white; height: 52px; width: 949px; position: relative;">
					<b><span style="margin-left: 4px;">${content.getContTitle() }</span></b>
					<span
						style="font-size: 20px; position: absolute; bottom: 0; right: 5px;">개봉일:
						<%=releaseDate%></span>
				</div>
				
				<div style="display: flex;">
					<!-- 평점 -->
					<div style="width: 475px; height: 323px; border-right: 1px solid black;">

					</div>
					
					<!-- 내 리뷰 -->
					<div style="width: 475px; height: 323px; position: relative;">
					
						<div style="position: absolute; bottom: 0; right: 1px;">
							<button class="btn" style="background-color: #ff416c; color: white;" id="allReview">+</button>
							<button class="btn" style="background-color: #ff416c; color: white;">리뷰쓰기</button>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		
		<!-- 시놉시스 -->
		<div style="height: 235px;">
			<div style="background-color: #ffb2c4; color: white; height: 30px; font-size: 20px;">
				<b style="margin-left: 4px;">시놉시스</b>
			</div>
			<div style="font-size: 15px; margin: 4px;">
				${content.getDescription() }
			</div>
		</div>

		<!-- 배우정보 -->
		<div style="width: 100%; height: 300px;">

	<div class="carousel-container">
		<div class="carousel-slide">
			<div class="inner" id="lastClone">
				<%for (int i = creditList.size()-6; i < creditList.size(); i++) { %>
				<div style="width: 201px; height:auto;">
					<%if(null==creditList.get(i)){ %>
						<img class="innerImage" src="resources/images/question.png" width="197px">
					<%}else{ %>
						<img class="innerImage" alt="resource/images/person-x.svg"
							src="http://image.tmdb.org/t/p/w342<%=creditList.get(i).getProfile_path()%>"
							width="197px" height="296px">
					<%} %>
				</div>
				<%}	%>
			</div>
			
			<%int count=0;
			for(int i=0; i<(creditList.size()/6); i++){ %>
			<div class="inner">
				<%for (int j = 0; j < 6; j++) { 
					if(count==creditList.size())break;
				%>
				<div style="width: 201px; height:auto;">
					<%if(null==creditList.get(count)){ %>
						<img class="innerImage" src="resources/images/question.png" height="296px" width="197px" style="bottom:0px;">
					<%}else{ %>
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=creditList.get(count).getProfile_path()%>"
							width="197px" height="296px">
					<%} %>
				</div>
				<%count++;}	%>
			</div>
			<%} %>
			
			<div class="inner" id="firstClone">
				<%
				for (int i = 0; i < 6; i++) {
				%>
				<div style="width: 201px; height:auto;">
					<%if(null==creditList.get(i)){ %>
						<img class="innerImage" src="resources/images/question.png" width="197px">
					<%}else{ %>
						<img class="innerImage" alt="resource/images/person-x.svg"
							src="http://image.tmdb.org/t/p/w342<%=creditList.get(i).getProfile_path()%>"
							width="197px" height="296px">
					<%} %>
				</div>
				<%}	%>
			</div>
		</div>
		<button id="prevBtn">
			<img src="resources/images/chevron-left.svg" width="20px"
				height="30px">
		</button>
		<button id="nextBtn">
			<img src="resources/images/chevron-right.svg" width="20px"
				height="30px">
		</button>
	</div>

		</div>

	</div>

	<!-- 푸터 -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#allReview").click(function(){
			location.href="allReview?contId=<%=contId %>";
		})
	})
	
	const carouselSlide = document.querySelector('.carousel-slide');
	const carouselImages = document.querySelectorAll('.inner');

	const prevBtn = document.querySelector('#prevBtn');
	const nextBtn = document.querySelector('#nextBtn');

	let counter = 1;
	const size = carouselImages[0].clientWidth;
	carouselSlide.style.transform = 'translateX(' + (-size * counter) + 'px)';

	// Buttons
	nextBtn.addEventListener('click', () => {
		if (counter >= carouselImages.length - 1) return;
		carouselSlide.style.transition = "transform 0.4s ease-in-out";
		counter++;
		carouselSlide.style.transform = 'translateX(' + (-size * counter) + 'px)';
	});

	prevBtn.addEventListener('click', () => {
		if (counter <= 0) return;
		carouselSlide.style.transition = "transform 0.4s ease-in-out";
		counter--;
		carouselSlide.style.transform = 'translateX(' + (-size * counter) + 'px)';
	});
	
	// Jump to First/Last Slide
	carouselSlide.addEventListener('transitionend', () => {
		console.log(carouselImages[counter]);
		if (carouselImages[counter].id === 'lastClone') {
			carouselSlide.style.transition = 'none'; // 트랜지션 효과 없애기
			counter = carouselImages.length - 2; // couter 초기화
			carouselSlide.style.transform = 'translateX(' + (-size * counter) + 'px)'; // 실제 마지막 이미지로 이동.
		} else if (carouselImages[counter].id === 'firstClone') {
			carouselSlide.style.transition = 'none';
			counter = carouselImages.length - counter; // couter 초기화
			carouselSlide.style.transform = 'translateX(' + (-size * counter) + 'px)';
		}
	});
</script>
</body>
</html>