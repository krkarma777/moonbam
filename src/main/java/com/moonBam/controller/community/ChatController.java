package com.moonBam.controller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moonBam.controller.community.oh.ChatRoomsService;


@Controller
public class ChatController {
	
	@Autowired
	ChatRoomsService crService;
	
	@RequestMapping(value = "/createChat", method = RequestMethod.GET)
	public String createChat() {
		return "/community/createChat";
	}
	
	/*
	 * @RequestMapping(value = "/saveChat", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public String saveChatRoom(@ModelAttribute ChatRoomDTO
	 * chatRoom) {
	 * 
	 * chatRoom.setmDate("2024-4-7"); chatRoom.setLeaderId(""); int n = 0; n =
	 * crService.saveChatRoom(chatRoom); if(n==1) { System.out.println("채팅방 정상 저장");
	 * } return ""; }
	 */
	
//asd
}//end class
