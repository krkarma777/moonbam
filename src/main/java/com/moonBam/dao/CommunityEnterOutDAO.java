package com.moonBam.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.ChatMemberDTO;
import com.moonBam.dto.ChatRoomDTO;

@Repository
public class CommunityEnterOutDAO {
	

	@Autowired
	SqlSessionTemplate session;

	public int chatMemberEnterInsert(Map<String, Object> chatMemberInsertMap) {
		
		System.out.println("미지 복구 테스트 0415");
		// TODO Auto-generated method stub
		int num = session.insert("CommunityChatEnterOutMapper.chatMemberInsertMap", chatMemberInsertMap);
//		if(num >= 2) { //중복 저장시 롤백
//			session.rollback();
//		}
		return num;
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
	
	

}
