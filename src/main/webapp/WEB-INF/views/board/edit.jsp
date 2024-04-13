<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.moonBam.dto.board.PostDTO"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<%

		String boardName = request.getParameter("bn");

// 수정할 글의 정보를 받아옵니다.
PostDTO post = (PostDTO) request.getAttribute("post");
%>
<meta charset="UTF-8">
<title>글 수정 - <%=post.getPostTitle()%></title>

<!-- Bootstrap JS (optional) -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


<!-- TinyMCE script -->
<script
	src="https://cdn.tiny.cloud/1/ok3w2lvptkyfth3qjjks4fv0f99459nvfx76ire0ttwxcrij/tinymce/6/tinymce.min.js"
	referrerpolicy="origin"></script>

<style>
/* 제목 입력란에 스타일을 적용하는 CSS 코드 */
#title {
	width: 100%;
	padding: 5px;
	font-size: 16px;
	border: 1px solid #ced4da; /* 제목 입력창의 테두리 스타일 추가 */
}

body {
	font-family: 'ChosunGu';
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
	margin: 0;
}
/* 수정된 스타일 */
.container {
	margin-top: 50px;
}

.submit-button {
	margin-top: 5px; /* 버튼과 TinyMCE 에디터 사이 간격 조정 */
	margin-right: 12px; /* 버튼을 왼쪽으로 12px 이동 */
	width: auto; /* 버튼의 너비를 자동으로 조절하도록 설정 */
}

/* 반응형 그리드 시스템 */
@media ( min-width : 576px) {
	.container {
		max-width: 540px; /* 작은 화면에 대한 최대 너비 조정 */
	}
}

@media ( min-width : 768px) {
	.container {
		max-width: 720px; /* 중간 크기 화면에 대한 최대 너비 조정 */
	}
}

@media ( min-width : 992px) {
	.container {
		max-width: 960px; /* 대형 화면에 대한 최대 너비 조정 */
	}
}

@media ( min-width : 1200px) {
	.container {
		max-width: 1140px; /* 매우 큰 화면에 대한 최대 너비 조정 */
	}
}

@font-face {
	font-family: 'ChosunGu';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunGu.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

/* 추가된 스타일 */
.row {
	display: flex;
	justify-content: flex-end; /* 버튼을 오른쪽으로 정렬 */
}

.submit-button {
	margin-left: 10px; /* 버튼과 에디터 간격 조정 */
}

/* 에디터를 감싸는 스타일 추가 */
.editor-wrapper {
	border: 1px solid #ddd; /* 네모난 선 스타일 추가 */
	padding: 20px; /* 내부 여백 추가 */
	margin-top: 20px; /* 상단 여백 추가 */
	position: relative; /* 추가: 상대적 위치 설정 */
	z-index: 1; /* 추가: 네비게이션 바보다 위에 나타나도록 설정 */
}

.mb-3 {
	margin-bottom: 20px; /* 제목 입력란과 에디터 간격을 조절하는 부분 */
}

#title {
	width: 100%; /* 폭을 100%로 설정하여 동일하게 조절 */
	padding: 5px;
	font-size: 16px;
}
/* 네비게이션바 위치 설정 */
.navbar {
	position: fixed; /* 추가: 고정 위치 설정 */
	width: 100%; /* 추가: 화면 전체 너비로 설정 */
	z-index: 1030; /* 추가: 에디터보다 위에 나타나도록 설정 */
	top: 0; /* 수정: 브라우저 상단에 고정 */
}
/* 검색창 너비 조절 */
.searchInput {
	width: 70vh;
}

