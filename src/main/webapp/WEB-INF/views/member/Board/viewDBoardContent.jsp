<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>문화인들의 밤</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<!-- 외부 css 파일 -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/member/unfound.css'/>">
	<!-- 부트 스트랩 -->
	<link href="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        body {
            padding: 20px;
            max-width: 1080px;
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
        	display: -webkit-box;
            -webkit-line-clamp: 1; 					/* 한 칸에 표시할 줄 수 */
            -webkit-box-orient: vertical;
            overflow: hidden;
        }
         #commentSection {
	        margin-top: 20px;
	        padding: 20px;
	        background-color: #f8f9fa;
	        border-radius: 10px;
	        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	    }
	    
	    .comment {
	        border-bottom: 1px solid #dee2e6;
	        padding: 15px 0;
	    }
	    
	    .comment-content {
	        margin-bottom: 10px;
	    }
	    
	    .comment-info {
	        font-size: 12px;
	        color: #6c757d;
	    }
	    
	    .comment-actions {
	        margin-top: 5px;
	    }
	    
	    .comment-actions button {
	        margin-right: 10px;
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


<hr>


<!-- 댓글 표시 -->
 <div class="card">
 	<div class="card-body">
 		<h5 class="card-title">댓글</h5>
 		<div id="commentContent"></div>
 	</div>
 </div>


<!-- 댓글 작성 폼 -->
<form id="commentForm">
    <div class="mb-3">
        <label for="commentContent" class="form-label">댓글 작성</label>
        <input 	type="text" id="newCommentNickname" placeholder="닉네임" required="required" 
				pattern="^[가-힣]{1,20}$|^[a-zA-Z0-9]{1,40}$" title="한글 20글자 또는 영어+숫자 40글자 이내로 입력해주세요."> 
        <input 	type="password" id="newCommentPassword" placeholder="패스워드" required="required" maxlength="30"> <br>
        <textarea class="form-control" id="newCommentContent" rows="3" placeholder="댓글을 입력하세요" maxlength="100"></textarea>
    </div>
    <button type="submit" class="btn btn-primary">댓글 등록</button>
</form>


<!-- 댓글 삭제 모달 창 -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="confirmDeleteModalLabel">댓글 삭제 확인</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>댓글을 삭제하시려면 패스워드를 입력하세요:</p>
        <input type="password" class="form-control" id="commentPasswordToDelete" placeholder="패스워드" maxlength="30">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary" id="confirmDeleteBtn">확인</button>
      </div>
    </div>
  </div>
</div>


<!-- 대댓글 삭제 모달 창 -->
<div class="modal fade" id="confirmDeleteReplyModal" tabindex="-1" aria-labelledby="confirmDeleteReplyModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="confirmDeleteReplyModalLabel">대댓글 삭제 확인</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>대댓글을 삭제하시려면 패스워드를 입력하세요:</p>
        <input type="password" class="form-control" id="replyPasswordToDelete" placeholder="패스워드" maxlength="30">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary" id="replyDeleteBtn">확인</button>
      </div>
    </div>
  </div>
</div>
<hr>


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

	var boardNum = $("#boardNum").val()

	$(function(){
		
		 //페이지 로딩 시 댓글 목록 출력
		 fetchComments($("#boardNum").val());
		 
	    // 댓글 삭제 버튼에 클릭 이벤트로 삭제 기능 부여
	    $(document).on('click', '.delete-btn', function() {
	        var anonymousCommentNum = $(this).data('comment-num');
	        $('#commentPasswordToDelete').val("");
	        $('#confirmDeleteModal').modal('show');
	        $('#confirmDeleteBtn').data('comment-num', anonymousCommentNum);
	    });
	    
	    // 대댓글 삭제 버튼에 클릭 이벤트로 삭제 기능 부여
	    $(document).on('click', '.reply-delete-btn', function() {
            var anonymousReplyNum = $(this).data('reply-num');
            $('#replyPasswordToDelete').val(""); 
            $('#confirmDeleteReplyModal').modal('show'); 
            $('#replyDeleteBtn').data('reply-num', anonymousReplyNum); 
        });

	    
	    // 대댓글 작성 폼 토글
	    $(document).on('click', '.comment-reply-btn', function() {
	        var commentNum = $(this).data('comment-num');
	        $("#replyForm-" + commentNum).toggle();
	    });
		
	  	 // 댓글 삭제 함수
		$('#confirmDeleteBtn').click(function() {
		    var anonymousCommentNum = $(this).data('comment-num');
		    var commentPassword = $('#commentPasswordToDelete').val();
		    
		    $.ajax({
		        url: "<c:url value='/deleteComment'/>",
		        type: "DELETE",
		        data: {
		            anonymousCommentNum: anonymousCommentNum,
		            commentPassword: commentPassword
		        },
		        success: function(response) {
		        	if(response == false){
		        		alert("비밀번호가 다릅니다")
		        	}
		            fetchComments(boardNum);
		            $('#confirmDeleteModal').modal('hide'); 
		        },
		        error: function(xhr, status, error) {
		            console.error('댓글 삭제에 실패하였습니다:', error);
		            $('#confirmDeleteModal').modal('hide');
		        }
		    });
		});
	  	 
		
	// 댓글 등록
	$('#commentForm').submit(function(event) {

			event.preventDefault();
		    var commentContent = $("#newCommentContent").val();
		    var commentNickname = $("#newCommentNickname").val()
		    var commentPassword = $("#newCommentPassword").val()
		    
		    $.ajax({
		        type: 'POST',  
		        url: "<c:url value='/addComment'/>",  
		        data: {
		        	commentContent: commentContent,
		        	boardNum: boardNum,
		        	commentNickname: commentNickname,
		        	commentPassword: commentPassword
		        }, 
		        success: function(response) {  
		        	fetchComments(boardNum);
		            $('#newCommentContent').val('');
		        },
		        error: function(xhr, status, error) {
		            console.error(xhr.responseText);
		        }
		    });
		});
		
	})
	

	//대댓글 등록
	$(document).on('click', '.submit-reply-btn', function(event) {
	    event.preventDefault();
	    var commentNum = $(this).data('comment-num');
	    var replyNickname = $("#replyNickname-" + commentNum).val();
	    var replyPassword = $("#replyPassword-" + commentNum).val();
	    var replyContent = $("#replyContent-" + commentNum).val();
	    
	    $.ajax({
	        type: 'POST',
	        url: "<c:url value='/addReply'/>",
	        data: {
	            anonymousCommentNum: commentNum,
	            replyNickname: replyNickname,
	            replyPassword: replyPassword,
	            replyContent: replyContent
	        },
	        success: function(response) {
	            // 대댓글 폼 초기화
	            $("#replyContent-" + commentNum).val("");
	            // 대댓글 폼 숨기기
	            $("#replyForm-" + commentNum).hide();
	            fetchComments(boardNum);
	        },
	        error: function(xhr, status, error) {
	            console.error('대댓글 등록에 실패하였습니다:', error);
	        }
	    });
	});

	
	
	// 댓글 목록 출력 함수
	 function fetchComments(boardNum) {
	    $.ajax({
	        url: "<c:url value='/allComments'/>",
	        type: 'POST',
	        data: {
	            boardNum: boardNum
	        },
	        success: function(response) {
	            var commentsHTML = '';
	            response.forEach(function(comment) {
	                commentsHTML += "<div class='card mb-3'>";
	                commentsHTML += 	"<div class='card-body d-flex justify-content-between'>";
	                commentsHTML += 		"<div class='comment-content'>";
	                commentsHTML += 			"<p class='card-text'><strong>작성자:</strong> " + comment.commentNickname + " (" + comment.commentEdittedDate + ") ";
	                commentsHTML += 				"<span class='control-group ml-auto'>";
	                commentsHTML += 				"<a class='btn-text delete-btn' data-comment-num='" + comment.anonymousCommentNum + "'>삭제</a> ";
	                commentsHTML += 				"<a class='btn-text comment-reply-btn' href='javascript:void(0);' data-comment-num='" + comment.anonymousCommentNum + "'>답글</a>";
	                commentsHTML += 			"</span></p>";
	                commentsHTML += 			"<p class='card-text'><strong>내용:</strong> " + comment.commentContent + "</p>";

		            // 대댓글 작성 폼
	                commentsHTML += 			"<form id='replyForm-" + comment.anonymousCommentNum + "' class='mb-3' style='display: none;'>";
	                commentsHTML += 				"<input type='text' id='replyNickname-" + comment.anonymousCommentNum + "' placeholder='닉네임' required='required' pattern='^[가-힣]{1,20}$|^[a-zA-Z0-9]{1,40}$' title='한글 20글자 또는 영어+숫자 40글자 이내로 입력해주세요.''>";
	                commentsHTML += 				"<input type='password' id='replyPassword-" + comment.anonymousCommentNum + "' placeholder='패스워드' required='required' maxlength='30'> <br>";
	                commentsHTML += 				"<textarea class='form-control' id='replyContent-" + comment.anonymousCommentNum + "' rows='2' placeholder='대댓글을 입력하세요' maxlength='100'></textarea>";
	                commentsHTML += 				"<button type='button' class='btn btn-primary submit-reply-btn' data-comment-num='" + comment.anonymousCommentNum + "'>대댓글 등록</button>";
	                commentsHTML += 			"</form>";
	                
	             	// 대댓글 출력을 위한 빈 div 추가
	                commentsHTML += 			"<div class='replies' id='replies-" + comment.anonymousCommentNum + "'></div>";
	                commentsHTML += 		"</div>";
	                commentsHTML += 	"</div>";
	                commentsHTML += "</div>";
	            });
	            $("#commentContent").html(commentsHTML);
	            
	         	// 각 댓글에 대해 대댓글을 가져와서 출력
                response.forEach(function(comment) {
                    fetchReplies(comment.anonymousCommentNum);
                });
	        },
	        error: function(xhr, status, error) {
	            console.error('댓글 목록을 가져오는데 실패하였습니다:', error);
	        }
	    });
	}
	
	
	// 대댓글 목록을 가져오고 출력하는 함수
    function fetchReplies(commentNum) {
        $.ajax({
            url: "<c:url value='/allReplies'/>",
            type: 'POST',
            data: {
                anonymousCommentNum: commentNum
            },
            success: function(response) {
                var repliesHTML = '';
                response.forEach(function(reply) {
                    repliesHTML += "<div class='card mb-3'>";
                    repliesHTML += 		"<div class='card-body'>";
                    repliesHTML += 			"<p><strong>작성자:</strong> " + reply.replyNickname + " (" + reply.replyEdittedDate + ") ";
					repliesHTML += 			"<a class='btn-text reply-delete-btn' href='javascript:void(0);' data-reply-num='" + reply.anonymousReplyNum + "'>삭제</a></p>";
                    repliesHTML += 			"<p><strong>내용:</strong> " + reply.replyContent + "</p>";
                    repliesHTML += 		"</div>";
                    repliesHTML += "</div>";
                });
                $("#replies-" + commentNum).html(repliesHTML);
            },
            error: function(xhr, status, error) {
                console.error('대댓글 목록을 가져오는데 실패하였습니다:', error);
            }
        });
    }
	
	
 	// 대댓글 삭제 함수
	$('#replyDeleteBtn').click(function() {
         var anonymousReplyNum = $(this).data('reply-num');
         var replyPassword = $('#replyPasswordToDelete').val();
         
         $.ajax({
             url: "<c:url value='/deleteReply'/>",
             type: "DELETE",
             data: {
                 anonymousReplyNum: anonymousReplyNum,
                 replyPassword: replyPassword
             },
             success: function(response) {
                 if (response == false) {
                     alert("비밀번호가 다릅니다");
                 } else {
                     fetchComments(boardNum); 
                 }
                 $('#confirmDeleteReplyModal').modal('hide');
             },
             error: function(xhr, status, error) {
                 console.error('대댓글 삭제에 실패하였습니다:', error);
                 $('#confirmDeleteReplyModal').modal('hide'); 
             }
         });
     });

	
	// 이전글, 다음글에서 제목을 누르면 세부글로 이동
	function submitForm(boardNum) {
	    window.location.href ="<c:url value='/viewDBoardContent'/>?boardNum="+boardNum;
	}
	
	
	//익명 유저가 추천/비추천을 했을 경우, 그 상태 유지
	if($("#userBylike").val() == "like"){
		$("#recommendVal").attr("data-val", "likeChecked");
        $("#recommendVal").attr("src", "<c:url value='/resources/images/member/like.svg'/>"); 
	}
	if($("#userBydislike").val() == "dislike"){
		$("#disrecommendVal").attr("data-val", "dislikeChecked");
        $("#disrecommendVal").attr("src", "<c:url value='/resources/images/member/dislike.svg'/>"); 
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
<script src="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
