package com.moonBam.controller.community.chat;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.moonBam.dto.ChatTableDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.ChatRoomService;
import com.moonBam.service.member.MemberService;
import com.moonBam.util.FileUtil;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

	@Autowired
	private ChatMessagesService chatMessagesService;

	@Autowired
	MemberService memberService;

	@Autowired
	ChatRoomService crService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	// 파일 이름 저장
	Set<String> numbers = new HashSet<>();

//// 받고 주고
//	@MessageMapping("/chat/send/{chatNum}")
//	@SendTo("/topic/messages/{chatNum}")
//	public ChatTableDTO sendMessage( @RequestParam(required = false) String chatContent,
//			@DestinationVariable("chatNum") String chatNum, Principal principal) {
//		// 기존 @payload ChatTableDTO ctDto 를 객체 생성으로 변경
//		// 넘어오는 ctDto의 값이 없음
//		ChatTableDTO ctDto = new ChatTableDTO(); 
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
//		// chatMessagesService.insert(ctDto); // 메시지 저장, db 저장 완료
//		// ///////////////////////
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
//		String userIdInSession = principal.getName();
//		MemberDTO memberDTO = memberService.findByUserId(userIdInSession);
//		String nickNameInSession = memberDTO.getNickname();
//		ctDto.setNickName(nickNameInSession);
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

	@Scheduled(fixedRate = 60000) // 1분(60,000밀리초)마다 실행
	public void saveMessagesToDatabase() { // chatData를 DB에 저장
		System.out.print("scheduled");
		if (!(numbers.isEmpty())) {
			// set에서 num가져와서 file에 읽고 insert하고 delet하고
			// 필요한거 set만
			// for를 get(num)하고 read > insert > delete
			for (String num : numbers) {
				System.out.println(" - "+ num);
				// read file
				FileUtil fu = new FileUtil();
				
				String fileString = fu.readChatContentFromFile(num);
				// ctDto
				ChatTableDTO ctDto = new ChatTableDTO(num, fileString);
				// insert db
				ctDto.setChatContent(fileString);
				chatMessagesService.insert(ctDto);
				// delete file
				fu.deleteFile(num);
			}
			numbers.clear();
		}
	}

	// 강퇴하기 기능
	public ChatTableDTO ChatKickUser( String content, String chatNum) {
		System.out.println("ChatKickUser");

		// get userId
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(content);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// string타입에서 json으로 변경하여 message값만 가져와서 string으로 저장하기 성공
		JSONObject jsonObj = (JSONObject) obj;
		String userId = (String) jsonObj.get("USERID");

		Date currentDate = new Date();

		// 출력 형식을 지정하기 위한 SimpleDateFormat 생성
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		// Date 객체를 원하는 형식의 문자열로 변환하여 출력
		String formattedDate = formatter.format(currentDate);
		System.out.println(formattedDate);

		ChatTableDTO ctDto = new ChatTableDTO();
		ctDto.setChatNum(chatNum);

		MemberDTO memberDTO = memberService.findByUserId(userId);
		String nickName = memberDTO.getNickname();
		ctDto.setNickName(nickName);

		System.out.println(jsonObj);
		String message = nickName + " 님이 퇴장되었습니다. " + formattedDate;
		jsonObj.appendField("MESSAGE", message);
		ctDto.setChatContent(jsonObj.toString());
		System.out.println(ctDto);

		// insert db
		int n = 0;

		System.out.println("ChatKickUser===================");
		System.out.println("강퇴할 유저의 이름 확인 후 서비스레이어 전달");
		System.out.println(userId);
		System.out.println("=================================");

		n = crService.ChatKickUser(userId, chatNum);

		System.out.println("1이면 정상처리됨==========");
		System.out.println(n);
		System.out.println("====================");

		// set add, save file
		FileUtil fu = new FileUtil();
		fu.saveChatContentToFile(ctDto.getChatContent(), chatNum);

		return ctDto;
	}

	public String pastMessage(String chatNum, Principal principal) {
		// read local file
		FileUtil fu = new  FileUtil();
		String localFile = fu.readChatContentFromFile(chatNum);
		// get db
		List<String> list = crService.getPastMessages(chatNum);
		
		String temp = "";
		if(list.size()>0) {
			for (String content : list) {
				temp +=content;
			}
		}
		temp += localFile;
		
		String result="";
		if(!(temp.equals(""))) {
			String str = temp.replaceAll("\\}\\n\\{","\\}---\\{");
			String[] strArr = str.split("---");
			
			for(String content : strArr) {
				// json
				JSONParser parser = new JSONParser();
				Object obj = null;
				try {
					obj = parser.parse(content);
					System.out.println(obj);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// string타입에서 json으로 변경하여 message값만 가져와서 string으로 저장하기 성공
				JSONObject jsonObj = (JSONObject) obj;
				
				String type = (String) jsonObj.get("TYPE");
				
				// add nickName
				// principal 
				String userIdInSession = principal.getName();
				MemberDTO memberDTO = memberService.findByUserId(userIdInSession);
				String nickName= memberDTO.getNickname();
				jsonObj.replace("NICKNAME", nickName);
				
				// check, inspection badword, forbiddenword
				String message = (String) jsonObj.get("MESSAGE");
				System.out.println(message);
				
				// talk 만 nickName 필요함
				if("TALK".equals(type)) {
					/// 금칙어 스캔 후 위 message와 비교 후 ** 처리하여 리턴하여 보내주기 (DB엔 쌩으로 저장됨, 보여지기만 대체하여 보냄)
					List<String> badWordsList = chatMessagesService.badWordsSelectAll();
					boolean noBadWords = false;
					for (String BadWord : badWordsList) { ////////
						// System.out.println("먼데 "+ BadWord);

						noBadWords = message.contains(BadWord); ///////// 일치하는 욕 포함된 메세지를 입력하면 여기에 true가 담김

						if (noBadWords) {
							///// true일시 작동하고 false일 때는 작동 안 하지요
							//// 욕을 ** 문자로 지환하기
							
							jsonObj.appendField("MESSAGE", message.replace(BadWord, "**"));
							System.out.println(jsonObj);
						}
					}
				}else{
					message = nickName + jsonObj.getAsString("MESSAGE") + " "+ jsonObj.getAsString("SERVERTIME"); 
					jsonObj.replace("MESSAGE", message);
				}
				result+=jsonObj + "---";
			}
			System.out.println(result);
		}
		return result; ////// 금칙어 처리된 아이가 브라우저 뿌리기용으로 보내짐.
	}
	
	@MessageMapping("/chat/test/{chatNum}")
	@SendTo("/topic/messages/{chatNum}")
	public String test(@DestinationVariable("chatNum") String chatNum, Principal principal, @Payload String body) {
		//
		JSONObject jsonObject = str2Json(body);
		String type  = (String) jsonObject.get("TYPE");
		
		// id to nickName
		MemberDTO memberDTO = memberService.findByUserId(jsonObject.getAsString("USERID"));
		String nickName= memberDTO.getNickname();
		
		String returnStr = "";
		
		// add file
		FileUtil fu = new FileUtil();
		
		switch (type) {
	 
			case "ENTER": {
				String message = " 님이 입장했습니다.";
				
				jsonObject.replace("MESSAGE", message);
				jsonObject.replace("NICKNAME", nickName);
				returnStr = jsonObject.toJSONString();
			
				fu.saveChatContentToFile(returnStr, chatNum);
			
				message = nickName + message + jsonObject.getAsString("SERVERTIME");
				jsonObject.replace("MESSAGE", message);
				
				returnStr = jsonObject.toJSONString();
				System.out.println(returnStr);
				break;
				
			}case "EXIT": {
				String message = "님이 퇴장했습니다.";
				jsonObject.replace("MESSAGE", message);
				jsonObject.replace("NICKNAME", nickName);
				returnStr = jsonObject.toJSONString();
				
				fu.saveChatContentToFile(returnStr, chatNum);
			
				message = nickName + message + jsonObject.getAsString("SERVERTIME");
				jsonObject.replace("MESSAGE", message);
				
				returnStr = jsonObject.toJSONString();
				System.out.println(returnStr);
				break;
				
			}case "KICKED": {
				String message = "님이 추방되었습니다.";
				jsonObject.replace("MESSAGE", message);
				jsonObject.replace("NICKNAME", nickName);
				returnStr = jsonObject.toJSONString();
				
				ChatKickUser(returnStr, chatNum);
				
				fu.saveChatContentToFile(returnStr, chatNum);
			
				message = nickName + message + jsonObject.getAsString("SERVERTIME");
				jsonObject.replace("MESSAGE", message);
				
				returnStr = jsonObject.toJSONString();
				messagingTemplate.convertAndSendToUser(principal.getName(),"/queue/more/"+chatNum, "추방되었습니다.");
				System.out.println(returnStr);
				break;
				
			}case "DELEGATE": {
				String message = "님이 방장이 되었습니다.";
				jsonObject.replace("MESSAGE", message);
				jsonObject.replace("NICKNAME", nickName);
				returnStr = jsonObject.toJSONString();
				
				crService.delegateMaster(principal, chatNum, jsonObject.getAsString("USERID"));
				
				fu.saveChatContentToFile(returnStr, chatNum);
			
				message = nickName + message + jsonObject.getAsString("SERVERTIME");
				jsonObject.replace("MESSAGE", message);
				
				returnStr = jsonObject.toJSONString();
				messagingTemplate.convertAndSendToUser(principal.getName(),"/queue/more/"+chatNum, "위임되었습니다.");
				System.out.println(returnStr);
				break;
			}
			case "TALK": {
				fu.saveChatContentToFile(jsonObject.toJSONString(), chatNum);
				jsonObject.appendField("NICKNAME", nickName);
				returnStr = jsonObject.toJSONString();
				System.out.println(returnStr);
			}
			numbers.add(chatNum);
		}
		
	return returnStr;
	}
	
	// 반복 함수
	public JSONObject str2Json(String str) {
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(str);
			System.out.println(obj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// string타입에서 json으로 변경하여 message값만 가져와서 string으로 저장하기 성공
		JSONObject jsonObj = (JSONObject) obj;
		return jsonObj;
	}

	// 이전 글 가져오기 안됨
	@MessageMapping("/chat/past/{chatNum}")
	@SendToUser("/queue/past")
	public String testPast(@DestinationVariable("chatNum") String chatNum, Principal principal) {
	    String pastMessages = pastMessage(chatNum, principal);
	    return pastMessages;
	}

}
	