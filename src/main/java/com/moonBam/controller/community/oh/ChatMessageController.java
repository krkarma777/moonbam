package com.moonBam.controller.community.oh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.moonBam.dto.ChatTableDTO;

@Controller
public class ChatMessageController {

    @Autowired
    private ChatMessagesService chatMessagesService;

    @MessageMapping("/chat/send")
    @SendTo("/topic/messages")
    public ChatTableDTO sendMessage(@Payload ChatTableDTO ctDto, @RequestParam String chatContent) {
    	System.out.println("sendMessage");
    	ctDto.setChatContent(chatContent);
        chatMessagesService.create(ctDto);  // 메시지 저장, db 저장
        return ctDto;
    }
}