package com.moonBam.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moonBam.dao.CommunityEnterOutDAO;
import com.moonBam.dto.ChatMemberDTO;
import com.moonBam.dto.ChatRoomDTO;

@Service
public class CommunityEnterOutService {
	
	///
	
	@Autowired
	CommunityEnterOutDAO comEnterOutDAO;

	
	
	///채팅방 입장////chatmember insert하고 chatRoom(채팅방정보)에서 인원수 +1 update하는 서비스
	@Transactional
	public int chatMemberEnterInsert(Map<String, Object> chatMemberInsertMap, Map<String, Integer> chatRoomRecordNumMap ) {
		// TODO Auto-generated method stub
		int num1 = 0;
		int num2 = 0;
		
		//chatmember에 insert
		num1 = comEnterOutDAO.chatMemberEnterInsert(chatMemberInsertMap);
		System.out.println("chatMemberEnterInsert 정상 진행");
		
		//chatroom에 현재 인원 수 update
		num2 = comEnterOutDAO.chatRoomCurrntNowAdd(chatRoomRecordNumMap);
		System.out.println("chatRoomCurrntNowAdd 정상 진행");
		
		return (num1+num2);
	}
	

	///채팅방 입장하기 위해 한 번 더 검수하는 서비스
	public ChatMemberDTO chatMemberInsertForOnlyOneSelect(Map<String, Object> chatMemberselectMap) {
		// TODO Auto-generated method stub
		return comEnterOutDAO.chatMemberInsertForOnlyOneSelect(chatMemberselectMap);
	}
	
	
	//강퇴여부까지 판단해서 진짜 방 입장하기
	public ChatMemberDTO chatMemberEnterSelect(Map<String, Object> chatMemberselectMap) {
		// TODO Auto-generated method stub
		return comEnterOutDAO.chatMemberEnterSelect(chatMemberselectMap);
	}

	///채팅방  id 1개로 채팅방 정보 다 가져오는 서비스
	public ChatRoomDTO chatRoomSelectById(int chatNum) {
		// TODO Auto-generated method stub
		return comEnterOutDAO.chatRoomSelectById(chatNum);
	}

	
	///채팅방 나가기////chatmember delete하고 chatRoom(채팅방정보)에서 인원수 -1 update하는 서비스
	@Transactional
	public int chatMemberDeleteBychatNumAndUserId(Map<String, Object> chatMemberDeleteMap, Map<String, Integer> chatRoomRecordNumMap) {
		// TODO Auto-generated method stub
		int num1 = 0;
		int num2 = 0;
	
		num1 = comEnterOutDAO.chatMemberDeleteBychatNumAndUserId(chatMemberDeleteMap);
		num2 = comEnterOutDAO.chatRoomCurrntNowAdd(chatRoomRecordNumMap);
				
		return (num1+num2);
	}

	
	///채팅방 삭제하기 (방장만 가능)////chatmember delete하고 chatRoom(채팅방정보)도 delete함
	@Transactional
	public int chatMemberDeleteByChatNum(int chatNum) {
		int num1 = 0;
		int num2 = 0;
		
		num1 =  comEnterOutDAO.chatMemberDeleteByChatNum(chatNum);
		num2 = comEnterOutDAO.chatRoomDeleteByChatNum(chatNum);
		
		return (num1+num2);
	}



}
