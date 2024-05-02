<%@page import="com.moonBam.dto.CommunityPageDTO"%>
<%@page import="com.moonBam.dto.ChatRoomDTO"%>
<%@page import="com.moonBam.dto.board.PageDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
	CommunityPageDTO cpDTO = (CommunityPageDTO)request.getAttribute("cpDTO");
	List<ChatRoomDTO> chatRoomList = cpDTO.getList();
	
	String communityCategory = (String)request.getAttribute("communityCategory");
  	System.out.println("in communityHome.jsp: "+ communityCategory);
%>
<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=fy0xnhraqx&submodules=geocoder"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="resources/js/fullpage/jquery.fullPage.js"></script>
<link rel="stylesheet" href="resources/js/fullpage/jquery.fullPage.css">
<!-- chatRoomController 에서 작동되는 기능에 결과에 따라서 session에 저장된 mesg값을 다르게 하여 alert를 띄우고 있음   -->
<% 
    String mesg = (String) session.getAttribute("mesg");
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
            //로그인 되어있는 경우에만 방 개설 가능
            var userId = '<sec:authentication property="name"/>';
            if ( userId == "anonymousUser" ){
                alert("로그인이 필요한 작업입니다.");
                event.preventDefault();
            }else{
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
                
                
            }
        })//
        
    })  
</script>
<link href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
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
        color:black; 
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
            for(int j=1; j<=3;j++){    %>
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
                            <div class="border-bottom" style="height: 40px; width: 300px; background-color: #ffb2c4; align-content: center;">
                                <a style="color:black; font-size: 16px;" onclick="chatRoomEnter('<%=chatNum%>')">[<%=category %>] <%=roomTitle %></a>
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
        <!-- 네이버 지도가 뿌려질 곳 -->
        <div id="map" style="width:100%;height:75vh; margin: 0 auto;"></div>
    
        <!-- Java로부터 받은 주소 정보 -->
        <%
            List<ChatRoomDTO> chatRoomMapList = (List<ChatRoomDTO>) request.getAttribute("chatRoomMapList");
        %>
    
        <!-- 지도 보기 버튼 -->
        <button id="showMapBtn" class="btn" style="float:right; background-color: #ff416c; color:white; margin-left: auto;">지도 보기</button>
    
    </section>
    
    </b></main>
    
    <!-- 네비게이션 바 -->
    <jsp:include page="../common/navBar.jsp"></jsp:include>
    
    <!-- 푸터 -->
    <jsp:include page="../common/footer.jsp"></jsp:include>
    
<script src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"></script>
    
     <script type="text/javascript">
    $(document).ready(function () {
        $('#showMapBtn').click(function () {
            console.log("map 호출");
            <% if (chatRoomMapList != null && !chatRoomMapList.isEmpty()) {
                for (ChatRoomDTO chatRoom : chatRoomMapList) { %>
                    var Addr_val = "<%= chatRoom.getAddr2() %>";
                    var roomTitle = "<%= chatRoom.getRoomTitle() %>";
                    var roomText = "<%= chatRoom.getRoomText() %>";
                    var chatNum = "<%= chatRoom.getChatNum() %>";

                    // 도로명 주소를 좌표 값으로 변환(API)
                    naver.maps.Service.geocode({
                        query: Addr_val
                    }, function(status, response) {
                        if (status !== naver.maps.Service.Status.OK) {
                            return alert('Something wrong!');
                        }

                        var result = response.v2,
                            items = result.addresses;

                        // 리턴 받은 좌표 값을 변수에 저장
                        var x = parseFloat(items[0].x);
                        var y = parseFloat(items[0].y);

                        // 마커 생성
                        var markerOptions = {
                            position: new naver.maps.LatLng(y, x),
                            map: map,
                            icon: {
                                url: 'resources/img/marker.png',
                                size: new naver.maps.Size(22, 36),
                                origin: new naver.maps.Point(0, 0),
                                anchor: new naver.maps.Point(11, 35)
                            }
                        };

                        // 마커 생성 및 추가
                        var marker = new naver.maps.Marker(markerOptions);
                        
                        // Initialize infoWindow state
                        var infoWindowOpen = false;
                        var infoWindow;

                        // 마커 클릭 이벤트
                        naver.maps.Event.addListener(marker, 'click', function () {
                            if (infoWindowOpen) {
                                infoWindow.close();
                                infoWindowOpen = false;
                            } else {
                                var href = 'http://localhost:8090/acorn/chatRoom/enter?chatNum=' + chatNum;
                                var contentString = '<h2>' + roomTitle + '</a></h2>' + '<p>' + roomText + '</p>';
                                infoWindow = new naver.maps.InfoWindow({
                                    content: contentString
                                });
                                infoWindow.open(map, marker);
                                infoWindowOpen = true;
                            }
                        });
                    });
            <% } } %>
        });
    });

    var map;
    function initMap() {
        map = new naver.maps.Map('map', {
            center: new naver.maps.LatLng(37.552758094502494, 126.98732600494576), //지도 시작 지점
            zoom: 12
        });
    }

    $(function() {
        initMap();
    });
</script>

         <script type="text/javascript">
        var arr2 = [
            { location: '장소1', lat: '위도1', lng: '경도1', RoomTitle: 'RoomTitle1', RoomText: 'RoomText1', ChatNum: '21' },
            { location: '장소2', lat: '위도2', lng: '경도2', RoomTitle: 'RoomTitle2', RoomText: 'RoomText2', ChatNum: '22' },
            // arr2에 장소 정보 추가
        ];

        $(function () {
            for (var i = 0; i < arr2.length; i++) {
                var marker = new naver.maps.Marker({
                    position: new naver.maps.LatLng(arr2[i].lat, arr2[i].lng),
                    map: map
                });

                var infoWindow = new naver.maps.InfoWindow();

                naver.maps.Event.addListener(marker, 'click', (function (marker, i) {
                    return function () {
                        var href = 'http://localhost:8090/acorn/chatRoom/enter?chatNum=' + arr2[i].ChatNum;
                        var contentString = '<h2><a href="' + href + '">' + arr2[i].RoomTitle + '</a></h2>' + '<p>' + arr2[i].RoomText + '</p>';
                        infoWindow.setContent(contentString);
                        infoWindow.open(map, marker);
                    }
                })(marker, i));
            }
        });
    </script>
    
 <!-- 방 입장 ajax 실행 함수가 있는 js파일 import  -->   	
<script src="${pageContext.request.contextPath}/resources/js/chat/communityEnter.js" type="text/javascript"></script>   	

   	
<script type="text/javascript">

var list = <%=request.getAttribute("list")%>;  
$(function (){
	// 팝업 창 위치 &크기 변수
	let sLeft=0;
	let sTop=0;
	let width ;
	let height ;
	
    for (var i = 0; i < list.length; i++) {
    	let cookie = getCookie("popup"+list[i])
    	if(cookie != "check"){
    		// 팝업 창의 위치 설정
    		setPostion(i);
    		// 팝업 창 띄위기
    		window.open("ViewPopupController/" + list[i]+"/community", "popup" + i,
    				"left=" + sLeft + ",top=" + sTop + ",width=" + width + ",height=" + height);
    	}
    }
    
    // 팝업 창의 위치 설정
  
function setPostion(i) {
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
</body>
</html>