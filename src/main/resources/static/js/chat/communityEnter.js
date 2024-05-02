/**
 * 
 *///방 입장 ajax



function chatRoomEnter(chatNum) {
	
	
	console.log("chatRoomEnter");
	
	
	$.ajax({

        type: "GET",
        url: "/acorn/chatRoom",
        data: {
          "chatNum" : chatNum
        },
        success: function (data, status, xhr) {
        	
        	//console.log("하이",data)
			
			if(data == "successToInsert"){
				//console.log(data);
				//opener.close();
				chatRoomOpen(chatNum); //다른 ajax실행
				
				
			}else if(data == "failToInsert"){
				
				alert("문제가 발생하였습니다.");
				window.close(); ///내 창 닫기
				//location.reload(true); ///새로고침
				
			}else if(data == "failToInsertCount"){
				
				alert("입장 가능한 인원 수를 초과하였습니다.");
				window.close(); ///내 창 닫기
				
			}
            

        },
        error: function (xhr, status, error) {
				
        	console.log("입장하기 error 발생",error)
        }
    })//ajax
    
    
}



function chatRoomOpen(chatNum) {

	$.ajax({

        type: "GET",
        url: "/acorn/chatRoom/enter",
        data: {
          "chatNum" : chatNum
        },
        success: function (data, status, xhr) {
        	
        	console.log("하이",data)
			
			if(data == "successToEnter"){
				//console.log(data);
				var url = "/acorn/chatRoom/enterFinal?chatNum="+chatNum;
				window.open(url, '_blank', 'width=500, height=700');
				return false;
				
			}else if(data == "failToEnterKicked"){
				
				alert("강퇴된 방에는 다시 입장할 수 없습니다.");
				window.close(); ///내 창 닫기
				//location.reload(true); ///새로고침
				
			}else if(data == "failToEnter"){
				
				alert("문제가 발생하였습니다.");
				window.close(); ///내 창 닫기
				
			}
            

        },
        error: function (xhr, status, error) {
				
        	console.log("입장하기 검수에서 error 발생",error)
        }
    })//ajax


}