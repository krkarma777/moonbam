<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<link
	href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
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
		let width;
    	let height;

    	for (var i = 0; i < list.length; i++) {
        	let cookie = getCookie("popup"+list[i])
        	if(cookie != "check"){
        		// 팝업 창의 위치 설정
        		setPostion(i);
        		// 팝업 창 띄위기
        		window.open("ViewPopupController/" + list[i]+"/main", "popup" + i,
        	            "left=" + sLeft + ",top=" + sTop + ",width=" + width + ",height=" + height + ", location=1");

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
			// 창의 시작점
			sLeft = width * ((i % 4));
			sTop = height * Math.floor(i / 4);
		}
	});
   
        		
	// 쿠키에 있어 checked이면 return checked
	 function getCookie(name) {
	        var decodedCookie = decodeURIComponent(document.cookie);
	        
			var cookies = decodedCookie.split(';');
			for (var i = 0; i < cookies.length; i++) {
	            var cookie = cookies[i].trim();
	            if (cookie.indexOf(name) === 0) {
	                return cookie.substring(name.length + 1);
	            }
	        }
	        return "";
	    }
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
			<div style="width: 20px;"></div>
			<!-- <a>누르면 MoveToContentsHomeServlet로 이동 -->
			<!-- <div class="align-self-center px-5"
				style="background-color: transparent">
				<a href="/acorn/?dg=tv"><img class="rounded-2"
					src="resources/images/tv.png" width="250px" height="250px"></a>
			</div> -->
			<div class="align-self-center" style="background-color: transparent">
				<a href="/acorn/?cg=community"><img class="rounded-2"
					src="resources/images/community.svg" width="250px" height="250px"></a>
			</div>
		</div>
	</div>
	
	<!-- 푸터 -->
	<jsp:include page="common/footer.jsp"></jsp:include>
	
	<script
		src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>