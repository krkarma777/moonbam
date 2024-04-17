package com.moonBam.controller.community.chat;
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
    	ctDto.setChatContent(chatContent);
        chatMessagesService.insert(ctDto);  // 메시지 저장, db 저장
        return ctDto;
    }
}