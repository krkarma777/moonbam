<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>게시판 글쓰기</title>
    
	<!-- 네이버 스마트에디터  -->
<!-- <head> 안에 추가 -->
<script type="text/javascript" src="<c:url value='/resources/libs/smarteditor/js/service/HuskyEZCreator.js'/>" charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!-- 2.10.0 버전엔 js 파일 일부분이 없어 오류 발생 ! -->

<script>

function save(){
	oEditors.getById["txtContent"].exec("UPDATE_CONTENTS_FIELD", []);  
    		//스마트 에디터 값을 텍스트컨텐츠로 전달
	var content = document.getElementById("smartEditor").value;
    		// 값을 불러올 땐 document.get으로 받아오기
    
    $("#content").val( $("#txtContent").val());
    		
	return; 
}

$(function(){
	
	$("form").on("submit", function(){
		save();
	})
})

</script>

</head>
<body>

<form id="board" action="insertPost" method="post">

제목: <input type="text" name="title">
닉네임: <input type="text" name="nickname">
비밀번호: <input type="text" name="password">
카테고리: <input type="text" name="category">	<!-- 나중에 select로 변경 -->
<input type="hidden" id="content" name="content">

<textarea id="txtContent" rows="10" cols="100" style="width: 100%;"></textarea>
<!-- textarea 밑에 script 작성하기 -->

<input type="submit" value="제출">

</form>


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
