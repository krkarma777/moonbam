<%@page import="com.moonBam.dto.MemberDTO"%>
<%@page import="com.moonBam.dto.ContentDTO"%>
<%@page import="com.moonBam.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
	#body{
		padding-left: 10%;
		padding-right: 10%;
	}
	#postDate{
		font-size: 70%;
		color: gray;
	}
	#contImg{
		text-align: right;
	}
	.like_btn{
		-webkit-user-select:none;
		-moz-user-select:none;
		-ms-user-select:none;
		user-select:none
	}
	#btns{
		
		margin-top: 10px;
		margin-bottom: 5px;
	}
	#like_wrapper2{

	}
	#like_wrapper{
		width: 100px;
		border: none;
		border-radius: 8px;
		padding-left: 0px;
		padding-right: 0px;
	}
	#show_more{
		border: none;
		border-radius: 8px;
		font-size: 20px;
		background-color: inherit;
	}
	
	#show_more_wrapper{
		text-align: right;
	}

	.dropdown-item:focus{
		/* background: gray; */
	}
	.noEffect{
		text-decoration: none;
		color: black;
	}
	
	.link-icon { position: relative; display: inline-block; width: auto; font-size: 14px; font-weight: 500; color: #333; margin-right: 10px; padding-top: 50px; background-repeat: no-repeat;}
	.link-icon.twitter { background-image: url(./resources/images/icon-twitter.png); background-repeat: no-repeat; }
	.link-icon.facebook { background-image: url(./resources/images/icon-facebook.png); background-repeat: no-repeat; } 
	.link-icon.kakao { background-image: url(./resources/images/icon-kakao.png); background-repeat: no-repeat; }
	.link-icon.copy { background-image: url(./resources/images/icon-copy.png); background-repeat: no-repeat; }

	#postText{
		padding-bottom: 50px;
		overflow: hidden;
	}
	
	#top{
		margin-top: 30px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	<%
	ReviewDTO review = (ReviewDTO)request.getAttribute("review");
	Long postId = review.getPostId(); 
	String userId = review.getUserId();
	String postDate = review.getPostDate();
	String postText = review.getPostText();
	String nickname = review.getNickname();
	String score = review.getScore();
	String isLike = review.getIsLike();
	String likeNum = review.getLikeNum();
	Long contId = review.getContId();
	
	ContentDTO content = (ContentDTO)request.getAttribute("content");
	String contTitle = content.getContTitle();
	String contImg = content.getContImg();
	contImg = "http://image.tmdb.org/t/p/"+"w154/"+contImg;
	//http://image.tmdb.org/t/p/w342/awmVj0xmD8CP4g0uD7dUrM8nqi.jpg

	String mesg = (String)request.getAttribute("mesg");
	
	
	%>
	if("<%=mesg%>"!="null"){
		alert("<%=mesg%>");
	}
	$(document).ready(function(){
		$("#like_wrapper").on("click", likeToggle);
		$("#share_twitter").on("click", shareTwitter);
		$("#share_facebook").on("click", shareFacebook);
		$("#share_copy").on("click", shareCopy); // 링크복사
	});
	
	//트위터 공유하기
	function shareTwitter() {
	    var sendText = "<%=contTitle%> - <%=userId%>님의 리뷰"; // 전달할 텍스트
	    var sendUrl = window.location.href; // 전달할 URL
	    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl, "_blank", "width=600,height=400");
	}
	
	//링크 복사
	function shareCopy(){
		window.navigator.clipboard.writeText(window.location.href);
	}
	//페이스북 공유 api - 로컬호스트에선 오류발생
	function shareFacebook(){
		var url = window.location.href;
		var type = 'article'; // website, article 등등
		var title = '<%=contTitle%> - <%=userId%>님의 리뷰';
		var description = '<%=postText%>';
		var imgUrl = '<%=contImg%>';

		if( ! $('meta[property="og:url"').attr('content') ) {
			$('head').append('<meta property="og:url" content="{' + url + '}" />');
		}
		if( ! $('meta[property="og:type"').attr('content') ) {
			$('head').append('<meta property="og:type" content="{' + type + '}" />');
		}
		if( ! $('meta[property="og:title"').attr('content') ) {
			$('head').append('<meta property="og:title" content="{' + title + '}" />');
		}
		if( ! $('meta[property="og:description"').attr('content') ) {
			$('head').append('<meta property="og:description" content="{' + description + '}" />');
		}
		if( ! $('meta[property="og:image"').attr('content') ) {
			$('head').append('<meta property="og:image" content="{' + imgUrl + '}" />');
			
		var linkUrl = window.location.href;
		window.open("https://www.facebook.com/sharer/sharer.php?u=" + encodeURIComponent(linkUrl), "_blank", "width=600,height=400");
		}
	}
	// 공감버튼 토글
	function likeToggle(){

		//버튼에 적혀있는 하트 공백제거해서 가져오기
		var statement = $(".like_btn").text().trim();
		var isLike = 0;
		
		// 공백하트인지 꽉찬 하트인지 검사해서 반대로 바꾸기
		if(statement == "♥"){
			$(".like_btn").text("♡");
			isLike = 0;
		} else if(statement == "♡"){
			$(".like_btn").text("♥");
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
						"postId": <%=postId%>,
						"isLike": isLike
					},
					dataType: "text",
					success: function(data, status, xhr){
						if(isLike==0)
							$("#likeNum").text($("#likeNum").text()-1);
						else{
							$("#likeNum").text($("#likeNum").text()-1+2);
						}
					},
					error: function(xhr, status, e){
					}
				}//json	
			);//ajax
		<%}%>//if
	}
