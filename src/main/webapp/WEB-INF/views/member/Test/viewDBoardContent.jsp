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
    <style>
        body {
            padding: 20px;
            max-width: 960px;
            margin: auto;
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #212529;
        }
        .card {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .card-body {
            padding: 20px;
        }
        .card-title {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .card-subtitle {
            color: #6c757d;
            margin-bottom: 10px;
        }
        .card-text {
            margin-bottom: 20px;
        }
        .btn-group {
            margin-right: 10px;
        }
        .btn {
            margin-bottom: 10px;
        }
        hr {
            margin-top: 20px;
            margin-bottom: 20px;
            border: 0;
            border-top: 1px solid #dee2e6;
        }
        .prev-next {
            margin-bottom: 20px;
        }
        .prev-next p {
            margin-bottom: 5px;
        }
        .prev-next span {
            cursor: pointer;
            color: blue;
        }
        .prev-next span:hover {
            text-decoration: underline;
        }
	    .button-style {
	        font-size: 12px;
	    }
	    .button-container {
	        display: flex;
	        align-items: center; 
	    }
		.nameText{
        	font-size: 13px;
        }
	    .timeText{
        	font-size: 13px;
        }
        #titleText{
        	cursor: pointer;
        }
    </style>
</head>
<body>

<div id="header">
    <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
</div>
<hr>

<input type="hidden" id="userBylike" name="userBylike" value="${userBylike}">
<input type="hidden" id="userBydislike" name="userBydislike" value="${userBydislike}">
<input type="hidden" id="boardNum" name="boardNum" value="${dto.boardNum}">
<input type="hidden" id="userKey" name="userKey" value="${userKey}">
<input type="hidden" id="recommendNum" name="recommendNum" value="${dto.recommendNum}">
<input type="hidden" id="disRecommendNum" name="disRecommendNum" value="${dto.disRecommendNum}">

<div class="card">
    <div class="card-body">
    
    	<!-- 글 제목과 카테고리 -->
        <h5 class="card-title">[${dto.category}] ${dto.title}</h5>
		<div class="row">
		
			<!-- 글 작성자 / 작성 날짜(수정 날짜) / 조회수 / 추천수 -->
		    <div class="col">
		        <div class="card-subtitle mb-2 text-muted">작성자: ${dto.nickname}</div>
		    </div>
		    <div class="col-auto">
		        <div class="mb-3">
		            <div class="card-subtitle mb-2 text-muted">
		            	<span>작성 날짜: ${dto.edittedDate}</span> |
		            	<span>조회수: ${dto.viewCount > 9999 ? '9999+' : dto.viewCount}</span> |
		            	<span>추천수: <span id="resultRecommendNum">${Math.min(Math.max(dto.recommendNum - dto.disRecommendNum, -999), 999)}</span></span>
		            </div>
		        </div>
		    </div>
		    <hr>
		</div>
		
		<!-- 글 내용 -->
        <div class="card-text">${dto.content}</div>
    </div>
    
    <!-- 추천 / 비추천 -->
    <div class="text-center mt-3"> 
        <img src="<c:url value='/resources/images/member/well.svg'/>" width="50" height="50" id="recommendVal" data-val="like">
        <img src="<c:url value='/resources/images/member/well.svg'/>" width="50" height="50" id="disrecommendVal" data-val="dislike">
    </div>
	<br>
</div>


<!-- 글 수정 / 글 삭제 / 글 목록 -->
<div class="button-container d-flex justify-content-end">
    <form action="<c:url value='/checkUpdatePost'/>/${dto.boardNum}" method="post" style="display: inline-block;">
        <input type="submit" id="update" value="글 수정" class="button-style">
    </form>
    <form action="<c:url value='/checkDeletePost'/>/${dto.boardNum}" method="post" style="display: inline-block;">
        <input type="submit" id="delete" value="글 삭제" class="button-style">
    </form>
    <form action="<c:url value='/viewDBoardList'/>" method="get" style="display: inline-block;">
        <input type="submit" id="list" value="글 목록" class="button-style">
    </form>
</div>


<hr>



<!-- 이전 글과 다음 글(없을 경우, 출력되지 않음) -->
<table width="100%">
    <colgroup>
        <col width="9%">
        <col width="5%">
        <col width="5%">
        <col width="30%">
        <col width="10%">
        <col width="10%">
        <col width="5%">
        <col width="5%">
    </colgroup>
    <div class="prev-next">
        <c:if test="${prev != null}">
            <tr>
                <td>이전 글:</td>
                <td><span class="badge bg-info">${prev.boardNum}</span></td>
                <td><span class="badge bg-secondary">${prev.category}</span></td>
                <td><span class="text-start"><span id="titleText" class="link-primary" onclick="submitForm(${prev.boardNum})">${prev.title}</span></span></td>
                <td><span class="nameText">${prev.nickname}</span></td>
                <td><span class="timeText">${prev.edittedDate}</span></td>
                <td><span class="badge bg-warning text-dark">${prev.viewCount > 9999 ? '9999+' : prev.viewCount}</span></td>
				<td><span class="badge bg-success">${Math.min(Math.max(prev.recommendNum - prev.disRecommendNum, -999), 999)}</span></td>

            </tr>
        </c:if>
        <c:if test="${next != null}">
            <tr>
                <td>다음 글:</td>
                <td><span class="badge bg-info">${next.boardNum}</span></td>
                <td><span class="badge bg-secondary">${next.category}</span></td>
                <td><span class="text-start"><span id="titleText" class="link-primary" onclick="submitForm(${next.boardNum})">${next.title}</span></span></td>
                <td><span class="nameText">${next.nickname}</span></td>
                <td><span class="timeText">${next.edittedDate}</span></td>
                <td><span class="badge bg-warning text-dark">${next.viewCount > 9999 ? '9999+' : next.viewCount}</span></td>
				<td><span class="badge bg-success">${Math.min(Math.max(next.recommendNum - next.disRecommendNum, -999), 999)}</span></td>
            </tr>
        </c:if>  
    </div>
</table> 

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
	    window.location.href ="<c:url value='/viewDBoardContent'/>?boardNum="+boardNum;
	}
	
	
	//추천을 위한 이미지 클릭 시 추천 숫자 변경 및 이미지 변경을 위한 ajax
	$("#recommendVal").on("click", function(){
		var userKey = $("#userKey").val();
		var boardNum = $("#boardNum").val();
		var recommendVal = $(this).attr("data-val");
		
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
</body>
</html>
