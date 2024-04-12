package com.moonBam.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.moonBam.dto.ChatRoomDTO;

public class ChatRoomDAO {

	@Autowired
	SqlSessionTemplate session;
	
	public int saveChatRoom(ChatRoomDTO chatroom) {
		int n = 0;
		n = session.insert("ChatMapper.saveChatRoom", chatroom);
		return n;
	}
}
