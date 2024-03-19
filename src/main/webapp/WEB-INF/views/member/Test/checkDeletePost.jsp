<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>게시판 글 삭제 확인</title>
    
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

<form id="board" action="<c:url value='/deletePost'/>" method="post">
	<input type="hidden" id="boardNum" name="boardNum" value="${dto.boardNum}">
	글 삭제를 위해 비밀번호를 입력해주세요.<br>
	<input type="text" id="password" name="password">
	<div id="falsePW"></div>
	
	<input id="ajaxCheck" type="button" value="제출">
	<input id="goBack" type="button" value="취소">
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
