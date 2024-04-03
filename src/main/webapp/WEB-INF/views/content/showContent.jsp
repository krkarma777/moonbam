<%@page import="com.moonBam.dto.ContentDTO"%>
<%@page import="com.moonBam.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
MemberDTO login = (MemberDTO)session.getAttribute("loginUser");
String userId = null;
String nickname = null;
if(login!=null){
	userId = login.getUserId();
	nickname = login.getNickname();
}
ContentDTO content = (ContentDTO)request.getAttribute("content");

//예외처리: DB에 contid에 해당하는 데이터가 없을 경우 -> 이전화면으로
Long contId = content.getContId();
String contTitle = content.getContTitle();
String description = content.getDescription();
String contImg = content.getContImg();
%>
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
	margin-right: 2px;
	border: 1px solid gray;
	border-radius: 5px;
	width:298px; 
	height:240px;
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

<body class="bg-light" style="height: 100vh;">

	<!-- 네비게이션바 -->
	<jsp:include page="../common/navBar.jsp"></jsp:include>

	<!-- 바디 -->
	<div style="height: 100px"></div>
	<div
		style="width: 1200px; height: auto; margin: auto;">
		<div style="display: flex;">
			<div>	
				<img src="http://image.tmdb.org/t/p/w342${content.getContImg() }" height="400px" width="279px">
			</div>
			<div style="width: auto; padding-left: 10px; align-items: center;">
				<span style="font-size: 20px;"><b>${content.getContTitle() }</b></span>
				<hr>
				${content.getDescription() }
			</div>
			<div>
				<div style="margin-left:10px; width:293px; height:160px; border:1px solid gray; border-radius: 5px;">
					<!-- Button trigger modal -->
				    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Write Your Feeling !</button>
				</div>
				<div style="margin-left:10px; width:293px; height:240px; border:1px solid gray; border-radius: 5px;">히히히</div>
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
		<button id="prevBtn"><img src="resources/images/chevron-left.svg" width="20px" height="30px"></button>
		<button id="nextBtn"><img src="resources/images/chevron-right.svg" width="20px" height="30px"></button>
	</div>
	</div>

	<!-- 푸터 -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<!-- 모달창 -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">간편 리뷰 작성</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      	<input type="hidden" value="<%=contId %>" id="contId">
	        <textarea cols="50" rows="12" id="postText"></textarea>
	        <p id="show_length">length</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <!-- <button type="button" class="btn btn-primary" id="writeReview">Save changes</button> -->
	        <button type="button" class="btn btn-primary" id="writeReview" data-bs-toggle="popover" data-bs-title="알림" data-bs-content="로그인이 필요한 작업입니다">Save changes</button>
	      </div>
	    </div>
	  </div>

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
		
		// 글자수 제한
		function check_length(){
			//console.log(this.value.length);
			var length = this.value.length;
			if(length>max_length){
				this.value = this.value.substr(0, max_length);
				this.focus();
			}
			$("#show_length").text(length+"/"+max_length);
		}
		// 엔터키 제한
		function check_enter(){
			if(event.keyCode==13){
				event.returnValue=false;
			}
		}
		
		// 내 리뷰란 업데이트 함수
		function updateMyReview(review){
			$("#myreview_link").attr("href", "review?postId="+review.postId);
			$("#myreview_user").text("<%=nickname %>");
			if(review.postText.length>150){
				$("#myreview_text").text(review.postText.substr(0, 145)+" ...");
			}else{
				$("#myreview_text").text(review.postText);
			}
			var length = $("#postText").val().length;
			
			// 모달창 글자수 처음 세팅
			$("#show_length").text(length+"/"+max_length);
		}
		
		// 리뷰 작성 완료
		function writeReview(){

			//로그인 정보 확인
			<%
			if(login!=null){
			%>
			
			var contId = $("#contId").val();
			var postText = $("#postText").val().substr(0, max_length);
			//내용이 있을 시만 저장작업 진행 && contId에 null값이 저장되지 않았을 경우에만 진행
			if(postText.length!=0 && contId !="null"){
				$.ajax(
					{
						type: "post",
						url:"my-review",
						data: {
							"contId": contId,
							"userId": "<%=userId%>",
							"nickname": "<%=nickname%>",
							"postText": postText
						},
						dataType: "text",
						success: function(data, status, xhr){
							//console.log("성공: " + data);
							var jsonData = JSON.parse(data);  //text->json
							updateMyReview(jsonData);         //리뷰 표시 업데이트 함수
							$('#exampleModal').modal('hide');
							$("#cont_myreview_container").show();
							
						},
						error: function(xhr, status, e){
							console.log("실패: " + xhr.status);
						}
					}//json
				);//ajax
			}//내용검사if
			<%}%>
		}//function
	
		const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]')
		const popoverList = [...popoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl))
	
	</script>
</body>
</html>