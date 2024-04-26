package com.moonBam.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moonBam.dto.ChatMemberDTO;
import com.moonBam.dto.ChatRoomDTO;

@Repository
public class CommunityEnterOutDAO {
	

	@Autowired
	SqlSessionTemplate session;

	public int chatMemberEnterInsert(Map<String, Object> chatMemberInsertMap) {
		// TODO Auto-generated method stub
		return session.insert("CommunityChatEnterOutMapper.chatMemberInsertMap", chatMemberInsertMap);
	}

	public ChatMemberDTO chatMemberInsertForOnlyOneSelect(Map<String, Object> chatMemberselectMap) {
		// TODO Auto-generated method stub
		return session.selectOne("CommunityChatEnterOutMapper.chatMemberInsertForOnlyOneSelect", chatMemberselectMap);
	}

	public ChatMemberDTO chatMemberEnterSelect(Map<String, Object> chatMemberselectMap) {
		// TODO Auto-generated method stub
		return session.selectOne("CommunityChatEnterOutMapper.chatMemberEnterSelect", chatMemberselectMap);
	}
	
	public ChatRoomDTO chatRoomSelectById(int chatNum) {
		// TODO Auto-generated method stub
		return session.selectOne("CommunityChatEnterOutMapper.chatRoomSelectById", chatNum);
	}

	public int chatMemberDeleteBychatNumAndUserId(Map<String, Object> chatMemberDeleteMap) {
		// TODO Auto-generated method stub
		return session.delete("CommunityChatEnterOutMapper.chatMemberDeleteBychatNumAndUserId",chatMemberDeleteMap);
	}

	
	public int chatRoomCurrntNowAdd(Map<String, Integer> chatRoomRecordNumMap) {
		// TODO Auto-generated method stub
		return session.update("CommunityChatEnterOutMapper.chatRoomCurrntNowAdd", chatRoomRecordNumMap);
	}

	public int chatMemberDeleteByChatNum(int chatNum) {
		// TODO Auto-generated method stub
		return session.delete("CommunityChatEnterOutMapper.chatMemberDeleteByChatNum",chatNum);
	}

	public int chatRoomDeleteByChatNum(int chatNum) {
		// TODO Auto-generated method stub
		return session.delete("CommunityChatEnterOutMapper.chatRoomDeleteByChatNum",chatNum);
	}
	
	

}
