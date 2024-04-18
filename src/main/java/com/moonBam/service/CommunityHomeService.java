package com.moonBam.service;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.CommunityHomeDAO;
import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.dto.CommunityPageDTO;

@Service
public class CommunityHomeService {
	@Autowired
	SqlSessionTemplate session;
	@Autowired
	CommunityHomeDAO dao;
	
	public CommunityPageDTO chatRoomList(String searchCategory, String searchValue, String curPage) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("searchCategory", searchCategory);
		map.put("searchValue", searchValue);
		
		System.out.println("map에 저장된 값을 dao레이어로 전달================");
		System.out.println(map);
		System.out.println("========================================");
		CommunityPageDTO cpDTO = dao.chatRoomList(session, map, curPage);
		
		return cpDTO;
	}

	public CommunityPageDTO myChatList(String searchCategory, String searchValue, String curPage, String userid) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("searchCategory", searchCategory);
		map.put("searchValue", searchValue);
		
		CommunityPageDTO cpDTO = dao.myChatList(session, map, curPage, userid);
		
		return cpDTO;
	}
	
}
