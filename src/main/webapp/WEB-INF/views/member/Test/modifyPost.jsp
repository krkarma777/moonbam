<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>게시판 글 수정하기</title>
    
<script type="text/javascript" src="<c:url value='/resources/libs/smarteditor/js/service/HuskyEZCreator.js'/>" charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script>

//에디터 함수
function save(){
	oEditors.getById["txtContent"].exec("UPDATE_CONTENTS_FIELD", []);  
	var content = document.getElementById("smartEditor").value;
    $("#content").val( $("#txtContent").val());
	return; 
}

$(function(){
	
	//카테고리 select DB 반영
	$("#categorySelect").val($("#category").val());
	
	//카테고리 변경 시 db에 저장할 카테고리 name의 value에 반영
	$("#categorySelect").on("change", function(){
		$("#category").val($("#categorySelect").val());
	})
	
	//제출 시 입력된 정보를 컨트롤러로 전송
	$("form").on("submit", function(){
		save();
	})
})

</script>

</head>
<body>

<form id="board" action="updateDBoard" method="post">

	<input type="hidden" id="boardNum" name="boardNum" value="${dto.boardNum}">
	<input type="hidden" id="category" name="category" value="${dto.category}">
	<input type="hidden" id="content" name="content" value="">

<!-- 로그인상태일 때는 안 뜸 *****************************************************-->
	닉네임: <input type="text" name="nickname" value="${dto.nickname}" required="required">
	비밀번호: <input type="text" name="password" value="${dto.password}" required="required"><br>
<!-- 로그인상태일 때는 안 뜸 *****************************************************-->

	제목: <input type="text" name="title" value="${dto.title}" required="required">
	카테고리: <jsp:include page="/WEB-INF/views/common/categoryForDBoard.jsp" flush="true"></jsp:include>
	
	<textarea id="txtContent" rows="10" cols="100" style="width: 100%;">
		${dto.content}
	</textarea>

	<input type="submit" value="제출">

</form>

<!-- 에디터 스크립트 -->
<script id="smartEditor" type="text/javascript"> 
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "txtContent",  //textarea ID 입력
	    sSkinURI: "/libs/smarteditor/SmartEditor2Skin.html",  //martEditor2Skin.html 경로 입력
	    fCreator: "createSEditor2",
	    htParams : { 
	    	// 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
	        bUseToolbar : true, 
		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
		bUseVerticalResizer : true, 
		// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
		bUseModeChanger : true 
	    }
	});
</script>


</body>
</html>
