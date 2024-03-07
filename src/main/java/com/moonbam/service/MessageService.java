package com.moonbam.service;


import com.moonbam.dao.MessageDAO;
import com.moonbam.dto.board.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MessageService {
	
	@Autowired
	MessageDAO messageDAO;
	
	

	public void insert(HashMap<String, String> map) {
		messageDAO.insert(map);
	}

	public List<MessageDTO> selectSendedMessage(String senderId) {
		return messageDAO.selectSendedMessage(senderId);
	}

	public List<MessageDTO> selectReceivedMessage(String receiverId) {
		return messageDAO.selectReceivedMessage(receiverId);
	}
	
	
	
	

}
