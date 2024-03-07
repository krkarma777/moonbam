package com.moonBam.dao;


import com.moonBam.dto.board.MessageDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MessageDAO {
	
	@Autowired
	SqlSessionTemplate session;

	public void insert(HashMap<String, String> map) {
		session.insert("MessageMapper.insertMessage",map);
	}

	public List<MessageDTO> selectSendedMessage(String senderId) {
		return session.selectList("MessageMapper.selectSendedMessage", senderId) ;
	}

	public List<MessageDTO> selectReceivedMessage(String receiverId) {
		return session.selectList("MessageMapper.selectReceivedMessage",receiverId);
	}

	

}
