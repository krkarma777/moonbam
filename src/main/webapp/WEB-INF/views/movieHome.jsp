<%@page import="com.moonBam.dto.ContentDTO"%>
<%@page import="com.moonBam.controller.board.util.ContentDataFormating"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.moonBam.dto.board.PostPageDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<%
//데이터 뽑아 오는 곳: 기준은 아직이지만 최근 상영 중에 인기순이 가장 좋지 않을까?
// 상위 10개 정도를 뽑아서 아래 for문 부분에 돌리기.
List<PostPageDTO> movieList = (List<PostPageDTO>) request.getAttribute("movieList");
List<PostPageDTO> movieMeetList = (List<PostPageDTO>) request.getAttribute("movieMeetList");
List<PostPageDTO> movieInfoList = (List<PostPageDTO>) request.getAttribute("movieInfoList");
ContentDataFormating cdf = new ContentDataFormating();
List<ContentDTO> movieTopList = (List<ContentDTO>) request.getAttribute("movieTopList");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/movieHome.css">
</head>
<body class="bg-light" style="height: 100vh;">
	<!-- 네비게이션 바 -->
	<jsp:include page="common/navBar.jsp"></jsp:include>

	<div style="height: 50px"></div>
	
	<div style="height: 910px; width: 1200px; border: 1px solid grey; margin: auto;">
	
	<!-- 영화 순위 -->
	<div class="carousel-container">
		<div class="carousel-slide">
			<div class="inner" id="lastClone">
				<%for (int i = 7; i <= movieTopList.size(); i++) { %>
				<div style="width: 200px; height:auto;">
					<div>
					<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="200px" height="300px">
					</a><br>
					</div>
					<%-- <div style="width: 200px; background-color: #ff416c;">
						<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>" style="color:black;">
							<%=movieTopList.get(i - 1).getContTitle()%>
						</a>
					</div> --%>
				</div>
				<%
				}
				%>
			</div>
			<div class="inner">
				<%
				for (int i = 1; i <= movieTopList.size() - 6; i++) {
				%>
				<div style="width: 200px; height:auto;">
					<div>
					<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="200px" height="300px">
					</a><br>
					</div>
					<%-- <div style="width: 200px; background-color: #ff416c;">
						<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>" style="color:black;">
							<%=movieTopList.get(i - 1).getContTitle()%>
						</a>
					</div> --%>
				</div>
				<%
				}
				%>
			</div>
			<div class="inner">
				<%
				for (int i = 7; i <= movieTopList.size(); i++) {
				%>
				<div style="width: 200px; height:auto;">
					<div>
					<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="200px" height="300px">
					</a><br>
					</div>
					<%-- <div style="width: 200px; background-color: #ff416c;">
						<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>" style="color:black;">
							<%=movieTopList.get(i - 1).getContTitle()%>
						</a>
					</div> --%>
				</div>
				<%
				}
				%>
			</div>
			<div class="inner" id="firstClone">
				<%
				for (int i = 1; i <= movieTopList.size() - 6; i++) {
				%>
				<div style="width: 200px; height:auto;">
					<div>
					<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="200px" height="300px">
					</a><br>
					</div>
					<%-- <div style="width: 200px; background-color: #ff416c;">
						<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>" style="color:black;">
							<%=movieTopList.get(i - 1).getContTitle()%>
						</a>
					</div> --%>
				</div>
				<%
				}
				%>
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
	
	<!-- 영화 순위(태그) -->
	<div style="background-color: #ff416c;">
		<a href="" color="black">#드라마</a>
		<a href="" color="black">#코미디</a>
		<a href="" color="black">#스릴러</a>
	</div>
	<div class="carousel-container2">
		<div class="carousel-slide2">
			<div class="inner2" id="lastClone2">
				<%for (int i = 7; i <= movieTopList.size(); i++) { %>
				<div style="width: 200px; height:auto;">
					<div>
					<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="200px" height="300px">
					</a><br>
					</div>
					<%-- <div style="width: 200px; background-color: #ff416c;">
						<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>" style="color:black;">
							<%=movieTopList.get(i - 1).getContTitle()%>
						</a>
					</div> --%>
				</div>
				<%
				}
				%>
			</div>
			<div class="inner2">
				<%
				for (int i = 1; i <= movieTopList.size() - 6; i++) {
				%>
				<div style="width: 200px; height:auto;">
					<div>
					<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="200px" height="300px">
					</a><br>
					</div>
					<%-- <div style="width: 200px; background-color: #ff416c;">
						<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>" style="color:black;">
							<%=movieTopList.get(i - 1).getContTitle()%>
						</a>
					</div> --%>
				</div>
				<%
				}
				%>
			</div>
			<div class="inner2">
				<%
				for (int i = 7; i <= movieTopList.size(); i++) {
				%>
				<div style="width: 200px; height:auto;">
					<div>
					<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="200px" height="300px">
					</a><br>
					</div>
					<%-- <div style="width: 200px; background-color: #ff416c;">
						<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>" style="color:black;">
							<%=movieTopList.get(i - 1).getContTitle()%>
						</a>
					</div> --%>
				</div>
				<%
				}
				%>
			</div>
			<div class="inner2" id="firstClone2">
				<%
				for (int i = 1; i <= movieTopList.size() - 6; i++) {
				%>
				<div style="width: 200px; height:auto;">
					<div>
					<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="200px" height="300px">
					</a><br>
					</div>
					<%-- <div style="width: 200px; background-color: #ff416c;">
						<a href="content-page?contId=<%=movieTopList.get(i - 1).getContId()%>" style="color:black;">
							<%=movieTopList.get(i - 1).getContTitle()%>
						</a>
					</div> --%>
				</div>
				<%
				}
				%>
			</div>
		</div>
		<button id="prevBtn2">
			<img src="resources/images/chevron-left.svg" width="20px"
				height="30px">
		</button>
		<button id="nextBtn2">
			<img src="resources/images/chevron-right.svg" width="20px"
				height="30px">
		</button>
	</div>

	<!-- 게시판 -->
	<div class="d-flex justify-content-center px-5" style="height: 300px">
		<div class="mx-1">
			<table border="1" style="width: 392px">
				<tr>
					<th style="height: 30px"><a href="/acorn/board/movieInfo">정보게시판</a></th>
				</tr>
				<%
				for (PostPageDTO post : movieInfoList) {
					String displayDate = cdf.minuteHourDay(post);
				%>
				<tr>
					<td style="height: 30px"><a
						href="/acorn/board/content?postId=<%=post.getPostId()%>&bn=<%=post.getPostBoard()%>">
							<%=post.getPostTitle()%></a> [<%=post.getCommentCount()%>]<br> <%=displayDate%>
						|<%=post.getViewNum()%> | <%=post.getLikeNum()%></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
		<div class="mx-1">
			<table border="1" style="width: 392px">
				<tr>
					<th style="height: 30px"><a href="/acorn/board/movie">자유게시판</a></th>
				</tr>
				<%
				for (PostPageDTO post : movieList) {
					String displayDate = cdf.minuteHourDay(post);
				%>
				<tr>
					<td style="height: 30px"><a
						href="/acorn/board/content?postId=<%=post.getPostId()%>&bn=<%=post.getPostBoard()%>">
							<%=post.getPostTitle()%></a> [<%=post.getCommentCount()%>]<br> <%=displayDate%>
						|<%=post.getViewNum()%> | <%=post.getLikeNum()%></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>

		<div class="mx-1">
			<table border="1" style="width: 392px">
				<tr>
					<th style="height: 30px"><a href="/acorn/board/movieMeet">모임게시판</a></th>
				</tr>
				<%
				for (PostPageDTO post : movieMeetList) {
					String displayDate = cdf.minuteHourDay(post);
				%>
				<tr>
					<td style="height: 30px"><a
						href="/acorn/board/content?postId=<%=post.getPostId()%>&bn=<%=post.getPostBoard()%>">
							<%=post.getPostTitle()%></a> [<%=post.getCommentCount()%>]<br> <%=displayDate%>
						|<%=post.getViewNum()%> | <%=post.getLikeNum()%></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
	</div>
	</div>

	<!-- <div style="position:fixed; bottom:0; width:100%; height:100px; background-color: red;"></div> -->

	<!-- 푸터 -->
	<jsp:include page="common/footer.jsp"></jsp:include>

<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
<script type="text/javascript">
	
		<!-- 영화 순위 -->
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
		
		
		<!-- 영화 순위(태그) -->
		const carouselSlide2 = document.querySelector('.carousel-slide2');
		const carouselImages2 = document.querySelectorAll('.inner2');

		const prevBtn2 = document.querySelector('#prevBtn2');
		const nextBtn2 = document.querySelector('#nextBtn2');

		let counter2 = 1;
		const size2 = carouselImages2[0].clientWidth;
		carouselSlide2.style.transform = 'translateX(' + (-size * counter2) + 'px)';

		// Buttons
		nextBtn2.addEventListener('click', () => {
			if (counter >= carouselImages2.length - 1) return;
			carouselSlide2.style.transition = "transform 0.4s ease-in-out";
			counter2++;
			carouselSlide2.style.transform = 'translateX(' + (-size * counter2) + 'px)';
		});

		prevBtn2.addEventListener('click', () => {
			if (counter <= 0) return;
			carouselSlide2.style.transition = "transform 0.4s ease-in-out";
			counter2--;
			carouselSlide2.style.transform = 'translateX(' + (-size * counter2) + 'px)';
		});

		// Jump to First/Last Slide
		carouselSlide2.addEventListener('transitionend', () => {
			console.log(carouselImages2[counter]);
			if (carouselImages2[counter2].id === 'lastClone') {
				carouselSlide2.style.transition = 'none'; // 트랜지션 효과 없애기
				counter2 = carouselImages2.length - 2; // couter 초기화
				carouselSlide2.style.transform = 'translateX(' + (-size * counter2) + 'px)'; // 실제 마지막 이미지로 이동.
			} else if (carouselImages2[counter2].id === 'firstClone') {
				carouselSlide2.style.transition = 'none';
				counter2 = carouselImages2.length - counter; // couter 초기화
				carouselSlide2.style.transform = 'translateX(' + (-size * counter2) + 'px)';
			}
		});

	</script>
</body>
</html>