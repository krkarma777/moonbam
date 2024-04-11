<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문밤</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

</head>
<!-- popup 시작 -->
<script type="text/javascript">
var list = <%= request.getAttribute("list") %>;   
    $(function (){
    	// 팝업 창 위치 &크기 변수
		let sLeft=0;
		let sTop=0;
		let width ;
    	let height ;
    	
        for (var i = 0; i < list.length; i++) {
        	let flag= true;	/*  false 시 팝업 안나옴 */
        	if(flag){
        		// 팝업 창의 위치 설정
        		setPostion(i);
        		// 팝업 창 띄위기
        	/* 	console.log(sLeft);
        		console.log(sTop);
        		console.log(width);
        		console.log(height); */
        		
        		window.open("ViewPopupController?num=" + list[i], "popup" + i,
        				"left=" + sLeft + ",top=" + sTop + ",width=" + width + ",height=" + height);
        	}
        }
        
        // 팝업 창의 위치 설정
      
	function setPostion(i) {
		console.count();
			let screenWidth = screen.width;
			let screenHeight = screen.height;
			// 창의 크기
			width = (screenWidth / 4);
			height = (screenHeight / 3);
//			console.log("화면 크기: " , width);
	//		console.log("화면 높이: " ,height);
			// 창의 시작점
			sLeft = width * ((i % 4));
			sTop = height * Math.floor(i / 4);
	//		console.log("팝업 크기: " , width);
	//		console.log("팝업 높이: " ,height);
		}
	});
</script>
<!-- popup 종료 -->

<body class="bg-light" style="height: 100vh;">  

	<!-- 네비게이션 바 -->
	<jsp:include page="common/navBar.jsp"></jsp:include>
	
	<!-- 바디 -->
	<div class="container-fluid" style="height: 100%; width: 100%;">
		<div class="d-flex align-content-center justify-content-center"
			style="height: 100%;">
			<div class="align-self-center" style="background-color: transparent;">
				<a href="/acorn/?cg=movie"><img class="rounded-2"
					src="resources/images/camera-reels.svg" width="250px" height="250px"></a>
			</div>
			<!-- <a>누르면 MoveToContentsHomeServlet로 이동 -->
			<div class="align-self-center px-5"
				style="background-color: transparent">
				<a href="/acorn/?dg=tv"><img class="rounded-2"
					src="resources/images/tv.png" width="250px" height="250px"></a>
			</div>
			<div class="align-self-center" style="background-color: transparent">
				<a href="/acorn/?cg=book"><img class="rounded-2"
					src="resources/images/book.svg" width="250px" height="250px"></a>
			</div>
		</div>
	</div>
	<!-- 푸터 -->
	<jsp:include page="common/footer.jsp"></jsp:include>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>