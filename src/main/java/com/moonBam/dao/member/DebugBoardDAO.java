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

	public int updateDBoardViewCount(int boardNum) {
		System.out.println(boardNum);
		int result = session.update("com.config.MemberMapper.viewDBoardContent", boardNum);
		return result;
	}

	public int insertPost(DebugBoardDTO dto) {
		int num = session.insert("com.config.MemberMapper.insertPost", dto);
		return num;
	}
	
	
}
