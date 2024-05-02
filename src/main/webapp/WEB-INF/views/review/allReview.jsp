<%@page import="com.moonBam.dto.ContentDTO"%>
<%@page import="com.moonBam.dto.MemberDTO"%>
<%@page import="com.moonBam.dto.ReviewPageDTO"%>
<%@page import="com.moonBam.dto.ReviewDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ReviewPageDTO rpDTO = (ReviewPageDTO)request.getAttribute("rpDTO");
List<ReviewDTO> reviewList = rpDTO.getList();

MemberDTO member = (MemberDTO) request.getAttribute("member");
String userId = null;
String nickname = null;
if (member != null) {
	userId = member.getUserId();
	nickname = member.getNickname();
}

String contId = (String)request.getAttribute("contId");
String contTitle = (String)request.getAttribute("contTitle");

ReviewDTO myReview = (ReviewDTO)request.getAttribute("myreview");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<sec:authorize access="isAuthenticated()">
	<script>
		$(document).ready(function(){
			$("#writeReview").on("click", writeReview);  //리뷰작성
			
		});//ready
	</script>
</sec:authorize>
<script type="text/javascript">
const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]')
const popoverList = [...popoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl))

	$(document).ready(function(){
		$("#postText").on("keyup", check_length); 	 //글자수 제한
		//$("#writeReview").click(writeReview); //리뷰 작성
		$(".like_btn").on("click", likeToggle); 	// 공감버튼 클릭
	})
	
	//최대글자수
	var max_length = 200;
	function check_length(){
		//console.log(this.value.length);
		var length = this.value.length;
		if(length>max_length){
			this.value = this.value.substr(0, max_length);
			this.focus();
		}
		$("#show_length").text(length+"/"+max_length);
	}
	
	function writeReview(){
		var contId = $("#contId").val();
		var postText = $("#postText").val().substr(0, max_length);
		if(postText.length!=0 && contId !="null"){
			$.ajax(
				{
					type: "post",
					url:"my-review",
					data: {
						"contId": contId,
						"userId": "<%=userId%>",
						"nickname": "<%=nickname%>",
						"postText": postText,
						"contTitle": "<%=contTitle%>"
					},
					dataType: "text",
					success: function(data, status, xhr){
						var jsonData = JSON.parse(data);
						location.reload(true);
					},
					error: function(xhr, status, e){
						console.log("실패: " + xhr.status);
					}
				}//json
			);//ajax
		}//내용검사if
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
		<%if(userId!=null){%>
		
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
	
</script>
<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
}
a{
	text-decoration: none;
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
</style>
</head>

<body class="bg-light" style="height: 100vh;">
	<!-- 네비게이션바 -->
	<jsp:include page="../common/navBar.jsp"></jsp:include>
	<div style="height: 50px"></div>
	
	<!-- 바디 -->
	<div style="height: 910px; width: 1200px; margin: auto;">
		<!-- 상단 버튼 -->
		<div style="">
			<!-- 개설 버튼 -->
			<button type="button" class="btn" style="float:right; background-color: #ff416c; color:white; margin-left: auto; margin-bottom: 5px;" 
			id="createCommunity" data-bs-toggle="modal" data-bs-target="#exampleModal"><b>
				<%if(myReview==null){ %>
					리뷰쓰기
				<%}else{ %>
					수정하기
				<%} %>
			</b></button>
		</div>
		
		<!-- 리뷰목록 -->
		<div style="width:1200px; height:795px; border: #ffb2c4 solid 1px;">
		<%if(reviewList==null){ %>
			<span style="margin: auto;">리뷰가 존재하지 않습니다.</span>
		<%}else { %>
			<table style="width:100%; height:100%;">
				<%int count = 0;
				for(int i=1; i<=3; i++){ %>
					<tr style="width: 1200px; height: 265px;">
						<%for(int j=1; j<=4; j++){
							if(count==reviewList.size()) break;
							String score = reviewList.get(count).getScore();
							if(score==null){
								score="0";
							}
						%>
							<td id="<%=count%>" style="width: 300px; height: 265px; float: left">
								<div class="border" style="width: 300px; height: 265px;">
									<div class="border-bottom" style="height: 40px; width: 299px; background-color: #ffb2c4; align-content: center; font-size: 19px;">
										&nbsp;<%=reviewList.get(count).getNickname() %>
									</div>
									<div class="border-top" style="height: 195px; width: 299px; font-size: 18px;">
										<a href="review?postId=<%=reviewList.get(count).getPostId() %>" style="color:black;">&nbsp;<%=reviewList.get(count).getPostText() %></a>
									</div>
									<div id="review_score">
										<span>&nbsp;☆ <%=Double.parseDouble(score)/2 %></span>
										<span class="like_btn" style="color:red" data-postId="<%=reviewList.get(count).getPostId() %>">
										<%if("1".equals(reviewList.get(count).getIsLike())){
										%>♥ 
										<%}else{ 
											//System.out.println("♡"+reviewList.get(index).getIsLike());
										%>♡
										<%} %>
							</span>
							<span id="likeNum<%=reviewList.get(count).getPostId() %>"><%=(reviewList.get(count).getLikeNum()!=null)?(reviewList.get(count).getLikeNum()):("") %></span>
						</div>
								</div>
							</td>
						<%count++;} %>
					</tr>
				<%} %>
			</table>
			
			<!-- 페이지네이션 -->
		<div style="margin-top: 5px; width:auto;">
			<jsp:include page="reviewPage.jsp"></jsp:include>
		</div>
		<%} %>
		</div>
		
	</div>
	<!-- 푸터 -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">간편 리뷰 작성</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      	<input type="hidden" value="<%=contId%>" id="contId">
	        <textarea cols="50" rows="12" id="postText"></textarea>
	        <p id="show_length">0/200</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <!-- <button type="button" class="btn btn-primary" id="writeReview">Save changes</button> -->
	        <button type="button" class="btn" style="background-color: #ff416c; color:white;" id="writeReview" data-bs-toggle="popover" data-bs-title="알림" data-bs-content="로그인이 필요한 작업입니다">완료</button>
	      </div>
	    </div>
	  </div>
	</div>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>