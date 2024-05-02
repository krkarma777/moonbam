<%@page import="org.json.JSONObject"%>
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
//1. 국내영화/해외영화 구분해서 가져와야함.(이건 api 바꿔야하긴 함)
//2. 더보기 태그 국내 영화 해외영화 바꿔야함(1번이 되면 가능)
List<PostPageDTO> movieList = (List<PostPageDTO>)request.getAttribute("movieList");
ContentDataFormating cdf = new ContentDataFormating();
List<ContentDTO> movieTopList = (List<ContentDTO>) request.getAttribute("movieTopList");
List<ContentDTO> genreMovieTopList = (List<ContentDTO>) request.getAttribute("genreMovieTopList");

String genre = (String)request.getAttribute("genre");
%>
<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<link
	href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/movieHome.css">
</head>
<body class="bg-light" style="height: 100vh;">
	<!-- 네비게이션 바 -->
	<jsp:include page="../common/navBar.jsp"></jsp:include>

	<div style="height: 50px"></div>
	
<!-- 바디 -->
<div style="height: 910px; width: 1200px; margin: auto;">
	
	<!-- 신작 영화 순위 -->
	<div style="background-color: #ffb2c4; height: 30px; width:1200px; font-size: 19px; ">
		<b>
		<span style="float: left; color:white">
			&nbsp;최신 영화
		</span>
		<span style="float: right; color:white">
			<a href="movieSearch" style="color:white; float: rigth;">전체영화</a>&nbsp;
		</span>
		</b>
	</div>
	<div class="carousel-container">
		<div class="carousel-slide">
			<div class="inner" id="lastClone">
				<%for (int i = 6; i <= movieTopList.size(); i++) { %>
				<div style="height:auto;" class="poster">
					<a href="showContent?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="197px" height="296px">
					</a>
					<div class="rank index-label">
						<%=i %>
					</div>
				</div>
				<%}	%>
			</div>
			<div class="inner">
				<%for (int i = 1; i <= movieTopList.size() - 5; i++) { %>
				<div style="height:auto;" class="poster">
					<a href="showContent?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="197px" height="296px">
					</a>
					<div class="rank index-label">
						<%=i %>
					</div>
				</div>
				<%}	%>
			</div>
			
			<div class="inner">
				<%for (int i = 6; i <= movieTopList.size(); i++) { %>
				<div style="height:auto;" class="poster">
					<a href="showContent?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="197px" height="296px">
					</a>
					<div class="rank index-label">
						<%=i %>
					</div>
				</div>
				<%}	%>
			</div>
			
			<div class="inner" id="firstClone">
				<%
				for (int i = 1; i <= movieTopList.size() - 5; i++) {
				%>
				<div style="height:auto;" class="poster">
					<a href="showContent?contId=<%=movieTopList.get(i - 1).getContId()%>">
						<img class="innerImage <%=i %>"
							src="http://image.tmdb.org/t/p/w342<%=movieTopList.get(i - 1).getContImg()%>"
							width="197px" height="296px">
					</a>
					<div class="rank index-label">
						<%=i %>
					</div>
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
	
	<!-- 영화 순위(태그) -->
	<!-- 아직 구현되지는 않음. 영화 데이터 가져오는 것 중에 장르가 없어서. -->
	<div style="background-color: #ffb2c4; margin-top: 4px; height: 30px; width:1200px; font-size: 19px; color:white;">
		<b>
		&nbsp;#<a href="/acorn/?cg=movie&genre=Drama" class="" style="color:white;">드라마</a>
		#<a href="/acorn/?cg=movie&genre=Comedy" class="" style="color:white;">코미디</a>
		#<a href="/acorn/?cg=movie&genre=Thriller" class="" style="color:white;">스릴러</a>
		<a href="movieSearch?searchCategory=<%=genre %>" style="color:white; float:right;">더보기&nbsp;</a>
		</b>
	</div>
	<div class="carousel-container2">
		<div class="carousel-slide2">
			<div class="inner2" id="lastClone2">
				<%for (int i = 6; i <= genreMovieTopList.size(); i++) { %>
				<div style="height:auto;" class="poster">
					<a href="showContent?contId=<%=genreMovieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=genreMovieTopList.get(i - 1).getContImg()%>"
							width="197px" height="296px">
					</a>
					<div class="rank index-label">
						<%=i %>
					</div>
				</div>
				<%}	%>
			</div>
			<div class="inner2">
				<%for (int i = 1; i <= genreMovieTopList.size() - 5; i++) { %>
				<div style="height:auto;" class="poster">
					<a href="showContent?contId=<%=genreMovieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=genreMovieTopList.get(i - 1).getContImg()%>"
							width="197px" height="296px">
					</a>
					<div class="rank index-label">
						<%=i %>
					</div>
				</div>
				<%}	%>
			</div>
			<div class="inner2">
				<%for (int i = 6; i <= genreMovieTopList.size(); i++) { %>
				<div style="height:auto;" class="poster">
					<a href="showContent?contId=<%=genreMovieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=genreMovieTopList.get(i - 1).getContImg()%>"
							width="197px" height="296px">
					</a>
					<div class="rank index-label">
						<%=i %>
					</div>
				</div>
				<%}	%>
			</div>
			<div class="inner2" id="firstClone2">
				<%for (int i = 1; i <= genreMovieTopList.size() - 5; i++) { %>
				<div style="height:auto;" class="poster">
					<a href="showContent?contId=<%=genreMovieTopList.get(i - 1).getContId()%>">
						<img class="innerImage"
							src="http://image.tmdb.org/t/p/w342<%=genreMovieTopList.get(i - 1).getContImg()%>"
							width="197px" height="296px">
					</a>
					<div class="rank index-label">
						<%=i %>
					</div>
				</div>
				<%}	%>
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
	<div class="d-flex justify-content-center px-5" style="height: 250px; margin-top: 4px; font-size: 19px; border-bottom: 1px solid black;">
		<div class="mx-1">
			<table style="width: 1200px">
				<tr style="background-color: #ffb2c4; height: 30px; width:1200px;">
					<th colspan="2">
						<span style="color:white">&nbsp;영화게시판</span>
						<span style="float: right;"><a href="/acorn/board/movie" style="color:white;">더보기&nbsp</a></span>
					</th>
				</tr>
				<div id="genreList">
				<tr>
					<td>
					<%int count = 0;
					//PostPageDTO post : movieList
					for (int i=0; i<movieList.size(); i++) {
						PostPageDTO post = movieList.get(i);
						if(count==7) break;
						String displayDate = cdf.minuteHourDay(post);
					%>
					<div style="height: 30px; width: 600px; padding:0px;"><b style="font-size: 16px" id="i">
						<a href="/acorn/board/content?postId=<%=post.getPostId()%>&bn=<%=post.getPostBoard()%>" style="color:black;">
							<%=post.getPostTitle()%>[<%=post.getCommentCount()%>]</a> 
						<span style="float: right;"><%=displayDate%> | <%=post.getViewNum()%> | <%=post.getLikeNum()%>&nbsp&nbsp</span>
						</b>
					</div>
					<%count++;} %>
					</td>
					
					<td>
					<%
					for (int i=7; i<movieList.size(); i++) {
						PostPageDTO post = movieList.get(i);
						String displayDate = cdf.minuteHourDay(post);
					%>
					<div style="height: 30px; width: 600px; padding:0px;"><b style="font-size: 16px" id="i">
						<a href="/acorn/board/content?postId=<%=post.getPostId()%>&bn=<%=post.getPostBoard()%>" style="color:black;">
							<span>
							<%if(post.getPostTitle().length()>22){ %>
								<%=post.getPostTitle().substring(0, 21) %>...
							<%}else{ %>
								<%=post.getPostTitle() %>
							<%} %>	
							[<%=post.getCommentCount()%>]
							</span></a> 
						<span style="float: right;"><%=displayDate%> | <%=post.getViewNum()%> | <%=post.getLikeNum()%></span>
						</b>
					</div>
					<%count++;} %>
					</td>
				</tr>
				</div>
			</table>
		</div>
	</div>
</div>

	<!-- 푸터 -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

<script
		src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
	})
	
	<!-- 신작 영화 순위 용 -->
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
	
		
	<!-- 장르 영화 순위 용 -->
	const carouselSlide2 = document.querySelector('.carousel-slide2');
	const carouselImages2 = document.querySelectorAll('.inner2');
	const prevBtn2 = document.querySelector('#prevBtn2');
	const nextBtn2 = document.querySelector('#nextBtn2');

	let counter2 = 1;
	const size2 = carouselImages2[0].clientWidth;
	carouselSlide2.style.transform = 'translateX(' + (-size2 * counter2) + 'px)';

	// Buttons
	nextBtn2.addEventListener('click', () => {
		if (counter >= carouselImages2.length - 1) return;
		carouselSlide2.style.transition = "transform 0.4s ease-in-out";
		counter2++;
		carouselSlide2.style.transform = 'translateX(' + (-size2 * counter2) + 'px)';
	});
		
	prevBtn2.addEventListener('click', () => {
		if (counter <= 0) return;
		carouselSlide2.style.transition = "transform 0.4s ease-in-out";
		counter2--;
		carouselSlide2.style.transform = 'translateX(' + (-size2 * counter2) + 'px)';
	});

	// Jump to First/Last Slide
	carouselSlide2.addEventListener('transitionend', () => {
		if (carouselImages2[counter2].id === 'lastClone2') {
			carouselSlide2.style.transition = 'none'; // 트랜지션 효과 없애기
			counter2 = carouselImages2.length - 2; // couter 초기화
			carouselSlide2.style.transform = 'translateX(' + (-size2 * counter2) + 'px)'; // 실제 마지막 이미지로 이동.
		} else if (carouselImages2[counter2].id === 'firstClone2') {
			carouselSlide2.style.transition = 'none';
			counter2 = carouselImages2.length - counter2; // couter 초기화
			carouselSlide2.style.transform = 'translateX(' + (-size2 * counter2) + 'px)';
		}
	});

	</script>
</body>
</html>