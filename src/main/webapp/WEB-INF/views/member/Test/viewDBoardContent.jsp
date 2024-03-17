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



<form action="<c:url value='/modifyPost'/>/${dto.boardNum}" method="post">
	<input type="submit" id="update" value="글 수정">
</form>
<button id="delete">글 삭제</button>
<button id="list">글 목록</button>

<script type="text/javascript">

	$(function(){
		
			//아이디와 글의 아이디가 같으면 바로 수정화면
			//아이디와 글의 아이디가 다르면 비밀번호 확인창
			//비밀번호 일치하면 수정 가능
			//비밀번호가 다르면 경고창, 취소하면 이전 화면으로 이동
			
		$("#delete").on("click", function(){
			window.location.href = "<c:url value='/deletePost'/>/${dto.nickname}/${dto.boardNum}";
			//아이디와 글의 아이디가 같으면 바로 삭제 확인화면
			//아이디와 글의 아이디가 다르면 비밀번호 확인창
			//삭제화면에서 경고 + 취소버튼 / 삭제버튼
			
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
					recommendVal: recommendVal,
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
