<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>글</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<!-- 외부 css 파일 -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/member/unfound.css'/>">
	<!-- 부트 스트랩 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>


<!-- 헤더 네비게이션바 -->
<div id="header">
    <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
</div>
<hr>


<!-- 출력되는 페이지 -->
<input type="hidden" id="userBylike" name="userBylike" value="${userBylike}">
<input type="hidden" id="userBydislike" name="userBydislike" value="${userBydislike}">
<input type="hidden" id="boardNum" name="boardNum" value="${dto.boardNum}">
<input type="hidden" id="userKey" name="userKey" value="${userKey}">
<input type="hidden" id="recommendNum" name="recommendNum" value="${dto.recommendNum}">
<input type="hidden" id="disRecommendNum" name="disRecommendNum" value="${dto.disRecommendNum}">
작성자:	${dto.nickname}												
제목:		${dto.title}<br>
카테고리:	${dto.category}
작성 날짜:	${dto.edittedDate}
조회수:	${dto.viewCount}
추천수:	<span id="resultRecommendNum">${dto.recommendNum - dto.disRecommendNum}</span>
<br>
<p>${dto.content}</p>


<!-- 추천 상태에 따른 이미지 출력 -->
<span>
	<img src="<c:url value='/resources/images/member/well.svg'/>" width="50" height="50" id="recommendVal" data-val="like">
</span>
<span>
	<img src="<c:url value='/resources/images/member/well.svg'/>" width="50" height="50" id="disrecommendVal" data-val="dislike">
</span>
<br>


<!-- post로 글 수정 / 삭제 / 목록 -->
<div>
	<form action="<c:url value='/checkUpdatePost'/>/${dto.boardNum}" method="post" style="display: inline-block;">
		<input type="submit" id="update" value="글 수정">
	</form>
	<form action="<c:url value='/checkDeletePost'/>/${dto.boardNum}" method="post" style="display: inline-block;">
		<input type="submit" id="delete" value="글 삭제">
	</form>
	<form action="<c:url value='/viewDBoardList/boardNum'/>" method="get" style="display: inline-block;">
		<input type="submit" id="list" value="글 목록">
	</form>
</div>
<hr>


<!-- 이전글 / 다음글 출력 -->
	<c:if test="${prev != null}">
		<p>
		이전 글: 
		    글번호: 	${prev.boardNum} | 카테고리: ${prev.category} |
		    제목: 	<span onclick="submitForm(${prev.boardNum})">${prev.title}</span> |
		    닉네임: 	${prev.nickname} |
		    작성 날짜:	${prev.edittedDate} |
		    조회수: 	${prev.viewCount} |
		    추천수: 	${prev.recommendNum - prev.disRecommendNum}
	    </p>
	</c:if>
	<c:if test="${next != null}">	
		<p>
		다음 글: 
		    글번호: 	${next.boardNum} | 카테고리: ${next.category} |
		    제목: 	<span onclick="submitForm(${next.boardNum})">${next.title}</span> |
		    닉네임: 	${next.nickname} |
		    작성 날짜:	${next.edittedDate} |
		    조회수: 	${next.viewCount} |
		    추천수: 	${next.recommendNum - next.disRecommendNum}
	    </p>
	</c:if>


<script type="text/javascript">

	//익명 유저가 추천/비추천을 했을 경우, 그 상태 유지
	$(function(){
		
		if($("#userBylike").val() == "like"){
			$("#recommendVal").attr("data-val", "likeChecked");
	        $("#recommendVal").attr("src", "<c:url value='/resources/images/member/like.svg'/>"); 
		}
		if($("#userBydislike").val() == "dislike"){
			$("#disrecommendVal").attr("data-val", "dislikeChecked");
	        $("#disrecommendVal").attr("src", "<c:url value='/resources/images/member/dislike.svg'/>"); 
		}
	})
	
	

	// 이전글, 다음글에서 제목을 누르면 세부글로 이동
	function submitForm(boardNum) {
	    window.location.href ="<c:url value='/viewDBoardContent'/>/"+boardNum;
	}
	
	//추천을 위한 이미지 클릭 시 추천 숫자 변경 및 이미지 변경을 위한 ajax
	$("#recommendVal").on("click", function(){
		var userKey = $("#userKey").val();
		var boardNum = $("#boardNum").val();
		var recommendVal = $(this).attr("data-val");
		console.log("추천 버튼 클릭")
		console.log(recommendVal)
		
		
		if (recommendVal === "like") {
			$.ajax({
				type: "POST",
				url: "<c:url value='/increaseDBoardRecommendNum'/>",
				data: {
					userKey: userKey,
					boardNum: boardNum,
					recommendVal: recommendVal
				},
				success: function(response) {
						console.log(response)
						$("#recommendNum").val(response)
						$("#resultRecommendNum").text($("#recommendNum").val()-$("#disRecommendNum").val())
						$("#recommendVal").attr("data-val", "likeChecked");
				        $("#recommendVal").attr("src", "<c:url value='/resources/images/member/like.svg'/>"); 
	            },
	          	error: function(error) {
	                console.error("추천 기능 에러:", error);
	            }
			})
		} else (
		   	alert("좋아요는 1일 1회만 가능합니다.")
		)
		
		
	})
	
	//비추천을 위한 이미지 클릭 시 추천 숫자 변경 및 이미지 변경을 위한 ajax
	$("#disrecommendVal").on("click", function(){
		var userKey = $("#userKey").val();
		var boardNum = $("#boardNum").val();
		var disrecommendVal = $(this).attr("data-val");
		console.log("비추천 버튼 클릭")
		console.log(disrecommendVal)
		
		if (disrecommendVal === "dislike") {
			$.ajax({
				type: "POST",
				url: "<c:url value='/decreaseDBoardRecommendNum'/>",
				data: {
					userKey: userKey,
					boardNum: boardNum,
					disrecommendVal: disrecommendVal
				},
				success: function(response) {
						$("#disRecommendNum").val(response)
						$("#resultRecommendNum").text($("#recommendNum").val()-$("#disRecommendNum").val())
						$("#disrecommendVal").attr("data-val", "dislikeChecked");
				        $("#disrecommendVal").attr("src", "<c:url value='/resources/images/member/dislike.svg'/>"); 
	            },
	          	error: function(error) {
	                console.error("비추천 기능 에러:", error);
	            }
			})
		} else (
		    alert("싫어요는 1일 1회만 가능합니다.")
		)
	})
	
	
	
	

</script>


<!-- 부트 스트랩 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script type="text/javascript">
var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
  return new bootstrap.Popover(popoverTriggerEl)
})
</script>
</body>
</html>
