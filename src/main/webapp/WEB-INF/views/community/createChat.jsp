<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>문밤</title>
  
  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
  <!-- jQuery UI -->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  
  <!-- 부트 스트랩 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

  <style type="text/css">
    * {
      padding: 0;
      margin: 0;
      box-sizing: border-box;
    }
    .item {
      margin-top: 2px;
      margin-bottom: 5px;
      margin-left: 10px;
      width: 450px;
    }
    .item2 {
      margin-top: 2px;
      margin-bottom: 5px;
      margin-left: 10px;
      width: 100px;
    }
    #myTextarea {
      border: 1px solid E5E5E5;
      border-radius: 5px;
      margin-left: 10px;
      width: 450px;
      height: 170px; 
    }
    span {
      font-size: 18px;
    }
  </style>

  <script>
    $(document).ready(function() {
		// 달력       
		$("#mDate").datepicker({
			dateFormat: "yy-mm-dd",
            onSelect: function(dateText, inst) {
              // 선택한 날짜
              var selectedDate = new Date(dateText);
              console.log(selectedDate);

              // 오늘 날짜
              var today = new Date();

              // 14일 후의 날짜
              var twoWeeksLater = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 14);

              // 선택한 날짜와 오늘 날짜의 차이 계산
              var timeDiff = selectedDate.getTime() - today.getTime();
              var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

              if (diffDays <= 14 && diffDays >= 0) {
                // 선택한 날짜가 오늘로부터 14일 이내인 경우에만 입력
                $(this).val(dateText);
              } else {
                // 그렇지 않은 경우에는 "날짜를 선택하세요"로 변경
                $(this).val('');
                $(this).attr('placeholder', '선택 불가: 오늘로부터 14일 이내의 날짜를 선택해주세요.');
              }
            }
          });

		// 모임 소개글 글자수 제한 및 실시간 글자수 표시
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

      
		//카테고리가 변경될 때 작성 양식 변경됨
		$('#category').change(function(){
		      var selectedCategory = $(this).val();
		      var placeholderText = "";
	
		      if(selectedCategory === "movie") {
		        placeholderText = "영화 이름을 적어주세요";
		      } else if(selectedCategory === "book") {
		        placeholderText = "도서명 또는 작가명을 적어주세요";
		      } else if(selectedCategory === "music") {
		        placeholderText = "노래이름 또는 가수명을 적어주세요";
		      } else if(selectedCategory === "ect") {
		        placeholderText = "모임에서 공유하고 싶은 주제를 적어주세요";
		      } else {
		        placeholderText = "카테고리를 선택하세요";
		      }
	
		      $('#roomTitle').attr("placeholder", placeholderText);
		      
		      // 카테고리가 선택되지 않았을 때 입력창 비활성화
		      if (selectedCategory === "") {
		        $('#roomTitle').attr("disabled", true);
		      } else {
		        $('#roomTitle').removeAttr("disabled");
		      }
		});

		// roomTitle 초기 설정
		$('#roomTitle').attr("placeholder", "카테고리를 선택하세요");
		$('#roomTitle').attr("disabled", true);
	
		// 폼 제출 전 양식 확인
	  	$("#createChat").on("submit", function() {
	  		//정보들 다 입력했는지 확인
			const category = $("#category").val();
			const roomTitle = $("#roomTitle").val();
			const amount = $("#amount").val();
			const mDate = $("#mDate").val();
			const roomText = $("#myTextarea").val();
			const postcode = $("#sample4_postcode").val();
			const roadAddress = $("#sample4_roadAddress").val();
			const jibunAddress = $("#sample4_jibunAddress").val();
	          
			if (category === "" || roomTitle === "" || amount === "" || mDate === "" || roomText === "" 
	            || postcode === "" || roadAddress === "" || jibunAddress === "") {
	            alert("모든 입력 항목을 채워주세요.");
	            event.preventDefault();
	            return false;
			}          
	          	          
	 	});//
      
      
	});//end doc
  </script>
