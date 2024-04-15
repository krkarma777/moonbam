package com.moonBam.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.ChatRoomDTO;

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
	
	public int delegateMaster(HashMap<String, String> map) {
		int n = 0;
		System.out.println("in dao");
		System.out.println(map);
		n = session.update("ChatMapper", map);
		return n;
	}

	public String checkMaster(String chatNum) {
		String master = session.selectOne("ChatMapper", chatNum);
		return master;
	}
}
