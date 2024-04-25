package com.moonBam.controller.community.chat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.moonBam.dto.ChatTableDTO;

@Controller
public class ChatMessageController_thread {

	@Autowired
	private ChatMessagesService chatMessagesService;

	ChatTableDTO ctDto;
	

	File file;
// 받고 주고
	@MessageMapping("/chat/send_thead/{chatNum}")
	@SendTo("/topic/messages/{chatNum}")
	public ChatTableDTO sendMessage(@Payload ChatTableDTO ctDto, @DestinationVariable("chatNum") int chatNum,
			@RequestParam String chatContent) {
		// pathvariable @DestinationVariable 
		// 합치기
		System.out.println("-----------------------------------------------");
		this.file = saveChatContentToFile(chatContent, chatNum);
		ctDto.setChatContent(chatContent);
		ctDto.setChatNum(chatNum);
		this.ctDto = ctDto;
		System.out.println(ctDto.toString());
		System.out.println("************************************************");
		
		
		return ctDto; ////// 금칙어 처리된 아이가 브라우저 뿌리기용으로 보내짐.
	}

	// 문제점
	// 1. 서버 시작 부터 계속 실행됨
	// 2. 소켓 해제 후 계속 실행
//	@Scheduled(fixedRate = 10000) // 1분(60,000밀리초)마다 실행
	public void saveMessagesToDatabase() { // chatData를 DB에 저장
		System.out.println("scheduled");
		if (this.ctDto != null) {
		//	System.out.println("\t"+file.getName());
		//	if(file.exists()) {
			String fileString = readChatContentFromFile(file.getAbsolutePath());
			System.out.println("\tread");
			this.ctDto.setChatContent(fileString);
			System.out.println(ctDto.toString());
			chatMessagesService.insert(this.ctDto);
			System.out.println("\tinsert");
			deleteFile(file.getAbsolutePath());
			System.out.println("\tdelete");
			// 데이터를 비움
			ctDto=null;
			//}
		}
	}

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
	private File saveChatContentToFile(String chatContent, int chatNum) {
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

	private String readChatContentFromFile(String filePath) {
	    StringBuilder stringBuilder = new StringBuilder();
	    try {
	        File file = new File(filePath);
	        if (!file.exists()) {
	            System.err.println("File not found: " + filePath);
	            return null;
	        }

	        BufferedReader reader = new BufferedReader(new FileReader(file));

	        String line;
	        while ((line = reader.readLine()) != null) {
	            stringBuilder.append(line).append("\n");
	        }

	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return stringBuilder.toString();
	}
	
	private boolean deleteFile(String filePath) {
	    File file = new File(filePath);
	    if (file.exists()) {
	        return file.delete();
	    } else {
	        System.err.println("File not found: " + filePath);
	        return false;
	    }
	}
	
}
