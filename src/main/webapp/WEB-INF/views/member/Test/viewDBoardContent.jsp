<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 글 목록 -->
<head>
	<meta charset="UTF-8">
	<title>글</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<!-- 외부 css 파일 -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/member/unfound.css'/>">
</head>

<body>


<!-- 헤더 네비게이션바 -->
<div id="header">
    <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
</div>
<hr>


<!-- 출력되는 페이지 -->
<input type="hidden" id="boardNum" name="boardNum" value="${dto.boardNum}">



<!-- ********************************************************************************************** -->
<input type="hidden" id="recommendBoardNum" name="recommendBoardNum" value="${recommendBoardNum}">
<input type="hidden" id="recommendState" name="recommendState" value="${recommendState}">
<!-- ********************************************************************************************** -->
<!-- 
	넘어온 value(추천|비추천}노말 상태를 저장한다.
	이걸 스크립트에서 확인하고 data-val과 이미지를 수정한다.
 -->

작성자:	${dto.nickname}												<!-- 같은 닉네임으로 검색 기능(확인창 출력) -->
제목:		${dto.title}<br>
카테고리:	${dto.category}
작성 날짜:	${dto.edittedDate}											<!-- 당일 날짜는 시분 || 그 외 날짜는 연월일 -->
조회수:	${dto.viewCount}
추천수:	<span id="recommendNum">${dto.recommendNum}</span><br>
<br>
<p>${dto.content}</p>


<!-- 추천 상태에 따른 이미지 출력 -->
<div>
	<img src="<c:url value='/resources/images/member/well.svg'/>" width="50" height="50" id="recommendVal" data-val="like">
</div>
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
		    추천수: 	${prev.recommendNum}		
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
		    추천수: 	${next.recommendNum}
	    </p>
	</c:if>


<script type="text/javascript">

	// 추천|비추|일반 상태 ***************************************
	$(function(){

		var recommendBoardNum = $("#recommendBoardNum").val();
		var recommendState =  $("#recommendState").val();
		var boardNum = $("#boardNum").val();
		
		console.log("recommendBoardNum: "+recommendBoardNum);
		console.log("recommendState: "+recommendState);
		
		if(recommendBoardNum == boardNum){
		    recommendState();			
	    
		    function recommendState(){
		        
		        if(recommendState == "like"){
		        	$("#recommendVal").attr("src", "<c:url value='/resources/images/member/like.svg'/>"); 
		        	 $("#recommendVal").data("val", "like");
		        } else if(recommendState == "dislike"){
		        	 $("#recommendVal").attr("src", "<c:url value='/resources/images/member/dislike.svg'/>"); 
		        	 $("#recommendVal").data("val", "dislike");
		        } 
		        
		        console.log("현재 data-val: " + $("#recommendVal").data("val"));
		    }
		}
	});
	// 추천|비추|일반 상태 ***************************************
	
	
	
	
	
	
	// 이전글, 다음글에서 제목을 누르면 세부글로 이동
	function submitForm(boardNum) {
	    window.location.href ="<c:url value='/viewDBoardContent'/>/"+boardNum;
	}
	
	//추천을 위한 이미지 클릭 시 추천 숫자 변경 및 이미지 변경을 위한 ajax
	$("#recommendVal").on("click", function(){
		var boardNum = $("#boardNum").val();
		var recommendVal = $("#recommendVal").data("val")
		
		if(recommendVal == "normal"){
			 $("#recommendVal").data("val", "like");
		} else if(recommendVal == "like") {
			 $("#recommendVal").data("val", "dislike");
		} else if(recommendVal == "normal"){
			 $("#recommendVal").data("val", "normal");
		}
		
		$.ajax({
			type: "POST",
			url: "<c:url value='/updateDBoardRecommendNum'/>",
			data: {
				boardNum: boardNum,
				recommendVal: recommendVal
			},
			success: function(response) {
				$("#recommendNum").text(response)
				if (recommendVal === "like") {
			        $("#recommendVal").attr("src", "<c:url value='/resources/images/member/like.svg'/>"); 
			    } else if (recommendVal === "dislike") {
			        $("#recommendVal").attr("src", "<c:url value='/resources/images/member/dislike.svg'/>"); 
			    } else if (recommendVal === "normal") {
			        $("#recommendVal").attr("src", "<c:url value='/resources/images/member/well.svg'/>"); 
			    }
               },
          		error: function(error) {
                   console.error("추천 기능 에러:", error);
               }
		})
	})

</script>

		
</body>

</html>
