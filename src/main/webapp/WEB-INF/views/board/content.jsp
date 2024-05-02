<%@page import="com.moonBam.dto.board.PostInfoDTO"%>
<%@page import="com.moonBam.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<%
	String postId = request.getParameter("postId");
%>
<meta charset="UTF-8">
<title>문화인들의 밤</title>




<!-- Bootstrap CSS -->
<link
	href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
	
<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	
	
<!--  -->	
<script>
function scrollToTop() {
    window.scrollTo(0, 0);
}

function scrollToBottom() {
    window.scrollTo(0, document.body.scrollHeight);
}

function scrollToComments() {
    var comments = document.getElementById("commentHead"); // 댓글창의 ID를 가정
    comments.scrollIntoView();
}

</script>
	


<script type="text/javascript">
$("document").ready(function() {
	// 삭제 버튼 클릭 이벤트 핸들러

	$(document).on('click', '.delete-post-btn', function() {
		// 게시글 ID 가져오기
		var postId = $(this).data('post-id');

		if (confirm('이 게시글을 삭제하시겠습니까?')) {
			// $.ajax({
			// 	url: '/acorn/api/post/' + postId, // 요청할 URL
			// 	type: 'DELETE', // HTTP 메소드
			// 	success: function(response) {
			// 		alert(response.message);
			// 		window.location.reload();
			// 	},
			// 	error: function(xhr) {
			// 		alert('삭제 실패: ' + xhr.responseJSON.message);
			// 	}
			// });
			alert("관리자 삭제된 게시글 db 연동중")
		}
	});
	$("#commentHead").click(function () {
		var url = "/acorn/board/postLike?postId=" + <%= request.getParameter("postId")%>;
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			success: function (response) {
				if (!response.success) {
					/* alert(response.message); */
				} else {
					$("#likeNum").text(response.likeCount);
				}
			},
			error: function (error) {
				console.error("에러:", error);
				alert("에러 발생");
			}
		});
	});
	<%
    MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");
    String userId="";
    if(dto!=null){
        userId = dto.getUserId();
    }
    %>

	<sec:authorize access="isAuthenticated()">
	var userId = '<sec:authentication property="principal.username" />';
	$("#scrap").on("click", function () {
		$.ajax({
			url: "/acorn/scrap",
			type: "post",
			dataType: "json",
			data: {
				postId: <%= postId %>,
				userId: userId
			},
			success: function (response) {
				if (response.success) {
					alert(response.message);
				} else {
					alert(response.message);
				}
			},
			error: function (xhr, status, error) {
				console.log("에러 발생", status, error);
				alert("에러 발생: " + error);
			}
		});
	});
	</sec:authorize>

	$.ajax({
		type: 'get',
		url: '/acorn/api/post/' +<%= postId %>,
		success: function (response) {
			var post = response.pDTO;
			var isAuthorized = response.isAuthorized;

			// 날짜 파싱 및 포매팅
			var date = new Date(post.postDate);
			var formattedDate = date.toLocaleDateString('ko-KR', {
				year: 'numeric',
				month: 'long',
				day: 'numeric',
				hour: '2-digit',
				minute: '2-digit',
				second: '2-digit'
			});
			//글 내용
			var postOneView = '<div class="container mt-4">' +
					'<div class="post-section">' +
					'<div class="post-title">' +
					'<h3>' + escapeHtml(response.pDTO.postTitle) + '</h3>' +
					'</div>' +
					'<div class="post-meta d-flex justify-content-between">' +
					'<div>' +
					'<small>' +
					'작성자: ' + post.nickname + ' | ' +
					'작성일: ' + formattedDate + // EL(fmt:formatDate) 대신 그대로 post.postDate 사용
					'</small>' +
					'</div>' +
					'<div>' +
					'<small>' +
					'조회수: ' + post.viewNum + ' | ' +
					'추천: ' + post.likeNum + ' | ' +
					'댓글: ' + post.commentCount +
					'</small>' +
					'</div>' +
					'</div>' +
					'<hr>' +
					'<div class="cpost-content">' +
					post.postText +
					'</div>' +
					'</div>' +
					'</div>';//

			//수정,삭제 버튼
			var updatedel = '';
			if (isAuthorized) {
				updatedel = `
				            <a href="/acorn/board/edit?postId=<%= request.getParameter("postId") %>&bn=<%=request.getParameter("bn")%>"><button type="button" class="btn btn-action btn-spacing">수정</button></a>
							<a href="javascript:void(0);" data-post-id="<%= postId %>" class="delete-post-btn btn btn-danger">삭제</a>
				    `;
			}//

			$('#postOneView').html(postOneView);
			$('#updatadel').html(updatedel);

		},
		error: function (xhr, status, error) {
			console.log("에러 발생 => ", error);
		}
	});//end ajax
	function escapeHtml(text) {
		var map = {
			'&': '&amp;',
			'<': '&lt;',
			'>': '&gt;',
			'"': '&quot;',
			"'": '&#039;'
		};
		return text.replace(/[&<>"']/g, function(m) { return map[m]; });
	}
	

});//end doc