/* 글씨체 적용 */
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
body{
	font-family: 'Pretendard-Regular';
}
</style>
<script>
//jQuery를 사용한 입력란 이벤트 처리
$(document).ready(function() {

	// 폼 제출 시 validateForm 함수 호출
	$('form').submit(function(event) {
		validateForm(event);
	});
	// 임시저장 버튼 클릭 시 호출되는 함수
	$('#save').click(function(event) {
	    event.preventDefault(); // 기본 이벤트 동작 방지
	    
	    // 제목과 내용을 가져옴
	    var title = $('#postTitle').val();
	    var content = tinymce.activeEditor.getContent();
	    var content2 = $('#postText').text();
	    var userId = '<sec:authentication property="name"/>';

	    // AJAX 요청
	    $.ajax({
	        type: 'POST',
	        url: '/acorn/board/save',
	        data: {
	            postTitle: title,
	            postText: content,
	            userId: userId
	        },
	        success: function(response) {
	            // 성공 시 알림
	            alert('게시글이 임시저장되었습니다.');
	        },
	        error: function(xhr, status, error) {
	            // 실패 시 오류 메시지 출력
	            console.error(xhr.responseText);
	        }
	    });
	});
	
});

    
	 // jQuery를 사용한 입력란 이벤트 처리
		$(document).ready(function() {

			// 폼 제출 시 validateForm 함수 호출
			$('form').submit(function(event) {
				validateForm(event);
			});
		});

		// form 요소에서 submit 이벤트가 발생할 때 호출되는 함수
		function validateForm(event) {
			// 제목과 내용을 가져옴
			var titleInput = $('#title');
			var contentInput = $('textarea[name="postText"]');


			 // 바이트 길이 계산 함수
		    function getByteLength(str) {
		        var byteLength = 0;
		        for (var i = 0; i < str.length; i++) {
		            var charCode = str.charCodeAt(i);
		            if (charCode <= 0x7f) {
		                byteLength += 1;
		            } else {
		                byteLength += 2; // 한글이나 다른 멀티바이트 문자
		            }
		        }
		        return byteLength;
		    }

		    // 제목의 바이트 길이를 확인
		    if (getByteLength(titleInput.val()) > 50) {
		        alert('제목은 50바이트를 초과할 수 없습니다.');
		        event.preventDefault(); // 폼 제출 중지
		    }
			
			// 제목과 내용이 비어있는지 확인
			if (titleInput.val().trim() === ''
					|| contentInput.val().trim() === '') {
				// 비어있을 경우 경고 메시지를 표시하고 submit을 중지
				alert('제목과 내용을 모두 입력하세요.');
				event.preventDefault();
			}

		}

		tinymce
				.init({
					selector : 'textarea',
					// 한국어로 설정, 헤더에서 사용자 국가 받아와서 변경 기능 추가 가능    
					language : 'ko_KR',
					// 사용할 플러그인
					plugins : 'anchor autolink charmap codesample emoticons image link lists media searchreplace visualblocks',
					// 에디터 툴바 설정
					toolbar : 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media  mergetags | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat | fileupload',
					file_picker_types : 'file image media',
					// 이미지, 동영상 업로드를 위한 콜백 설정
					file_picker_callback: function(callback, value, meta) {
						// 입력 필드 생성 (파일 타입)
					    var input = document.createElement('input');
					    input.setAttribute('type', 'file');
					
					    // 파일 타입에 따라 입력 필드의 'accept' 속성 설정
					    if (meta.filetype === 'image') {
					        // 이미지 파일만 선택할 수 있도록 설정
					        input.setAttribute('accept', 'image/*');
					    } else if (meta.filetype === 'video') {
					        // 비디오 파일만 선택할 수 있도록 설정
					        input.setAttribute('accept', 'video/*');
					    }
					
					    input.onchange = function() {
					        var file = this.files[0];
					        var formData = new FormData();
					        formData.append('fileUpload', file);
					
					        // AJAX 요청으로 서버에 파일 업로드
					        $.ajax({
					            url: '/acorn/upload', // 서버의 파일 업로드 URL
					            type : 'POST',
					            data : formData,
					            processData : false,
					            contentType : false,
					            success : function(response) {
					                // 서버로부터 반환된 파일 URL을 콜백 함수에 전달하여 에디터에 이미지 삽입
					                callback(response.fileUrl);
					            },
					            error : function() {
					                // 파일 업로드 실패 시 경고 메시지 표시
					                alert('파일 업로드에 실패했습니다.');
					            }
					        });
					    };

					    // 파일 선택기를 사용자에게 표시
					    input.click();
					},

					branding : false // 에디터의 브랜딩을 비활성화
			});
	</script>
	</head>
