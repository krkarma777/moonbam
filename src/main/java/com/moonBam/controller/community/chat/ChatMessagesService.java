
package com.moonBam.controller.community.chat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moonBam.dto.ChatTableDTO;
import com.moonBam.dto.CommunityBadWordsDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessagesService {

	private final ChatMessagesDAO chatMessagesDAO;

	public int insert(ChatTableDTO requestDTO) {
		return chatMessagesDAO.insert(requestDTO);
	}
	
	
	///금칙어 TABLE SELECT ALL 하는 기능
	public List<String> badWordsSelectAll() {
		return chatMessagesDAO.badWordsSelectAll();
	}
	
}