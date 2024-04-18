
package com.moonBam.controller.community.chat;

import org.springframework.stereotype.Service;

import com.moonBam.dto.ChatTableDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessagesService {

	private final ChatMessagesDAO chatMessagesDAO;

	public int insert(ChatTableDTO requestDTO) {
		return chatMessagesDAO.insert(requestDTO);
	}
}