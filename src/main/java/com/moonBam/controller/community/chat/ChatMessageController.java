package com.moonBam.controller.community.chat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.moonBam.dto.ChatTableDTO;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;




@Controller
public class ChatMessageController {

    @Autowired
    private ChatMessagesService chatMessagesService;
    
    
// 받고 주고
    @MessageMapping("/chat/send/{chatNum}")
   @SendTo("/topic/messages/{chatNum}")
 //   @ResponseBody
    public ChatTableDTO sendMessage(@Payload ChatTableDTO ctDto, @RequestParam String chatContent) {
    	
    	ctDto.setChatContent(chatContent);
        chatMessagesService.insert(ctDto);  // 메시지 저장, db 저장 완료 ///////////////////////
        
        
        //string을 json객체로 쪼갰음 왜냐면 chatContent안에 userid와 time 등도 같이 있어서. 내가 비교할건 message뿐이라 떼와야했음
        String sendChatContent =  ctDto.getChatContent();
        
        JSONParser parser = new JSONParser();
        Object obj = null;
		try {
			obj = parser.parse(sendChatContent);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//string타입에서 json으로 변경하여  message값만 가져와서 string으로 저장하기 성공
        JSONObject jsonObj = (JSONObject) obj;
        String message = (String) jsonObj.get("message");
        System.out.println("message      "+message);

        
        ///금칙어 스캔 후 위 message와 비교 후 ** 처리하여 리턴하여 보내주기 (DB엔 쌩으로 저장됨, 보여지기만 대체하여 보냄)
        List<String> badWordsList = chatMessagesService.badWordsSelectAll();
       
        boolean noBadWords = false;
        for (String BadWord : badWordsList) { ////////
        	System.out.println("먼데 "+ BadWord);
     
        	noBadWords = message.contains(BadWord); /////////일치하는 욕 포함된 메세지를 입력하면 여기에 true가 담김
        	
        	if(noBadWords) {
        		/////true일시 작동하고  false일 때는 작동 안 하지요
        		////욕을 ** 문자로 지환하기
        		ctDto.setChatContent(ctDto.getChatContent().replace(BadWord, "**"));
        		
        	}
        	
		}
        
        return ctDto; //////금칙어 처리된 아이가 브라우저 뿌리기용으로 보내짐.
    }
}