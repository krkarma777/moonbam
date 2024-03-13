<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<style>
.carousel-container {
	width: 1200px;
	margin: auto; /*width가 지정되어 있어야 가운데로 옴.*/
	overflow: hidden;
	position: relative;
}

.carousel-slide {
	display: flex;
	width: 1200px;
	margin: auto;
}

.inner {
	display: flex;
	width: 100%;
}

.innerReview {
	margin-right: ;
	border: 1px solid black;
	width:300px; 
	height:280px;
}

#prevBtn {
	position: absolute;
	top: 50%;
	left: 0;
	transform: translate(0%, -50%);
	background-color: white;
	opacity: 0.5;
	/*background: no-repeat;*/
	border: none;
}
#nextBtn {
	position: absolute;
	top: 50%;
	right: 0;
	transform: translate(0%, -50%);
	background-color: white;
	opacity: 0.5;
	/*background: no-repeat;*/
	border: none;
}
</style>
</head>

<body>

	<!-- 네비게이션바 -->
	<jsp:include page="../common/navBar.jsp"></jsp:include>

	<!-- 바디 -->
	<div style="height: 100px"></div>
	<div
		style="width: 1200px; height: auto; margin: auto;">
		<div style="display: flex;">
			<div>	
				<img src="${content.getContImg() }" height="400px" width="279px">
			</div>
			<div style="width: auto; padding-left: 10px; align-items: center;">
				<span style="font-size: 20px;"><b>${content.getContTitle() }</b></span>
				<hr>
				${content.getDescription() }
			</div>
			<div>
				<div style="margin-left:10px; width:293px; height:160px; border:1px solid red;">호호호</div>
				<div style="margin-left:10px; width:293px; height:240px; border:1px solid green;">히히히</div>
			</div>
		</div>

		<div style="height: 30px; width: 100%;">
			<div style="float: left; font-size: 18px;">리뷰</div>
			<div style="float: right; font-size: 18px;"><a href="allReview">더보기+</a></div>
		</div>
			
		<div class="carousel-container">
		<div class="carousel-slide">
			<div class="inner" id="lastClone">
				
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자5</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자6</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자7</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자8</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
			</div>
			<div class="inner">
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자1</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자2</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자3</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자4</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
			</div>
			<div class="inner">
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자5</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자6</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자7</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자8</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
			</div>
			<div class="inner" id="firstClone">
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자1</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자2</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자3</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
				<div class="innerReview">
					<div style="width:100%; height:20px;">작성자4</div>
					<div style="width:100%; height:260px;">리뷰 내용</div>
					<div style="width:100%; height:20px;">좋아요</div>
				</div>
			</div>
		</div>
		<button id="prevBtn"><img src="image/chevron-left.svg" width="20px" height="30px"></button>
		<button id="nextBtn"><img src="image/chevron-right.svg" width="20px" height="30px"></button>
	</div>
	</div>

	<!-- 푸터 -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
	<script type="text/javascript">

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