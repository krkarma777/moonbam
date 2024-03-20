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

	public List<DebugBoardDTO> viewDBoardList(String orderBy) {
		List<DebugBoardDTO> list = session.selectList("com.config.MemberMapper.viewDBoardList", orderBy);	//Mapper에 기입 필요 없음
		return list;
	}

	public DebugBoardDTO viewDBoardContent(int boardNum) {
		DebugBoardDTO dto = session.selectOne("com.config.MemberMapper.viewDBoardContent", boardNum);
		return dto;
	}

	public void updateDBoardViewCount(int boardNum) {
		System.out.println(boardNum+"번 글 조회수 +1");
		session.update("com.config.MemberMapper.viewDBoardContent", boardNum);
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

	public DebugBoardDTO prevPost(int boardNum) {
		DebugBoardDTO dto = session.selectOne("com.config.MemberMapper.prevPost", boardNum);
		return dto;
	}

	public DebugBoardDTO nextPost(int boardNum) {
		DebugBoardDTO dto = session.selectOne("com.config.MemberMapper.nextPost", boardNum);
		return dto;
	}

	public List<DebugBoardDTO> searchList(HashMap<String, String> map) {
		List<DebugBoardDTO> list = session.selectList("com.config.MemberMapper.searchList", map);	
		return list;
	}
	
	
}
