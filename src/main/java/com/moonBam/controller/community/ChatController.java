package com.moonBam.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moonBam.dto.ChatRoomDTO;

@Controller
public class ChatController {
	
	@RequestMapping(value = "/createChat", method = RequestMethod.GET)
	public String createChatForm() {
		return "/community/createChat";
	}
	
	@ResponseBody
	@RequestMapping(value = "/createChat", method = RequestMethod.POST)
	public String createChat(ChatRoomDTO dto) {
		System.out.println(dto);
		return "모임 만들기 성공";
	}
	
	

}//end class
