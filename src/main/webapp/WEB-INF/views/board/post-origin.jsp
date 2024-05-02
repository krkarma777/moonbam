<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page import="com.moonBam.dto.board.PostSaveDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String userId = (String) request.getAttribute("userId");
	String boardName = request.getParameter("bn");
	String savecount = request.getParameter("savecount");
%>
<meta charset="UTF-8">
<title>문화인들의 밤</title>

<!-- Bootstrap JS (optional) -->
<link
	href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
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

<!-- Font Awesome -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    />



<style>
/* 검색창 너비 조절 */
.searchInput {
	width: 70vh; /* 너비를 100%로 설정하여 부모 요소의 전체 폭을 차지하도록 함 */
}

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
	margin-top: 10px; /* 버튼과 TinyMCE 에디터 사이 간격 조정 */
	margin-right: 12px; /* 버튼을 왼쪽으로 12px 이동 */
	width: auto; /* 버튼의 너비를 자동으로 조절하도록 설정 */
}

/* 네비게이션바 고정 */
.fixed-top {
	position: fixed;
	top: 0;
	width: 100%;
	z-index: 1030; /* 다른 요소들 위에 표시되도록 z-index 설정 */
}

/* 컨테이너에 상단 패딩 추가 네비게이션바 글 간격 조정 */
.container {
	padding-top: 100px; /* 네비게이션바 높이에 따라 조정 */
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
		url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunGu.woff')
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

.navbar {
	position: fixed; /* 추가: 고정 위치 설정 */
	width: 100%; /* 추가: 화면 전체 너비로 설정 */
	z-index: 2; /* 추가: 에디터보다 위에 나타나도록 설정 */
	top: 0; /* 수정: 브라우저 상단에 고정 */
}

/* 글씨체 적용 */
@font-face {
	font-family: 'Pretendard-Regular';
	src:
		url('https://fastly.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff')
		format('woff');
	font-weight: 400;
	font-style: normal;
}

body {
	font-family: 'Pretendard-Regular';
}

/* 임시저장글 모달창 내용 스타일 */
.modal-content {
	background-color: #fefefe;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}

/* 임시저장 삭제 버튼 스타일 */
.delete-btn {
    background: none;
    border: none;
    cursor: pointer;
}

.delete-btn i {
    font-size: 90spx;
    vertical-align: middle;
}

</style>


<script>
  
	
    
	 // jQuery를 사용한 입력란 이벤트 처리
		$(document).ready(function() {

			// 폼 제출 시 validateForm 함수 호출
			$('form').submit(function(event) {
				validateForm(event);
			});
			
			// 임시저장 버튼 클릭 시 호출되는 함수
			$('#save').click(save);
			
			//임시저장목록 버튼 클릭 시 모달창 나타내기
			$('#saveModal').click(function(){});
			
});//end doc

	// 임시저장글 불러오기
	function loadPostSave(postSaveId) {
	    $.ajax({
	        type: 'post',
	        url: '/acorn/board/saveSelect',
	        dataType : 'json',
	        data: {
	            postSaveId: postSaveId
	        },
	        success: function(response) {
	        	alert('임시저장글 선택');
	        	// 모달 창을 숨김
	            
	            // 성공 시 제목과 내용 입력란에 데이터를 채움
	            $('#postTitle').val(response.postSaveTitle);
	            $('#postText').val(response.postSaveText);
	        },
	        error: function(xhr, status, error) {
	            console.log(error);
	        }
	    });//end ajax
	}//end loadSavePost	
		
	// 임시저장 삭제 함수
		function deleteSave(postSaveId){
	    // 삭제 전에 사용자에게 확인을 받는다.
	    if (confirm('정말로 삭제하시겠습니까?')) {
	        // 확인 시 AJAX 요청
	        $.ajax({
	            type: 'POST',
	            url: '/acorn/board/saveDelete',
	            data: {
	                postSaveId: postSaveId
	            },
	            success: function(response) {
	                // 성공 시 페이지 새로고침
	                location.reload();
	                alert('임시저장글이 삭제되었습니다.');
	            },
	            error: function(xhr, status, error) {
	                console.error(xhr.responseText);
	            }
	        }); //end ajax
	  	  }
		
		};//end 임시저장 삭제 함수
		
		//임시저장 버튼 클릭 시 호출되는 함수
		function save() {
		    // 제목과 내용을 가져옴
		    var title = $('#postTitle').val();
		    var content = tinymce.activeEditor.getContent();
		    var userId = "<%=userId%>";
		
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
		    if (getByteLength(title) > 50) {
		        alert('제목은 50바이트를 초과할 수 없습니다.');
		        event.preventDefault();
		        return;
		    }
		
		    // 제목과 내용이 비어있는지 확인
		    if (title.trim() === '' || content.trim() === '') {
		        alert('제목과 내용을 모두 입력하세요.');
		        event.preventDefault();
		        return;
		    }
		
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
		    }); //end ajax
		}//end 임시저장
		

		// form 요소에서 submit 이벤트가 발생할 때 호출되는 함수
		function validateForm(event) {
			// 제목과 내용을 가져옴
			var titleInput = $('#postTitle');
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
		        return;
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


	<div class="container mt-5 editor-wrapper">
		<form method="post" action="/acorn/board/write">
			<!-- 말머리 선택 버튼 그룹 -->
			<div class="mb-3 btn-group" role="group">
				<%
				if (boardName.equals("movie")) {
				%>
				<input type="hidden" name="postCategory" id="postCategory" value="1">
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
				<%
				}
				%>
			</div>

			<div class="mb-3">
				<input type="text" name="postTitle" id="postTitle"
					class="form-control" placeholder="제목을 입력하세요">
			</div>

			<input type="hidden" name="userId" id="userId"
				value="<%=request.getAttribute("userId")%>"> <input
				type="hidden" name="bn" id="bn"
				value="<%=request.getParameter("bn")%>">

			<div class="mb-3">
				<textarea name="postText" class="form-control"
					placeholder="내용을 입력하세요"></textarea>
			</div>

			<div class="row">
				<button type="submit" class="btn btn-primary submit-button">작성</button>
				<button type="button" class="btn btn-primary submit-button" id="save">임시저장</button>
				<button type="button" class="btn btn-primary submit-button" id="saveModal">임시저장목록</button>
			</div>
		</form>
	</div>

	<!-- 임시저장 모달 창 -->
	<div id="myModal" class="modal">
		<div class="modal-content">
			<span class="close">&times;</span>
			<table class="table table-bordered"
				style="table-layout: fixed; width: 100%;">
				<colgroup>
					<col style="width: 15%;">
					<col style="width: 45%;">
					<col style="width: 25%;">
					<col style="width: 15%;">
				</colgroup>

				<tr>
					<th style="text-align: center;">순번</th>
					<th style="text-align: center;">제목</th>
					<th style="text-align: center;">저장일</th>
					<th style="text-align: center;">삭제</th>
				</tr>

				<%
				List<PostSaveDTO> postSaveList = (List<PostSaveDTO>) request.getAttribute("postSaveList");
				if (postSaveList != null && !postSaveList.isEmpty()) {
					int n = 1;
					for (int i = 0; i < postSaveList.size(); i++) {
						PostSaveDTO postSave = postSaveList.get(i);
						Long postSaveId = postSave.getPostSaveId();
						String postSaveTitle = postSave.getPostSaveTitle();
						String postSaveDate = postSave.getPostSaveDate();
						System.out.println(postSaveDate);
				%>
				<tr>
					<td style="text-align: center;"><%=n%></td>
					<td><b onclick="loadPostSave(<%=postSaveId%>)"><%=postSaveTitle%></b></td>
					<td style="text-align: center;"><%=postSaveDate%></td>
					<td style="text-align: center;">
						<button class="delete-btn" onclick="deleteSave(<%=postSaveId%>)">
							<i class="fa-regular fa-trash-can"></i>
						</button>
					</td>
				</tr>

				<%
				n++;
				} //end for
				} else {
				%>
				<tr>
					<td colspan="4" style="text-align: center;">임시 저장된 글이 없습니다.</td>
				</tr>
				<%
				}
				%>

			</table>
		</div>
	</div>

	<script>


    
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

</script>

</body>
</html>
