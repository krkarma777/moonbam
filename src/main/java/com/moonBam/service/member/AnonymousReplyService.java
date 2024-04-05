package com.moonBam.service.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.member.AnonymousCommentDAO;
import com.moonBam.dao.member.AnonymousReplyDAO;
import com.moonBam.dto.AnonymousCommentDTO;
import com.moonBam.dto.AnonymousReplyDTO;

@Service
public class AnonymousReplyService {

	@Autowired
	AnonymousReplyDAO anonymousReplyDAO;

	public List<AnonymousReplyDTO> getAllReplys(int anonymousCommentNum){
		List<AnonymousReplyDTO> replys = anonymousReplyDAO.getAllReplys(anonymousCommentNum);
		return replys;
	}

	public void addReply(AnonymousReplyDTO dto) {
		anonymousReplyDAO.addReply(dto);
	}

	public int deleteReply(Map<String, Object> map) {
		int num = anonymousReplyDAO.deleteReply(map);
		return num;
	}

}
