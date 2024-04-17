package com.moonBam.controller.community;

import java.security.Principal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.ChatRoomService;
import com.moonBam.service.member.MemberLoginService;

import jakarta.validation.Valid;


@Controller
public class ChatController {
	
	@Autowired
	ChatRoomService crService;
	
	@Autowired
	MemberLoginService memberLoginService;
	
	@RequestMapping(value = "/createChat", method = RequestMethod.GET)
	public String createChat() {
		return "community/createChat";
	}
	
	@RequestMapping(value = "/saveChat", method=RequestMethod.POST)	
	@ResponseBody
	public String saveChatRoom(@Valid @ModelAttribute ChatRoomDTO chatRoom, BindingResult bindingResult, Principal principal) {
		
		System.out.println(chatRoom);
		String userId = principal.getName();
		System.out.println("userId => "+userId);
		
		
        if (bindingResult.hasErrors()) {
            // 유효성 검사 에러 처리
            return "에러 발생";
        }

        // 정상 처리
        return "성공";
		
//		chatRoom.setmDate("2024-4-7");
//		chatRoom.setLeaderId("");
//		int n = 0;
//		n = crService.saveChatRoom(chatRoom);
//		if(n==1) {
//			System.out.println("채팅방 정상 저장");
//		}
		
//		return "채팅방 개설 완료"   ;//TODO해당 채팅방 주소로 가도록 나중에 세팅
	}


	@RequestMapping(value = "/delegateMaster")
	@ResponseBody
	public String delegateMaster(
			@RequestParam String chatNum, 
			@RequestParam String newMaster,
			Principal principal
			) {
		HashMap<String, String> map = new HashMap<>();
		map.put("chatNum", chatNum);
		map.put("to", newMaster);
		int n = crService.delegateMaster(map);
		
		//////////////////권한위임 기존 방장이 하는지 검사
		
		String formerMaster = principal.getName();
//		MemberDTO memberDTO = memberLoginService.findByPrincipal(principal);
		
		Boolean checkMaster = (formerMaster == crService.checkMaster(chatNum));
		
		if(!checkMaster) {
			System.out.println("너 방장 아니지");
		}else {
			if(n==1) {System.out.println("권한 위임 정상 처리");}else {System.out.println("권한 위임 실패");}
		}
		//////////////////
		
		return "";
	}
	
}//end class
