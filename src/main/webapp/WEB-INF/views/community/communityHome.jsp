<%@page import="com.moonBam.dto.CommunityPageDTO"%>
<%@page import="com.moonBam.dto.ChatRoomDTO"%>
<%@page import="com.moonBam.dto.board.PageDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	CommunityPageDTO cpDTO = (CommunityPageDTO)request.getAttribute("cpDTO");
	List<ChatRoomDTO> chatRoomList = cpDTO.getList();
%>
<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<!-- 정상 지도 뿌리기 (이거 열어두면 송하 돈 나감)  작동 테스트 완료-->
<!-- <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=fy0xnhraqx&submodules=geocoder"></script> -->
<!-- 임시 지도 뿌리기 (오류 나는 게 맞음)  -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=ClientId&submodules=geocoder"></script>


<link rel="stylesheet" href="resources/js/fullpage/jquery.fullPage.css">
<!-- chatRoomController 에서 작동되는 기능에 결과에 따라서 session에 저장된 mesg값을 다르게 하여 alert를 띄우고 있음   -->
	<% String mesg = (String) session.getAttribute("mesg");
	if(mesg != null ){	
		
		%>	
		
		<script>
		alert("<%= mesg %>");
		</script>
	
	<%} 
	
	session.removeAttribute("mesg");
	
	%>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="resources/js/fullpage/jquery.fullPage.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		//풀페이지 시작
		$('#fullpage').fullpage({
			//options here
			autoScrolling:true,
			scrollHorizontally: true,
	        navigation:true,
		});
		
		//내 채팅방 목록 보기
		$("#myChatList").click(function(){
			console.log("myChatList")
			location.href = "myChatList";
		})
		
		//community개설로 이동
		$("#createCommunity").click(function(){			
			// 새 창에서 소모임 개설 페이지 열기
		    var newWindow = window.open('about:blank', '_blank', 'width=470px,height=705px');
			
		    // 새 창의 위치를 브라우저의 정중앙으로 설정
		    var screenWidth = window.screen.width;
		    var screenHeight = window.screen.height;
		    var windowWidth = 470;
		    var windowHeight = 705;		
		    var left = (screenWidth - windowWidth) / 2;
		    var top = (screenHeight - windowHeight) / 2;
		    
		    newWindow.moveTo(left, top);
		    
		    newWindow.location.href = 'http://localhost:8090/acorn/createChat';
		})
		
	})
