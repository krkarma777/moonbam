package com.moonBam.controller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.service.ChatRoomService;

@Controller
public class ChatController {
	
	@Autowired
	ChatRoomService crService;
	
	@RequestMapping(value = "/createChat", method = RequestMethod.GET)
	public String createChat() {
		return "/community/createChat";
	}
	
	@RequestMapping(value = "/saveChat", method=RequestMethod.GET)
	@ResponseBody
	public String saveChatRoom(@ModelAttribute ChatRoomDTO chatRoom) {
		
		chatRoom.setAmount(5);
		chatRoom.setCategory("asd");
		chatRoom.setcDate("2024/01/01");
		chatRoom.setmDate("2024/02/01");
		chatRoom.setChatNum(2);
		chatRoom.setCurrentNow(3);
		chatRoom.setLeaderId("ㅁㄴㅇ");
		chatRoom.setLoc("저세상 어딘가");
		chatRoom.setRoomText("영화관 죽돌이");
		chatRoom.setRoomTitle("내일 지구가 멸망해도 영화를 보겠다");
		
		int n = 0;
		n = crService.saveChatRoom(chatRoom);
		if(n==1) {
			System.out.println("채팅방 정상 저장");
		}
		return "";
	}
	
//asd
}//end class
