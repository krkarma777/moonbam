<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.moonBam.dto.ChatRoomDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=fy0xnhraqx&submodules=geocoder"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>


<body>

<!-- 네이버 지도가 뿌려질 곳 !  -->
<div id="map" style="width:100%;height:75vh; margin: 0 auto;"></div>

<%
    // 위의 @GetMapping("/mapAPI") 메서드에서 가져온 주소 정보
    List<ChatRoomDTO> chatRoomMapList = (List<ChatRoomDTO>) request.getAttribute("chatRoomMapList");
%>

<button id="showMapBtn">지도 보기</button>

<script type="text/javascript">
    $(document).ready(function () {
        $('#showMapBtn').click(function () {
            <%-- 모든 주소에 대해 반복하여 마커를 생성 --%>
            <%
            if (chatRoomMapList != null && !chatRoomMapList.isEmpty()) {
                for (ChatRoomDTO chatRoom : chatRoomMapList) {
            %>
                    var Addr_val = "<%= chatRoom.getAddr2() %>";

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
                    });
            <%
                }
            }
            %>
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

</body>

