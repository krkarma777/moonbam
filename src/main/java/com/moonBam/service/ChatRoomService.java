package com.moonBam.service;

import java.util.HashMap;

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

	public int delegateMaster(HashMap<String, String> map) {
		
		int n = dao.delegateMaster(map);
		
		return n;
	}

	public String checkMaster(String chatNum) {
		String master = dao.checkMaster(chatNum);
		return master;
	}
}
