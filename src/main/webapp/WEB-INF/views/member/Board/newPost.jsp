<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>문화인들의 밤</title>
    <!-- 부트스트랩 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
        <style>
        body {
            padding: 20px;
            max-width: 960px;
            margin: auto;
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f8f9fa;
            color: #212529;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        input[type="text"],
        input[type="password"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #007bff;
            border: none;
            color: #fff;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        #txtContent {
		    resize: vertical;
		}
		.ck-editor__editable { 
			height: 400px; 
		}
    </style>
    
<script src="https://cdn.ckeditor.com/ckeditor5/41.1.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/41.1.0/classic/translations/ko.js"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script type="text/javascript">

	$(function(){
		//카테고리 변경 시 db에 저장할 카테고리 name의 value에 반영
		$("#categorySelect").on("change", function(){
			$("#category").val($("#categorySelect").val());
		})
		
		//제출 시 입력된 정보를 컨트롤러로 전송
		$("form").on("submit", function(){
			$("#content").val( $("#txtContent").val());
		})
	})

</script>

</head>
<body>

    <!-- 헤더 네비게이션바 -->
    <div id="header">
        <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
    </div>

<form id="board" action="insertPost" method="POST">

    <input type="hidden" id="category" name="category" value="정보">
    <input type="hidden" id="content" name="content">


    <div class="mb-3">
        <label for="title" class="form-label">제목:</label>
        <input type="text" id="title" name="title" class="form-control" required="required">
    </div>
    <div class="row">
        <div class="col-md-6 mb-3">
            <label for="nickname" class="form-label">닉네임:</label>
            <input type="text" id="nickname" name="nickname" class="form-control" required="required" minlength="2">
        </div>
        
        <div class="col-md-6 mb-3">
        	<label for="password" class="form-label">비밀번호:</label>
        	<input type="password" id="password" name="password" class="form-control" maxlength="10" required="required" maxlength="30">
    	</div>
    </div>
    <div class="mb-3">
        <label for="txtContent" class="form-label">내용:</label>
        <textarea id="txtContent" name="txtContent" class="form-control">내용을 입력해주세요</textarea>
    </div>
    
    <div class="row">
        <div class="col-md-6 mb-3">
            <label for="categorySelect" class="form-label">카테고리:</label>
        	<jsp:include page="/WEB-INF/views/common/categoryForDBoard.jsp" flush="true"></jsp:include>
        </div>
		<div class="col-md-6 mb-3">
		    <div class="text-end">
		        <input type="submit" value="제출">
		    </div>
		</div>
    </div>
    

</form>


<script src="${pageContext.request.contextPath}/resources/js/member/ckeditor.js"></script>

<!-- 부트 스트랩 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>


