<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>문밤</title>

	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<!-- jQuery UI -->
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
	
	<!-- 네이버 우편번호 찾기 API -->
	<!-- <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=fy0xnhraqx"></script> -->
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=fy0xnhraqx&submodules=geocoder"></script>


<script>
    $(document).ready(function() {
        // 달력
        $("#datepicker").datepicker({
            dateFormat: "yy-mm-dd"
        });

        // 모임 소개글 글자수 제한
        $("#myTextarea").on("input", function() {
            const maxLength = 150;
            let text = $(this).val();
            const count = text.length;
            
            if (count > maxLength) {
                text = text.substring(0, maxLength);
                $(this).val(text);
            }
            
            $("#charCount").text(count + " / " + maxLength);
        });      

        // 폼 제출 전 양식 확인
        $("#createChat").on("submit", function() {
            const category = $("#category").val();
            const roomTitle = $("#roomTitle").val();
            const amount = $("#amount").val();
            const loc = $("#loc").val();
            const mDate = $("#mDate").val();
            const roomText = $("#myTextarea").val();
            
            if (category === "" || roomTitle === "" || amount === "" || loc === "" || mDate === "" || roomText === "") {
                alert("모든 입력 항목을 채워주세요.");
                return false;
            }
        });
        
        //////////////////////////////////////////주소찾기
        
		// 네이버 지도 API를 사용하여 주소 검색 기능 구현
        $("#mapOpen").click(function() {
            var mapContainer = document.getElementById('mapmap'); // 지도를 표시할 div
            var mapOption = {
                center: new naver.maps.LatLng(37.3595704, 127.105399), // 초기 지도의 중심 좌표
                zoom: 10 // 초기 지도의 확대 레벨
            };
            var map = new naver.maps.Map(mapContainer, mapOption); // 지도 생성
            
            // 주소 검색 후 선택한 주소를 입력 필드에 채우는 함수
            function searchAddressToInput() {
                var geocoder = new naver.maps.services.Geocoder();
                var address = $("#address").val();
                
                // 주소로 좌표를 검색
                geocoder.addressToCoord(address, function(coord) {
                    var lat = coord.y;
                    var lng = coord.x;
                    
                    // 검색한 좌표로 지도 이동
                    map.setCenter(new naver.maps.LatLng(lat, lng));
                    
                    // 선택한 주소를 입력 필드에 채우기
                    $("#loc").val(address);
                });
            }
            
            // 검색 버튼 클릭 시 주소 검색
            $("#searchBtn").click(function() {
                searchAddressToInput();
            });
            
            // 엔터 키 입력 시 주소 검색
            $("#address").keydown(function(event) {
                if (event.keyCode === 13) {
                    searchAddressToInput();
                }
            });
            
            // 지도를 보여주는 div 표시
            mapContainer.style.display = "block";
        });
        
        // 주소검색 버튼 누르면 우편번호 찾을 수 있는 팝업 열기
    // 주소검색 버튼 누르면 우편번호 찾을 수 있는 팝업 열기
    $("#findaddress").click(function() {
        // 팝업을 열기 위한 네이버 우편번호 서비스 API 활용
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new naver.maps.AddressService({
            oncomplete: function(data) {
                // 선택한 주소의 우편번호와 주소를 입력 필드에 채우기
                $("#loc").val(data.address);
                // 추가 필요 시 우편번호도 입력할 수 있도록
                $("#zipcode").val(data.zonecode);
                // 팝업이 열린 위치로 스크롤 이동
                document.body.scrollTop = document.documentElement.scrollTop = currentScroll;
            }
        }).open();
    });
        
		//////////////////////////////////////////주소찾기
		
		
    });//end doc
</script>
<style type="text/css">
.search { position:absolute;z-index:1000;top:20px;left:20px; }
.search #address { width:150px;height:20px;line-height:20px;border:solid 1px #555;padding:5px;font-size:12px;box-sizing:content-box; }
.search #submit { height:30px;line-height:30px;padding:0 10px;font-size:12px;border:solid 1px #555;border-radius:3px;cursor:pointer;box-sizing:content-box; }
</style>
</head>
<body>


<h1>모임만들기</h1>
<form action="/acorn/createChat" method="post" id="createChat">
	<b>카테고리</b>
		<select name="category">
			<option value="" selected>선택</option>
			<option value="movie">영화</option>
			<option value="book">책</option>
			<option value="ect">기타</option>
		</select>
	<br><br>
	<b>모임 이름</b> <input type="text" placeholder="작품명 / 지역구 / 모임날짜" name="roomTitle">
	<br><br>
	<b>인원수</b>
		<select name="amount">
			<option value="" selected>선택</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select>
	<br><br>
	
		
    <b>모임 장소</b>
	<input type="text" id="loc" name="loc" placeholder="주소를 입력하세요.">
	<br>
    <input type="button" value="지도열기" id="mapOpen">
    <!-- 네이버 지도를 표시할 div -->
    <div id="mapmap" style="width: 500px; height: 400px; display: none;"></div>
	<br>
	<input type="button" value="주소검색" id="findaddress">
	
	
	
	<div id="wrap" class="section">
	    <h2>주소와 좌표 검색 API 사용하기</h2>
	    <p>Geocoder 서브 모듈의 Service 객체를 사용하여 주소로 좌표를 검색하거나(Geocode) 좌표로 주소를 검색하는(Reversegeocode) 예제입니다.<br />
	    입력 창에 주소를 입력하여 검색하면 해당 주소의 좌표로 이동하며, 지도를 클릭하면 해당 지점의 경위도 좌표로 주소를 검색합니다.</p>
	    <div id="map" style="width:100%;height:600px;">
	        <div class="search" style="">
	            <input id="address" type="text" placeholder="검색할 주소" value="불정로 6" />
	            <input id="submit" type="button" value="주소 검색" />
	        </div>
	    </div>
	    <code id="snippet" class="snippet"></code>
	</div>
	
	
	<br><br>
	<b>모임 날짜</b> <input type="text" id="datepicker" placeholder="날짜를 선택하세요" name="mDate">
	<br><br>
	<b>모임 소개글</b><br>
	<textarea id="myTextarea" rows="10" cols="30" oninput="checkLength()" name="roomText"></textarea>
	<span id="charCount">0</span> / 150
	<br><br>
	<input type="submit" value="만들기">
	<input type="reset" value="초기화">
