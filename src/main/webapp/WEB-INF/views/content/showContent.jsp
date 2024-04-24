<%@page import="com.moonBam.dto.ReviewDTO"%>
<%@page import="com.moonBam.dto.RateDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.moonBam.dto.CreditDTO"%>
<%@page import="com.moonBam.dto.ContentDTO"%>
<%@page import="com.moonBam.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
MemberDTO member = (MemberDTO) request.getAttribute("member");
String userId = null;
String nickname = null;
if (member != null) {
	userId = member.getUserId();
	nickname = member.getNickname();
}

ContentDTO content = (ContentDTO) request.getAttribute("content");

//예외처리: DB에 contid에 해당하는 데이터가 없을 경우 -> 이전화면으로
Long contId = content.getContId();
String contTitle = content.getContTitle();
String description = content.getDescription();
String contImg = content.getContImg();
String releaseDate = content.getReleaseDate();
if (description == null) {
	description = "해당 컨텐츠에 대한 설명이 존재하지 않습니다.";
}

List<CreditDTO> creditList = (List<CreditDTO>) request.getAttribute("creditList");

List<ReviewDTO> reviewList = (List<ReviewDTO>) request.getAttribute("reviewList");

//avgRate 구하기, 별점범위당 갯수 구하기
List<RateDTO> rateList = (List<RateDTO>) request.getAttribute("rateList");
int rateAmount = 0;
if (rateList != null) { //0이 아닐 경우
	rateAmount = rateList.size();
}
//모든 별점 순회
double sum = 0;
double[] rateDistribution = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
for (int i = 0; i < rateAmount; i++) {
	RateDTO rate = rateList.get(i);
	double score = rate.getScore();
	sum += score;
	//별점(score)가 10이면 [9]에 +1증가
	rateDistribution[(int) score - 1]++;
}

