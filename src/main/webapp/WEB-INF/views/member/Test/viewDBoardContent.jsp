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
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/member/unfound.css'/>">
</head>

<body>

<div id="header">
    <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
</div>

<input type="hidden" id="boardNum" name="boardNum" value="${dto.boardNum}">
		
작성자:	${dto.nickname}
제목:		${dto.title}
카테고리:	${dto.category}
	
작성 날짜:	${dto.edittedDate}
조회수:	${dto.viewCount}
추천수:	<div id="recommendNum">${dto.recommendNum}</div>

내용:		${dto.content}

<br>


<img src="<c:url value='/resources/images/member/well.svg'/>" width="50" height="50" id="recommendVal" data-val="like">


<br>
<button id="update">글 수정</button>
<button id="delete">글 삭제</button>
<button id="list">글 목록</button>




<hr>






<script type="text/javascript">

	$(function(){
		$("#update").on("click", function(){
			window.location.href = "<c:url value='/checkUpdatePost'/>/${dto.boardNum}";
		})
		$("#delete").on("click", function(){
			window.location.href = "<c:url value='/checkDeletePost'/>/${dto.boardNum}";
			//삭제된 게시물은 복구할 수 없습니다.
			//게시물을 삭제하시겠습니까?
		})
				
		$("#list").on("click", function(){
			window.location.href ="<c:url value='/viewDBoardList'/>";
		})
		
		$("#recommendVal").on("click", function(){
			var boardNum = $("#boardNum").val();
			var recommendVal = $("#recommendVal").data("val"); 

			if(recommendVal == "normal"){
				 $("#recommendVal").data("val", "like");
			} else if(recommendVal == "like") {
				 $("#recommendVal").data("val", "dislike");
			} else {
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
				    }
					if (recommendVal === "normal") {
				        $("#recommendVal").attr("src", "<c:url value='/resources/images/member/well.svg'/>"); 
				    }
					
                		},
           		error: function(error) {
                    console.error("추천 기능 에러:", error);
                }
			})
			
			
		})
	})

</script>

		
</body>

</html>
