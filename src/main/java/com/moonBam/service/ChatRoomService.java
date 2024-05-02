package com.moonBam.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moonBam.dao.ChatRoomDAO;
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
	
	@Transactional
	public void delegateMaster(Principal principal, String chatNum, String userId) {
		
		System.out.println("ChatRoomService.delegateMaster");
		//////////////////권한위임 기존 방장이 하는지 검사
			
		String formerMaster = principal.getName();
		System.out.println(formerMaster);
		
		Boolean checkMaster = (formerMaster.equals(dao.checkMaster(chatNum)));
		if(checkMaster) {
			System.out.println("방장맞음");
			dao.delegateMaster(chatNum, userId);
		}else {
			System.out.println("방장아님");
		}
		
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
		
		System.out.println("방 인원 수 -1");
		int n2 = dao.updateCurrentNow(chatNum);
		System.out.println("n2가 1이면 정상처리됨");
		System.out.println(n2);
		
		return n;
	}

	public List<ChatRoomDTO> getAllChatRooms() {
		
		return dao.getAllChatRooms();

	}
	
	//leaderId와 roomtitle로 chatRoom select
	public ChatRoomDTO chatRoomNowSelect(Map<String, String> chatRoomSelect) {
		return dao.chatRoomNowSelect(chatRoomSelect);
	}

	public List<String> getPastMessages(String chatNum) {
		return dao.getPastMessages(chatNum);
	}

	
	
	
	
	
}//end class
