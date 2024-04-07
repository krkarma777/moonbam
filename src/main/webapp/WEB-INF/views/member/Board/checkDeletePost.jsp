<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>게시글 삭제 비밀번호 확인 페이지</title>
    
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    
    <!-- 부트스트랩 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
    <!-- 사용자 정의 스타일 -->
    <style>
        body {
            padding: 20px;
            max-width: 960px;
            margin: auto;
            font-family: 'Noto Sans KR', sans-serif; /* 한국어 글꼴 지정 */
            background-color: #f8f9fa;
            color: #212529;
        }

        form {
            margin-top: 30px;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        #falsePW {
            color: red;
            margin-bottom: 10px;
        }

        .btns {
            padding: 10px 20px;
            background-color: #007bff;
            border: none;
            color: #fff;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btns:hover {
            background-color: #0056b3;
        }
        
        #btnContainer{
        	text-align: right;
        }


    </style>
</head>
<body>

    <!-- 헤더 네비게이션바 -->
    <div id="header">
        <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
    </div>

	<form id="board" action="<c:url value='/deletePost'/>" method="post">
	    <div class="right-align">
	        <input type="hidden" id="boardNum" name="boardNum" value="${dto.boardNum}">
	        글 삭제를 위해 비밀번호를 입력해주세요.<br>
	        <input type="text" id="password" name="password">
	        <div id="falsePW"></div>
	        
	       	<div id="btnContainer">
				<input class="btns" id="ajaxCheck" type="button" value="제출">
	        	<input class="btns" id="goBack" type="button" value="취소">
		    </div>
	    </div>
	</form>


<script type="text/javascript">

	$(function(){
	
	    $("#ajaxCheck").on("click", function(){
	        var boardNum = $("#boardNum").val()
	        var password = $("#password").val()
	        
	        $.ajax({
	            
	            type: "POST",
	            url: "<c:url value='/checkPostPW'/>",
	            data: {
	                boardNum: boardNum,
	                password: password,
	            },
	            success: function(response) {
	                        if(response == "yes"){
	                            if(window.confirm("정말로 삭제하시겠습니까?\n삭제된 게시물은 복구할 수 없습니다.")){
	                                $("#board")[0].submit();    
	                            } else (
	                                window.history.go(-1)
	                            )
	                        } else {
	                            $("#falsePW").text("비밀번호가 다릅니다.");
	                        }
	                    },
	                error: function(error) {
	                    console.error("추천 기능 에러:", error);
	                }
	        })
	
	    })
	    
	    $("#goBack").on("click", function(){
	        window.history.go(-1)
	    })
	})

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
        crossorigin="anonymous"></script>  

</body>
</html>