</script>


	<!-- 스타일 태그 시작 -->
	<style>
		.searchInput {
			width: 70vh;
		}

		/* 댓글 섹션 위에 마진을 추가하는 새로운 CSS 클래스 */
		.comment-section {
			margin-top: 10px;
		}

		/* 네비게이션바 고정 */
		.fixed-top {
			position: fixed;
			top: 0;
			width: 100%;
			z-index: 1030; /* 다른 요소들 위에 표시되도록 z-index 설정 */
		}

		.navbar-light {
			background-color: #f8f9fa; /* 네비게이션바의 배경색 */
		}

		/* 검색 버튼 색상 */
		.btn-outline-success {
			color: #28a745;
			border-color: #28a745;
		}

		.btn-outline-success:hover {
			color: white;
    background-color: #28a745;
    border-color: #28a745;
}

/* 글쓰기, 수정, 삭제 버튼 색상 추가 */
.btn-action {
    color: #495057; /* 버튼 텍스트 색상 */
    border-color: #ced4da; /* 버튼 테두리 색상 */
    background-color: #e9ecef; /* 버튼 배경색 */
}

.btn-action:hover {
    color: white;
    background-color: #adb5bd; /* 마우스 오버 시 배경색 */
    border-color: #adb5bd; /* 마우스 오버 시 테두리 색상 */
}

/* 카드 헤더 색상 변경 */
.card-header {
    background-color: #e9ecef; /* 카드 헤더 배경색 */
    color: #495057; /* 카드 헤더 텍스트 색상 */
}

/* 댓글 섹션 스타일 조정 */
.comment-section .card {
    border: 1px solid #ced4da; /* 카드 테두리 */
}

.comment-section .card-header {
    background-color: #f8f9fa; /* 댓글 카드 헤더 배경색 */
    color: #495057; /* 댓글 카드 헤더 텍스트 색상 */
}

/* 컨테이너에 상단 패딩 추가 네비게이션바 글 간격 조정 */
.container {
	padding-top: 100px; /* 네비게이션바 높이에 따라 조정 */
}

/* 수정/삭제/목록 버튼의 간격 조절 */
.btn-spacing {
    margin-right: 2px;
    margin-left: 2px;
}

/* 긴 이미지 게시글 범위 조정 */
.cpost-content img {
	max-width: 100%;
	height: auto;
}

 /* 게시글 스타일 변경 */
        .post-section {
            padding: 20px 0; /* 상하 패딩 추가 */
            border-bottom: 1px solid #e9ecef; /* 하단 경계선 추가 */
            margin-bottom: 20px; /* 하단 여백 추가 */
        }

        .post-title {
            font-size: 1.5em;
            font-weight: bold;
            margin-bottom: 10px; /* 제목 아래 여백 추가 */
        }

        .post-meta {
            font-size: 0.9em;
            color: #6c757d;
            margin-bottom: 15px; /* 메타 정보 아래 여백 추가 */
        }

        .post-content {
            font-size: 1.1em;
            line-height: 1.6;
        }

/* 댓글 리스트 스타일 */
.comment-list {
    list-style: none;
    padding: 0;
    margin-top: 10px;
}

.comment-list .comment-item {
    padding: 10px 0;
    border-bottom: 1px solid #e9ecef; /* 구분선 */
}

.comment-item:last-child {
    border-bottom: none; /* 마지막 아이템의 하단 경계선 제거 */
}

.comment-content {
    margin-bottom: 0; /* 댓글 내용의 하단 여백 제거 */
}

.comment-meta {
    font-size: 0.9em;
    color: #6c757d;
}

.comment-actions {
    text-align: right;
}

.comment-actions button {
    font-size: 0.8em;
}

/* 사이드바 위치 조절*/
.sidebar {
    position: fixed; /* 고정된 위치에 표시 */
    right: 200px; /* 오른쪽 여백 */
    top: 85%; /* 화면의 중간 높이 */
    transform: translateY(-80%); /* 세로 중앙 정렬 */
}

