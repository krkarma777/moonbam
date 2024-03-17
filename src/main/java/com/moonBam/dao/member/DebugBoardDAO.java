package com.moonBam.dao.member;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.DebugBoardDTO;

@Repository
public class DebugBoardDAO {

	@Autowired
	SqlSessionTemplate session;

	public List<DebugBoardDTO> viewDBoardList() {
		List<DebugBoardDTO> list = session.selectList("com.config.MemberMapper.viewDBoardList");
		return list;
	}

	public DebugBoardDTO viewDBoardContent(int boardNum) {
		DebugBoardDTO dto = session.selectOne("com.config.MemberMapper.viewDBoardContent", boardNum);
		return dto;
	}

	public void updateDBoardViewCount(HashMap<String, Integer> map) {
		session.update("com.config.MemberMapper.viewDBoardContent", map);
	}

	public void insertPost(DebugBoardDTO dto) {
		session.insert("com.config.MemberMapper.insertPost", dto);
	}

	public void updateDBoard(DebugBoardDTO dto) {
		int num = session.insert("com.config.MemberMapper.updateDBoard", dto);
	}

	public void deleteDBoard(int boardNum) {
		session.delete("com.config.MemberMapper.deleteDBoard", boardNum);		
	}

	public void updateDBoardRecommendNum(HashMap<String, Integer> map) {
		session.update("com.config.MemberMapper.updateDBoardRecommendNum", map);
		
	}
	
	
}
