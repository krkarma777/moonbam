package com.moonBam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.dto.board.PostPageDTO;

@Repository
public class ChatRoomDAO {

	@Autowired
	SqlSessionTemplate session;
	
	public int saveChatRoom(ChatRoomDTO chatroom) {
		
		System.out.println("미지 복구 테스트 0415");
		int n = 0;
		System.out.println("in dao");
		System.out.println(chatroom);
		n = session.insert("ChatMapper.saveChatRoom", chatroom);
		return n;
	}
	
	public int delegateMaster(String chatNum, String userId) {
		int n = 0;
		System.out.println("in dao");
		
		Map<String, String> map = new HashMap<>();
		map.put("chatNum", chatNum);
		map.put("userId", userId);
		
		System.out.println(map);
		
		n = session.update("ChatMapper.delegateMaster", map);
		return n;
	}

	public String checkMaster(String chatNum) {
		String master = session.selectOne("ChatMapper.checkMaster", chatNum);
		System.out.println("master : " + master);
		return master;
	}

	public List<ChatRoomDTO> getAllChatRooms() {
		List<ChatRoomDTO> chatRoomMapList = session.selectList("getAllChatRooms");
		return chatRoomMapList;
	}
	

	//leaderId와 roomtitle로 chatRoom select
	public ChatRoomDTO chatRoomNowSelect(Map<String, String> chatRoomSelect) {
		return session.selectOne("chatRoomNow", chatRoomSelect);
	}

	

	public int ChatKickUser(String user) {
		System.out.println("dao레이어에서 강퇴 유저 이름 수신 후 매퍼 전달 ==========");
		System.out.println(user);
		System.out.println("===========================================");
		System.out.println("강퇴 대상 유저 isKicked 칼럼 t로 업데이트");
		
		int n = session.update("ChatMapper.ChatKickUser", user);
		
		System.out.println("n이 1이면 정상 처리===============================");
		System.out.println(n);
		System.out.println("=============================================");
		
		return n;
	}

	public int updateCurrentNow(String chatNum) {
		int n = 0;
		n = session.update("ChatMapper.updateCurrentNow2", chatNum);
		
		return n;
	}

	public List<String> getPastMessages(String chatNum) {
		List<String> list = session.selectList("pastMessage", chatNum);
		return list;
	}

}