.sidebar button {
    display: block; /* 블록 레벨 요소로 표시 */
    margin-bottom: 10px; /* 버튼 간의 여백 */
}

/* 사이드바 스타일 변경 */
.my-button {
    background-color: #f5f5f5; /* 배경색 */
    color: black; /* 글자색 */
    padding: 10px 15px; /* 상하, 좌우 패딩 */
    border: none; /* 테두리 제거 */
    border-radius: 5px; /* 둥근 모서리 */
    cursor: pointer; /* 마우스 오버시 커서 변경 */
    font-size: 15px; /* 글자 크기 */
}

.btn-custom {
    border-color: white; /* 테두리 색상 */
    background-color: white; /* 배경색 */
    color: black; /* 버튼 내 텍스트 색상 */
}


.modal-header {
            border-bottom: none; /* 모달 헤더의 하단 경계선 제거 */
        }
        
.modal-footer{
			 border-top: none; /* 모달의 상단 경계선 제거 */
		}



/* 글씨체 적용 */
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://fastly.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
body{
	font-family: 'Pretendard-Regular';
}

/* 좋아요 버튼 여백 조정 */
#commentHead {
	margin-bottom: 20px;

} /* 버튼 아래의 여백을 늘려서 버튼을 위로 올립니다


</style>

</head>
<body>

	<!-- 네비게이션바 -->
	<jsp:include page="/WEB-INF/views/common/navBar.jsp" flush="true"></jsp:include><br>
	
	<div class="container mt-4">
		<div class="post-section">
			<!-- 게시글 보여지는 부분 -->
			<div id="postOneView"></div>
			<!-- 좋아요 버튼 -->
			<div class="text-center" style="margin-top: 100px;">
				<sec:authorize access="isAuthenticated()">
					<button type="button" class="btn btn-custom" id="commentHead">
						좋아요 <span class="badge text-bg-primary" id="likeNum"><%=request.getAttribute("likeNum")%></span>
					</button>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<button type="button" class="btn btn-custom" id="commentHead" data-bs-toggle="modal" data-bs-target="#likeModal">
						좋아요 <span class="badge text-bg-primary" id="likeNum"><%=request.getAttribute("likeNum")%></span>
					</button>
				</sec:authorize>
			</div>
			<hr>
			<!-- 좋아요 모달 -->
			<div class="modal fade" id="likeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="exampleModalLabel"></h1>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        로그인이 필요한 기능입니다. <br>
			        지금 회원가입 혹은 로그인하고 공통의 취향을 나눠보세요.
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-primary" onclick="location.href='/acorn/mainLogin';">로그인</button>
			        <button type="button" class="btn btn-primary" onclick="location.href='/acorn/mainLogin';">회원가입</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			<!--  게시글과 수정/목록 버튼의 공간 여백을 위한 새로운 클래스 적용 -->
   			<div id="comment" class="comment-section">
   				<div class="d-flex justify-content-between">
	   				<!-- 왼쪽에 위치할 목록 버튼 -->
					<button type="button" class="btn btn-action btn-spacing" id="scrap">스크랩</button>
					<div>
					    <a href="/acorn/board/<%=request.getParameter("bn")%>"><button type="button" class="btn btn-action btn-spacing" >목록</button></a>
					</div>
					
   					<!-- 오른쪽에 위치할 기타 버튼들 -->
					<sec:authorize access="isAuthenticated()">
						<a href="/acorn/board/write?bn=<%=request.getParameter("bn")%>" class="btn btn-action btn-spacing">글쓰기</a>
					</sec:authorize>
						<!-- 수정, 삭제 버튼-->
   						<span id="updatadel"></span>
   					</div><!-- end 오른쪽 버튼 -->  				
   				</div><!-- end <div class="d-flex justify-content-between"> -->  				
   				<!-- 댓글 내용 -->
				<div style="margin-top: 10px;">
					<jsp:include page="commentMain.jsp"></jsp:include>
				</div>				    			
   			</div><!-- end <div id="comment" class="comment-section"> -->
		</div>
	</div>

	<!-- 사이드바 버튼 생성 -->
	<div class="sidebar">
	    <button class="my-button" onclick="scrollToTop()">▲</button>
	    <button class="my-button" onclick="scrollToBottom()">▼</button>
	    <button class="my-button" onclick="scrollToComments()">&#x1F4AC;</button>
	</div>

</body>
</html>
