
package com.moonBam.controller.community.oh;

import org.springframework.stereotype.Service;

import com.moonBam.dto.ChatTableDTO;

import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor
public class ChatMessagesService {

	private final ChatMessagesDAO chatMessagesDAO;

	public int create(ChatTableDTO requestDTO) {
		System.out.println("create");
		return chatMessagesDAO.create(requestDTO);
	}
}