package com.moonBam.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {
	
	@RequestMapping(value = "/createChat", method = RequestMethod.GET)
	public String createChat() {
		return "/community/createChat";
	}
	
	

}//end class
