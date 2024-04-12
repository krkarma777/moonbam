package com.moonBam.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.dto.CommunityPageDTO;

@Repository
public class CommunityHomeDAO {

	public CommunityPageDTO chatRoomList(SqlSessionTemplate session, HashMap<String, String> map, String curPage) {
		CommunityPageDTO cpDTO = new CommunityPageDTO();
		int perPage = cpDTO.getPerPage();
		int offset = (Integer.parseInt(curPage)-1)*perPage;
		
		List<ChatRoomDTO> chatRoomList = session.selectList("chatRoomList", map, new RowBounds(offset, perPage));
		
		cpDTO.setCurPage(Integer.parseInt(curPage));
		cpDTO.setList(chatRoomList);
		cpDTO.setTotalCount(totalCount(session,map));
		
		return cpDTO;
	}

	private int totalCount(SqlSessionTemplate session, HashMap<String, String> map) {
		return session.selectOne("chatRoomTotalCount", map);
	}

	
}