</script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
}
a{
	text-decoration: none;
}
button {
	border: 1.111;
	padding-top: 4; padding-bottom: 4;
	padding-left: 8; padding-right: 8;
}
.pagination{
	justify-content: center;
}
.pagination .page-link{
	border: none;
	color:white; 
	background-color: #ff416c; 
	opacity:0.8;
	color: white;
}
.pagination .page-item.active .page-link{
	color:white; 
	background-color: #ff416c;
	opacity:0.8; 
	color: white;
	border: none;
}
/* 활성화된 페이지 번호 스타일 */
.pagination .page-item.active .page-link {
    background-color: #FF285A;
    background-image: linear-gradient(180deg, #FF285A, #FF174D);
    /* 그라디언트 효과 */
    border: none; /* 테두리 제거 */
}
</style>

</head>
<body class="bg-light" style="height: 100vh;">

	<!-- 바디 -->
	<main id="fullpage" class=""><b>
	
	<!-- 1section -->
	<section class="section">
	<div style="height: 910px; width: 1200px; margin: auto;">
		
		<!-- 상단 버튼 -->
		<div style="">
			<!-- 내 채팅방 목록 보기 버튼 -->
			<button type="button" class="btn" style="background-color: #ff416c; color:white; margin-left: auto;" id="myChatList"><b>내방</b></button>
			<!-- 개설 버튼 -->
			<button type="button" class="btn" style="float:right; background-color: #ff416c; color:white; margin-left: auto;" id="createCommunity"><b>개설</b></button>
		</div>
		
		<!-- community목록 -->
		<table style="margin-top: 5px; width:1200px; height:795px">
			<%int count = 0;
			for(int j=1; j<=3;j++){	%>
				<tr style="width: 1200px; height: 265px;">
					<%for(int k=1; k<=4; k++) { 
						if(count==chatRoomList.size()) break;
						ChatRoomDTO chatRoom = chatRoomList.get(count);
						int chatNum = chatRoom.getChatNum();
						String roomTitle = chatRoom.getRoomTitle();
						String roomText = chatRoom.getRoomText();
						int currentNow = chatRoom.getCurrentNow();
						int amount = chatRoom.getAmount();
						String addr1 = chatRoom.getAddr1();
						String category = chatRoom.getCategory();
							
						switch(category) {
						    case "music" : category = "음악";
						         break;
						    case "movie": category = "영화";
						         break;
						    case "book": category = "독서";
					         	 break;
						    case "etc": category = "기타";
						    	 break;
					}
						
						
					%>
					<td id="<%=count%>" style="width: 300px; height: 265px; float: left">
						<div class="border" style="width: 300px; height: 265px;">
							<div class="border-bottom" style="height: 40px; width: 299px; background-color: #ffb2c4; align-content: center;">
								<a href="chatRoom?chatNum=<%=chatNum%>" style="color:black; font-size: 15px;">[<%=category %>] <%=roomTitle %></a>
							</div>
							<div class="border-top" style="height: 225px; width: 299px;">
								<div style=" font-size: 18px; height: 175px;">
									<%=roomText %>
								</div>
								<div style="width: 100%; height:49px; font-size: 15px; padding-left: 2px; padding-right: 5px; position:relative;">
									<div style="float: left;"><%=addr1 %></div>
									<div style="position: absolute; right: 0; bottom: 0; margin-right: 2px;"><%=currentNow %>/<%=amount %></div>
								</div>
							</div>
						</div>
					</td>
					<%count++; } %>
				</tr>
			<%} %>
		</table>
		
		<!-- 페이지네이션 -->
		<div style="margin-top: 5px; width:auto;">
			<jsp:include page="communityPage.jsp"></jsp:include>
		</div>
	</div>
	</section>
	
	<!-- 2section -->
<section class="section">
	<div style="height: 910px; width: 1200px; margin: auto;">
        <div id="map" style="width:100%;height:100%;"></div>
    </div>
</section>

<script type="text/javascript">
    var map = new naver.maps.Map("map", {
        center: new naver.maps.LatLng(37.5, 127.0), // 지도 초기 위치 설정
        zoom: 10, // 초기 확대 수준 설정
        mapTypeControl: true
    });

    <%
 // 위의 @GetMapping("/mapAPI") 메서드에서 가져온 주소 정보
 List<ChatRoomDTO> chatRoomMapList = (List<ChatRoomDTO>)request.getAttribute("chatRoomMapList");

 if (chatRoomMapList != null && !chatRoomMapList.isEmpty()) {
     // chatRoomMapList가 null이 아니고 비어 있지 않은 경우에만 실행
     for (ChatRoomDTO chatRoom : chatRoomMapList) {
 %>
 naver.maps.Service.geocode({
     query: '<%= chatRoom.getAddr1() + " " + chatRoom.getAddr2() %>'
 }, function(status, response) {
     if (status === naver.maps.Service.Status.ERROR) {
         return alert('검색에 실패했습니다.');
     }

     if (response.v2.meta.totalCount === 0) {
         return alert('검색 결과가 없습니다.');
     }

     var item = response.v2.addresses[0],
         point = new naver.maps.Point(item.x, item.y),
         marker = new naver.maps.Marker({
             position: new naver.maps.LatLng(point.y, point.x),
             map: map
         });

     // 마커 클릭 시 정보 표시
     naver.maps.Event.addListener(marker, 'click', function() {
         alert('<%= chatRoom.getPost() %>');
     });
 });
 <%
     }
 }
 %>
</script>

	
	</b></main>
	
	<!-- 네비게이션 바 -->
	<jsp:include page="../common/navBar.jsp"></jsp:include>
	
	<!-- 푸터 -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>