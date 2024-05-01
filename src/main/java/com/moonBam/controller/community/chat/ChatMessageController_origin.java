//package com.moonBam.controller.community.chat;
//
//import java.security.Principal;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.moonBam.dto.ChatTableDTO;
//import com.moonBam.dto.MemberDTO;
//import com.moonBam.service.ChatRoomService;
//import com.moonBam.service.member.MemberService;
//import com.moonBam.util.FileUtil;
//
//import jakarta.servlet.http.HttpServletRequest;
//import net.minidev.json.JSONObject;
//import net.minidev.json.parser.JSONParser;
//import net.minidev.json.parser.ParseException;
//
//@Controller
//public class ChatMessageController {
//
//	@Autowired
//	private ChatMessagesService chatMessagesService;
//	
//	@Autowired
//	MemberService memberService;
//	
//	@Autowired
//	ChatRoomService crService;
//	
//	// 파일 이름 저장
//	Set<String> numbers = new HashSet<>();
//
//// 받고 주고
//	@MessageMapping("/chat/send/{chatNum}")
//	@SendTo("/topic/messages/{chatNum}")
//	public ChatTableDTO sendMessage(@Payload ChatTableDTO ctDto, @RequestParam(required = false) String chatContent,
//			@DestinationVariable("chatNum") String chatNum, Principal principal) {
////		System.out.println("here");
////		ChatTableDTO cto1 = (ChatTableDTO) req.getAttribute("test");
////		System.out.println(cto1);
//		
//		ctDto.setChatNum(chatNum);
//		ctDto.setChatContent(chatContent);
//		
//		// set add, save file 
//		FileUtil fu = new FileUtil();
//		numbers.add(chatNum);
//		fu.saveChatContentToFile(chatContent, chatNum);
//		
//		// insert db
//		ctDto.setChatContent(chatContent);
//		ctDto.setChatNum(chatNum);
//	//	chatMessagesService.insert(ctDto); // 메시지 저장, db 저장 완료 ///////////////////////
//
//		// string을 json객체로 쪼갰음 왜냐면 chatContent안에 userid와 time 등도 같이 있어서. 내가 비교할건
//		// message뿐이라 떼와야했음
//		String sendChatContent = ctDto.getChatContent();
//
//		JSONParser parser = new JSONParser();
//		Object obj = null;
//		try {
//			obj = parser.parse(sendChatContent);
//
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// string타입에서 json으로 변경하여 message값만 가져와서 string으로 저장하기 성공
//		JSONObject jsonObj = (JSONObject) obj;
//		String message = (String) jsonObj.get("message");
//		// System.out.println("message "+message);
//
//		// add nickname
//				String userIdInSession = principal.getName();
//				MemberDTO memberDTO = memberService.findByUserId(userIdInSession);
//				String nickNameInSession = memberDTO.getNickname();
//				ctDto.setNickName(nickNameInSession);
//		
//		/// 금칙어 스캔 후 위 message와 비교 후 ** 처리하여 리턴하여 보내주기 (DB엔 쌩으로 저장됨, 보여지기만 대체하여 보냄)
//		List<String> badWordsList = chatMessagesService.badWordsSelectAll();
//
//		boolean noBadWords = false;
//		for (String BadWord : badWordsList) { ////////
//			// System.out.println("먼데 "+ BadWord);
//
//			noBadWords = message.contains(BadWord); ///////// 일치하는 욕 포함된 메세지를 입력하면 여기에 true가 담김
//
//			if (noBadWords) {
//				///// true일시 작동하고 false일 때는 작동 안 하지요
//				//// 욕을 ** 문자로 지환하기
//				ctDto.setChatContent(ctDto.getChatContent().replace(BadWord, "**"));
//
//			}
//
//		}
//
//		return ctDto; ////// 금칙어 처리된 아이가 브라우저 뿌리기용으로 보내짐.
//	}
//	
//	
//	
//	// 문제점
//	// 1. 서버 시작 부터 계속 실행됨
//	// 2. 소켓 해제 후 계속 실행
//	@Scheduled(fixedRate = 6000) // 1분(60,000밀리초)마다 실행
//	public void saveMessagesToDatabase() { // chatData를 DB에 저장
//		if (!(numbers.isEmpty())) {
//			// set에서 num가져와서 file에 읽고 insert하고 delet하고
//			// 필요한거 set만
//			// for를 get(num)하고 read > insert > delete
//			for (String num : numbers) {
//				// path file
//				String filePath = "src/main/resources/static/com/" + num + ".txt";
//				// read file
//				FileUtil fu = new FileUtil();
//				String fileString = fu.readChatContentFromFile(filePath);
//				// ctDto
//				ChatTableDTO ctDto = new ChatTableDTO(num, fileString);
//				// insert db
//				ctDto.setChatContent(fileString);
//				chatMessagesService.insert(ctDto);
//				// delete file
//				fu.deleteFile(filePath);
//			}
//			numbers.clear();
//		}
//	}
//	
//	
//	
//	
//	// 강퇴하기 기능
//		@MessageMapping(value = "/Chatmore/ChatKickUser/{chatNum}")
//		@SendTo("/topic/messages/{chatNum}")
//		public ChatTableDTO ChatKickUser(@RequestParam String content, @DestinationVariable("chatNum") String chatNum) {
//		    System.out.println("ChatKickUser");
//
//		    // get userId
//		    JSONParser parser = new JSONParser();
//			Object obj = null;
//			try {
//				obj = parser.parse(content);
//
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			// string타입에서 json으로 변경하여 message값만 가져와서 string으로 저장하기 성공
//			JSONObject jsonObj = (JSONObject) obj;
//			String userId = (String) jsonObj.get("userId");
//		    
//			
//			
//		    
//		    Date currentDate = new Date();
//
//		    // 출력 형식을 지정하기 위한 SimpleDateFormat 생성
//		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//
//		    // Date 객체를 원하는 형식의 문자열로 변환하여 출력
//		    String formattedDate = formatter.format(currentDate);
//		    System.out.println(formattedDate);
//
//		    ChatTableDTO ctDto = new ChatTableDTO();
//		    ctDto.setChatNum(chatNum);
//		    
//			MemberDTO memberDTO = memberService.findByUserId(userId);
//			String nickName = memberDTO.getNickname();
//			ctDto.setNickName(nickName);
//			
//	System.out.println(jsonObj);	
//			jsonObj.remove("userId");
//			String message = nickName + " 님이 퇴장되었습니다. "+ formattedDate;
//			jsonObj.appendField("message",message);
//			ctDto.setChatContent(jsonObj.toString());
//	System.out.println(ctDto);
//
//	//insert db
//	int n = 0;
//
//	System.out.println("ChatKickUser===================");
//	System.out.println("강퇴할 유저의 이름 확인 후 서비스레이어 전달");
//	System.out.println(userId);
//	System.out.println("=================================");
//
//	 n = crService.ChatKickUser(userId, chatNum);
//
//	System.out.println("1이면 정상처리됨==========");
//	System.out.println(n);
//	System.out.println("====================");
//
//
//	// set add, save file 
//			FileUtil fu = new FileUtil();
//			numbers.add(chatNum);
//			fu.saveChatContentToFile(ctDto.getChatContent(), chatNum);
//
//
//		   return ctDto;
//		}
//}