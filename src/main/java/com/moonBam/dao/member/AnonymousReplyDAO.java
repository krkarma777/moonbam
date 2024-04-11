package com.moonBam.dao.member;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.AnonymousReplyDTO;

@Repository
public class AnonymousReplyDAO {

	@Autowired
	SqlSessionTemplate session;

	public List<AnonymousReplyDTO> getAllReplys(int anonymousCommentNum) {
		List<AnonymousReplyDTO> replys = session.selectList("com.config.MemberMapper.getAllReplys", anonymousCommentNum);
		return replys;
	}

	public void addReply(AnonymousReplyDTO dto) {
		session.insert("com.config.MemberMapper.addReply", dto);
		
	}

	public int deleteReply(Map<String, Object> map) {
		int num = session.delete("com.config.MemberMapper.deleteReply", map);
		return num;
		
	}
}
