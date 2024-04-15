package com.moonBam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.ChatRoomDAO;
import com.moonBam.dto.ChatRoomDTO;

@Service
public class ChatRoomService {
	////

	@Autowired
	ChatRoomDAO dao;
	
	public int saveChatRoom(ChatRoomDTO chatRoom) {
		
		int n = dao.saveChatRoom(chatRoom);
		return n;
	}
}
