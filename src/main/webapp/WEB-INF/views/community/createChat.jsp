<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	<!-- 부트 스트랩 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<style type="text/css">
	*{
		padding: 0;
		margin: 0;
		box-sizing: border-box;
	}
	.item{
		margin-top: 2px;
		margin-bottom: 5px;
		margin-left: 10px;
		width: 450px;
	}
	.item2{
		margin-top: 2px;
		margin-bottom: 5px;
		margin-left: 10px;
		width: 100px;
	}
	#myTextarea{
		border: 1px solid E5E5E5;
		border-radius: 5px;
		margin-left: 10px;
		width: 450px;
		height: 170px; 
	}
	span{
		font-size: 18px;
	}
</style>

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
            
            $("#charCount").text(count);
        });      

        // 폼 제출 전 양식 확인
        $("#createChat").on("submit", function() {
            const category = $("#category").val();
            const roomTitle = $("#roomTitle").val();
            const amount = $("#amount").val();
            const mDate = $("#mDate").val();
            const roomText = $("#myTextarea").val();
            const postcode = $("#sample4_postcode").val();
            const roadAddress = $("#sample4_roadAddress").val();
            const jibunAddress = $("#sample4_jibunAddress").val();
            
            if (category === "" || roomTitle === "" || amount === "" || mDate === "" || roomText === "" || postcode === "" || roadAddress === "" || jibunAddress === "") {
                alert("모든 입력 항목을 채워주세요.");
                return false;
            }
        });

    });//end doc
</script>
</head>
<body class="bg-light" style="height:700px; width:100%; position: relative; border: 0px solid black;">
<div style="height: 100%; width: 100%">
	<div style="height: 30px; background-color: #ffb2c4; font-size: 19px; margin-bottom: 5px; color: white;">
		<b>모임 만들기</b>
	</div>
		<form action="/acorn/saveChat" method="post" id="createChat">
			<span>카테고리</span>
				<select name="category" class="form-select form-select-sm item">
					<option value="" selected>선택하세요</option>
					<option value="movie">영화</option>
					<option value="book">독서</option>
					<option value="music">음악</option>
					<option value="ect">기타</option>
				</select>
			<span>모임 이름</span> <input class="form-control form-control-sm item" type="text" placeholder="작품명 / 지역구 / 모임날짜" name="roomTitle">
			<span>인원수</span>
				<select name="amount" class="form-select form-select-sm item" style="width: 100px;">
					<option value=2>2</option>
					<option value=3>3</option>
					<option value=4>4</option>
					<option value=5>5</option>
				</select>	
		    <span>모임 장소</span>
		    <input class="btn" style="background-color: #ff416c; color: white; font-size: 10px; width:70px; type="button" onclick="sample4_execDaumPostcode()" value="주소검색"><br>
			<!-- 다음주소 시작-->
			<input class="form-control form-control-sm item2" type="text" name="post" id="sample4_postcode" placeholder="우편번호">			
			<input class="form-control form-control-sm item" type="text" name="addr1" id="sample4_roadAddress" placeholder="도로명주소">
			<input class="form-control form-control-sm item" type="text" name="addr2" id="sample4_jibunAddress" placeholder="지번주소">
			<span id="guide" style="color:#999"></span>
			<!-- 다음주소 끝 -->
			<br>
			<span>모임 날짜</span> <input class="form-control form-control-sm item" type="text" id="datepicker" placeholder="날짜를 선택하세요" name="cDate">
			<span>모임 소개글</span>
				<div style="float: right;">
					<span id="charCount">0</span><span> / 150</span>
				</div>
			<div class="form-floating">
				<textarea class="form-control" id="myTextarea" rows="10" cols="30" oninput="checkLength()" name="roomText"></textarea>
			</div>
			<div style="position: absolute; width:490px; margin-left: 4px;">
				<input class="btn" type="reset" value="초기화" style="background-color: #ff416c; color:white; float: left;">
				<input class="btn" type="submit" value="만들기" style="background-color: #ff416c; color:white; float: right;">
			</div>
		</form>	
</div>


<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample4_roadAddress').value = fullRoadAddr;
                document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

                } else {
                    document.getElementById('guide').innerHTML = '';
                }
            }
        }).open();
    }
</script>

</body>
</html>
