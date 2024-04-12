package com.moonBam.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.ChatRoomDTO;

@Repository
public class ChatRoomDAO {

	@Autowired
	SqlSessionTemplate session;
	
	public int saveChatRoom(ChatRoomDTO chatroom) {
		int n = 0;
		System.out.println("in dao");
		System.out.println(chatroom);
		n = session.insert("ChatMapper.saveChatRoom", chatroom);
		return n;
	}
}
