package com.moonBam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.ChatRoomDAO;
import com.moonBam.dto.ChatMemberDTO;
import com.moonBam.dto.ChatRoomDTO;

@Service
public class ChatRoomService {
	////

	@Autowired
	ChatRoomDAO dao;
	
	
	//소모임 개설 insert
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


	public int ChatKickUser(String user, String chatNum) {
		System.out.println("서비스 레이어에서 강퇴 대상 이름 수신 후 dao 전달=========");
		System.out.println(user);
		System.out.println("===========================================");
		
		int n = dao.ChatKickUser(user);
		
		
		int n2 = dao.updateCurrentNow(chatNum);
		
		
		return n;
	}

	public List<ChatRoomDTO> getAllChatRooms() {
		
		return dao.getAllChatRooms();

	}
	
	//leaderId와 roomtitle로 chatRoom select
	public ChatRoomDTO chatRoomNowSelect(Map<String, String> chatRoomSelect) {
		return dao.chatRoomNowSelect(chatRoomSelect);
	}

	
	
	
	
	
}//end class
