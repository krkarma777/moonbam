package com.moonBam.service.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.member.AnonymousCommentDAO;
import com.moonBam.dto.AnonymousCommentDTO;

@Service
public class AnonymousCommentService {
	
	@Autowired
	AnonymousCommentDAO anonymousCommentDAO;

	public List<AnonymousCommentDTO> getAllComments(int boardNum){
		List<AnonymousCommentDTO> comments = anonymousCommentDAO.getAllComments(boardNum);
		return comments;
	}

	public void addComment(AnonymousCommentDTO dto) {
		anonymousCommentDAO.addComment(dto);
	}

	public int deleteComment(Map<String, Object> map) {
		int num = anonymousCommentDAO.deleteComment(map);
		return num;
	}

}