</script>
</head>
<body>
	<%-- <jsp:include page="//common/navbar.jsp"></jsp:include> --%>
	<div class="row" id="body">
		<div class="row" id="top">
			<div class="col-9">
				<div class="row"><%=userId %> <span id="postDate"><%=postDate %></span></div>
				<div class="row"><a href="content-page?contId=<%=contId%>" class="noEffect"><%=contTitle %></a><span id="score">☆ <%=Double.parseDouble(score)/2 %></span></div>
			</div>

			<div class="col-3" id="contImg"><a href="content-page?contId=<%=contId%>"><img src="<%=contImg %>" width="100" height="100"></a></div>
			<hr>
		</div>
		<div class="row" id="middle">
			<!-- 리뷰 내용  -->
			<div id="postText"><%=postText %></div>
			
			<!-- 버튼 그룹 -->
			<div id="btns" class="row">
				<!-- 좋아요 버튼 -->
				<div class="col" id="like_wrapper2">
					<button id="like_wrapper">
						<span class="like_btn" style="color:red">
						<%if("1".equals(isLike)){%>♥ 
						<%}else{ %>♡<%} %>
						</span>
						
						<span id="likeNum"><%=(likeNum!=null)?(likeNum):("") %></span>
					</button>
				</div>
				<!-- 더보기 버튼  -->
				<div class="col" id="show_more_wrapper">
					<!-- <button id="show_more">
						...
					</button> -->
					<div class="btn-group dropup">
					  <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
					    ...
					  </button>
					  <ul class="dropdown-menu">
					    <li><a href="#" class="dropdown-item noEffect" data-bs-toggle="modal" data-bs-target="#exampleModal">공유</a></li>
					    <li><a href="report?postId=<%=postId %>&reason=부적절한리뷰" class="dropdown-item noEffect">리뷰신고</a></li>
					    <!-- <li><a href="#" class="dropdown-item noEffect">AI</a></li> -->
					  </ul>
					</div>
				</div>
			</div>
			
			
			
			<hr>
		</div>
		<div class="row" id="bottom"></div>
		
		
		<jsp:include page="../board/commentMain.jsp"></jsp:include>
	</div>
	
	<!-- 공유기능 모달창 -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">공유하기</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <!--한칸 더 추가되는 버그 수정 필요  -->
	      <div class=" modal-body d-flex justify-content-center" role="group" aria-label="Basic outlined example">
	      		<a id="btnCopy" class="link-icon copy" href="javascript:shareCopy();" />링크복사
				<a id="btnTwitter" class="link-icon twitter" href="javascript:shareTwitter();" />트위터
				<a id="btnFacebook" class="link-icon facebook" href="javascript:shareFacebook();" />페이스북    
				<a id="btnKakao" class="link-icon kakao" href="javascript:shareKakao();" >카카오톡</a>
				
	      </div>
	    </div>
	  </div>
	  
	<!-- 링크복사 알림창 -->
	<div class="toast-container position-fixed bottom-0 end-0 p-3">
	  <div id="liveToast" class="toast align-items-center text-bg-primary" role="alert" aria-live="assertive" aria-atomic="true">
	    <div class="d-flex">
		    <div class="toast-body">
		      링크가 복사되었습니다 !
		    </div>
		    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
	 	</div>
	  </div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<!--링크복사 알림창 부트스트랩 js  -->
	<script>
		const toastTrigger = document.getElementById('btnCopy')
		const toastLiveExample = document.getElementById('liveToast')
	
		if (toastTrigger) {
		  const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
		  toastTrigger.addEventListener('click', () => {
		    toastBootstrap.show()
		  })
		}
	</script>
</body>
</html>