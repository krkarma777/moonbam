package com.moonBam.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.CommunityEnterOutDAO;
import com.moonBam.dto.ChatMemberDTO;
import com.moonBam.dto.ChatRoomDTO;

@Service
public class CommunityEnterOutService {
	
	///
	
	@Autowired
	CommunityEnterOutDAO comEnterOutDAO;

	public int chatMemberEnterInsert(Map<String, Object> chatMemberInsertMap) {
		// TODO Auto-generated method stub
		return comEnterOutDAO.chatMemberEnterInsert(chatMemberInsertMap);
	}

	public ChatMemberDTO chatMemberEnterSelect(Map<String, Object> chatMemberselectMap) {
		// TODO Auto-generated method stub
		return comEnterOutDAO.chatMemberEnterSelect(chatMemberselectMap);
	}

	public ChatRoomDTO chatRoomSelectById(int chatNum) {
		// TODO Auto-generated method stub
		return comEnterOutDAO.chatRoomSelectById(chatNum);
	}

	public int chatMemberDeleteBychatNumAndUserId(Map<String, Object> chatMemberDeleteMap) {
		// TODO Auto-generated method stub
		return comEnterOutDAO.chatMemberDeleteBychatNumAndUserId(chatMemberDeleteMap);
	}


}
