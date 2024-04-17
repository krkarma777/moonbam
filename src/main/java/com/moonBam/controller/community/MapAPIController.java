package com.moonBam.controller.community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.service.ChatRoomService;



@Controller
public class MapAPIController {

	@Autowired
	ChatRoomService crService;
	
	@GetMapping("/mapAPI")
	public String mapList(Model model) {
	    List<ChatRoomDTO> chatRoomMapList = crService.getAllChatRooms();

	    if (chatRoomMapList != null && !chatRoomMapList.isEmpty()) {
	        // chatRoomMapList가 null이 아니고 비어 있지 않은 경우에만 모델에 추가
	        model.addAttribute("chatRoomMapList", chatRoomMapList);
	    }

	    return "community/communityHome"; // 반환할 뷰의 이름을 리턴
	}
}