<body>

	<!-- 네비게이션바 -->
<%-- 	<jsp:include page="//common/navbar.jsp"></jsp:include> --%>
	<!-- <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
		<div class="container-fluid">
			로고
			<a class="navbar-brand" href="#">로고</a>

			토글 버튼
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			네비게이션 항목
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav mx-auto">
					검색 바
					<form class="d-flex w-100">
						<input class="form-control me-2 searchInput" type="search"
							placeholder="검색" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">검색</button>
					</form>
				</ul>
				<ul class="navbar-nav">
					로그인, 마이페이지, 회원가입 버튼
					<li class="nav-item"><a class="nav-link" href="#">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="#">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>
				</ul>
			</div>
		</div>
	</nav> -->



	<div class="container mt-5 editor-wrapper">
		<form method="post" action="/acorn/board/edit" onsubmit="return validateForm();">
					<!-- 말머리 선택 버튼 그룹 -->
			<div class="mb-3 btn-group" role="group">
			<% if(boardName.equals("movie")) {%>
				<input type="hidden" name="postCategory" id="postCategory" value="<%= post.getCategoryId()%>">
				<button type="button" class="btn btn-outline-primary category-btn"
					onclick="setCategory('1')">일반</button>
				<button type="button" class="btn btn-outline-secondary category-btn"
					onclick="setCategory('2')">신작</button>
				<button type="button" class="btn btn-outline-success category-btn"
					onclick="setCategory('3')">후기</button>
				<button type="button" class="btn btn-outline-danger category-btn"
					onclick="setCategory('4')">추천</button>
				<button type="button" class="btn btn-outline-warning category-btn"
					onclick="setCategory('5')">토론</button>
				<button type="button" class="btn btn-outline-info category-btn"
					onclick="setCategory('6')">해외</button>
			<%} %>
			</div>
			<div class="mb-3">
				<input type="text" name="postTitle" id="postTitle" class="form-control" value="<%= post.getPostTitle() %>">
			</div>

			<input type="hidden" name="userId" id="userId" value="<%= userId %>">
			<input type="hidden" name="bn" id="bn" value="<%=request.getParameter("bn")%>">
			<input type="hidden" name="postId" id="postId" value="<%=request.getParameter("postId")%>">

			<div class="mb-3">
				<textarea name="postText" class="form-control"><%= post.getPostText() %></textarea>
			</div>

			<div class="row">
				<button type="submit" class="btn btn-primary submit-button">수정 완료</button>
				<button type="button" class="btn btn-primary submit-button" id="save">임시저장</button>
			</div>
		</form>
	</div>
<script>
$(document).ready(function() {
    // 해당 카테고리 버튼에 'active' 클래스 추가
    setActiveCategory();

    function setActiveCategory() {
        var categoryId = "<%= post.getCategoryId() %>"; // JSP에서 카테고리 ID 가져오기
        setCategory(categoryId); // 카테고리 설정 함수 호출
    }


    //버튼 클릭 했을 때 색깔 나타나는 함수
     function setCategory(category) {
         // 모든 버튼의 'active' 클래스 제거
         var buttons = document.getElementsByClassName('category-btn');
         for (var i = 0; i < buttons.length; i++) {
             buttons[i].classList.remove('active');
         }

         // 클릭된 버튼에 'active' 클래스 추가
         var activeBtn = document.querySelector('.btn[onclick="setCategory(\'' + category + '\')"]');
         if (activeBtn) {
             activeBtn.classList.add('active');
         }

         document.getElementById('postCategory').value = category;
     }
});

function setCategory(category) {
    // 모든 버튼의 'active' 클래스 제거
    var buttons = document.getElementsByClassName('category-btn');
    for (var i = 0; i < buttons.length; i++) {
        buttons[i].classList.remove('active');
    }

    // 클릭된 버튼에 'active' 클래스 추가
    var activeBtn = document.querySelector('.btn[onclick="setCategory(\'' + category + '\')"]');
    if (activeBtn) {
        activeBtn.classList.add('active');
    }

    document.getElementById('postCategory').value = category;
}
</script>
</body>
</html>
