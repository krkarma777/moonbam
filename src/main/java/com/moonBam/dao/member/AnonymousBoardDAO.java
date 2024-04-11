package com.moonBam.dao.member;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.AnonymousBoardDTO;

@Repository
public class AnonymousBoardDAO {

	@Autowired
	SqlSessionTemplate session;

	public List<AnonymousBoardDTO> viewDBoardList(String orderBy) {
		List<AnonymousBoardDTO> list = session.selectList("com.config.MemberMapper.viewDBoardList", orderBy);	//Mapper에 기입 필요 없음
		return list;
	}

	public AnonymousBoardDTO viewDBoardContent(int boardNum) {
		AnonymousBoardDTO dto = session.selectOne("com.config.MemberMapper.viewDBoardContent", boardNum);
		return dto;
	}

	public void updateDBoardViewCount(int boardNum) {
		session.update("com.config.MemberMapper.updateDBoardViewCount", boardNum);
	}

	public void insertPost(AnonymousBoardDTO dto) {
		session.insert("com.config.MemberMapper.insertPost", dto);
	}

	public void updateDBoard(AnonymousBoardDTO dto) {
		int num = session.insert("com.config.MemberMapper.updateDBoard", dto);
	}

	public void deleteDBoard(int boardNum) {
		session.delete("com.config.MemberMapper.deleteDBoard", boardNum);		
	}

	public void increaseDBoardRecommendNum(int boardNum) {
		session.update("com.config.MemberMapper.increaseDBoardRecommendNum", boardNum);
	}
	
	public void decreaseDBoardRecommendNum(int boardNum) {
		session.update("com.config.MemberMapper.decreaseDBoardRecommendNum", boardNum);
	}

	public AnonymousBoardDTO prevPost(int boardNum) {
		AnonymousBoardDTO dto = session.selectOne("com.config.MemberMapper.prevPost", boardNum);
		return dto;
	}

	public AnonymousBoardDTO nextPost(int boardNum) {
		AnonymousBoardDTO dto = session.selectOne("com.config.MemberMapper.nextPost", boardNum);
		return dto;
	}

	public List<AnonymousBoardDTO> searchList(HashMap<String, String> map) {
		List<AnonymousBoardDTO> list = session.selectList("com.config.MemberMapper.searchList", map);	
		return list;
	}

	
	
}