</form>


<!-- 주소찾기 -->
<script type="text/javascript">
    var map = new naver.maps.Map("map", {
        center: new naver.maps.LatLng(37.3595316, 127.1052133),
        zoom: 15,
        mapTypeControl: true
    });

    var infoWindow = new naver.maps.InfoWindow({
        anchorSkew: true
    });

    map.setCursor('pointer');

    function searchCoordinateToAddress(latlng) {
        infoWindow.close();
        naver.maps.Service.reverseGeocode({
            coords: latlng,
            orders: [
                naver.maps.Service.OrderType.ADDR,
                naver.maps.Service.OrderType.ROAD_ADDR
            ].join(',')
        }, function(status, response) {
            if (status === naver.maps.Service.Status.ERROR) {
                return alert('Something Wrong!');
            }
            var items = response.v2.results,
                address = '',
                htmlAddresses = [];
            for (var i=0, ii=items.length, item, addrType; i<ii; i++) {
                item = items[i];
                address = makeAddress(item) || '';
                addrType = item.name === 'roadaddr' ? '[도로명 주소]' : '[지번 주소]';
                htmlAddresses.push((i+1) +'. '+ addrType +' '+ address);
            }
            infoWindow.setContent([
                '<div style="padding:10px;min-width:200px;line-height:150%;">',
                '<h4 style="margin-top:5px;">검색 좌표</h4><br />',
                htmlAddresses.join('<br />'),
                '</div>'
            ].join('\n'));
            infoWindow.open(map, latlng);
        });
    }

    function searchAddressToCoordinate(address) {
        naver.maps.Service.geocode({
            query: address
        }, function(status, response) {
            if (status === naver.maps.Service.Status.ERROR) {
                return alert('Something Wrong!');
            }
            if (response.v2.meta.totalCount === 0) {
                return alert('totalCount' + response.v2.meta.totalCount);
            }
            var htmlAddresses = [],
                item = response.v2.addresses[0],
                point = new naver.maps.Point(item.x, item.y);
            if (item.roadAddress) {
                htmlAddresses.push('[도로명 주소] ' + item.roadAddress);
            }
            if (item.jibunAddress) {
                htmlAddresses.push('[지번 주소] ' + item.jibunAddress);
            }
            if (item.englishAddress) {
                htmlAddresses.push('[영문명 주소] ' + item.englishAddress);
            }
            infoWindow.setContent([
                '<div style="padding:10px;min-width:200px;line-height:150%;">',
                '<h4 style="margin-top:5px;">검색 주소 : '+ address +'</h4><br />',
                htmlAddresses.join('<br />'),
                '</div>'
            ].join('\n'));
            map.setCenter(point);
            infoWindow.open(map, point);
        });
    }

    function initGeocoder() {
        map.addListener('click', function(e) {
            searchCoordinateToAddress(e.coord);
        });

        $('#address').on('keydown', function(e) {
            var keyCode = e.which;
            if (keyCode === 13) { // Enter Key
                searchAddressToCoordinate($('#address').val());
            }
        });

        $('#submit').on('click', function(e) {
            e.preventDefault();
            searchAddressToCoordinate($('#address').val());
        });

        searchAddressToCoordinate('정자동 178-1');
    }

    function makeAddress(item) {
        if (!item) {
            return;
        }
        var name = item.name,
            region = item.region,
            land = item.land,
            isRoadAddress = name === 'roadaddr';
        var sido = '', sigugun = '', dongmyun = '', ri = '', rest = '';
        if (hasArea(region.area1)) {
            sido = region.area1.name;
        }
        if (hasArea(region.area2)) {
            sigugun = region.area2.name;
        }
        if (hasArea(region.area3)) {
            dongmyun = region.area3.name;
        }
        if (hasArea(region.area4)) {
            ri = region.area4.name;
        }
        if (land) {
            if (hasData(land.number1)) {
                if (hasData(land.type) && land.type === '2') {
                    rest += '산';
                }
                rest += land.number1;
                if (hasData(land.number2)) {
                    rest += ('-' + land.number2);
                }
            }
            if (isRoadAddress === true) {
                if (checkLastString(dongmyun, '면')) {
                    ri = land.name;
                } else {
                    dongmyun = land.name;
                    ri = '';
                }
                if (hasAddition(land.addition0)) {
                    rest += ' ' + land.addition0.value;
                }
            }
        }
        return [sido, sigugun, dongmyun, ri, rest].join(' ');
    }

    function hasArea(area) {
        return !!(area && area.name && area.name !== '');
    }

    function hasData(data) {
        return !!(data && data !== '');
    }

    function checkLastString(word, lastString) {
        return new RegExp(lastString + '$').test(word);
    }

    function hasAddition(addition) {
        return !!(addition && addition.value);
    }

    naver.maps.onJSContentLoaded = initGeocoder;

</script>


</body>
</html>
