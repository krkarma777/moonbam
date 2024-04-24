package com.moonBam.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.dto.ChatTableDTO;
import com.moonBam.dto.CommunityPageDTO;

@Repository
public class CommunityHomeDAO {

	public CommunityPageDTO chatRoomList(SqlSessionTemplate session, HashMap<String, Object> map, String curPage) {
		CommunityPageDTO cpDTO = new CommunityPageDTO();
		int perPage = cpDTO.getPerPage();
		int offset = (Integer.parseInt(curPage)-1)*perPage;
		
		List<ChatRoomDTO> chatRoomList = session.selectList("chatRoomList", map, new RowBounds(offset, perPage));
		
		cpDTO.setCurPage(Integer.parseInt(curPage));
		cpDTO.setList(chatRoomList);
		cpDTO.setTotalCount(totalCount(session,map));
		
		return cpDTO;
	}

	private int totalCount(SqlSessionTemplate session, HashMap<String, Object> map) {
		return session.selectOne("chatRoomTotalCount", map);
	}
	
	public CommunityPageDTO myChatList(SqlSessionTemplate session, HashMap<String, Object> map, String curPage,
			String userid) {
		CommunityPageDTO cpDTO = new CommunityPageDTO();
		int perPage = cpDTO.getPerPage();
		int offset = (Integer.parseInt(curPage)-1)*perPage;
		
		List<String> list = session.selectList("myChatRoomList", userid);
		
		if(0==list.size()) {
			return chatRoomList(session, map, curPage);
		}else {
			//like in을 위해서 문자 배열 만들기
			String aaa = "(";
			for (int i=0; i<list.size(); i++) {
				if((i+1)==list.size()) {
					aaa += list.get(i);
				}else {
					aaa += list.get(i)+",";
				}
			}
			aaa +=")";
			
			map.put("myChatRoomList", aaa);
			
			List<ChatRoomDTO> myChatList = session.selectList("myChatList", map, new RowBounds(offset, perPage));
			
			cpDTO.setCurPage(Integer.parseInt(curPage));
			cpDTO.setList(myChatList);
			cpDTO.setTotalCount(totalCount(session,map));
			
			return cpDTO;
		}
		
	}
	
}
