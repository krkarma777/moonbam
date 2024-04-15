//package com.moonBam.controller.community;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.moonBam.dto.ChatRoomDTO;
//
//
//@Controller
//public class ChatController {
//	
//	@Autowired
//	ChatRoomsService crService;
//	
//	@RequestMapping(value = "/createChat", method = RequestMethod.GET)
//	public String createChat() {
//		return "/community/createChat";
//	}
//	
//	@RequestMapping(value = "/saveChat", method=RequestMethod.POST)
//	@ResponseBody
//	public String saveChatRoom(@ModelAttribute ChatRoomDTO chatRoom) {
//		
//		chatRoom.setmDate("2024-4-7");
//		chatRoom.setLeaderId("");
//		int n = 0;
//		n = crService.saveChatRoom(chatRoom);
//		if(n==1) {
//			System.out.println("채팅방 정상 저장");
//		}
//		return "";
//	}
//	@RequestMapping(value = "/delegateMaster")
//	@ResponseBody
//	public String delegateMaster(
//			@RequestParam String chatNum, 
//			@RequestParam String oldMaster, 
//			@RequestParam String newMaster) {
//		HashMap<String, String> map = new HashMap<>();
//		map.put("chatNum", chatNum);
//		map.put("from", oldMaster);
//		map.put("to", newMaster);
//		int n = crService.delegateMaster(map);
//		
//		//////////////////권한위임 기존 방장이 하는지 검사
//		
//		Boolean checkMaster = oldMaster == crService.checkMaster(chatNum);
//		
//		if(!checkMaster) {
//			System.out.println("너 방장 아니지");
//		}else {
//			if(n==1) {System.out.println("권한 위임 정상 처리");}else {System.out.println("권한 위임 실패");}
//		}
//		//////////////////
//		
//		return "";
//	}
//	
////asd
//}//end class
