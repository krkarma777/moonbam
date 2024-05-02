package com.moonBam.controller.community;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.service.ChatRoomService;
import com.moonBam.service.CommunityEnterOutService;
import com.moonBam.service.member.MemberLoginService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class ChatController {
	
	@Autowired
	ChatRoomService crService;
	
	@Autowired
	MemberLoginService memberLoginService;
	
	
	@Autowired
	CommunityEnterOutService comEnterOutService;
	
	//소모임 대문에서 개설버튼 누르면 소모임 만드는 폼으로 이동
	@RequestMapping(value = "/createChat", method = RequestMethod.GET)
	public String createChat() {
		return "community/createChat";
	}
	
	@RequestMapping(value = "/saveChat", method=RequestMethod.POST)	
	//@ResponseBody//////////////////////////////////
	public String saveChatRoom(@Valid @ModelAttribute ChatRoomDTO chatRoom, BindingResult bindingResult, Principal principal) {


		String userId = principal.getName();
		
		/////////////////////////////////소모임방(chatRoom) 개설 insert
		//roomTitle 설정
		String roomTitle = chatRoom.getRoomTitle();
		String addr1 = chatRoom.getAddr1();
		LocalDate thismDate = chatRoom.getmDate();//지금 모임방의 모임날짜 가져오기
		String mmDate = thismDate.format(DateTimeFormatter.ofPattern("MM-dd"));//모임방 이름에 사용할 모임날짜 04-30 형식으로 변경		
		String[] addr_arr = addr1.split(" ");
		String loc = addr_arr[0];
		roomTitle = roomTitle+"/"+loc+"/"+mmDate;
		chatRoom.setRoomTitle(roomTitle);
		//cDate 설정
		chatRoom.setcDate(LocalDate.now());
		//leaderId 설정
		chatRoom.setLeaderId(userId);

		int n = crService.saveChatRoom(chatRoom);
		System.out.println("채팅방이"+n+"개 생성되었습니다.");
		
		/////////////////////////////////chatMember에 정보 insert
		
		//leaderId와 roomtitle로 dto select해서 가져오기
		Map<String, String> chatRoomSelect = new HashMap<>();
		chatRoomSelect.put("userId", userId);
		chatRoomSelect.put("roomTitle", roomTitle);
		ChatRoomDTO chatRoomNow = crService.chatRoomNowSelect(chatRoomSelect);
		System.out.println("chatRoomNow dto => "+chatRoomNow);
		
		int chatNum = chatRoomNow.getChatNum();
		int currntNow = chatRoomNow.getCurrentNow();
		 
		/////chatmember에 insert  MAP
		Map<String, Object> chatMemberInsertMap = new HashMap<>();
		chatMemberInsertMap.put("chatNum", chatNum);
		chatMemberInsertMap.put("userId", userId);
		
		Map<String, Integer> chatRoomUpdateMap = new HashMap<>();
		chatRoomUpdateMap.put("chatNum", chatNum);
		chatRoomUpdateMap.put("currntNow", (currntNow+1));
		
		
		//chatroom에 currentNow를 +1 update  MAP
		int n2 = comEnterOutService.chatMemberEnterInsert(chatMemberInsertMap,chatRoomUpdateMap);
		System.out.println("chatMember에"+n2+"개가 추가되고 현재 채팅방 인원수가 "+(currntNow+1)+"로 변경되었습니다.");		
		
		return "redirect:/chatRoom?chatNum="+chatNum;

	}

//	@RequestMapping(value = "/delegateMaster")
//	public String delegateMaster(
//			@RequestParam String chatNum, 
//			@RequestParam String userId,
//			Principal principal,
//			HttpSession session
//			) {
//		System.out.println("안녕 나는 chatController.delegateMaster야");
//		
//		
//		crService.delegateMaster(principal, chatNum, userId);
//		
//		session.setAttribute("newLeader", userId);
//		
//		return "redirect:/chatRoom?chatNum="+chatNum;
//	}
	
	//강퇴하기 기능
	@RequestMapping(value = "/Chatmore/ChatKickUser", method = RequestMethod.GET)
	public String ChatKickUser (String userId, String chatNum, HttpSession session) {
		
		int n = 0;
		
		System.out.println("ChatKickUser===================");
		System.out.println("강퇴할 유저의 이름 확인 후 서비스레이어 전달");
		System.out.println(userId);
		
		//session.setAttribute("Kicked", "yes");
		session.setAttribute("KickedUserId", userId);
		
		System.out.println("=================================");
		
		n = crService.ChatKickUser(userId, chatNum);
		
		System.out.println("1이면 정상처리됨==========");
		System.out.println(n);
		System.out.println("====================");
		
		
		
		return "redirect:/chatRoom?chatNum="+chatNum;
	}
	
	
	
	
//	@RequestMapping()
	
}//end class
