package com.moonBam.controller.community.chat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.moonBam.dto.ChatTableDTO;

@Controller
public class ChatMessageController_thread {

	@Autowired
	private ChatMessagesService chatMessagesService;

	ChatTableDTO ctDto;

// 받고 주고
	@MessageMapping("/chat/send_thead/{chatNum}")
	@SendTo("/topic/messages/{chatNum}")
	public ChatTableDTO sendMessage(@Payload ChatTableDTO ctDto, @DestinationVariable("chatNum") String chatNum,
			@RequestParam String chatContent) {
		// pathvariable @DestinationVariable 
		// 합치기
		File file = saveChatContentToFile(chatContent, chatNum);
		//System.out.println(file.toString());
		// this.jsonData = mergeMessage(this.jsonData, chatContent);
		this.ctDto = ctDto;

		String aa= "{\"type\":\"TALK\",\"userId\":\"acornjayk@gmail.com\",\"message\":\"hello\",\"serverTime\":\"2024. 4. 24. 오후 3:01:31\"}";
		String bb= "{\"type\":\"enter\",\"userId\":\"acornjayk@gmail.com\",\"message\":\"hello\",\"serverTime\":\"2024. 4. 24. 오후 3:01:31\"}";
		
		String cc = mergeMessage(aa, bb);
		System.out.println(cc);
		
		
		return ctDto; ////// 금칙어 처리된 아이가 브라우저 뿌리기용으로 보내짐.
	}

//	@Scheduled(fixedRate = 5000) // 1분(60,000밀리초)마다 실행
//	public void saveMessagesToDatabase() { // chatData를 DB에 저장
//		if (this.ctDto != null) {
//			this.ctDto.setChatContent(jsonData);
//			chatMessagesService.insert(this.ctDto);
//			jsonData = "";
//			// 데이터를 비움
//
//		}
//	}

	public static String mergeMessage(String json1, String json2) {
		try {
			if (json1 == null) {
				json1 = "{}";
			}
			// 첫 번째 JSON 문자열을 JSONObject로 변환
			JSONObject obj1 = new JSONObject(json1);

			// 두 번째 JSON 문자열을 JSONObject로 변환
			JSONObject obj2 = new JSONObject(json2);

			// 두 JSONObject를 병합
			for (String key : obj2.keySet()) {
				obj1.put(key, obj2.get(key));
			}

			// 병합된 JSONObject를 문자열로 변환하여 반환
			return obj1.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	// chatContent를 파일에 저장하는 메서드
	private File saveChatContentToFile(String chatContent, String chatNum) {
	    String filePath = "src/main/resources/static/com/" + chatNum + ".txt";
	    File file = new File(filePath);
	    try {
	        if (!file.exists()) {
	            file.createNewFile(); // 파일이 존재하지 않으면 새로 생성
	            System.out.println("create file");
	        }
	        FileWriter writer = new FileWriter(file, true); // 두 번째 매개변수를 true로 하면 파일 끝에 추가한다.
	        writer.write(chatContent + "\n"); // 파일에 데이터 추가
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return file;
	}

}