String avgRate = "리뷰가 존재하지 않습니다.";
// 별점이 1개 이상일 때
if (rateAmount > 0) {
	avgRate = String.format("%.1f", sum / rateAmount / 2);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/movieHome.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#allReview").click(function(){
			location.href="allReview?contId=<%=contId%>";
		})
	})
	
	//최대글자수
	var max_length = 200;

	//ready
	$(document).ready(function(){
		$("#show_length").text($("#postText").val().length+"/"+max_length); //글자수 표시
		/* $("#writeReview").on("click", writeReview);  //리뷰작성 */
		$("#postText").on("keyup", check_length); 	 //글자수 제한
		$("#postText").on("keypress", check_enter);  //엔터키 제한
		$(".rate input").on("change", rating)  		//별점 선택
		$(".like_btn").on("click", likeToggle) 		// 공감버튼 클릭
		
		// 화면 로딩시 배우 정보 로딩 및 뿌려주기
		// console.log("tests")
		// showCredits();
		
		// 별점 막대그래프 높이 설정 함수
		setAvgGraph();
		
	});//ready
	
	// 별점 막대그래프 높이 설정 함수
	function setAvgGraph(){
		<%for (int i = 0; i < rateDistribution.length; i++) {
	// 버전1
	//전체높이 * 해당별점갯수/전체별점갯수
	//문제: y축이 너무 높아짐
	//double height = 70*rateDistribution[i]/rateAmount;

	// 버전2
	//y축 수치값을 (가장 많은 갯수 + 10%)로 설정
	//전체높이 * 해당별점갯수/(가장 높은 갯수 + 10%)
	double max = rateDistribution[0];
	for (double num : rateDistribution) {
		if (num > max) { //최대값 비교해서 구하기
			max = num;
		}
	}
	// 70px * (최대값+10%)
	double height = 120 * rateDistribution[i] / (max * 1.1);

	if (height > 120)
		height = 120;
	if (height == 0)
		height = 2;%>
			$("#score<%=i + 1%>").css("height", "<%=height%>px");
			$("#score<%=i + 1%>").css("width", "20px");
		<%}%>
	}
	
	//화면 로딩시 내 리뷰 표시 함수 
	function setMyReview(){
<%-- 		<%
		//로그인 정보 확인
		if(login!=null){
		%> --%>
		// 화면 최초 생성시 로그인상태+작성했던 리뷰가 있다면 불러와서 표시
		//내 리뷰 불러오기
		$.ajax(
			{
				type: "get",
				url:"my-review",
				data: {
					"userId": "<%=userId%>",
					"contId": "<%=contId%>"
				},
				dataType: "text",
				success: function(data, status, xhr){
					//console.log("성공: " + data);
					if(data.length>0){
						var jsonData = JSON.parse(data);
						$("#postText").html(jsonData.postText);
						updateMyReview(jsonData)
						$('#exampleModal').modal('hide');
						$("#cont_myreview_container").show();
						//console.log("별점: " + jsonData.score)
						if(jsonData.score!=null){
							$("#rating"+jsonData.score).attr("checked", "checked");
						}
						$("#reviewBtn").text("수정하기")
					}else{
						$("#reviewBtn").text("리뷰쓰기")
					}
				},
				error: function(xhr, status, e){
					console.log("실패: " + xhr.status);
				}
			}//ajax	
		);//ajax
<%-- 		<%}%>//if 종료 --%>
	}
	
	// 공감버튼 토글
	function likeToggle(){

		// 버튼 누른 리뷰의 postId 가져오기
		var postId = $(this).attr("data-postId");
		
		//버튼에 적혀있는 하트 공백제거해서 가져오기
		var statement = $(this).text().trim();
		var isLike = 0;
		//console.log(statement)
		
		// 공백하트인지 꽉찬 하트인지 검사해서 반대로 바꾸기
		if(statement == "♥"){
			$(this).text("♡");
			isLike = 0;
		} else if(statement == "♡"){
			$(this).text("♥");
			isLike = 1;
		}
		
		//로그인정보가 있을 때
		//DB에 비동기 반영
		<%if (userId != null) {%>
		
			$.ajax(
				{
					type: "post",
					url:"like",
					data: {
						"userId": "<%=userId%>",
						"postId": postId,
						"isLike": isLike
					},
					success: function(data, status, xhr){
						if(isLike==0)
							$("#likeNum"+postId).text($("#likeNum"+postId).text()-1);
						else{
							$("#likeNum"+postId).text($("#likeNum"+postId).text()-1+2);
						}
					},
					error: function(xhr, status, e){
					}
				}//json	
			);//ajax
		<%}%>//if
	}
	
	// 별점 선택
	function rating(){
		$.ajax(
			{
				type: "post",
				url:"score",
				data: {
					"userId": "<%=userId%>",
					"contId": "<%=contId%>",
					"score": this.value
				},
				success: function(data, status, xhr){
					//console.log("성공: " + data);
				},
				error: function(xhr, status, e){
					//console.log("실패: " + xhr.status);
				}
			}		
		);
	}
	
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
		$("#myreview_user").text("<%=nickname%>");
		if(review.postText.length>150){
			$("#myreview_text").html(review.postText.substr(0, 145)+" ...");
		}else{
			$("#myreview_text").html(review.postText);
		}
		var length = $("#postText").val().length;
		
		// 모달창 글자수 처음 세팅
		$("#show_length").text(length+"/"+max_length);
	}
	
	// 리뷰 작성 완료
	function writeReview(){

<%-- 		//로그인 정보 확인
		<%
		if(login!=null){
		%> --%>
		
		var contId = $("#contId").val();
		var postText = $("#postText").val().substr(0, max_length);
		<%-- console.log("리뷰쓰기 테스트 "+contId+" "+postText+" <%=userId%>"+" <%=nickname%>"); --%>
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
						$("#reviewBtn").text("수정하기")
					},
					error: function(xhr, status, e){
						console.log("실패: " + xhr.status);
					}
				}//json
			);//ajax
		}//내용검사if
		<%-- <%}%> --%>
	}//function
	
	const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]')
	const popoverList = [...popoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl))
	
</script>
<sec:authorize access="isAuthenticared()">
	<script>
		$(document).ready(function(){
			$("#writeReview").on("click", writeReview);  //리뷰작성
			
			//화면 로딩시 내 리뷰 표시 함수 
			setMyReview();
		});//ready
	</script>
</sec:authorize>
<style>
@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);
    .rate { display: inline-block;border: 0;margin-right: 15px;}
	.rate > input {display: none;}
	.rate > label {float: right;color: #ddd}
	.rate > label:before {display: inline-block;font-size: 1rem;padding: .3rem .2rem;margin: 0;cursor: pointer;font-family: FontAwesome;content: "\f005 ";}
	.rate .half:before {content: "\f089 "; position: absolute;padding-right: 0;}
	.rate input:checked ~ label, 
	.rate label:hover,.rate label:hover ~ label { color: #ff416c !important;  } 
	.rate input:checked + .rate label:hover,
	.rate input input:checked ~ label:hover,
	.rate input:checked ~ .rate label:hover ~ label,  
	.rate label:hover ~ input:checked ~ label { color: #ff416c !important;  } 
	
* {
	padding: 0;
	margin: 0;
}

#graph {
	height: 70px;
	padding-left: 5px;
	padding-right: 5px;
	margin-bottom: 20px;
}

.graph_bar {
	/* background-color: #0d6efd; */
	background-color: #ff416c;
	margin-left: 1px;
	margin-right: 1px;
	border-top-left-radius: 8px;
	border-top-right-radius: 8px;
	text-align: center;
}

#show_length {
	color: gray;
	text-align: right;
}

#postText {
	border: none;
	/*  overflow: scroll; */
	overflow: auto;
	overflow-x: hidden;
	outline: none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
	resize: none; /*remove the resize handle on the bottom right*/
}

