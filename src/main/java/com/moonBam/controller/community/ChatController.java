package com.moonBam.controller.community;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.dto.ChatTableDTO;
import com.moonBam.service.ChatRoomService;
import com.moonBam.service.CommunityEnterOutService;
import com.moonBam.service.member.MemberLoginService;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {

	@Autowired
	ChatRoomService crService;

	@Autowired
	MemberLoginService memberLoginService;

	@Autowired
	CommunityEnterOutService comEnterOutService;

	
	 private final SimpMessagingTemplate template;
	 
	// 소모임 대문에서 개설버튼 누르면 소모임 만드는 폼으로 이동
	@RequestMapping(value = "/createChat", method = RequestMethod.GET)
	public String createChat() {
		return "community/createChat";
	}

	@RequestMapping(value = "/saveChat", method = RequestMethod.POST)
	// @ResponseBody//////////////////////////////////
	public String saveChatRoom(@Valid @ModelAttribute ChatRoomDTO chatRoom, BindingResult bindingResult,
			Principal principal) {

		String userId = principal.getName();

		///////////////////////////////// 소모임방(chatRoom) 개설 insert
		// roomTitle 설정
		String roomTitle = chatRoom.getRoomTitle();
		String addr1 = chatRoom.getAddr1();
		LocalDate thismDate = chatRoom.getmDate();// 지금 모임방의 모임날짜 가져오기
		String mmDate = thismDate.format(DateTimeFormatter.ofPattern("MM-dd"));// 모임방 이름에 사용할 모임날짜 04-30 형식으로 변경
		String[] addr_arr = addr1.split(" ");
		String loc = addr_arr[0];
		roomTitle = roomTitle + "/" + loc + "/" + mmDate;
		chatRoom.setRoomTitle(roomTitle);
		// cDate 설정
		chatRoom.setcDate(LocalDate.now());
		// leaderId 설정
		chatRoom.setLeaderId(userId);

		int n = crService.saveChatRoom(chatRoom);
		System.out.println("채팅방이" + n + "개 생성되었습니다.");

		///////////////////////////////// chatMember에 정보 insert

		// leaderId와 roomtitle로 dto select해서 가져오기
		Map<String, String> chatRoomSelect = new HashMap<>();
		chatRoomSelect.put("userId", userId);
		chatRoomSelect.put("roomTitle", roomTitle);
		ChatRoomDTO chatRoomNow = crService.chatRoomNowSelect(chatRoomSelect);
		System.out.println("chatRoomNow dto => " + chatRoomNow);

		int chatNum = chatRoomNow.getChatNum();
		int currntNow = chatRoomNow.getCurrentNow();

		///// chatmember에 insert MAP
		Map<String, Object> chatMemberInsertMap = new HashMap<>();
		chatMemberInsertMap.put("chatNum", chatNum);
		chatMemberInsertMap.put("userId", userId);

		Map<String, Integer> chatRoomUpdateMap = new HashMap<>();
		chatRoomUpdateMap.put("chatNum", chatNum);
		chatRoomUpdateMap.put("currntNow", (currntNow + 1));

		// chatroom에 currentNow를 +1 update MAP
		int n2 = comEnterOutService.chatMemberEnterInsert(chatMemberInsertMap, chatRoomUpdateMap);
		System.out.println("chatMember에" + n2 + "개가 추가되고 현재 채팅방 인원수가 " + (currntNow + 1) + "로 변경되었습니다.");

		return "redirect:/chatRoom?chatNum=" + chatNum;

	}

	@RequestMapping(value = "/delegateMaster")
	@ResponseBody
	public String delegateMaster(@RequestParam String chatNum, @RequestParam String newMaster, Principal principal) {
		HashMap<String, String> map = new HashMap<>();
		map.put("chatNum", chatNum);
		map.put("to", newMaster);
		int n = crService.delegateMaster(map);

		////////////////// 권한위임 기존 방장이 하는지 검사

		String formerMaster = principal.getName();

		Boolean checkMaster = (formerMaster == crService.checkMaster(chatNum));

		if (!checkMaster) {
			System.out.println("너 방장 아니지");
		} else {
			String mesg = (n == 1) ? "권한 위임 정상 처리" : "권한 위임 실패";
			System.out.println(mesg);
		}
		//////////////////

		return "";
	}

	// 강퇴하기 기능
	@MessageMapping(value = "/Chatmore/{chatNum}/ChatKickUser")
	//@RequestMapping(value = "/Chatmore/{chatNum}/ChatKickUser", method = RequestMethod.GET)
	@SendTo("/topic/messages/{chatNum}")
	public ChatTableDTO ChatKickUser(@RequestParam String userId, @PathVariable("chatNum") String chatNum) {
	    System.out.println("ChatKickUser");
	    // 현재 시간을 나타내는 Date 객체 생성
	    Date currentDate = new Date();

	    // 출력 형식을 지정하기 위한 SimpleDateFormat 생성
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	    // Date 객체를 원하는 형식의 문자열로 변환하여 출력
	    String formattedDate = formatter.format(currentDate);
	    System.out.println(formattedDate);

	    int n = 0;

	    System.out.println("ChatKickUser===================");
	    System.out.println("강퇴할 유저의 이름 확인 후 서비스레이어 전달");
	    System.out.println(userId);
	    System.out.println("=================================");

	    // n = crService.ChatKickUser(userId, chatNum);

	    System.out.println("1이면 정상처리됨==========");
	    System.out.println(n);
	    System.out.println("====================");

	    ChatTableDTO ctDto = new ChatTableDTO();
	    ctDto.setChatNum(chatNum);

	    String test = "{\"type\":\"KICKED\",\"message\":\"" + userId
	            + " 님이 강퇴되었습니다.\\t"+ formattedDate+ "\"}" ;
	    ctDto.setChatContent(test);
	    System.out.println("kicked");
	    template.convertAndSend("/topic/messages/" + chatNum, ctDto);
	   return ctDto;
	}


//	@RequestMapping()

}// end class
