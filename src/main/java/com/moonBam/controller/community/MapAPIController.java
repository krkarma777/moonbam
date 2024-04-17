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
	        // ChatRoomDTO의 addr1, addr2, post를 뽑아오는 메서드를 서비스 클래스에 구현한 것으로 가정
	        List<ChatRoomDTO> chatRoomList = crService.getAllChatRooms();

	        // 모델에 ChatRoomDTO 리스트를 담아서 View로 전달
	        model.addAttribute("chatRoomList", chatRoomList);

	        return "community/communityHome"; // 반환할 뷰의 이름을 리턴
	    }
}