#cont_myreview_container {
	display: none;
	border: none;
}

#myreview_link {
	text-decoration: none;
	color: white;
}

#myreview_text {
	max-height: 300px;
	overflow: hidden;
	font-size: 16px;
}

#myreview_footer {
	font-size: 16px;
}

#myreview_link {
	text-decoration: none;
	color: white;
}

#review_col {
	margin: 10px;
	background-color: #f8f8f8;
	border: 1px solid #E2E2E2;
	border-radius: 16px;
	padding: 20px;
	max-width: 25%;
	/* min-width: 350px; */
	min-width: 200px;
}

#review_title {
	margin-top: 50px;
	margin-bottom: 25px;
}

#review_nick {
	font_size: 18px;
	height: 24px
}

#review_hr {
	margin-bottom: 8px;
}

#remove_deco {
	text-decoration: none;
	color: black;
}

#review_body {
	font-size: 15px;
	height: 135px;
	margin-bottom: 5px;
	overflow-wrap: break-word;
	overflow: hidden;
}

#review_score {
	font-size: 17px;
}

.like_btn {
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none
}

#show_length {
	color: gray;
	text-align: right;
}

}
.del_deco {
	text-decoration: none;
	color: black;
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
				<img src="http://image.tmdb.org/t/p/w342${content.getContImg() }" height="375px" width="250px">
			</div>

			<div style="width: 950px;">
				<!-- 영화 제목, 개봉일 -->
				<div style="font-size: 35px; background-color: #ffb2c4; color: white; height: 52px; width: 949px; position: relative;">
					<b><span style="margin-left: 4px;"> <%
 if (content.getContTitle().length() > 40) {
 %>
							<%=content.getContTitle().substring(0, 40)%> ... <%
							} else {
							%>
							${content.getContTitle()} <%
 }
 %>
					</span></b> 
					<span style="font-size: 20px; position: absolute; bottom: 0; right: 5px;">개봉일:
						<%=releaseDate%></span>
				</div>

				<div style="display: flex;">
					<!-- 평점 -->
					<div style="width: 475px; height: 323px; position: relative; padding: 10px;">
						<span class="card-text" id="avgRate" style="font-size: 30px;">
							☆<%=avgRate%>
							(<%=rateAmount%>)
						</span>
						<div style="height: 150px;"></div>
						<div class="align-items-end" id="graph" style="display: flex;">
							<div class="col graph_bar graph-bar" id="score1">0.5</div>
							<div class="col graph_bar graph-bar" id="score2">1.0</div>
							<div class="col graph_bar graph-bar" id="score3">1.5</div>
							<div class="col graph_bar graph-bar" id="score4">2.0</div>
							<div class="col graph_bar graph-bar" id="score5">2.5</div>
							<div class="col graph_bar graph-bar" id="score6">3.0</div>
							<div class="col graph_bar graph-bar" id="score7">3.5</div>
							<div class="col graph_bar graph-bar" id="score8">4.0</div>
							<div class="col graph_bar graph-bar" id="score9">4.5</div>
							<div class="col graph_bar graph-bar" id="score10">5.0</div>
						</div>
					</div>

					<!-- 내 리뷰 -->
					<div style="width: 475px; height: 323px; position: relative;">
						<div style="width: 100%; height: 100%;" id="cont_myreview_container">
							<div id="cont_myreview" style=" height: 100%; width: 100%;">
								<a href="" id="myreview_link">
									<div class="card">
										<div class="card-header" id="myreview_user">Quote</div>
										<div class="card-body" style="height: 280px;">
											<blockquote class="blockquote mb-0">
												<div id="myreview_text">A well-known quote, contained in a
													blockquote element.</div>
												<%-- <footer class="blockquote-footer" id="myreview_footer">Someone famous in <cite title="Source Title"><%=nickname %></cite></footer> --%>
											</blockquote>
										</div>
									</div>
								</a>
							</div>
							<div style="position: absolute; bottom:0; left: 1px;">
								<fieldset class="rate">
                             		<input type="radio" id="rating10" name="rating" value="10"><label for="rating10" title="5점"></label>
                             		<input type="radio" id="rating9" name="rating" value="9"><label class="half" for="rating9" title="4.5점"></label>
                             		<input type="radio" id="rating8" name="rating" value="8"><label for="rating8" title="4점"></label>
                             		<input type="radio" id="rating7" name="rating" value="7"><label class="half" for="rating7" title="3.5점"></label>
                             		<input type="radio" id="rating6" name="rating" value="6"><label for="rating6" title="3점"></label>
                             		<input type="radio" id="rating5" name="rating" value="5"><label class="half" for="rating5" title="2.5점"></label>
                             		<input type="radio" id="rating4" name="rating" value="4"><label for="rating4" title="2점"></label>
                             		<input type="radio" id="rating3" name="rating" value="3"><label class="half" for="rating3" title="1.5점"></label>
                             		<input type="radio" id="rating2" name="rating" value="2"><label for="rating2" title="1점"></label>
                             		<input type="radio" id="rating1" name="rating" value="1"><label class="half" for="rating1" title="0.5점"></label>
                         		</fieldset>
							</div>
						</div>
						<div style="position: absolute; bottom: 0; right: 1px;">
							<button class="btn"
								style="background-color: #ff416c; color: white;" id="allReview">리뷰++</button>
							<button class="btn" id="reviewBtn"
								style="background-color: #ff416c; color: white;"
								data-bs-toggle="modal" data-bs-target="#exampleModal"></button>
						</div>
					</div>
				</div>
			</div>

		</div>

		<!-- 시놉시스 -->
		<div style="height: 235px;">
			<div
				style="background-color: #ffb2c4; color: white; height: 30px; font-size: 20px;">
				<b style="margin-left: 4px;">시놉시스</b>
			</div>
			<div style="font-size: 15px; margin: 4px;">
				${content.getDescription() }</div>
		</div>

		<!-- 배우정보 -->
		<div style="width: 100%; height: 300px;">

			<div class="carousel-container">
				<div class="carousel-slide">
					<div class="inner" id="lastClone">
						<%
						for (int i = creditList.size() - 6; i < creditList.size(); i++) {
						%>
						<div style="width: 201px; height: auto;">
							<%
							if (null == creditList.get(i)) {
							%>
							<img class="innerImage" src="resources/images/question.png"
								width="197px">
							<%
							} else {
							%>
							<img class="innerImage"
								src="http://image.tmdb.org/t/p/w342<%=creditList.get(i).getProfile_path()%>"
								width="197px" height="296px">
							<%
							}
							%>
						</div>
						<%
						}
						%>
					</div>

					<%
					int count = 0;
					for (int i = 0; i < (creditList.size() / 6); i++) {
					%>
					<div class="inner">
						<%
						for (int j = 0; j < 6; j++) {
						%>
						<div style="width: 201px; height: auto;">
							<%
							if (null == creditList.get(count)) {
							%>
							<img class="innerImage" src="resources/images/question.png"
								height="296px" width="197px" style="bottom: 0px;">
							<%
							} else {
							%>
							<img class="innerImage"
								src="http://image.tmdb.org/t/p/w342<%=creditList.get(count).getProfile_path()%>"
								width="197px" height="296px">
							<%
							}
							%>
						</div>
						<%
						count++;
						}
						%>
					</div>
					<%
					}
					%>

					<div class="inner" id="firstClone">
						<%
						for (int i = 0; i < 6; i++) {
						%>
						<div style="width: 201px; height: auto;">
							<%
							if (null == creditList.get(i)) {
							%>
							<img class="innerImage" src="resources/images/question.png"
								width="197px">
							<%
							} else {
							%>
							<img class="innerImage" alt="resource/images/person-x.svg"
								src="http://image.tmdb.org/t/p/w342<%=creditList.get(i).getProfile_path()%>"
								width="197px" height="296px">
							<%
							}
							%>
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

		</div>

	</div>

	<!-- 푸터 -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<!-- 모달창 -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">간편 리뷰 작성</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<input type="hidden" value="<%=contId%>" id="contId">
					<textarea cols="50" rows="12" id="postText"></textarea>
					<p id="show_length">length</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<!-- <button type="button" class="btn btn-primary" id="writeReview">Save changes</button> -->
					<button type="button" class="btn" id="writeReview" style="background-color: #ff416c; color:white;"
						data-bs-toggle="popover" data-bs-title="알림"
						data-bs-content="로그인이 필요한 작업입니다">완료</button>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
//캐러셀
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