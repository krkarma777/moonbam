//package com.moonBam.controller.community.chat;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import com.moonBam.dto.ChatTableDTO;
//
//@Controller
//class DatabaseSaver implements Runnable {
//	@Autowired
//	ChatMessagesService chatMessagesService;
//    private ChatTableDTO chatMessage;
//
//    public DatabaseSaver(ChatTableDTO chatMessage) {
//        this.chatMessage = chatMessage;
//    }
//
//    @Override
//    public void run() {
//        while (flag) {
//            try {
//                Thread.sleep(ONE_MINUTE); // 1분 대기
//                chatMessagesService.insert(chatMessage);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//}