</head>
<body class="bg-light" style="height:700px; width:100%; position: relative; border: 0px solid black;">
  <div style="height: 100%; width: 100%">
    <div style="height: 30px; background-color: #ffb2c4; font-size: 19px; margin-bottom: 5px; color: white;">
      <b>모임 만들기</b>
    </div>
    <form action="/acorn/saveChat" method="post" id="createChat">
      <%-- <input type="hidden" name="leaderId" value="<%=request.getAttribute("userId")%>"> --%>
      
      <span>카테고리</span>
      <select name="category" id="category" class="form-select form-select-sm item">
        <option value="" selected>선택하세요</option>
        <option value="movie">영화</option>
        <option value="book">독서</option>
        <option value="music">음악</option>
        <option value="ect">기타</option>
      </select>
      <input class="form-control form-control-sm item" type="text" placeholder="카테고리를 선택하세요" id="roomTitle" name="roomTitle">
    
      <span>인원수</span>
      <select name="amount" class="form-select form-select-sm item" style="width: 100px;">
        <option value=2>2</option>
        <option value=3>3</option>
        <option value=4>4</option>
        <option value=5>5</option>
      </select>
    
      <span>모임 장소</span>
      <input class="btn" style="background-color: #ff416c; color: white; font-size: 10px; width:70px;" type="button" onclick="sample4_execDaumPostcode()" value="주소검색"><br>
      <!-- 다음주소 시작-->
      <input class="form-control form-control-sm item2" type="text" name="post" id="sample4_postcode" placeholder="우편번호">      
      <input class="form-control form-control-sm item" type="text" name="addr1" id="sample4_roadAddress" placeholder="도로명주소">
      <input class="form-control form-control-sm item" type="text" name="addr2" id="sample4_jibunAddress" placeholder="지번주소">
      <div id="guide" style="color:#999; font-size: 15px;"></div>
      <!-- 다음주소 끝 -->
      
      <span>모임 날짜</span><br>
      <span style="font-size: 12px; color: gray;">※설정한 모임 날짜로부터 3일 후에 모임방이 자동 삭제됩니다.</span><br>
      <span style="font-size: 12px; color: gray;">※오늘로부터 14일 이내로만 설정 가능합니다.</span>
      <input class="form-control form-control-sm item" type="text" id="mDate" placeholder="날짜를 선택하세요." name="mDate">
      
      <span>모임 소개글</span>
      <span id="charCount" style="font-size: 12px; color: gray;">0</span><span style="font-size: 12px; color: gray;"> / 150</span>
      <div class="form-floating">
        <textarea class="form-control" id="myTextarea" rows="10" cols="30" name="roomText"></textarea>
      </div>
      <div style="position: absolute; width:490px; margin-left: 4px;">
        <input class="btn" type="reset" value="초기화" style="background-color: #ff416c; color:white; float: left;">
        <input class="btn" type="submit" value="만들기" style="background-color: #ff416c; color:white; float: right;">
      </div>
    </form> 
  </div>

<!-- 다음 주소 -->
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <script>
    function sample4_execDaumPostcode() {
      new daum.Postcode({
        oncomplete: function(data) {
          var fullRoadAddr = data.roadAddress;
          var extraRoadAddr = '';

          if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
            extraRoadAddr += data.bname;
          }
          if(data.buildingName !== '' && data.apartment === 'Y'){
             extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }
          if(extraRoadAddr !== ''){
            extraRoadAddr = ' (' + extraRoadAddr + ')';
          }
          if(fullRoadAddr !== ''){
            fullRoadAddr += extraRoadAddr;
          }

          document.getElementById('sample4_postcode').value = data.zonecode;
          document.getElementById('sample4_roadAddress').value = fullRoadAddr;
          document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

          if(data.autoRoadAddress) {
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
  <!-- 다음 주소 -->

</body>
</html>
