//package com.moonBam.controller.community;
//
//import java.security.Principal;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.moonBam.controller.community.chat.ChatMessagesService;
//import com.moonBam.dto.ChatRoomDTO;
//import com.moonBam.dto.ChatTableDTO;
//import com.moonBam.dto.MemberDTO;
//import com.moonBam.service.ChatRoomService;
//import com.moonBam.service.CommunityEnterOutService;
//import com.moonBam.service.member.MemberLoginService;
//import com.moonBam.service.member.MemberService;
//import com.moonBam.util.FileUtil;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import net.minidev.json.JSONObject;
//import net.minidev.json.parser.JSONParser;
//import net.minidev.json.parser.ParseException;
//
//@Controller
//@RequiredArgsConstructor
//public class ChatController {
//
//	@Autowired
//	ChatRoomService crService;
//
//	@Autowired
//	MemberLoginService memberLoginService;
//
//	@Autowired
//	CommunityEnterOutService comEnterOutService;
//
//	
//	 private final SimpMessagingTemplate template;
//	 
//	 @Autowired
//		private ChatMessagesService chatMessagesService;
//		@Autowired
//		MemberService memberService;
//	 
//	// 소모임 대문에서 개설버튼 누르면 소모임 만드는 폼으로 이동
//	@RequestMapping(value = "/createChat", method = RequestMethod.GET)
//	public String createChat() {
//		return "community/createChat";
//	}
//
//	@RequestMapping(value = "/saveChat", method = RequestMethod.POST)
//	// @ResponseBody//////////////////////////////////
//	public String saveChatRoom(@Valid @ModelAttribute ChatRoomDTO chatRoom, BindingResult bindingResult,
//			Principal principal) {
//
//		String userId = principal.getName();
//
//		///////////////////////////////// 소모임방(chatRoom) 개설 insert
//		// roomTitle 설정
//		String roomTitle = chatRoom.getRoomTitle();
//		String addr1 = chatRoom.getAddr1();
//		LocalDate thismDate = chatRoom.getmDate();// 지금 모임방의 모임날짜 가져오기
//		String mmDate = thismDate.format(DateTimeFormatter.ofPattern("MM-dd"));// 모임방 이름에 사용할 모임날짜 04-30 형식으로 변경
//		String[] addr_arr = addr1.split(" ");
//		String loc = addr_arr[0];
//		roomTitle = roomTitle + "/" + loc + "/" + mmDate;
//		chatRoom.setRoomTitle(roomTitle);
//		// cDate 설정
//		chatRoom.setcDate(LocalDate.now());
//		// leaderId 설정
//		chatRoom.setLeaderId(userId);
//
//		int n = crService.saveChatRoom(chatRoom);
//		System.out.println("채팅방이" + n + "개 생성되었습니다.");
//
//		///////////////////////////////// chatMember에 정보 insert
//
//		// leaderId와 roomtitle로 dto select해서 가져오기
//		Map<String, String> chatRoomSelect = new HashMap<>();
//		chatRoomSelect.put("userId", userId);
//		chatRoomSelect.put("roomTitle", roomTitle);
//		ChatRoomDTO chatRoomNow = crService.chatRoomNowSelect(chatRoomSelect);
//		System.out.println("chatRoomNow dto => " + chatRoomNow);
//
//		int chatNum = chatRoomNow.getChatNum();
//		int currntNow = chatRoomNow.getCurrentNow();
//
//		///// chatmember에 insert MAP
//		Map<String, Object> chatMemberInsertMap = new HashMap<>();
//		chatMemberInsertMap.put("chatNum", chatNum);
//		chatMemberInsertMap.put("userId", userId);
//
//		Map<String, Integer> chatRoomUpdateMap = new HashMap<>();
//		chatRoomUpdateMap.put("chatNum", chatNum);
//		chatRoomUpdateMap.put("currntNow", (currntNow + 1));
//
//		// chatroom에 currentNow를 +1 update MAP
//		int n2 = comEnterOutService.chatMemberEnterInsert(chatMemberInsertMap, chatRoomUpdateMap);
//		System.out.println("chatMember에" + n2 + "개가 추가되고 현재 채팅방 인원수가 " + (currntNow + 1) + "로 변경되었습니다.");
//
//		return "redirect:/chatRoom?chatNum=" + chatNum;
//
//	}
//
//	@RequestMapping(value = "/delegateMaster")
//	@ResponseBody
//	public String delegateMaster(@RequestParam String chatNum, @RequestParam String newMaster, Principal principal) {
//		HashMap<String, String> map = new HashMap<>();
//		map.put("chatNum", chatNum);
//		map.put("to", newMaster);
//		int n = crService.delegateMaster(map);
//
//		////////////////// 권한위임 기존 방장이 하는지 검사
//
//		String formerMaster = principal.getName();
//
//		Boolean checkMaster = (formerMaster == crService.checkMaster(chatNum));
//
//		if (!checkMaster) {
//			System.out.println("너 방장 아니지");
//		} else {
//			String mesg = (n == 1) ? "권한 위임 정상 처리" : "권한 위임 실패";
//			System.out.println(mesg);
//		}
//		//////////////////
//
//		return "";
//	}
//
//	
//
//}// end class
