package com.moonBam.dao.member;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.AnonymousCommentDTO;

@Repository
public class AnonymousCommentDAO {

	@Autowired
	SqlSessionTemplate session;

	public List<AnonymousCommentDTO> getAllComments(int boardNum) {
		List<AnonymousCommentDTO> comments = session.selectList("com.config.MemberMapper.getAllComments", boardNum);
		return comments;
	}

	public void addComment(AnonymousCommentDTO dto) {
		session.insert("com.config.MemberMapper.addComment", dto);
		
	}

	public int deleteComment(Map<String, Object> map) {
		int num = session.delete("com.config.MemberMapper.deleteComment", map);
		return num;
		
	}
	
	
	
}
