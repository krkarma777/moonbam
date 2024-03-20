<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>게시판 글 수정하기</title>
    
<script src="https://cdn.ckeditor.com/ckeditor5/41.1.0/classic/ckeditor.js"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script>

$(function(){
	
	//카테고리 select DB 반영
	$("#categorySelect").val($("#category").val());
	
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

<form id="board" action="<c:url value='/checkUpdatePost/updateDBoard'/>" method="post">

	<input type="hidden" id="boardNum" name="boardNum" value="${dto.boardNum}">
	<input type="hidden" id="category" name="category" value="${dto.category}">
	<input type="hidden" id="content" name="content" value="">

	닉네임: <input type="text" name="nickname" value="${dto.nickname}" required="required">
	비밀번호: <input type="text" name="password" value="${dto.password}"><br>
	제목: <input type="text" name="title" value="${dto.title}" required="required">
	카테고리: <jsp:include page="/WEB-INF/views/common/categoryForDBoard.jsp" flush="true"></jsp:include>
	
	<textarea id="txtContent" rows="10" cols="100" style="width: 100%;">
		${dto.content}
	</textarea>

	<input type="submit" value="제출">

</form>

<script src="${pageContext.request.contextPath}/resources/js/member/ckeditor.js"></script>

</body>
</html>